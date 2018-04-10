package com.minh.nguyen.service;

import com.minh.nguyen.constants.Constants;
import com.minh.nguyen.dto.AuthorityDTO;
import com.minh.nguyen.dto.UserDTO;
import com.minh.nguyen.entity.UrAuyEntity;
import com.minh.nguyen.entity.UserEntity;
import com.minh.nguyen.mapper.AuthorityMapper;
import com.minh.nguyen.mapper.UrAuyMapper;
import com.minh.nguyen.mapper.UserMapper;
import com.minh.nguyen.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpSession;
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

    public List<AuthorityDTO> getDefaultAuthority(Integer reId){
        List<AuthorityDTO> lstAuth = authorityMapper.getDefaultAuthorityForRole(reId);

        return lstAuth;
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
}