package com.minh.nguyen.service;

import com.google.common.util.concurrent.UncheckedTimeoutException;
import com.minh.nguyen.constants.Constants;
import com.minh.nguyen.dto.InputDTO;
import com.minh.nguyen.dto.LanguageDTO;
import com.minh.nguyen.dto.ProblemDTO;
import com.minh.nguyen.entity.*;
import com.minh.nguyen.exception.CompileErrorException;
import com.minh.nguyen.mapper.*;
import com.minh.nguyen.util.CompileUtil;
import com.minh.nguyen.util.Runner.Outcome;
import com.minh.nguyen.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author Mr.Minh
 * @since 29/01/2018
 * Purpose: Class designed for asynchronous judging process,
 * you may configure more option in class SecurityConfig
 */
@Service
public class JudgeService extends BaseService {
    private static Logger logger = LoggerFactory.getLogger(JudgeService.class);

    @Autowired
    private SimpMessageSendingOperations simpMessagingTemplate;

    @Autowired
    private ProblemMapper problemMapper;

    @Autowired
    private SubmitDetailMapper submitDetailMapper;

    @Autowired
    private SnSDlMapper snSDlMapper;

    @Autowired
    private SubmissionMapper submissionMapper;

    @Autowired
    private CtPmMapper ctPmMapper;

    @Autowired
    private CePmMapper cePmMapper;

    @Async
    public void judge(ProblemDTO problemDTO, LanguageDTO languageDTO, SubmissionEntity submissionEntity, Integer urId, Integer ctId, Integer ceId) {
        logger.debug("Doing me a heavy discomfort....");
        boolean runTimeErr = false;
        boolean wrongAns = false;
        boolean timeLimitErr = false;
        int timeTotal = 0;
        int testCount = 0;
        SnSDlEntity snSDlEntity = new SnSDlEntity();
        SubmitDetailEntity submitDetailEntity = new SubmitDetailEntity();
        try {
            String fileName = StringUtil.buildString("submission-snId-",submissionEntity.getId().toString(),"-" ,problemDTO.getCode());
            String location = Constants.SUBMISSION_LOCATION_PREFIX;
            CompileUtil.doCompile(languageDTO, problemDTO, location, fileName);
        } catch (CompileErrorException | UncheckedTimeoutException e) {
            e.printStackTrace();
            // compile err
            submissionEntity.setJudgeStatus(Constants.STATUS_COMPILE_ERROR);
            submissionEntity.setVerdict(Constants.VERDICT_COMPILE_ERROR);
            submitDetailEntity.setResult(e.getMessage());
            setUpdateInfo(submitDetailEntity);
            setCreateInfo(submitDetailEntity);
            submitDetailMapper.insertSubmitDetail(submitDetailEntity);
            snSDlEntity.setsDlId(submitDetailEntity.getId());
            snSDlEntity.setSnId(submissionEntity.getId());
            setUpdateInfo(snSDlEntity);
            setCreateInfo(snSDlEntity);
            snSDlMapper.insert(snSDlEntity);
            submissionMapper.updateNotNullByPK(submissionEntity);

            //send message
            sendMessage(submissionEntity, ctId, ceId);
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < problemDTO.getLstInput().size(); i++) {
            InputDTO inputDTO = problemDTO.getLstInput().get(i);
            submissionEntity.setVerdict(Constants.VERDICT_JUDGING + (i + 1));

            //send message contains judging status to view
            sendMessage(submissionEntity, ctId, ceId);

            //set id = null de insert = BaseMapper không bị lỗi
            submitDetailEntity.setId(null);
            submitDetailEntity.setInput(StringUtil.getFirstPartOfString(inputDTO.getInput(), 100));
            submitDetailEntity.setAnswer(StringUtil.getFirstPartOfString(inputDTO.getOutput(), 100));
            try {
                Outcome outcome = CompileUtil.doRun(languageDTO, problemDTO, inputDTO
                        , submissionEntity.getId());

                //outcome.timeelapse là nano giây -> ms giây
                int timeElapsed = (int) (outcome.getTimeElapsed() == null ? 0 : outcome.getTimeElapsed() / 1000000);
                timeElapsed = Math.max(timeElapsed, 15);
                submitDetailEntity.setTimeRun(timeElapsed);

                //run time err
                if (outcome.getExitCode() != 0) {
                    runTimeErr = true;
                    submitDetailEntity.setResult(Constants.VERDICT_RUNTIME_ERROR);
                }

                //successful run
                else {
                    StringUtil.CompareResult compareResult = StringUtil.compareString(outcome.getOutput(), inputDTO.getOutput());
                    submitDetailEntity.setResult(compareResult.getResult());
                    submitDetailEntity.setStatus(compareResult.getStatus());
                    submitDetailEntity.setOutput(StringUtil.getFirstPartOfString(outcome.getOutput(), 100));
                    if (compareResult.getStatus() == Constants.STATUS_WRONG_ANSWER) {
                        wrongAns = true;
                    }
                    testCount++;
                    timeTotal += timeElapsed;
                }

            } catch (UncheckedTimeoutException e) {

                //time limit err
                timeLimitErr = true;
                submitDetailEntity.setTimeRun(problemDTO.getTimeLimit());
                submitDetailEntity.setResult(Constants.VERDICT_TIME_LIMIT_EXCEEDED);
            } finally {
                setUpdateInfo(submitDetailEntity);
                setCreateInfo(submitDetailEntity);
                submitDetailMapper.insertSubmitDetail(submitDetailEntity);
                snSDlEntity.setsDlId(submitDetailEntity.getId());
                snSDlEntity.setSnId(submissionEntity.getId());
                setUpdateInfo(snSDlEntity);
                setCreateInfo(snSDlEntity);
                snSDlMapper.insert(snSDlEntity);
            }
        }
        if (runTimeErr) {
            //RTE
            submissionEntity.setJudgeStatus(Constants.STATUS_RUNTIME_ERROR);
            submissionEntity.setVerdict(Constants.VERDICT_RUNTIME_ERROR);
            timeTotal /= testCount == 0 ? 1 : testCount;
        } else if (timeLimitErr) {
            //TLE
            submissionEntity.setJudgeStatus(Constants.STATUS_TIME_LIMIT_EXCEEDED);
            submissionEntity.setVerdict(Constants.VERDICT_TIME_LIMIT_EXCEEDED);
            timeTotal = problemDTO.getTimeLimit();
        } else if (wrongAns) {
            //WA
            submissionEntity.setJudgeStatus(Constants.STATUS_WRONG_ANSWER);
            submissionEntity.setVerdict(Constants.VERDICT_WRONG_ANSWER);
            timeTotal /= testCount == 0 ? 1 : testCount;
        } else {
            //AC
            submissionEntity.setJudgeStatus(Constants.STATUS_ACCEPTED);
            submissionEntity.setVerdict(Constants.VERDICT_ACCEPTED);
            timeTotal /= testCount == 0 ? 1 : testCount;
        }
        submissionEntity.setTimeRun(timeTotal);
        submissionMapper.updateNotNullByPK(submissionEntity);

        //send message to scoreboard as update
        sendMessage(submissionEntity, ctId, ceId);

        //if submission is AC then we perform more magic
        if (!runTimeErr && ! timeLimitErr && !wrongAns){
            updateProblemInformation(ctId,ceId,submissionEntity,problemDTO,urId);
        }
    }

