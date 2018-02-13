package com.minh.nguyen.service;

import com.minh.nguyen.constants.Constants;
import com.minh.nguyen.dto.*;
import com.minh.nguyen.entity.*;
import com.minh.nguyen.form.contest.ContestSettingForm;
import com.minh.nguyen.mapper.*;
import com.minh.nguyen.vo.contest.ContestInformationVO;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author Mr.Minh
 * @since 07/01/2018
 * Purpose:
 */
@Service
public class ContestService extends BaseService {
    @Autowired
    private ContestMapper contestMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ProblemMapper problemMapper;

    @Autowired
    private UrCtAuyMapper urCtAuyMapper;

    @Autowired
    private CtPmMapper ctPmMapper;

    @Autowired
    private ProblemService problemService;

    @Autowired
    private JudgeService judgeService;

    @Autowired
    private InputMapper inputMapper;

    @Autowired
    private LanguageMapper languageMapper;

    @Autowired
    private SubmissionMapper submissionMapper;

    @Autowired
    private CtSnMapper ctSnMapper;

    @Autowired
    private HttpSession httpSession;
    public int createContest(ContestDTO contestDTO) {
        ContestEntity contestEntity = new ContestEntity();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(contestDTO.getDate());
        stringBuilder.append(" ");
        stringBuilder.append(contestDTO.getTime());
        contestDTO.setStartTime(stringBuilder.toString());
        modelMapper.map(contestDTO, contestEntity);
        try {
            //set initial contest info and insert
            SetCreateContestInfor(contestEntity);
            int insertRecord = contestMapper.insertContest(contestEntity);
            if (insertRecord == 0){
                rollBack(Constants.MSG_INSERT_ERR);
            }

            //get current loggin user
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            UserEntity userEntity = new UserEntity();
            userEntity.setHandle(auth.getName());
            List<UserEntity> lstUser = userMapper.selectWithExample(userEntity);

            //assume that only 1 user has current handle
            if(lstUser.size() != 1){
                rollBack(Constants.MSG_SYSTEM_ERR);
            }
            userEntity = lstUser.get(0);

            //insert view contest authority
            UrCtAuyEntity urCtAuyEntity = new UrCtAuyEntity();
            urCtAuyEntity.setUrId(userEntity.getId());
            urCtAuyEntity.setCtId(contestEntity.getId());
            urCtAuyEntity.setAuyId(Constants.AUTH_VIEW_CONTEST);
            setCreateInfo(urCtAuyEntity);
            setUpdateInfo(urCtAuyEntity);
            insertRecord = urCtAuyMapper.insert(urCtAuyEntity);

            //assume that insert success
            if (insertRecord != 1){
                rollBack(Constants.MSG_INSERT_ERR);
            }

            //insert edit contest authority
            urCtAuyEntity.setAuyId(Constants.AUTH_EDIT_CONTEST);
            insertRecord = urCtAuyMapper.insert(urCtAuyEntity);
            if (insertRecord != 1){
                rollBack(Constants.MSG_SYSTEM_ERR);
            }

            return contestEntity.getId();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    private void SetCreateContestInfor(ContestEntity contestEntity) {
        setCreateInfo(contestEntity);
        setUpdateInfo(contestEntity);
        contestEntity.setIsPublic(1);
        contestEntity.setIsPublished(0);
        contestEntity.setShowStatus(1);
        contestEntity.setCanPractice(1);
        contestEntity.setJudgeType(2);
        contestEntity.setShowTest(3);
        contestEntity.setShowSubmit(3);
        contestEntity.setShowToAll(1);
    }

    public ContestDTO getContestInfo(int ctId) {
        ContestDTO contestDTO = new ContestDTO();
        ContestEntity contestEntity = new ContestEntity();
        contestEntity.setId(ctId);
        contestEntity = contestMapper.selectByPK(contestEntity);
        modelMapper.map(contestEntity, contestDTO);
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        DateFormat timeFormat = new SimpleDateFormat("HH:mm");
        contestDTO.setDate(dateFormat.format(contestEntity.getStartTime()));
        contestDTO.setTime(timeFormat.format(contestEntity.getStartTime()));
        return contestDTO;
    }
    //create timer information
    public ContestDTO getContestTime(int ctId){
        ContestDTO contestDTO = new ContestDTO();
        DateFormat dateFormat = new SimpleDateFormat("MMM d yyyy HH:mm:ss");
        ContestEntity contestEntity = new ContestEntity();
        contestEntity.setId(ctId);
        contestEntity = contestMapper.selectByPK(contestEntity);
        Date now = new Date();

        //get contest's start time and end time
        Date startTime = contestEntity.getStartTime();
        Date endTime = DateUtils.addMinutes(startTime,contestEntity.getDuration());

        contestDTO.setDoUpdateCountDown(0);
        contestDTO.setStartTime(dateFormat.format(startTime));
        contestDTO.setEndTime(dateFormat.format(endTime));
        contestDTO.setName(contestEntity.getName());

        //get current time and compare to startTime and endTime
        if(now.compareTo(startTime) < 0) {
            contestDTO.setTimerMessage("Kỳ thi chưa bắt đầu");
        }else if (now.compareTo(startTime) >= 0
                    && now.compareTo(endTime) <= 0){
            contestDTO.setTimerMessage("Kỳ thi đang diễn ra");
            contestDTO.setDoUpdateCountDown(1);
        }else{
            contestDTO.setTimerMessage("Kỳ thi đã kết thúc");
        }

        return contestDTO;
    }
    public List<ProblemDTO> getProblemToAdd(int ctId){
        List<ProblemDTO> lst = problemMapper.getProblemForContest(ctId);
        for(ProblemDTO problemDTO : lst){
            StringBuilder stringBuilder = new StringBuilder();
            List<TagDTO> lstTag = problemDTO.getLstTag();
            for(int i = 0;i < lstTag.size();++i){
                stringBuilder.append(lstTag.get(i).getName());
                if (i < lstTag.size() - 1){
                    stringBuilder.append(",");
                }
            }
            problemDTO.setTag(stringBuilder.toString());
            if(Constants.BLANK.equals(stringBuilder.toString())){
                problemDTO.setTag(null);
            }
        }
        return lst;
    }
    public void setProblemHiddenStatus(Integer ctId,Integer pmId,Integer status){
        CtPmEntity ctPmEntity = new CtPmEntity();
        ctPmEntity.setCtId(ctId);
        ctPmEntity.setPmId(pmId);
        ctPmEntity.setIsHidden(status);
        setUpdateInfo(ctPmEntity);
        ctPmMapper.updateByPKExceptNullFields(ctPmEntity);
    }
    public void addProblemToContest(Integer ctId, String[] lstPmId) throws Exception{
        try {
            for (String pmId : lstPmId) {
                CtPmEntity ctPmEntity = new CtPmEntity();
                ctPmEntity.setCtId(ctId);
                ctPmEntity.setPmId(Integer.parseInt(pmId));
                ctPmEntity.setIsHidden(0);
                setUpdateInfo(ctPmEntity);
                setCreateInfo(ctPmEntity);
                ctPmMapper.insert(ctPmEntity);
            }
        }
        catch(Exception e){
            throw e;
        }
    }

    public List<ProblemDTO> getProblemToDisplay(Integer ctId){
        List<ProblemDTO> lst = problemMapper.getProblemToDisplay(ctId);
        int cnt = 0;
        for(ProblemDTO problemDTO : lst){
            if (problemDTO.getIsHidden() == 0){
                problemDTO.setAlias(++cnt);
            }
            else{
                problemDTO.setAlias(-1);
            }
        }
        return lst;
    }
    public List<ProblemDTO> getProblemToSubmit(Integer ctId){
        List<ProblemDTO> lst = problemMapper.getProblemToSubmit(ctId);
        int cnt = 0;
        for(ProblemDTO problemDTO : lst){
            String name = ++cnt + ". " + problemDTO.getName();
            problemDTO.setName(name);
        }
        return lst;
    }
    public ContestInformationVO getInformation(int ctId){
        ContestInformationVO contestInformationVO = new ContestInformationVO();
        ContestEntity contestEntity = new ContestEntity();
        contestEntity.setId(ctId);
        contestEntity = contestMapper.selectByPK(contestEntity);
        modelMapper.map(contestEntity,contestInformationVO);
        Date startTime = contestEntity.getStartTime();
        SimpleDateFormat sdfr = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        contestInformationVO.setStart(sdfr.format(startTime));
        Date endTime = DateUtils.addMinutes(startTime,contestEntity.getDuration());
        contestInformationVO.setEnd(sdfr.format(endTime));
        return contestInformationVO;
    }
    public void updateContest(ContestSettingForm contestSettingForm) throws Exception{
        try {
            ContestEntity contestEntity = new ContestEntity();
            String startTime = contestSettingForm.getDate() + " " + contestSettingForm.getTime();
            DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm");
            modelMapper.map(contestSettingForm, contestEntity);
            contestEntity.setStartTime(dateFormat.parse(startTime));
            setUpdateInfo(contestEntity);
            contestMapper.updateByPKExceptNullFields(contestEntity);
        } catch (Exception e) {
            throw e;
        }
    }
    public List<SubmissionDTO> getSubmissionInContest(Integer ctId, boolean getAll){
        List<SubmissionDTO> lst = null;
        if (getAll){
            lst = submissionMapper.getSubmissionInContest(ctId,null);
        }else{
            String handle = (String)httpSession.getAttribute(Constants.CURRENT_LOGIN_USER_HANDLE);
            lst =  submissionMapper.getSubmissionInContest(ctId,handle);
        }
        for(SubmissionDTO submissionDTO : lst){
            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
            String strDate = dateFormat.format(submissionDTO.getCreateTime());
            submissionDTO.setSubmitTime(strDate);
        }
        return lst;
    }
    //execute submission in contest
    public void doSubmit(String sourceCode,Integer ctId, Integer leId,Integer pmId){
        LanguageEntity languageEntity = new LanguageEntity();
        languageEntity.setId(leId);
        languageEntity = languageMapper.selectByPK(languageEntity);
        LanguageDTO languageDTO = new LanguageDTO();
        modelMapper.map(languageEntity,languageDTO);
        ProblemDTO problemDTO = new ProblemDTO();
        problemDTO.setId(pmId);
        problemService.getProblemInfo(problemDTO);
        List<InputDTO> lstInput = inputMapper.getAllTest(pmId);
        problemDTO.setLstInput(lstInput);
        problemDTO.setSourceCode(sourceCode);
        Integer urId = (Integer)httpSession.getAttribute(Constants.CURRENT_LOGIN_USER_ID);
        if (urId == null){
            rollBack(Constants.MSG_SESSION_TIMEOUT);
        }
        SubmissionEntity submissionEntity = new SubmissionEntity();
        submissionEntity.setLeId(languageDTO.getId());
        submissionEntity.setSourceCode(problemDTO.getSourceCode());
        submissionEntity.setPmId(problemDTO.getId());
        submissionEntity.setTimeRun(0);
        submissionEntity.setMemoryUsed(0);
        submissionEntity.setVerdict(Constants.VERDICT_COMPILING);
        submissionEntity.setJudgeStatus(Constants.STATUS_JUDGING);
        submissionEntity.setUrId(urId);
        setUpdateInfo(submissionEntity);
        setCreateInfo(submissionEntity);
        submissionMapper.insertSubmission(submissionEntity);

        CtSnEntity ctSnEntity = new CtSnEntity();
        ctSnEntity.setCtId(ctId);
        ctSnEntity.setSnId(submissionEntity.getId());
        setCreateInfo(ctSnEntity);
        setUpdateInfo(ctSnEntity);
        ctSnMapper.insert(ctSnEntity);

        judgeService.judge(problemDTO,languageDTO,submissionEntity,urId);
    }
}
