package com.minh.nguyen.service;

import com.google.common.util.concurrent.UncheckedTimeoutException;
import com.minh.nguyen.constants.Constants;
import com.minh.nguyen.dto.InputDTO;
import com.minh.nguyen.dto.LanguageDTO;
import com.minh.nguyen.dto.ProblemDTO;
import com.minh.nguyen.dto.TagDTO;
import com.minh.nguyen.entity.InputEntity;
import com.minh.nguyen.entity.LanguageEntity;
import com.minh.nguyen.entity.PmItEntity;
import com.minh.nguyen.entity.ProblemEntity;
import com.minh.nguyen.exception.CompileErrorException;
import com.minh.nguyen.form.problem.ProblemSubmitForm;
import com.minh.nguyen.form.problem.ProblemUpdateTestForm;
import com.minh.nguyen.mapper.*;
import com.minh.nguyen.util.CompileUtil;
import com.minh.nguyen.util.FileUtil;
import com.minh.nguyen.util.StringUtil;
import com.minh.nguyen.vo.problem.ProblemTestVO;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.RollbackException;
import java.io.File;
import java.lang.reflect.Type;
import java.util.List;

/**
 * @author Mr.Minh
 * @since 01/01/2018
 * Purpose:
 */
@Service("ProblemService")
public class ProblemService extends BaseService{

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

    @Autowired
    private JudgeService judgeService;
    private static Logger logger = LoggerFactory.getLogger(ProblemService.class);

    public void tryCompile(ProblemDTO problemDTO) throws CompileErrorException,UncheckedTimeoutException {
        LanguageEntity languageEntity = new LanguageEntity();
        languageEntity.setId(problemDTO.getLeId());
        languageEntity = languageMapper.selectByPK(languageEntity);
        LanguageDTO languageDTO = new LanguageDTO();
        modelMapper.map(languageEntity,languageDTO);
        try {
            String fileName = "Solution-" + problemDTO.getId() + "-" + problemDTO.getCode();
            String location = Constants.PROBLEM_LOCATION + problemDTO.getCode();
            File dir = new File(location);
            dir.mkdir();
            CompileUtil.doCompile(languageDTO,problemDTO,location + "\\",fileName);
        } catch (CompileErrorException | UncheckedTimeoutException e) {
            throw e;
        }
    }
    public void tryJudge(Integer pmId,ProblemSubmitForm problemSubmitForm){
        LanguageEntity languageEntity = new LanguageEntity();
        languageEntity.setId(problemSubmitForm.getLeId());
        languageEntity = languageMapper.selectByPK(languageEntity);
        LanguageDTO languageDTO = new LanguageDTO();
        modelMapper.map(languageEntity,languageDTO);
        ProblemDTO problemDTO = new ProblemDTO();
        problemDTO.setId(pmId);
        getProblemInfo(problemDTO);
        List<InputDTO> lstInput = inputMapper.getAllTest(pmId);
        problemDTO.setLstInput(lstInput);
        problemDTO.setSourceCode(problemSubmitForm.getSourceCode());
        judgeService.judge(problemDTO,languageDTO);

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
        String location = Constants.PROBLEM_LOCATION + problemDTO.getCode()
                + "\\";
        String filename = "input-itId-" + inputEntity.getId();
        FileUtil.createFileWithContent(location, filename, "txt",
                inputEntity.getInput());
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
            if (Constants.BLANK.equals(stringBuilder.toString())){
                problemDTO.setTag(null);
            }
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
            inputDTO.setInput(StringUtil.getFirst100Chars(inputDTO.getInput()));
            inputDTO.setOutput(StringUtil.getFirst100Chars(inputDTO.getOutput()));
        }
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
            recordCnt = problemMapper.updateByPKExceptNullFields(problemEntity);
        }
        catch(Exception e){
            e.printStackTrace();
            rollBack(Constants.MSG_SYSTEM_ERR);
        }
        if (recordCnt != 1){
            rollBack(Constants.MSG_UPDATE_ERR);
        }
    }
    public List<LanguageDTO> getAllLanguage(){
        List<LanguageEntity> lst = languageMapper.selectAll(LanguageEntity.class);
        Type listType = new TypeToken<List<LanguageDTO>>() {}.getType();
        List<LanguageDTO> lstLanguage = modelMapper.map(lst, listType);
        return lstLanguage;
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
    public void updateTest(ProblemUpdateTestForm problemUpdateTestForm) {
        InputEntity inputEntity = new InputEntity();
        modelMapper.map(problemUpdateTestForm, inputEntity);
        setUpdateInfo(inputEntity);
        try {
            inputMapper.updateByPKExceptNullFields(inputEntity);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        String location = Constants.PROBLEM_LOCATION + problemUpdateTestForm.getCode()
                + "\\";
        String filename = "input-itId-" + inputEntity.getId();
        FileUtil.createFileWithContent(location, filename, "txt",
                inputEntity.getInput());
    }

}
