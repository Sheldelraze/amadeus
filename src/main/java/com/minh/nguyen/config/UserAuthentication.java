package com.minh.nguyen.config;

import com.minh.nguyen.dto.AuthorityDTO;
import com.minh.nguyen.dto.UserDTO;
import com.minh.nguyen.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserAuthentication implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<UserDTO> lstUser = userMapper.getUserAuthority(username);

        if (null == lstUser || 0 == lstUser.size()) {
            throw new UsernameNotFoundException("User not found");
        }
        UserDTO user = lstUser.get(0);
        List<AuthorityDTO> lstAuthority = user.getLstAuthority();
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for(AuthorityDTO authority : lstAuthority){
            grantedAuthorities.add(new SimpleGrantedAuthority(authority.getName()));
        }
        return new org.springframework.security.core.userdetails.User(user.getHandle(), user.getPassword(),
                grantedAuthorities);
    }


}