    private void updateProblemInformation(Integer ctId,Integer ceId,SubmissionEntity submissionEntity,ProblemDTO problemDTO,Integer urId){

        //increase solve count if this user has not solved this problem before
        ProblemEntity problemEntity = new ProblemEntity();
        problemEntity.setId(problemDTO.getId());
        problemEntity = problemMapper.selectByPK(problemEntity);
        setUpdateInfo(problemEntity);

        //check if user solve this problem before, if not then update solve count
        Integer cntSolvedBefore = problemMapper.checkIfSolvedBefore(problemDTO.getId(), urId);
        if (cntSolvedBefore == 0) {
            problemEntity.setSolveCnt(1 + problemEntity.getSolveCnt());
        }

        //check if first solve
        if (problemEntity.getFirstSolveTime() == null) {
            problemEntity.setFirstSolveTime(submissionEntity.getCreateTime());
        }

        //increase total submission
        problemEntity.setTotalSubmission(1 + problemEntity.getTotalSubmission());
        problemMapper.updateNotNullByPK(problemEntity);

        //if problem in contest then update contest information
        if (ctId != null){
            CtPmEntity ctPmEntity = new CtPmEntity();
            ctPmEntity.setCtId(ctId);
            ctPmEntity.setPmId(problemDTO.getId());
            ctPmEntity = ctPmMapper.selectByPK(ctPmEntity);
            setUpdateInfo(ctPmEntity);

            //check if user solve this problem before, if not then update solve count
            Integer checkIfSolved = problemMapper.checkIfSolvedBeforeInContest(problemDTO.getId(),ctId, urId);

            //we just update submission status above so if this is really the first time user solve then solve count should be exactly 1
            if (checkIfSolved == 1) {
                ctPmEntity.setSolveCnt(1 + ctPmEntity.getSolveCnt());
            }

            //check if first solve
            if (ctPmEntity.getFirstSolve() == null) {
                ctPmEntity.setFirstSolve(submissionEntity.getCreateTime());
            }

            //increase total submission
            ctPmEntity.setTotalSubmission(1 + ctPmEntity.getTotalSubmission());
            ctPmMapper.updateNotNullByPK(ctPmEntity);
        }

        //if problem in course then update course
        if (ceId != null){
            CePmEntity cePmEntity = new CePmEntity();
            cePmEntity.setCeId(ceId);
            cePmEntity.setPmId(problemDTO.getId());
            cePmEntity = cePmMapper.selectByPK(cePmEntity);
            setUpdateInfo(cePmEntity);

            //check if user solve this problem before, if not then update solve count
            Integer checkIfSolved = problemMapper.checkIfSolvedBeforeInCourse(problemDTO.getId(),ceId, urId);
            if (checkIfSolved == 1) {
                cePmEntity.setSolveCnt(1 + cePmEntity.getSolveCnt());
            }

            //check if first solve
            if (cePmEntity.getFirstSolve() == null) {
                cePmEntity.setFirstSolve(submissionEntity.getCreateTime());
            }

            //increase total submission
            cePmEntity.setTotalSubmission(1 + cePmEntity.getTotalSubmission());
            cePmMapper.updateNotNullByPK(cePmEntity);
        }
    }
    private void sendMessage(SubmissionEntity submissionEntity, Integer ctId, Integer ceId) {
        simpMessagingTemplate.convertAndSend(Constants.WEB_SOCKET_PREFIX + Constants.STATUS_TOPIC, submissionEntity);
        if (ctId != null) {
            simpMessagingTemplate.convertAndSend(Constants.WEB_SOCKET_PREFIX + Constants.CONTEST_TOPIC + ctId, submissionEntity);
        } else if (ceId != null) {
            simpMessagingTemplate.convertAndSend(Constants.WEB_SOCKET_PREFIX + Constants.COURSE_TOPIC + ceId, submissionEntity);
        }
    }
}
