package com.minh.nguyen.service;

import com.minh.nguyen.constants.Constants;
import com.minh.nguyen.dto.*;
import com.minh.nguyen.entity.LecturerEntity;
import com.minh.nguyen.entity.StudentEntity;
import com.minh.nguyen.entity.UrAuyEntity;
import com.minh.nguyen.entity.UserEntity;
import com.minh.nguyen.exception.NoSuchPageException;
import com.minh.nguyen.mapper.*;
import com.minh.nguyen.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Mr.Minh
 * @since 08/04/2018
 * Purpose:
 */
@Service
public class UserService extends BaseService{
    @Autowired
    private HttpSession httpSession;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UrAuyMapper urAuyMapper;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private AuthorityMapper authorityMapper;

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private LecturerMapper lecturerMapper;

    @Autowired
    private SubmissionMapper submissionMapper;

    @Autowired
    private ContestMapper contestMapper;

    public List<AuthorityDTO> getDefaultAuthority(Integer reId){
        List<AuthorityDTO> lstAuth = authorityMapper.getDefaultAuthorityForRole(reId);

        return lstAuth;
    }

    public Integer getUserRoleByID(Integer urId){
        Integer roleId = userMapper.getRoleForUser(urId);
        if (roleId == null || roleId == 0){
            throw new NoSuchPageException("User not found");
        }
        return roleId;
    }

    @Transactional
    public void createUser(UserDTO userDTO){

        //check if handle already taken
        UserEntity userEntity = new UserEntity();
        userEntity.setHandle(userDTO.getHandle());
        int checkExclusive = userMapper.countWithExample(userEntity);
        if (checkExclusive > 0){
            rollBack(Constants.MSG_HANDLE_EXISTED_ERR);
        }

        //insert user table first
        userEntity = modelMapper.map(userDTO,UserEntity.class);
        userEntity.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
        userEntity.setAvatar(Constants.DEFAULT_AVATAR);
        setCreateInfo(userEntity);
        setUpdateInfo(userEntity);
        int recordCnt = userMapper.insertUser(userEntity);
        if (recordCnt == 0){
            rollBack(Constants.MSG_SYSTEM_ERR);
        }

        //then we check if current login user has CAN_EDIT_AUTHORITY
        List<Integer> lstDefaultAuthOfCurrentUser = (List<Integer>) httpSession.getAttribute(Constants.CURRENT_LOGIN_USER_DEFAULT_AUTHORITIES);
        if (CollectionUtils.isEmpty(lstDefaultAuthOfCurrentUser)){
            if (lstDefaultAuthOfCurrentUser.contains(Constants.AUTH_EDIT_AUTHORITY_ID)){

                //then we insert registered user's authorities
                UrAuyEntity urAuyEntity = new UrAuyEntity();
                setCreateInfo(urAuyEntity);
                setUpdateInfo(urAuyEntity);
                Integer currentUserID = (Integer)httpSession.getAttribute(Constants.CURRENT_LOGIN_USER_ID);
                urAuyEntity.setUrId(currentUserID);
                for(String authStr : userDTO.getLstAuyId()){
                    Integer authInt = Integer.valueOf(authStr);
                    urAuyEntity.setAuyId(authInt);
                    urAuyMapper.insert(urAuyEntity);
                }
            }
        }

        if (userDTO.getReId().equals(Constants.ROLE_STUDENT_ID)){
            StudentEntity studentEntity = new StudentEntity();
            setCreateInfo(studentEntity);
            setUpdateInfo(studentEntity);
            studentEntity.setPoint(0);
            studentEntity.setSolveCnt(0);
            studentEntity.setUrId(userEntity.getId());
            recordCnt = studentMapper.insert(studentEntity);
            if (recordCnt == 0){
                rollBack(Constants.MSG_SYSTEM_ERR);
            }
        }
        //we will treat admin, lecturer and supervisor the same (which means their information will be stored in 'lecturer' table in database)
        //TODO: might need to update this logic later
        else{
            LecturerEntity lecturerEntity = new LecturerEntity();
            setUpdateInfo(lecturerEntity);
            setCreateInfo(lecturerEntity);
            lecturerEntity.setUrId(userEntity.getId());
            recordCnt = lecturerMapper.insert(lecturerEntity);
            if (recordCnt == 0){
                rollBack(Constants.MSG_SYSTEM_ERR);
            }
        }
    }

    public UserDTO getUserInformationToUpdate(Integer urId){
        UserEntity userEntity = new UserEntity();
        userEntity.setId(urId);
        userEntity = userMapper.selectByPK(userEntity);
        return modelMapper.map(userEntity,UserDTO.class);
    }

    //first we get all default authorities, then we check if current user has that authority, if they do then we set isCheck = true
    public List<AuthorityDTO> getDefaultAuthoritiesForUser(Integer urId){
        List<AuthorityDTO> lstAllDefaultAuth = new ArrayList<>();
        List<AuthorityDTO> lstUserDefaultAuth = authorityMapper.getDefaultAuthorityForUser(urId);
        for(AuthorityDTO defaultAuth : Constants.LST_DEFAULT_AUTHORITY){
            AuthorityDTO auth = new AuthorityDTO(defaultAuth.getId(),defaultAuth.getName());
            for(AuthorityDTO currentAuth : lstUserDefaultAuth){
                if (currentAuth.getId().equals(defaultAuth.getId())){
                    auth.setCheck(true);
                    break;
                }
            }
            lstAllDefaultAuth.add(auth);
        }
        return lstAllDefaultAuth;
    }

    @Transactional
    public void updateUser(UserDTO user){
        UserEntity userEntity = modelMapper.map(user,UserEntity.class);
        if (!StringUtil.isEmpty(user.getPassword())){
            userEntity.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        }
        setUpdateInfo(userEntity);

        //we don't update handle here
        userEntity.setHandle(null);

        int recordCnt = userMapper.updateNotNullByPK(userEntity);
        if (recordCnt == 0){
            rollBack(Constants.MSG_SYSTEM_ERR);
        }

        //delete all old default authorities
        authorityMapper.deleteAllDefaultAuthForUser(user.getId());

        //insert new default authorities (if any)
        if (user.getLstAuyId() != null) {
            UrAuyEntity urAuyEntity = new UrAuyEntity();
            setUpdateInfo(urAuyEntity);
            setCreateInfo(urAuyEntity);
            urAuyEntity.setUrId(user.getId());
            for (String authStr : user.getLstAuyId()) {
                urAuyEntity.setAuyId(Integer.valueOf(authStr));
                urAuyMapper.insert(urAuyEntity);
            }
        }
    }

    public StudentDTO getStudentProfile(Integer urId){

        //get general information
        StudentDTO student = userMapper.getStudentProfile(urId);

        //get solved problem list
        List<SubmissionDTO> lstSolvedProblem = submissionMapper.getAllSolvedProblem(urId);

        //format submit time
        for (SubmissionDTO submissionDTO : lstSolvedProblem) {
            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
            String strDate = dateFormat.format(submissionDTO.getCreateTime());
            submissionDTO.setSubmitTime(strDate);
        }
        student.setLstSolved(lstSolvedProblem);

        //get contest participated and rank
        List<ContestDTO> lstContest = contestMapper.getParticipatedContest(urId,Constants.AUTH_PARTICIPATE_CONTEST_ID);
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        for(ContestDTO contest : lstContest){
            contest.setStartTime(dateFormat.format(contest.getCreateTime()));
        }
        student.setLstContest(lstContest);

        return student;
    }
}
