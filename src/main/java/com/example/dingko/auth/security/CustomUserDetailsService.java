package com.example.dingko.auth.security;

import com.example.dingko.common.domain.CustomUserDetails;
import com.example.dingko.common.mapper.UserMapper;
import com.example.dingko.common.utils.HashParameterMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        HashParameterMap params = new HashParameterMap();
        params.setString("userId", username);
        UserDetails userInfo = null;
        try {
            //내가 설정한 User 정보(VO)를 스프링이 제공하는 UserDetails를 변환 하기 위해서는 VO가 UserDetails를 구현하고 있어야 한다.
            userInfo = (UserDetails) this.userMapper.getUser(params.getParameterMap());
        } catch (Exception e){
            e.printStackTrace();
        }
        return userInfo;
    }
}
