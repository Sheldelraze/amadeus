package com.minh.nguyen.service;

import com.minh.nguyen.constants.Constants;
import com.minh.nguyen.dto.ProblemDTO;
import com.minh.nguyen.entity.ProblemEntity;
import com.minh.nguyen.exception.CompileErrorException;
import com.minh.nguyen.form.problem.ProblemSolutionForm;
import com.minh.nguyen.mapper.ProblemMapper;
import com.minh.nguyen.util.CompileUtil;
import com.minh.nguyen.util.ExceptionUtil;
import com.minh.nguyen.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.List;

/**
 * @author Mr.Minh
 * @since 01/01/2018
 * Purpose:
 */
@Service
public class ProblemService extends BaseService<ProblemEntity> {
    @Autowired
    private FileUtil fileUtil;

    @Autowired
    private CompileUtil compileUtil;

    @Autowired
    private ProblemMapper problemMapper;

    @Autowired
    private ExceptionUtil exceptionUtil;
    private static Logger logger = LoggerFactory.getLogger(ProblemService.class);
    public void tryCompile(ProblemSolutionForm problemSolutionForm) {
        File file = fileUtil.createFile(Constants.TEST_COMPILE_LOCATION, Constants.TEST_COMPILE_FILENAME, problemSolutionForm.getLanguage());
        fileUtil.writeToFile(problemSolutionForm.getSourceCode(), file);
        try {
            compileUtil.doCompile(file,problemSolutionForm.getLanguage());
        } catch (CompileErrorException e) {
            logger.warn(e.getMessage());
        }
    }
    @Transactional
    public void createProblem(ProblemDTO problemDTO){
        ProblemEntity problemEntity = new ProblemEntity();
        problemEntity.setCode(problemDTO.getCode());
        List<ProblemEntity> getRecord = problemMapper.selectWithExample(problemEntity);
        if (getRecord.size() > 0){
            rollBack(Constants.MSG_DUPLICATE_PROBLEM_ERR);
        }
        setCreateInfo(problemEntity);
        setUpdateInfo(problemEntity);
        int insertRecord = problemMapper.insertEntity(problemEntity);
        if (insertRecord != 1){
            rollBack(Constants.MSG_SYSTEM_ERR);
        }
        problemDTO.setId(problemEntity.getId());
    }


}
