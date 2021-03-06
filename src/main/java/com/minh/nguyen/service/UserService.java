package com.minh.nguyen.service;

import com.minh.nguyen.constants.Constants;
import com.minh.nguyen.dto.*;
import com.minh.nguyen.entity.*;
import com.minh.nguyen.exception.NoSuchPageException;
import com.minh.nguyen.mapper.*;
import com.minh.nguyen.util.FileUtil;
import com.minh.nguyen.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
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

    @Autowired
    private ClassMapper classMapper;

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

    public LecturerDTO getLecturerProfile(Integer urId){
        LecturerDTO lecturer = userMapper.getLecturerProfile(urId);

        //check if any of these property are blank, if they do then set null
        if (StringUtil.isBlank(lecturer.getAward())){
            lecturer.setAward(null);
        }
        if (StringUtil.isBlank(lecturer.getEducation())){
            lecturer.setEducation(null);
        }
        if (StringUtil.isBlank(lecturer.getCareer())){
            lecturer.setCareer(null);
        }
        if (StringUtil.isBlank(lecturer.getPublication())){
            lecturer.setPublication(null);
        }
        return lecturer;
    }

    public void updatePassword(Integer urId,String oldPassword,String newPassword){
        //check if current logging user is the one who change password
        Integer currentLoginUserID = (Integer)httpSession.getAttribute(Constants.CURRENT_LOGIN_USER_ID);
        if (currentLoginUserID == null || !currentLoginUserID.equals(urId)){
            rollBack(Constants.MSG_NOT_ALLOWED_ERR);
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setId(urId);
        userEntity = userMapper.selectByPK(userEntity);
        if (userEntity == null){
            throw new NoSuchPageException("User not found!");
        }

        //check if the old password user enter matches with password stored in database
        if (!bCryptPasswordEncoder.matches(oldPassword,userEntity.getPassword())){
            rollBack(Constants.MSG_WRONG_OLD_PASSWORD_ERR);
        }

        //if valid then we update new password (encrypt it first)
        setUpdateInfo(userEntity);
        userEntity.setPassword(bCryptPasswordEncoder.encode(newPassword));
        int recordCnt = userMapper.updateNotNullByPK(userEntity);
        if (recordCnt != 1){
            rollBack(Constants.MSG_SYSTEM_ERR);
        }
    }

    public String uploadAvatar(Integer urId,MultipartFile multipartFile){
        //check if current logging user is the one who change password
        Integer currentLoginUserID = (Integer)httpSession.getAttribute(Constants.CURRENT_LOGIN_USER_ID);
        if (currentLoginUserID == null || !currentLoginUserID.equals(urId)){
            rollBack(Constants.MSG_NOT_ALLOWED_ERR);
        }

        if (multipartFile.getSize() == 0) {
            rollBack(Constants.MSG_UPLOAD_ERR);
        }
        if (multipartFile.getSize() / (1024 * 1024) > Constants.MAX_AVATAR_SIZE) {
            rollBack(Constants.MSG_UPLOAD_FILE_TOO_BIG_ERR);
        }

        //get current numnber of avatar this user has uploaded
        String parentFolder = StringUtil.buildString(Constants.AVATAR_LOCATION_PREFIX,"userId-" + urId);
        File folder = new File(parentFolder);
        Integer currentIndex = 1;
        if (!folder.exists()){
            folder.mkdir();
        }
        else {
            File[] listOfFiles = folder.listFiles();
            if (listOfFiles == null || listOfFiles.length == 0) {
                currentIndex = 1;
            } else {
                currentIndex = listOfFiles.length + 1;
            }
        }
        //create child folder based on the current index
        String childLocation = StringUtil.buildString(parentFolder,File.separator,currentIndex.toString());
        FileUtil.store(multipartFile,childLocation);

        //return avatar's storage location
        String storeLoaction = StringUtil.buildString(childLocation,File.separator,multipartFile.getOriginalFilename());

        //remove the resource location of the string, we only need to store the latter half
        //for example if the store location is: src\\main\\resources\\static\\storage\\avatar\\userId-1\\1\\profile.png
        //then we need to save this url in database: /storage/avatar/userId-1/1/profile.png
        storeLoaction = storeLoaction.substring(Constants.RESOURCE_LOCATION_PREFIX.length());

        //replace File.seperator with "/" since url in html slashes need to be forward
        storeLoaction = storeLoaction.replace(File.separator, "/");

        //update database
        UserEntity userEntity = new UserEntity();
        userEntity.setId(urId);
        userEntity = userMapper.selectByPK(userEntity);
        setUpdateInfo(userEntity);
        userEntity.setAvatar(storeLoaction);
        int recordCnt = userMapper.updateNotNullByPK(userEntity);
        if (recordCnt == 0){
            rollBack(Constants.MSG_SYSTEM_ERR);
        }

        //update avatar value stored in session
        httpSession.setAttribute(Constants.CURRENT_LOGIN_USER_AVATAR,storeLoaction);
        return storeLoaction;
    }

    @Transactional
    public void updateLecturer(Integer urId,LecturerDTO lecturerDTO,UserDTO userDTO){
        LecturerEntity lecturerEntity = modelMapper.map(lecturerDTO,LecturerEntity.class);
        setUpdateInfo(lecturerEntity);
        int recordCnt = lecturerMapper.updateNotNullByPK(lecturerEntity);
        if (recordCnt == 0){
            rollBack(Constants.MSG_SYSTEM_ERR);
        }
        userDTO.setId(urId);
        userUserProfile(userDTO);
    }

    @Transactional
    public void updateStudent(Integer urId,StudentDTO studentDTO,UserDTO userDTO){
        StudentEntity studentEntity = modelMapper.map(studentDTO,StudentEntity.class);
        setUpdateInfo(studentEntity);
        int recordCnt = studentMapper.updateNotNullByPK(studentEntity);
        if (recordCnt == 0){
            rollBack(Constants.MSG_SYSTEM_ERR);
        }
        userDTO.setId(urId);
        userUserProfile(userDTO);
    }

    private void userUserProfile(UserDTO userDTO){
        UserEntity userEntity = modelMapper.map(userDTO,UserEntity.class);
        setUpdateInfo(userEntity);
        int recordCnt = userMapper.updateNotNullByPK(userEntity);
        if (recordCnt == 0){
            rollBack(Constants.MSG_SYSTEM_ERR);
        }
        if (userDTO.getFullname() != null){
            httpSession.setAttribute(Constants.CURRENT_LOGIN_USER_FULLNAME,userDTO.getFullname());
        }
    }

    public List<ClassEntity> getAllClass(){
        List<ClassEntity> lstClass = classMapper.selectAll(ClassEntity.class);

        return lstClass;
    }
}
