package com.example.dingko.auth.security;

import com.example.dingko.common.domain.ROLE;
import com.example.dingko.common.domain.UserVO;
import com.example.dingko.common.service.UserService;
import com.example.dingko.common.utils.HashParameterMap;
import com.example.dingko.common.utils.StringExtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.Assert;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 시스템 인증 프로바이더
 * */
@Slf4j
public class CustomAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private UserService userService;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 사용자 인증 및 사용자 정보 Token에 담는다
     * */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        Assert.isInstanceOf(VALID_TOKEN_CLASS, authentication, "Only " + VALID_TOKEN_CLASS.getName() + " is supported");

        UsernamePasswordAuthenticationToken authToken = (UsernamePasswordAuthenticationToken) authentication;

        String userId = authentication.getName();
        String userPw = (String) authentication.getCredentials();

        HashParameterMap params = new HashParameterMap();

        //1. ID 또는 비밀번호가 입력되었는지 확인
        if ( StringExtUtils.isEmpty(userId) || StringExtUtils.isEmpty(userPw) ) {
            throw new InsufficientAuthenticationException("인증정보가 없어 인증에 실패했습니다. 관리자에게 문의하세요. (customAuthenticationProvider)");
        }

        //사용자 정보 가져오기
        UserDetails userVO = customUserDetailsService.loadUserByUsername(authToken.getName());

        //2. ID에 맞는 사용자가 있는지 확인
        if( userVO == null ) {
            throw new BadCredentialsException("해당 아이디 정보가 존재하지 않습니다. 입력하신 아이디를 확인하세요. (customAuthenticationProvider)");
        }

        //3.패스워드가 맞는지 확인
        //matches(입력된 비밀번호, 저장된 비밀번호 체크)
        boolean matchPw = passwordEncoder.matches(userPw,userVO.getPassword());
        if(!matchPw){
            throw new BadCredentialsException("비밀번호가 틀렸습니다. 입력하신 아이디와 비밀번호를 확인하세요.");
        }

        List<GrantedAuthority> authorities = (List<GrantedAuthority>) userVO.getAuthorities();
        //Credentials은 인증 확인 후 clear 해준다
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(userVO.getUsername(), null, userVO.getAuthorities());

        return token;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean supports(Class<?> authenticate) {
        return VALID_TOKEN_CLASS.isAssignableFrom(authenticate);
    }

    @SuppressWarnings("rawtypes")
    private static final Class VALID_TOKEN_CLASS = UsernamePasswordAuthenticationToken.class;
}
