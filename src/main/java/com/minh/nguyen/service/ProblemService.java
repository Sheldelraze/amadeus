package com.minh.nguyen.service;

import com.google.common.util.concurrent.UncheckedTimeoutException;
import com.minh.nguyen.constants.Constants;
import com.minh.nguyen.dto.InputDTO;
import com.minh.nguyen.dto.ProblemDTO;
import com.minh.nguyen.dto.TagDTO;
import com.minh.nguyen.entity.InputEntity;
import com.minh.nguyen.entity.LanguageEntity;
import com.minh.nguyen.entity.PmItEntity;
import com.minh.nguyen.entity.ProblemEntity;
import com.minh.nguyen.exception.CompileErrorException;
import com.minh.nguyen.form.problem.ProblemUpdateTestForm;
import com.minh.nguyen.mapper.InputMapper;
import com.minh.nguyen.mapper.LanguageMapper;
import com.minh.nguyen.mapper.PmItMapper;
import com.minh.nguyen.mapper.ProblemMapper;
import com.minh.nguyen.util.CompileUtil;
import com.minh.nguyen.util.ExceptionUtil;
import com.minh.nguyen.util.FileUtil;
import com.minh.nguyen.vo.problem.ProblemTestVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.RollbackException;
import java.io.File;
import java.util.List;

/**
 * @author Mr.Minh
 * @since 01/01/2018
 * Purpose:
 */
@Service("ProblemService")
public class ProblemService extends BaseService<ProblemEntity> {
    @Autowired
    private FileUtil fileUtil;

    @Autowired
    private CompileUtil compileUtil;

    @Autowired
    private ProblemMapper problemMapper;

    @Autowired
    private InputMapper inputMapper;

    @Autowired
    private PmItMapper pmItMapper;

    @Autowired
    private LanguageMapper languageMapper;
    @Autowired
    private ExceptionUtil exceptionUtil;
    private static Logger logger = LoggerFactory.getLogger(ProblemService.class);
    public void tryCompile(ProblemDTO problemDTO) throws CompileErrorException,UncheckedTimeoutException {
        LanguageEntity languageEntity = new LanguageEntity();
        languageEntity.setId(problemDTO.getLeId());
        languageEntity = languageMapper.selectByPK(languageEntity);
        try {
            compileUtil.doCompile(languageEntity,problemDTO);
        } catch (CompileErrorException | UncheckedTimeoutException e) {
            throw e;
        }
    }
    public void tryJudge(ProblemDTO problemDTO){
        try{
            tryCompile(problemDTO);
        }catch(Exception e){

        }
    }
    @Transactional
    public void createProblem(ProblemDTO problemDTO){
        ProblemEntity problemEntity = new ProblemEntity();
        problemEntity.setCode(problemDTO.getCode());
        for(int i = 0;i < problemDTO.getCode().length();i++){
            char x = problemDTO.getCode().charAt(i);
            if (x >= 'a' && x <= 'z'){
                continue;
            }
            if (x >= 'A' && x <= 'Z'){
                continue;
            }
            if (x >= '0' && x <= '9'){
                continue;
            }
            if (x != '_'){
                rollBack(Constants.MSG_TEXT_NOT_VALID);
            }
        }
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
    public void createTest(ProblemDTO problemDTO){
        InputEntity inputEntity = new InputEntity();
        PmItEntity pmItEntity = new PmItEntity();
        setUpdateInfo(inputEntity);
        setCreateInfo(inputEntity);
        setCreateInfo(pmItEntity);
        setUpdateInfo(pmItEntity);
        modelMapper.map(problemDTO,inputEntity);
        try{
            inputEntity.setId(null);
            int recordCnt = inputMapper.insertInput(inputEntity);
            if (recordCnt == 0){
                throw new RollbackException();
            }
            pmItEntity.setItId(inputEntity.getId());
            pmItEntity.setPmId(problemDTO.getId());
            recordCnt = pmItMapper.insert(pmItEntity);
            if (recordCnt == 0){
                throw new RollbackException();
            }
        }catch (RollbackException e){
            rollBack(Constants.MSG_INSERT_ERR);
        }catch(Exception e){
            e.printStackTrace();
            throw e;
        }
    }
    public List<ProblemDTO> getAllProblem(){
        List<ProblemDTO> lst = problemMapper.getAllProblem();
        for(ProblemDTO problemDTO : lst){
            StringBuilder stringBuilder = new StringBuilder();
            List<TagDTO> lstTag = problemDTO.getLstTag();
            for(int i = 0;i < lstTag.size();i++){
                stringBuilder.append(lstTag.get(i).getName());
                if (i < lstTag.size() - 1){
                    stringBuilder.append(", ");
                }
            }
            problemDTO.setTag(stringBuilder.toString());
        }
        return lst;
    }
    public void getShowInStatementTest(ProblemDTO problemDTO){
        List<InputDTO> lstInput = inputMapper.getShowInStatementTest(problemDTO.getId());
        problemDTO.setLstInput(lstInput);
    }
    public void getAllTest(ProblemTestVO problemTestVO){
        List<InputDTO> lstInput = inputMapper.getAllTest(problemTestVO.getId());
        problemTestVO.setLstInput(lstInput);
        for(InputDTO inputDTO : lstInput){
            inputDTO.setInput(trimString(inputDTO.getInput()));
            inputDTO.setOutput(trimString(inputDTO.getOutput()));
        }
    }
    public String trimString(String s){
        if (null == s){
            return null;
        }
        if (s.length() > 100){
            s = s.substring(0,100);
            s += "...";
        }
        return s;
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
            e.printStackTrace();
            rollBack(Constants.MSG_SYSTEM_ERR);
        }
        if (recordCnt != 1){
            rollBack(Constants.MSG_UPDATE_ERR);
        }
    }
    public void deleteTest(int itId){
        InputEntity inputEntity = new InputEntity();
        inputEntity.setId(itId);
        inputMapper.deleteByPK(inputEntity);
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
    public void getTestCase(ProblemUpdateTestForm problemUpdateTestForm){
        InputEntity inputEntity = new InputEntity();
        inputEntity.setId(problemUpdateTestForm.getId());
        inputEntity = inputMapper.selectByPK(inputEntity);
        modelMapper.map(inputEntity,problemUpdateTestForm);
    }
    public void updateTest(ProblemUpdateTestForm problemUpdateTestForm){
        InputEntity inputEntity = new InputEntity();
        modelMapper.map(problemUpdateTestForm,inputEntity);
        try{
            inputMapper.updateByPKExceptFields(inputEntity);
        }catch(Exception e){
            e.printStackTrace();
            throw e;
        }
    }

}
