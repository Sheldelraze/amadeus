package com.minh.nguyen.service;

import com.minh.nguyen.dto.AuthorityDTO;
import com.minh.nguyen.dto.UserDTO;
import com.minh.nguyen.mapper.AuthorityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private AuthorityMapper authorityMapper;

    public List<AuthorityDTO> getDefaultAuthority(Integer reId){
        List<AuthorityDTO> lstAuth = authorityMapper.getDefaultAuthorityForRole(reId);

        return lstAuth;
    }
    public void createUser(UserDTO userDTO){

    }
}
