package com.minh.nguyen.service;

import com.minh.nguyen.constants.Constants;
import com.minh.nguyen.dto.AuthorityDTO;
import com.minh.nguyen.dto.UserDTO;
import com.minh.nguyen.entity.UrAuyEntity;
import com.minh.nguyen.entity.UserEntity;
import com.minh.nguyen.mapper.AuthorityMapper;
import com.minh.nguyen.mapper.UrAuyMapper;
import com.minh.nguyen.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpSession;
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
    public void createUser(UserDTO userDTO){

        //insert user table first
        UserEntity userEntity = modelMapper.map(userDTO,UserEntity.class);
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
}
