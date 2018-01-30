package com.minh.nguyen.service;

import com.google.common.util.concurrent.UncheckedTimeoutException;
import com.minh.nguyen.constants.Constants;
import com.minh.nguyen.dto.InputDTO;
import com.minh.nguyen.dto.LanguageDTO;
import com.minh.nguyen.dto.ProblemDTO;
import com.minh.nguyen.entity.ProblemEntity;
import com.minh.nguyen.entity.SnSDlEntity;
import com.minh.nguyen.entity.SubmissionEntity;
import com.minh.nguyen.entity.SubmitDetailEntity;
import com.minh.nguyen.exception.CompileErrorException;
import com.minh.nguyen.mapper.*;
import com.minh.nguyen.util.CompileUtil;
import com.minh.nguyen.util.Runner.Outcome;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.parsing.Problem;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    public void judge(ProblemDTO problemDTO, LanguageDTO languageDTO){
        logger.info("hallo from judge");
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
            CompileUtil.doCompile(languageDTO, problemDTO,location,fileName);
        }catch (CompileErrorException | UncheckedTimeoutException e) {
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
        for(int i = 0;i < problemDTO.getLstInput().size();i++){
            InputDTO inputDTO = problemDTO.getLstInput().get(i);
            submissionEntity.setVerdict(Constants.VERDICT_JUDGING + (i +1));
            try{
                Outcome outcome = CompileUtil.doRun(languageDTO,problemDTO,inputDTO
                                ,submissionEntity.getId());
                //run time err
                if (outcome.getExitCode() == -1){

                }
                //run success
            }catch(UncheckedTimeoutException e){
                //time limit err
            }

        }
    }
}
