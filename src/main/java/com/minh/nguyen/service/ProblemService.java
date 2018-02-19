package com.minh.nguyen.service;

import com.google.common.util.concurrent.UncheckedTimeoutException;
import com.minh.nguyen.constants.Constants;
import com.minh.nguyen.dto.*;
import com.minh.nguyen.entity.*;
import com.minh.nguyen.exception.CompileErrorException;
import com.minh.nguyen.exception.UserTryingToBeSmartException;
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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.RollbackException;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.lang.reflect.Type;
import java.util.List;

/**
 * @author Mr.Minh
 * @since 01/01/2018
 * Purpose:
 */
@Service("ProblemService")
public class ProblemService extends BaseService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ProblemMapper problemMapper;

    @Autowired
    private InputMapper inputMapper;

    @Autowired
    private PmItMapper pmItMapper;

    @Autowired
    private LanguageMapper languageMapper;

    @Autowired
    private UrPmAuyMapper urPmAuyMapper;

    @Autowired
    private SubmissionMapper submissionMapper;

    @Autowired
    private JudgeService judgeService;

    @Autowired
    private HttpSession httpSession;

    private static Logger logger = LoggerFactory.getLogger(ProblemService.class);

    public void tryCompile(ProblemDTO problemDTO) throws CompileErrorException, UncheckedTimeoutException {
        LanguageEntity languageEntity = new LanguageEntity();
        languageEntity.setId(problemDTO.getLeId());
        languageEntity = languageMapper.selectByPK(languageEntity);
        LanguageDTO languageDTO = new LanguageDTO();
        modelMapper.map(languageEntity, languageDTO);
        try {
            String fileName = "Solution-" + problemDTO.getId() + "-" + problemDTO.getCode();
            String location = Constants.PROBLEM_LOCATION + problemDTO.getCode();
            File dir = new File(location);
            dir.mkdir();
            CompileUtil.doCompile(languageDTO, problemDTO, location + "\\", fileName);
        } catch (CompileErrorException | UncheckedTimeoutException e) {
            throw e;
        }
    }

    @Transactional
    public void tryJudge(Integer pmId, ProblemSubmitForm problemSubmitForm) {
        //init problem and language information
        LanguageEntity languageEntity = new LanguageEntity();
        languageEntity.setId(problemSubmitForm.getLeId());
        languageEntity = languageMapper.selectByPK(languageEntity);
        LanguageDTO languageDTO = new LanguageDTO();
        modelMapper.map(languageEntity, languageDTO);
        ProblemDTO problemDTO = new ProblemDTO();
        problemDTO.setId(pmId);
        getProblemInfo(problemDTO);
        List<InputDTO> lstInput = inputMapper.getAllTest(pmId);
        problemDTO.setLstInput(lstInput);
        problemDTO.setSourceCode(problemSubmitForm.getSourceCode());

        //init and insert submission information
        SubmissionEntity submissionEntity = new SubmissionEntity();
        submissionEntity.setLeId(languageDTO.getId());
        submissionEntity.setSourceCode(problemDTO.getSourceCode());
        submissionEntity.setPmId(problemDTO.getId());
        submissionEntity.setTimeRun(0);
        submissionEntity.setMemoryUsed(0);
        submissionEntity.setVerdict(Constants.VERDICT_COMPILING);
        submissionEntity.setJudgeStatus(Constants.STATUS_JUDGING);
        Integer urId = (Integer) httpSession.getAttribute(Constants.CURRENT_LOGIN_USER_ID);
        submissionEntity.setUrId(urId);
        setUpdateInfo(submissionEntity);
        setCreateInfo(submissionEntity);
        submissionMapper.insertSubmission(submissionEntity);
        judgeService.judge(problemDTO, languageDTO, submissionEntity, urId);

    }

    @Transactional
    public void createProblem(ProblemDTO problemDTO) {

        //check if problem code existed
        ProblemEntity problemEntity = new ProblemEntity();
        problemEntity.setCode(problemDTO.getCode());
        for (int i = 0; i < problemDTO.getCode().length(); i++) {
            char x = problemDTO.getCode().charAt(i);
            if (x >= 'a' && x <= 'z') {
                continue;
            }
            if (x >= 'A' && x <= 'Z') {
                continue;
            }
            if (x >= '0' && x <= '9') {
                continue;
            }
            if (x != '_') {
                rollBack(Constants.MSG_TEXT_NOT_VALID);
            }
        }
        List<ProblemEntity> getRecord = problemMapper.selectWithExample(problemEntity);
        if (getRecord.size() > 0) {
            rollBack(Constants.MSG_DUPLICATE_PROBLEM_ERR);
        }

        //insert problem
        setCreateProblemInfo(problemEntity);
        int insertRecord = problemMapper.insertEntity(problemEntity);
        if (insertRecord != 1) {
            rollBack(Constants.MSG_SYSTEM_ERR);
        }

        //get current loggin user
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserEntity userEntity = new UserEntity();
        userEntity.setHandle(auth.getName());
        List<UserEntity> lstUser = userMapper.selectWithExample(userEntity);

        //assume that only 1 user has current handle
        if (lstUser.size() != 1) {
            rollBack(Constants.MSG_SYSTEM_ERR);
        }
        userEntity = lstUser.get(0);

        //insert view problem authority
        UrPmAuyEntity urPmAuyEntity = new UrPmAuyEntity();
        urPmAuyEntity.setPmId(problemEntity.getId());
        urPmAuyEntity.setUrId(userEntity.getId());
        urPmAuyEntity.setAuyId(Constants.AUTH_VIEW_PROBLEM_ID);
        setCreateInfo(urPmAuyEntity);
        setUpdateInfo(urPmAuyEntity);
        insertRecord = urPmAuyMapper.insert(urPmAuyEntity);

        //assume that insert success
        if (insertRecord != 1) {
            rollBack(Constants.MSG_SYSTEM_ERR);
        }

        //insert edit problem authority
        urPmAuyEntity.setAuyId(Constants.AUTH_EDIT_PROBLEM_ID);
        insertRecord = urPmAuyMapper.insert(urPmAuyEntity);
        if (insertRecord != 1) {
            rollBack(Constants.MSG_SYSTEM_ERR);
        }

        problemDTO.setId(problemEntity.getId());
    }

    @Transactional
    public void createTest(ProblemDTO problemDTO) {
        InputEntity inputEntity = new InputEntity();
        PmItEntity pmItEntity = new PmItEntity();
        setUpdateInfo(inputEntity);
        setCreateInfo(inputEntity);
        setCreateInfo(pmItEntity);
        setUpdateInfo(pmItEntity);
        modelMapper.map(problemDTO, inputEntity);
        try {
            inputEntity.setId(null);
            int recordCnt = inputMapper.insertInput(inputEntity);
            if (recordCnt == 0) {
                throw new RollbackException();
            }
            pmItEntity.setItId(inputEntity.getId());
            pmItEntity.setPmId(problemDTO.getId());
            recordCnt = pmItMapper.insert(pmItEntity);
            if (recordCnt == 0) {
                throw new RollbackException();
            }
        } catch (RollbackException e) {
            rollBack(Constants.MSG_INSERT_ERR);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        String location = Constants.PROBLEM_LOCATION + problemDTO.getCode()
                + "\\";
        String filename = "input-itId-" + inputEntity.getId();
        FileUtil.createFileWithContent(location, filename, "txt",
                inputEntity.getInput());
    }

    public List<ProblemDTO> getAllProblem() {
        List<ProblemDTO> lst = problemMapper.getAllProblem();
        for (ProblemDTO problemDTO : lst) {
            StringBuilder stringBuilder = new StringBuilder();
            List<TagDTO> lstTag = problemDTO.getLstTag();
            for (int i = 0; i < lstTag.size(); i++) {
                stringBuilder.append(lstTag.get(i).getName());
                if (i < lstTag.size() - 1) {
                    stringBuilder.append(", ");
                }
            }
            problemDTO.setTag(stringBuilder.toString());
            if (Constants.BLANK.equals(stringBuilder.toString())) {
                problemDTO.setTag(null);
            }
        }
        return lst;
    }

    public void getShowInStatementTest(ProblemDTO problemDTO) {
        List<InputDTO> lstInput = inputMapper.getShowInStatementTest(problemDTO.getId());
        problemDTO.setLstInput(lstInput);
    }

    public void getAllTest(ProblemTestVO problemTestVO) {
        List<InputDTO> lstInput = inputMapper.getAllTest(problemTestVO.getId());
        problemTestVO.setLstInput(lstInput);
        for (InputDTO inputDTO : lstInput) {
            inputDTO.setInput(StringUtil.getFirst100Chars(inputDTO.getInput()));
            inputDTO.setOutput(StringUtil.getFirst100Chars(inputDTO.getOutput()));
        }
    }

    @Transactional
    public void updateProblem(ProblemDTO problemDTO) {
        ProblemEntity problemEntity = new ProblemEntity();
        problemEntity.setId(problemDTO.getId());
        try {
            problemEntity = problemMapper.selectByPK(problemEntity);
        } catch (Exception e) {
            rollBack(Constants.MSG_SYSTEM_ERR);
        }
        modelMapper.map(problemDTO, problemEntity);
        setUpdateInfo(problemEntity);
        int recordCnt = 0;
        try {
            recordCnt = problemMapper.updateByPKExceptNullFields(problemEntity);
        } catch (Exception e) {
            e.printStackTrace();
            rollBack(Constants.MSG_SYSTEM_ERR);
        }
        if (recordCnt != 1) {
            rollBack(Constants.MSG_UPDATE_ERR);
        }
    }

    public List<LanguageDTO> getAllLanguage() {
        List<LanguageEntity> lst = languageMapper.selectAll(LanguageEntity.class);
        Type listType = new TypeToken<List<LanguageDTO>>() {
        }.getType();
        List<LanguageDTO> lstLanguage = modelMapper.map(lst, listType);
        return lstLanguage;
    }

    public void deleteTest(int itId) {
        InputEntity inputEntity = new InputEntity();
        inputEntity.setId(itId);
        inputMapper.deleteByPK(inputEntity);
    }

    public void setCreateProblemInfo(ProblemEntity problemEntity) {
        setCreateInfo(problemEntity);
        setUpdateInfo(problemEntity);
        problemEntity.setTimeLimit(2000);
        problemEntity.setMemoryLimit(64);
        problemEntity.setDifficulty(1);
        problemEntity.setIsPublished(0);
        problemEntity.setIsPublic(0);
        problemEntity.setSolveCnt(0);
        problemEntity.setTotalSubmission(0);
    }

    public void getProblemInfo(ProblemDTO problemDTO) {
        ProblemEntity problemEntity = new ProblemEntity();
        problemEntity.setId(problemDTO.getId());
        List<ProblemEntity> lst = problemMapper.selectWithExample(problemEntity);
        if (lst.size() != 1) {
            rollBack(Constants.MSG_SYSTEM_ERR);
        }
        modelMapper.map(lst.get(0), problemDTO);
    }

    public void getTestCase(ProblemUpdateTestForm problemUpdateTestForm) {
        InputEntity inputEntity = new InputEntity();
        inputEntity.setId(problemUpdateTestForm.getId());
        inputEntity = inputMapper.selectByPK(inputEntity);
        modelMapper.map(inputEntity, problemUpdateTestForm);
    }

    @Transactional
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

    public List<UserDTO> findUserForProblemRole(String fullname, Integer reId, Integer pmId) {
        List<UserDTO> lstUser = userMapper.findUserForProblemRole(fullname, reId, pmId);
        return lstUser;
    }


    /**
     * addRole
     * 1 = CAN_VIEW_PROBLEM
     * 2 = CAN_VIEW_PROBLEM + CAN_EDIT_PROBLEM
     */
    @Transactional
    public void addRole(String[] urId, Integer auyId, Integer pmId) throws UserTryingToBeSmartException {
        UrPmAuyEntity urPmAuyEntity = new UrPmAuyEntity();
        setCreateInfo(urPmAuyEntity);
        setUpdateInfo(urPmAuyEntity);
        urPmAuyEntity.setPmId(pmId);
        if (auyId != 1 && auyId != 2) {
            throw new UserTryingToBeSmartException();
        }

        //if auyId == 1 or 2
        urPmAuyEntity.setAuyId(Constants.AUTH_VIEW_PROBLEM_ID);
        for (String id : urId) {
            urPmAuyEntity.setUrId(Integer.parseInt(id));
            urPmAuyMapper.insert(urPmAuyEntity);
        }

        //else
        if (auyId == 2) {
            urPmAuyEntity.setAuyId(Constants.AUTH_EDIT_PROBLEM_ID);
            for (String id : urId) {
                urPmAuyEntity.setUrId(Integer.parseInt(id));
                urPmAuyMapper.insert(urPmAuyEntity);
            }
        }
    }

    public List<UserDTO> getListProblemRole(Integer pmId) {
        Integer urId = (Integer) httpSession.getAttribute(Constants.CURRENT_LOGIN_USER_ID);
        List<UserDTO> lstUser = userMapper.getListProblemRole(urId, pmId);

        return lstUser;
    }

    public void deleteRole(Integer pmId, Integer urId) {
        UrPmAuyEntity urPmAuyEntity = new UrPmAuyEntity();
        urPmAuyEntity.setPmId(pmId);
        urPmAuyEntity.setUrId(urId);
        urPmAuyMapper.deleteForRealWithExample(urPmAuyEntity);
    }
}
