package com.minh.nguyen.service;

import com.minh.nguyen.constants.Constants;
import com.minh.nguyen.dto.*;
import com.minh.nguyen.entity.*;
import com.minh.nguyen.exception.UserTryingToBeSmartException;
import com.minh.nguyen.form.contest.ContestSettingForm;
import com.minh.nguyen.mapper.*;
import com.minh.nguyen.vo.contest.ContestInformationVO;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
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
            if (insertRecord == 0) {
                rollBack(Constants.MSG_INSERT_ERR);
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

            //insert view contest authority
            UrCtAuyEntity urCtAuyEntity = new UrCtAuyEntity();
            urCtAuyEntity.setUrId(userEntity.getId());
            urCtAuyEntity.setCtId(contestEntity.getId());
            urCtAuyEntity.setAuyId(Constants.AUTH_VIEW_CONTEST);
            setCreateInfo(urCtAuyEntity);
            setUpdateInfo(urCtAuyEntity);
            insertRecord = urCtAuyMapper.insert(urCtAuyEntity);

            //assume that insert success
            if (insertRecord != 1) {
                rollBack(Constants.MSG_INSERT_ERR);
            }

            //insert edit contest authority
            urCtAuyEntity.setAuyId(Constants.AUTH_EDIT_CONTEST);
            insertRecord = urCtAuyMapper.insert(urCtAuyEntity);
            if (insertRecord != 1) {
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
    public ContestDTO getContestTime(int ctId) {
        ContestDTO contestDTO = new ContestDTO();
        DateFormat dateFormat = new SimpleDateFormat("MMM d yyyy HH:mm:ss");
        ContestEntity contestEntity = new ContestEntity();
        contestEntity.setId(ctId);
        contestEntity = contestMapper.selectByPK(contestEntity);
        Date now = new Date();

        //get contest's start time and end time
        Date startTime = contestEntity.getStartTime();
        Date endTime = DateUtils.addMinutes(startTime, contestEntity.getDuration());

        contestDTO.setDoUpdateCountDown(0);
        contestDTO.setStartTime(dateFormat.format(startTime));
        contestDTO.setEndTime(dateFormat.format(endTime));
        contestDTO.setName(contestEntity.getName());

        //get current time and compare to startTime and endTime
        if (now.compareTo(startTime) < 0) {
            contestDTO.setTimerMessage("Kỳ thi chưa bắt đầu");
        } else if (now.compareTo(startTime) >= 0
                && now.compareTo(endTime) <= 0) {
            contestDTO.setTimerMessage("Kỳ thi đang diễn ra");
            contestDTO.setDoUpdateCountDown(1);
        } else {
            contestDTO.setTimerMessage("Kỳ thi đã kết thúc");
        }

        return contestDTO;
    }
    public List<ProblemDTO> getProblemForLeaderboard(int ctId){
        List<ProblemDTO> lstProblem = problemMapper.getProblemForLeaderboard(ctId);
        for(ProblemDTO problem : lstProblem){
            int ac = problem.getSolveCnt();
            int all = problem.getTotalSubmission();
            if (all == 0){
                problem.setSolvePercentage("(0%)");
                continue;
            }
            NumberFormat df = new DecimalFormat("#.00");
            double percentage = 100.0 * ac / all;
            problem.setSolvePercentage("(" + df.format(percentage) + "%)");
        }
        return lstProblem;
    }
    public List<ProblemDTO> getProblemToAdd(int ctId) {
        List<ProblemDTO> lst = problemMapper.getProblemForContest(Constants.AUTH_VIEW_PROBLEM,ctId);
        for (ProblemDTO problemDTO : lst) {
            StringBuilder stringBuilder = new StringBuilder();
            List<TagDTO> lstTag = problemDTO.getLstTag();
            for (int i = 0; i < lstTag.size(); ++i) {
                stringBuilder.append(lstTag.get(i).getName());
                if (i < lstTag.size() - 1) {
                    stringBuilder.append(",");
                }
            }
            problemDTO.setTag(stringBuilder.toString());
            if (Constants.BLANK.equals(stringBuilder.toString())) {
                problemDTO.setTag(null);
            }
        }
        return lst;
    }

    //get leaderboard information here
    public List<UserDTO> getLeaderboardInfor(Integer ctId) {

        //1 user -> many problems
        List<UserDTO> lstUser = userMapper.getLeaderboardInfor(ctId, Constants.AUTH_PARTICIPATE_CONTEST);
        for (UserDTO user : lstUser) {
            int score = 0;
            int penalty = 0;

            long start = user.getContestStartTime().getTime();
            /**
             * 1 problem -> many submission
             * however, we only need to calculate number of submissions and time penalty up to the first accepted one.
             */
            for (ProblemDTO problem : user.getLstProblem()) {
                int isSolved = 0;
                int submitCnt = 0;
                int time = 0;
                String solveTime = "--:--";
                problem.setIsFirstSolve(0);
                if (null != problem.getLstSubmission()) {
                    for (SubmissionDTO submit : problem.getLstSubmission()) {

                        //we will ignore any submissions submitted after the first AC-ed one.
                        if (0 == isSolved) {
                            if (submit.getJudgeStatus().equals(Constants.STATUS_ACCEPTED)) {
                                isSolved = 1;

                                //calculate submit time if AC-ed
                                long current = submit.getCreateTime().getTime();
                                long elapsed = current - start;
                                int minutes = (int) Math.floor((elapsed / 1000 / 60) % 60);
                                int hours = (int) Math.floor((elapsed / (1000 * 60 * 60)));
                                String h = StringUtils.leftPad(String.valueOf(hours), 2, "0");
                                String m = StringUtils.leftPad(String.valueOf(minutes), 2, "0");
                                solveTime = h + ":" + m;
                                time += (int) Math.floor((elapsed / 1000 / 60));

                                //check if first solver
                                if (null != problem.getFirstSolveTime()){
                                    if (problem.getFirstSolveTime().equals(submit.getCreateTime())){
                                        problem.setIsFirstSolve(1);
                                    }
                                }
                            } else {
                                //if not AC-ed
                                time += Constants.SUBMISSION_FAIL_PENALTY;
                            }
                            submitCnt++;
                        }
                    }
                }

                //score = number of AC-ed problems
                score += isSolved;

                //calculate penalty only when AC-ed
                if (isSolved == 1) {
                    penalty += time;
                }
                problem.setSubmitCnt(submitCnt);
                problem.setIsSolved(isSolved);
                problem.setSolveTime(solveTime);
            }
            user.setScore(score);
            user.setPenalty(penalty);
        }
        Collections.sort(lstUser,new ScoreboardComparator());
        return lstUser;
    }
    class ScoreboardComparator implements Comparator<UserDTO> {

        @Override
        public int compare(UserDTO o1, UserDTO o2) {
            if (o1.getScore().equals(o2.getScore())){
                return o1.getPenalty().compareTo(o2.getPenalty());
            }
            return o2.getScore().compareTo(o1.getScore());
        }
    }
    public void setProblemHiddenStatus(Integer ctId, Integer pmId, Integer status) {
        CtPmEntity ctPmEntity = new CtPmEntity();
        ctPmEntity.setCtId(ctId);
        ctPmEntity.setPmId(pmId);
        ctPmEntity.setIsHidden(status);
        setUpdateInfo(ctPmEntity);
        ctPmMapper.updateByPKExceptNullFields(ctPmEntity);
    }

    public void addProblemToContest(Integer ctId, String[] lstPmId) throws Exception {
        try {
            for (String pmId : lstPmId) {
                CtPmEntity ctPmEntity = new CtPmEntity();
                ctPmEntity.setCtId(ctId);
                ctPmEntity.setPmId(Integer.parseInt(pmId));
                ctPmEntity.setIsHidden(0);
                setUpdateInfo(ctPmEntity);
                setCreateInfo(ctPmEntity);
                ctPmMapper.insert(ctPmEntity);

                //reset firstSolve time
                problemMapper.resetFirstSolveTime(Integer.parseInt(pmId));
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public List<ProblemDTO> getProblemToDisplay(Integer ctId) {
        List<ProblemDTO> lst = problemMapper.getProblemToDisplay(ctId);
        int cnt = 0;
        for (ProblemDTO problemDTO : lst) {
            if (problemDTO.getIsHidden() == 0) {
                problemDTO.setAlias(++cnt);
            } else {
                problemDTO.setAlias(-1);
            }
        }
        return lst;
    }

    public List<ProblemDTO> getProblemToSubmit(Integer ctId) {
        List<ProblemDTO> lst = problemMapper.getProblemToSubmit(ctId);
        int cnt = 0;
        for (ProblemDTO problemDTO : lst) {
            String name = ++cnt + ". " + problemDTO.getName();
            problemDTO.setName(name);
        }
        return lst;
    }

    public ContestInformationVO getInformation(int ctId) {
        ContestInformationVO contestInformationVO = new ContestInformationVO();
        ContestEntity contestEntity = new ContestEntity();
        contestEntity.setId(ctId);
        contestEntity = contestMapper.selectByPK(contestEntity);
        modelMapper.map(contestEntity, contestInformationVO);
        Date startTime = contestEntity.getStartTime();
        SimpleDateFormat sdfr = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        contestInformationVO.setStart(sdfr.format(startTime));
        Date endTime = DateUtils.addMinutes(startTime, contestEntity.getDuration());
        contestInformationVO.setEnd(sdfr.format(endTime));
        return contestInformationVO;
    }

    public void updateContest(ContestSettingForm contestSettingForm) throws Exception {
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

    public List<SubmissionDTO> getSubmissionInContest(Integer ctId, boolean getAll) {
        List<SubmissionDTO> lst = null;
        if (getAll) {
            lst = submissionMapper.getSubmissionInContest(ctId, null);
        } else {
            String handle = (String) httpSession.getAttribute(Constants.CURRENT_LOGIN_USER_HANDLE);
            lst = submissionMapper.getSubmissionInContest(ctId, handle);
        }
        for (SubmissionDTO submissionDTO : lst) {
            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
            String strDate = dateFormat.format(submissionDTO.getCreateTime());
            submissionDTO.setSubmitTime(strDate);
        }
        return lst;
    }

    //execute submission in contest
    public void doSubmit(String sourceCode, Integer ctId, Integer leId, Integer pmId) {
        LanguageEntity languageEntity = new LanguageEntity();
        languageEntity.setId(leId);
        languageEntity = languageMapper.selectByPK(languageEntity);
        LanguageDTO languageDTO = new LanguageDTO();
        modelMapper.map(languageEntity, languageDTO);
        ProblemDTO problemDTO = new ProblemDTO();
        problemDTO.setId(pmId);
        problemService.getProblemInfo(problemDTO);
        List<InputDTO> lstInput = inputMapper.getAllTest(pmId);
        problemDTO.setLstInput(lstInput);
        problemDTO.setSourceCode(sourceCode);
        Integer urId = (Integer) httpSession.getAttribute(Constants.CURRENT_LOGIN_USER_ID);
        if (urId == null) {
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

        judgeService.judge(problemDTO, languageDTO, submissionEntity, urId);
    }

    public List<UserDTO> getListContestRole(Integer ctId) {
        Integer urId = (Integer) httpSession.getAttribute(Constants.CURRENT_LOGIN_USER_ID);
        List<UserDTO> lstUser = userMapper.getListContestRole(urId, ctId);
        return lstUser;
    }

    public List<UserDTO> findUserForContestRole(String fullname, Integer reId, Integer ctId) {
        List<UserDTO> lstUser = userMapper.findUserForContestRole(fullname, reId, ctId);
        return lstUser;
    }

    /**
     * addRole
     * 1 = CAN_VIEW_PROBLEM
     * 2 = CAN_VIEW_PROBLEM + CAN_EDIT_PROBLEM
     * 3 = CAN_PARTICIPATE_CONTEST
     */
    public void addRole(String[] urId, Integer auyId, Integer ctId) throws UserTryingToBeSmartException {
        UrCtAuyEntity urCtAuyEntity = new UrCtAuyEntity();
        setCreateInfo(urCtAuyEntity);
        setUpdateInfo(urCtAuyEntity);
        urCtAuyEntity.setCtId(ctId);
        if (auyId != 1 && auyId != 2 && auyId != 3) {
            throw new UserTryingToBeSmartException();
        }
        //if auyId == 1 or 2
        if (auyId == 1 || auyId == 2) {
            urCtAuyEntity.setAuyId(Constants.AUTH_VIEW_CONTEST);
            for (String id : urId) {
                urCtAuyEntity.setUrId(Integer.parseInt(id));
                urCtAuyMapper.insert(urCtAuyEntity);
            }
        }
        if (auyId == 2) {
            urCtAuyEntity.setAuyId(Constants.AUTH_EDIT_CONTEST);
            for (String id : urId) {
                urCtAuyEntity.setUrId(Integer.parseInt(id));
                urCtAuyMapper.insert(urCtAuyEntity);
            }
        }

        if (auyId == 3) {
            urCtAuyEntity.setAuyId(Constants.AUTH_PARTICIPATE_CONTEST);
            for (String id : urId) {
                urCtAuyEntity.setUrId(Integer.parseInt(id));
                urCtAuyMapper.insert(urCtAuyEntity);
            }
        }
    }

    public void deleteRole(Integer ctId, Integer urId) {
        UrCtAuyEntity urCtAuyEntity = new UrCtAuyEntity();
        urCtAuyEntity.setCtId(ctId);
        urCtAuyEntity.setUrId(urId);
        urCtAuyMapper.deleteForRealWithExample(urCtAuyEntity);
    }
}
