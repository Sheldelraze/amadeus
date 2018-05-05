package com.minh.nguyen.service;

import com.minh.nguyen.constants.Constants;
import com.minh.nguyen.dto.*;
import com.minh.nguyen.entity.*;
import com.minh.nguyen.exception.NoSuchPageException;
import com.minh.nguyen.exception.UserTryingToBeSmartException;
import com.minh.nguyen.form.contest.ContestSettingForm;
import com.minh.nguyen.mapper.*;
import com.minh.nguyen.util.StringUtil;
import com.minh.nguyen.validator.ContestValidator;
import com.minh.nguyen.vo.contest.ContestInformationVO;
import com.minh.nguyen.vo.contest.ContestListVO;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

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
    private AnnouncementMapper announcementMapper;

    @Autowired
    private CtPmMapper ctPmMapper;

    @Autowired
    private CtAtMapper ctAtMapper;

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
    private ContestValidator contestValidator;

    @Autowired
    private HttpSession httpSession;

    @Autowired
    private GeneralService generalService;

    @Autowired
    private TaskScheduler scheduler;

    @Configurable
    public class UpdateContestRankTask implements Runnable {

        private Date finishTime;

        private Integer ctId;

        public UpdateContestRankTask(Integer ctId,Date finishTime) {
            this.ctId = ctId;
            this.finishTime = finishTime;
        }

        @Override
        public void run() {
            List<UserDTO> lstRank = userMapper.getLeaderboardIOIForContest(ctId, Constants.AUTH_PARTICIPATE_CONTEST_ID);
            UrCtAuyEntity urCtAuyEntity = new UrCtAuyEntity();
            urCtAuyEntity.setCtId(ctId);
            urCtAuyEntity.setAuyId(Constants.AUTH_PARTICIPATE_CONTEST_ID);
            urCtAuyEntity.setUpdateTime(finishTime);
            setUpdateInfo(urCtAuyEntity);
            int curRank = 1;
            for(UserDTO user : lstRank){
                urCtAuyEntity.setUrId(user.getId());
                urCtAuyEntity.setRank(curRank++);
                urCtAuyMapper.updateNotNullByPK(urCtAuyEntity);
            }
        }
    }

    //this function will be schedule every time contest is created or updated
    //it will schedule a function implement in a subclass of Runnable class to be executed Constants.UPDATE_CONTEST_DELAY minutes after contest has finished
    //It's purpose is to update the contest rank to database
    @Async
    public void updateAfterContestFinish(Integer ctId,Date finishDateTime) {
        ScheduledExecutorService localExecutor = Executors.newSingleThreadScheduledExecutor();
        scheduler = new ConcurrentTaskScheduler(localExecutor);
        UpdateContestRankTask updateContestRankTask = new UpdateContestRankTask(ctId,finishDateTime);
        scheduler.schedule(updateContestRankTask,finishDateTime);
    }

    @Transactional
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
            setCreateContestInfor(contestEntity);
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
            urCtAuyEntity.setAuyId(Constants.AUTH_VIEW_CONTEST_ID);
            setCreateInfo(urCtAuyEntity);
            setUpdateInfo(urCtAuyEntity);
            insertRecord = urCtAuyMapper.insert(urCtAuyEntity);

            //assume that insert success
            if (insertRecord != 1) {
                rollBack(Constants.MSG_INSERT_ERR);
            }

            //insert edit contest authority
            urCtAuyEntity.setAuyId(Constants.AUTH_EDIT_CONTEST_ID);
            insertRecord = urCtAuyMapper.insert(urCtAuyEntity);
            if (insertRecord != 1) {
                rollBack(Constants.MSG_SYSTEM_ERR);
            }

            //set up schedule to update rank after contest finish
            Date endTime = DateUtils.addMinutes(contestEntity.getStartTime(), contestEntity.getDuration());
            endTime = DateUtils.addMinutes(endTime, Constants.UPDATE_CONTEST_DELAY);
            updateAfterContestFinish(contestEntity.getId(),endTime);
            return contestEntity.getId();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    private void setCreateContestInfor(ContestEntity contestEntity) {
        setCreateInfo(contestEntity);
        setUpdateInfo(contestEntity);
        contestEntity.setIsPublic(1);
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

    public List<ProblemDTO> getProblemForLeaderboard(int ctId) {
        List<ProblemDTO> lstProblem = problemMapper.getProblemForLeaderboardInContest(ctId);
        for (ProblemDTO problem : lstProblem) {
            int ac = problem.getSolveCnt();
            int all = problem.getTotalSubmission();
            if (all == 0 || ac == 0) {
                problem.setSolvePercentage("(0%)");
                continue;
            } else if (all == ac) {
                problem.setSolvePercentage("(100%)");
                continue;
            }
            NumberFormat df = new DecimalFormat("#.00");
            double percentage = 100.0 * ac / all;
            problem.setSolvePercentage("(" + df.format(percentage) + "%)");
        }
        return lstProblem;
    }

    public List<ProblemDTO> getProblemToAdd(int ctId) {
        Integer urId = (Integer) httpSession.getAttribute(Constants.CURRENT_LOGIN_USER_ID);
        List<ProblemDTO> lst = problemMapper.getProblemForContest(urId, Constants.AUTH_VIEW_PROBLEM_ID, ctId);
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
            if ("".equals(stringBuilder.toString())) {
                problemDTO.setTag(null);
            }
        }
        return lst;
    }

    //get leaderboard information here
    public List<UserDTO> getLeaderboardInfor(Integer ctId) {
        ContestDTO contest = getContestInfo(ctId);
        List<UserDTO> lstUser = null;
        //1 user -> many problems
        if (contest.getJudgeType().equals(Constants.JUDGE_TYPE_ACM)) {
            lstUser = userMapper.getLeaderboardIOIForContest(ctId, Constants.AUTH_PARTICIPATE_CONTEST_ID);
        } else {
            lstUser = userMapper.getLeaderboardACMForContest(ctId, Constants.AUTH_PARTICIPATE_CONTEST_ID, Constants.STATUS_ACCEPTED);
        }
        for (UserDTO user : lstUser) {
            int score = 0;
            int penalty = 0;

            long start = user.getContestStartTime().getTime();
            /**
             * 1 user can submit many times for 1 problem
             * however, we only need to calculate number of submissions and time penalty up to the first accepted one.
             */
            for (ProblemDTO problem : user.getLstProblem()) {
                int isSolved = 0;
                int submitCnt = 0;
                int time = 0;
                String solveTime = "--:--";
                problem.setIsFirstSolve(0);

                if (null != problem.getLstSubmission()) {
                    problem.setCorrectAns(0);
                    for (SubmissionDTO submit : problem.getLstSubmission()) {

                        //we will ignore any submissions submitted after the first AC-ed one.
                        if (0 == isSolved) {
                            //judge by ACM type (only accepted by correct all answers)
                            if (contest.getJudgeType().equals(Constants.JUDGE_TYPE_ACM)) {
                                if (submit.getJudgeStatus().equals(Constants.STATUS_ACCEPTED)) {
                                    isSolved = 1;

                                    //calculate submit time if AC-ed
                                    long current = submit.getCreateTime().getTime();
                                    long elapsed = current - start;
                                    if (elapsed > 0) {
                                        int minutes = (int) Math.floor((elapsed / 1000 / 60) % 60);
                                        int hours = (int) Math.floor((elapsed / (1000 * 60 * 60)));
                                        String h = StringUtils.leftPad(String.valueOf(hours), 2, "0");
                                        String m = StringUtils.leftPad(String.valueOf(minutes), 2, "0");
                                        solveTime = h + ":" + m;
                                        time += (int) Math.floor((elapsed / 1000 / 60));
                                    } else {
                                        solveTime = "00:00";
                                    }
                                    //check if first solver
                                    if (null != problem.getFirstSolveTime()) {
                                        if (problem.getFirstSolveTime().equals(submit.getCreateTime())) {
                                            problem.setIsFirstSolve(1);
                                        }
                                    }
                                } else {
                                    //if not AC-ed then add penalty
                                    time += Constants.SUBMISSION_FAIL_PENALTY;
                                }

                                //add submission count up to the first AC-ed one
                                submitCnt++;

                            } else { //judge by IOI style (total correct ans / total test)
                                if (submit.getCorrectAns() != null && submit.getCorrectAns().equals(problem.getTestCnt())) {
                                    isSolved = 1;

                                    //calculate submit time if AC-ed
                                    long current = submit.getCreateTime().getTime();
                                    long elapsed = current - start;
                                    if (elapsed >= 0) {
                                        int minutes = (int) Math.floor((elapsed / 1000 / 60) % 60);
                                        int hours = (int) Math.floor(elapsed / (10000 * 6 * 60));
                                        String h = StringUtils.leftPad(String.valueOf(hours), 2, "0");
                                        String m = StringUtils.leftPad(String.valueOf(minutes), 2, "0");
                                        solveTime = h + ":" + m;
                                        time += (int) Math.floor((elapsed / 1000 / 60));
                                    } else {
                                        solveTime = "00:00";
                                    }
                                } else {
                                    time += Constants.SUBMISSION_FAIL_PENALTY;
                                }

                                //if not AC-ed then add penalty
                                if (submit.getCorrectAns() != null && submit.getCorrectAns().compareTo(problem.getCorrectAns()) > 0) {
                                    problem.setCorrectAns(submit.getCorrectAns());
                                }
                            }
                        }
                    }
                }

                //score = number of AC-ed problems
                if (contest.getJudgeType().equals(Constants.JUDGE_TYPE_ACM)) {
                    score += isSolved;
                } else {
                    score += problem.getCorrectAns();
                }
                //calculate penalty only when AC-ed
                if (isSolved == 1) {
                    penalty += time;
                }
                problem.setSubmitCnt(submitCnt);
                problem.setIsSolved(isSolved);
                problem.setSolveTime(solveTime);


                //check if last submission still pending
                if (!CollectionUtils.isEmpty(problem.getLstSubmission())) {
                    SubmissionDTO submit = problem.getLstSubmission().get(problem.getLstSubmission().size() - 1);
                    if (submit.getJudgeStatus().equals(Constants.STATUS_JUDGING)){
                        problem.setIsSolved(-1);
                    }
                }
            }
            user.setScore(score);
            user.setPenalty(penalty);
        }
        Collections.sort(lstUser, new ScoreboardComparator());

        return lstUser;
    }

    //first we sort desc by score, then we sort asc by time penalty
    class ScoreboardComparator implements Comparator<UserDTO> {

        @Override
        public int compare(UserDTO o1, UserDTO o2) {
            if (o1.getScore().equals(o2.getScore())) {
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
        ctPmMapper.updateNotNullByPK(ctPmEntity);
    }

    @Transactional
    public void addProblemToContest(Integer ctId, String[] lstPmId) throws Exception {
        try {
            for (String pmId : lstPmId) {
                CtPmEntity ctPmEntity = new CtPmEntity();
                ctPmEntity.setCtId(ctId);
                ctPmEntity.setPmId(Integer.parseInt(pmId));
                ctPmEntity.setIsHidden(0);
                ctPmEntity.setSolveCnt(0);
                ctPmEntity.setTotalSubmission(0);
                setUpdateInfo(ctPmEntity);
                setCreateInfo(ctPmEntity);
                ctPmMapper.insert(ctPmEntity);
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public List<ProblemDTO> getProblemToDisplay(Integer ctId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        boolean getAllProblem = false;
        if (null != auth && !StringUtil.isNull(auth.getName())) {
            if (contestValidator.checkPermission(auth, ctId, Constants.AUTH_EDIT_CONTEST_TEXT)) {
                getAllProblem = true;
            }
        }
        List<ProblemDTO> lst = problemMapper.getProblemToDisplayInContest(ctId, getAllProblem);
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
        List<ProblemDTO> lst = problemMapper.getProblemToSubmitInContest(ctId);
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
        ContestEntity contestEntity = new ContestEntity();
        String startTime = contestSettingForm.getDate() + " " + contestSettingForm.getTime();
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm");
        modelMapper.map(contestSettingForm, contestEntity);
        contestEntity.setStartTime(dateFormat.parse(startTime));
        setUpdateInfo(contestEntity);
        contestMapper.updateNotNullByPK(contestEntity);

        //set schedule update rank after contest
        Date endTime = DateUtils.addMinutes(contestEntity.getStartTime(), contestEntity.getDuration());
        endTime = DateUtils.addMinutes(endTime, Constants.UPDATE_CONTEST_DELAY);
        updateAfterContestFinish(contestEntity.getId(),endTime);
    }

    @Transactional
    public void addQuestion(Integer ctId, Integer pmId, String question) {
        AnnouncementEntity announcementEntity = new AnnouncementEntity();
        announcementEntity.setPmId(pmId);
        announcementEntity.setQuestion(question);
        announcementEntity.setIsAnswered(0);
        announcementEntity.setIsHidden(0);
        announcementEntity.setIsFromCreator(0);
        setUpdateInfo(announcementEntity);
        setCreateInfo(announcementEntity);
        announcementMapper.insertAnnouncement(announcementEntity);

        CtAtEntity ctAtEntity = new CtAtEntity();
        ctAtEntity.setCtId(ctId);
        ctAtEntity.setAtId(announcementEntity.getId());
        setCreateInfo(ctAtEntity);
        setUpdateInfo(ctAtEntity);
        ctAtMapper.insert(ctAtEntity);
    }

    @Transactional
    public void addAnnouncement(Integer ctId, Integer pmId, String answer) {
        AnnouncementEntity announcementEntity = new AnnouncementEntity();
        announcementEntity.setPmId(pmId);
        announcementEntity.setAnswer(answer);
        announcementEntity.setIsAnswered(1);
        announcementEntity.setIsHidden(0);
        announcementEntity.setIsFromCreator(1);
        setUpdateInfo(announcementEntity);
        setCreateInfo(announcementEntity);
        announcementMapper.insertAnnouncement(announcementEntity);

        CtAtEntity ctAtEntity = new CtAtEntity();
        ctAtEntity.setCtId(ctId);
        ctAtEntity.setAtId(announcementEntity.getId());
        setCreateInfo(ctAtEntity);
        setUpdateInfo(ctAtEntity);
        ctAtMapper.insert(ctAtEntity);
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
    @Transactional
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
        submissionEntity.setPoint(0);
        submissionEntity.setIsPublic(Constants.PUBLIC_FLAG);
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

        judgeService.judge(problemDTO, languageDTO, submissionEntity, urId, ctId, null);
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
     * 1 = CAN_VIEW_CONTEST
     * 2 = CAN_VIEW_CONTEST + CAN_EDIT_CONTEST
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
            urCtAuyEntity.setAuyId(Constants.AUTH_VIEW_CONTEST_ID);
            for (String id : urId) {
                urCtAuyEntity.setUrId(Integer.parseInt(id));
                urCtAuyMapper.insert(urCtAuyEntity);
            }
        }
        if (auyId == 2) {
            urCtAuyEntity.setAuyId(Constants.AUTH_EDIT_CONTEST_ID);
            for (String id : urId) {
                urCtAuyEntity.setUrId(Integer.parseInt(id));
                urCtAuyMapper.insert(urCtAuyEntity);
            }
        }

        if (auyId == 3) {
            urCtAuyEntity.setAuyId(Constants.AUTH_PARTICIPATE_CONTEST_ID);
            for (String id : urId) {
                urCtAuyEntity.setUrId(Integer.parseInt(id));
                urCtAuyMapper.insert(urCtAuyEntity);
            }
        }
    }

    public List<AnnouncementDTO> getAnnouncementList(Integer ctId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        boolean getAllAnnouncement = false;
        if (null != auth && !StringUtil.isNull(auth.getName())) {
            if (contestValidator.checkPermission(auth, ctId, Constants.AUTH_EDIT_CONTEST_TEXT)) {
                getAllAnnouncement = true;
            }
        }
        List<AnnouncementDTO> lstAnnounce = announcementMapper.getAnnouncementListInContest(ctId, getAllAnnouncement);
        for (AnnouncementDTO announce : lstAnnounce) {
            if (announce.getProblem().getId().equals(0)) {
                announce.getProblem().setName("Thông báo chung");
            }
            SimpleDateFormat sdfr = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            announce.setTimePosted(sdfr.format(announce.getUpdateTime()));
        }
        return lstAnnounce;
    }

    public void doApply(Integer urId, Integer ctId) {
        if (!contestValidator.checkApplyPermission(ctId, urId)) {
            rollBack(Constants.MSG_ALREADY_IN_CONTEST_ERR);
        }
        UrCtAuyEntity urCtAuyEntity = new UrCtAuyEntity();
        urCtAuyEntity.setCtId(ctId);
        urCtAuyEntity.setUrId(urId);
        urCtAuyEntity.setAuyId(Constants.AUTH_PARTICIPATE_CONTEST_ID);
        setCreateInfo(urCtAuyEntity);
        setUpdateInfo(urCtAuyEntity);
        urCtAuyMapper.insert(urCtAuyEntity);
    }

    public void deleteRole(Integer ctId, Integer urId) {
        UrCtAuyEntity urCtAuyEntity = new UrCtAuyEntity();
        urCtAuyEntity.setCtId(ctId);
        urCtAuyEntity.setUrId(urId);
        urCtAuyMapper.deleteForRealWithExample(urCtAuyEntity);
    }

    public void changeAnnounceHiddenState(Integer atId, Integer newState) {
        AnnouncementEntity announcementEntity = new AnnouncementEntity();
        announcementEntity.setId(atId);
        announcementEntity.setIsHidden(newState);
        setUpdateInfo(announcementEntity);
        int recordCnt = announcementMapper.updateNotNullByPK(announcementEntity);
        if (recordCnt == 0) {
            rollBack(Constants.MSG_UPDATE_ERR);
        }
    }

    public Integer getAnnouncementCount(Integer ctId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        boolean getAllAnnouncement = false;
        if (null != auth && !StringUtil.isNull(auth.getName())) {
            if (contestValidator.checkPermission(auth, ctId, Constants.AUTH_EDIT_CONTEST_TEXT)) {
                getAllAnnouncement = true;
            }
        }
        return announcementMapper.countAnnouncementListInContest(ctId, getAllAnnouncement);
    }

    public void answerQuestion(Integer atId, String answer) {
        AnnouncementEntity announcementEntity = new AnnouncementEntity();
        announcementEntity.setId(atId);
        announcementEntity.setIsAnswered(1);
        announcementEntity.setAnswer(answer);
        setUpdateInfo(announcementEntity);
        announcementMapper.updateNotNullByPK(announcementEntity);
    }

    public String getQuestion(Integer atId) {
        AnnouncementEntity announcementEntity = new AnnouncementEntity();
        announcementEntity.setId(atId);
        announcementEntity = announcementMapper.selectByPK(announcementEntity);
        if (announcementEntity == null) {
            throw new NoSuchPageException("Announcement not found!");
        }
        return announcementEntity.getQuestion();
    }

    public void setListContest(ContestListVO contestLst) {
        List<ContestDTO> lstContest = contestMapper.getAllContest(Constants.AUTH_PARTICIPATE_CONTEST_ID);
        List<ContestDTO> lstOngoing = new ArrayList<>();
        List<ContestDTO> lstPast = new ArrayList<>();
        for (ContestDTO contest : lstContest) {
            if (contest.getUserCnt() == null){
                contest.setUserCnt(0);
            }

            //get contest's start time and end time
            Date startTime = contest.getCreateTime();
            Date endTime = DateUtils.addMinutes(startTime, contest.getDuration());
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            DateFormat dateFormat1 = new SimpleDateFormat("dd-MM-yyyy \r\n HH:mm:ss");
            contest.setDate(dateFormat1.format(contest.getCreateTime()));
            Date now = new Date();
            if (now.compareTo(endTime) < 0) {
                lstOngoing.add(contest);
                if (now.compareTo(startTime) >= 0) {
                    contest.setOngoing(true);
                } else {
                    contest.setOngoing(false);
                    contest.setStartTime(dateFormat.format(startTime));
                }
            } else {
                lstPast.add(contest);
            }
        }
        contestLst.setOngoingLst(lstOngoing);
        contestLst.setPastLst(lstPast);
    }

    //submission in contest
    public SubmissionDTO getSubmitDetail(int snId, Integer ctId) {
        SubmissionDTO submissionDTO = generalService.getSubmitDetail(snId);
        ContestEntity contestEntity = new ContestEntity();
        contestEntity.setId(ctId);
        contestEntity = contestMapper.selectByPK(contestEntity);
        ContestDTO contestDTO = new ContestDTO();
        contestDTO.setId(ctId);
        contestDTO.setName(contestEntity.getName());
        submissionDTO.setContestDTO(contestDTO);

        //if contest creator does not allow participator to view test, then show compile error message only
        if (!contestValidator.canViewTest(ctId)) {
            if (submissionDTO.getJudgeStatus().equals(Constants.STATUS_COMPILE_ERROR)) {
                List<SubmitDetailDTO> lstSubmit = new ArrayList<>();
                SubmitDetailDTO submit = new SubmitDetailDTO();
                submit.setResult(submissionDTO.getLstSubmitDetail().get(0).getResult());
                submit.setTimeRun(0);
                submit.setMemoryUsed(0);
                submit.setAnswer("");
                submit.setOutput("");
                submit.setInput("");
                lstSubmit.add(submit);
                submissionDTO.setLstSubmitDetail(lstSubmit);
            } else {
                submissionDTO.setLstSubmitDetail(null);
            }
        }
        return submissionDTO;
    }


}
