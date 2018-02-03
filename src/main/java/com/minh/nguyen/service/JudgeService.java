package com.minh.nguyen.service;

import com.google.common.util.concurrent.UncheckedTimeoutException;
import com.minh.nguyen.constants.Constants;
import com.minh.nguyen.dto.InputDTO;
import com.minh.nguyen.dto.LanguageDTO;
import com.minh.nguyen.dto.ProblemDTO;
import com.minh.nguyen.entity.SnSDlEntity;
import com.minh.nguyen.entity.SubmissionEntity;
import com.minh.nguyen.entity.SubmitDetailEntity;
import com.minh.nguyen.exception.CompileErrorException;
import com.minh.nguyen.mapper.*;
import com.minh.nguyen.util.CompileUtil;
import com.minh.nguyen.util.Runner.Outcome;
import com.minh.nguyen.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author Mr.Minh
 * @since 29/01/2018
 * Purpose:
 */
@Component("JudgeService")
public class JudgeService extends BaseService {
    private static Logger logger = LoggerFactory.getLogger(JudgeService.class);
    @Autowired
    private ProblemMapper problemMapper;

    @Autowired
    private InputMapper inputMapper;

    @Autowired
    private PmItMapper pmItMapper;

    @Autowired
    private LanguageMapper languageMapper;

    @Autowired
    private SubmitDetailMapper submitDetailMapper;

    @Autowired
    private SnSDlMapper snSDlMapper;

    @Autowired
    private SubmissionMapper submissionMapper;

    @Async
    public void judge(ProblemDTO problemDTO, LanguageDTO languageDTO) {
        logger.info("hallo from judge");
        boolean runTimeErr = false;
        boolean wrongAns = false;
        boolean timeLimitErr = false;
        int timeTotal = 0;
        int testCount = 0;
        SubmissionEntity submissionEntity = new SubmissionEntity();
        SubmitDetailEntity submitDetailEntity = new SubmitDetailEntity();
        SnSDlEntity snSDlEntity = new SnSDlEntity();
        submissionEntity.setLeId(languageDTO.getId());
        submissionEntity.setSourceCode(problemDTO.getSourceCode());
        submissionEntity.setPmId(problemDTO.getId());
        submissionEntity.setTimeRun(0);
        submissionEntity.setMemoryUsed(0);
        submissionEntity.setVerdict(Constants.VERDICT_COMPILING);
        submissionEntity.setJudgeStatus(Constants.STATUS_JUDGING);
        setUpdateInfo(submissionEntity);
        setCreateInfo(submissionEntity);
        submissionMapper.insertSubmission(submissionEntity);

        try {
            String fileName = ("submission-snId-" + submissionEntity.getId())
                    + "-" + problemDTO.getCode();
            String location = Constants.SUBMISSION_LOCATION;
            CompileUtil.doCompile(languageDTO, problemDTO, location, fileName);
        } catch (CompileErrorException | UncheckedTimeoutException e) {
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
            submissionMapper.updateByPK(submissionEntity);
            return;
        }
        for (int i = 0; i < problemDTO.getLstInput().size(); i++) {
            InputDTO inputDTO = problemDTO.getLstInput().get(i);
            submissionEntity.setVerdict(Constants.VERDICT_JUDGING + (i + 1));
            submissionMapper.updateByPK(submissionEntity);
            //set id = null de insert = BaseMapper không bị lỗi
            submitDetailEntity.setId(null);
            submitDetailEntity.setInput(StringUtil.getFirst100Chars(inputDTO.getInput()));
            submitDetailEntity.setAnswer(StringUtil.getFirst100Chars(inputDTO.getOutput()));
            try {
                Outcome outcome = CompileUtil.doRun(languageDTO, problemDTO, inputDTO
                        , submissionEntity.getId());
                //outcome.timeelapse là nano giây -> ms giây
                int timeElapsed =  (int)(outcome.getTimeElapsed() / 1000000);
                timeElapsed = Math.max(timeElapsed,15);
                submitDetailEntity.setTimeRun(timeElapsed);
                //run time err
                if (outcome.getExitCode() != 0) {
                    runTimeErr = true;
                    submitDetailEntity.setResult(Constants.VERDICT_RUNTIME_ERROR);
                }
                //run success
                else {
                    StringUtil.CompareResult compareResult = StringUtil.compareString(outcome.getOutput(), inputDTO.getOutput());
                    submitDetailEntity.setResult(compareResult.getResult());
                    submitDetailEntity.setStatus(compareResult.getStatus());


                    submitDetailEntity.setOutput(StringUtil.getFirst100Chars(outcome.getOutput()));
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
            submissionEntity.setJudgeStatus(Constants.STATUS_RUNTIME_ERROR);
            submissionEntity.setVerdict(Constants.VERDICT_RUNTIME_ERROR);
            timeTotal /= testCount == 0 ? 1 : testCount;
        } else if (timeLimitErr) {
            submissionEntity.setJudgeStatus(Constants.STATUS_TIME_LIMIT_EXCEEDED);
            submissionEntity.setVerdict(Constants.VERDICT_TIME_LIMIT_EXCEEDED);
            timeTotal = problemDTO.getTimeLimit();
        } else if (wrongAns) {
            submissionEntity.setJudgeStatus(Constants.STATUS_WRONG_ANSWER);
            submissionEntity.setVerdict(Constants.VERDICT_WRONG_ANSWER);
            timeTotal /= testCount == 0 ? 1 : testCount;
        } else {
            submissionEntity.setJudgeStatus(Constants.STATUS_ACCEPTED);
            submissionEntity.setVerdict(Constants.VERDICT_ACCEPTED);
            timeTotal /= testCount == 0 ? 1 : testCount;
        }
        submissionEntity.setTimeRun(timeTotal);
        submissionMapper.updateByPK(submissionEntity);
    }
}
