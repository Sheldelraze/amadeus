package com.minh.nguyen.service;

import com.google.common.util.concurrent.UncheckedTimeoutException;
import com.minh.nguyen.constants.Constants;
import com.minh.nguyen.dto.ProblemDTO;
import com.minh.nguyen.entity.ProblemEntity;
import com.minh.nguyen.exception.CompileErrorException;
import com.minh.nguyen.form.problem.ProblemSolutionForm;
import com.minh.nguyen.mapper.ProblemMapper;
import com.minh.nguyen.util.CompileUtil;
import com.minh.nguyen.util.ExceptionUtil;
import com.minh.nguyen.util.FileUtil;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.parsing.Problem;
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
    public void tryCompile(ProblemDTO problemDTO) throws CompileErrorException,UncheckedTimeoutException {
        File file = fileUtil.createFile(Constants.TEST_COMPILE_LOCATION, Constants.TEST_COMPILE_FILENAME, problemDTO.getLanguage());
        fileUtil.writeToFile(problemDTO.getSourceCode(), file);
        try {
            compileUtil.doCompile(file,problemDTO.getLanguage());
        } catch (CompileErrorException | UncheckedTimeoutException e) {
            throw e;
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
        setCreateProblemInfo(problemEntity);
        int insertRecord = problemMapper.insertEntity(problemEntity);
        if (insertRecord != 1){
            rollBack(Constants.MSG_SYSTEM_ERR);
        }
        problemDTO.setId(problemEntity.getId());
    }

    @Transactional
    public void updateProblem(ProblemDTO problemDTO){
        ProblemEntity problemEntity = new ProblemEntity();
        problemEntity.setId(problemDTO.getId());
        try {
            problemEntity = problemMapper.selectByPK(problemEntity);
        }catch(Exception e){
            rollBack(Constants.MSG_SYSTEM_ERR);
        }
        modelMapper.map(problemDTO,problemEntity);
        setUpdateInfo(problemEntity);
        int recordCnt = 0;
        try {
            recordCnt = problemMapper.updateByPKExceptFields(problemEntity);
        }
        catch(Exception e){
            rollBack(Constants.MSG_SYSTEM_ERR);
        }
        if (recordCnt != 1){
            rollBack(Constants.MSG_UPDATE_ERR);
        }
    }
    public void setCreateProblemInfo(ProblemEntity problemEntity){
        problemEntity.setTimeLimit(2000);
        problemEntity.setMemoryLimit(64);
        problemEntity.setDifficulty(1);
        problemEntity.setIsPublished(0);
    }
    public void getProblemInfo(ProblemDTO problemDTO){
        ProblemEntity problemEntity = new ProblemEntity();
        problemEntity.setId(problemDTO.getId());
        List<ProblemEntity> lst = problemMapper.selectWithExample(problemEntity);
        if (lst.size() != 1){
            rollBack(Constants.MSG_SYSTEM_ERR);
        }
        modelMapper.map(lst.get(0),problemDTO);
    }
}
