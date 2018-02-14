package com.minh.nguyen.config;

import com.minh.nguyen.constants.Constants;
import com.minh.nguyen.dto.AuthorityDTO;
import com.minh.nguyen.dto.UserDTO;
import com.minh.nguyen.entity.UserEntity;
import com.minh.nguyen.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * THIS CLASS WILL GET USER INFORMATION FROM DB BASE ON HANDLE (PASSWORD, AUTHORITIES)
 * AND THE PASSWORD WILL BE USED TO COMPARE WITH WHAT USER ENTER ON FORM.
 */
@Service
public class UserAuthentication implements UserDetailsService {

    @Autowired
    private HttpSession httpSession;

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //get user's default authorities (if any)
        List<UserDTO> lstUser = userMapper.getUserAuthority(username);
        UserEntity userEntity = new UserEntity();
        userEntity.setHandle(username);
        List<UserEntity> lst = userMapper.selectWithExample(userEntity);
        if (null == lstUser || 0 == lstUser.size() || 0 == lst.size()) {
            throw new UsernameNotFoundException("User not found");
        }

        //save authorities (remember these are only default authorities)
        UserDTO user = lstUser.get(0);
        List<AuthorityDTO> lstAuthority = user.getLstAuthority();
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        List<Integer> defaultAuth = new ArrayList<>();
        for(AuthorityDTO authority : lstAuthority){
            grantedAuthorities.add(new SimpleGrantedAuthority(authority.getName()));
            defaultAuth.add(authority.getId());
        }

        //save current user information for future use
        //when session expire the web will prompt user to login again so this will never be null (i hope XD)
        httpSession.setAttribute(Constants.CURRENT_LOGIN_USER_ID,lst.get(0).getId());
        httpSession.setAttribute(Constants.CURRENT_LOGIN_USER_HANDLE,lst.get(0).getHandle());
        httpSession.setAttribute(Constants.CURRENT_LOGIN_USER_ROLE,lst.get(0).getReId());
        httpSession.setAttribute(Constants.CURRENT_LOGIN_USER_DEFAULT_AUTHORITIES,defaultAuth);



        return new org.springframework.security.core.userdetails.User(user.getHandle(), user.getPassword(),
                grantedAuthorities);
    }


}