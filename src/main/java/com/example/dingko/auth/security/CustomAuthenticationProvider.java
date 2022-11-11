package com.example.dingko.auth.security;

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

    /**
     * 사용자 인증 및 사용자 정보 Token에 담는다
     * */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Assert.isInstanceOf(VALID_TOKEN_CLASS, authentication, "Only " + VALID_TOKEN_CLASS.getName() + " is supported");

        //접근IP조회
//        HttpServletRequest httpServletRequest = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
//        String userIpAddress = httpServletRequest.getHeader("X-FORWARDED-FOR");
//        String userAgent = httpServletRequest.getHeader("User-Agent");
//        if(StringExtUtils.isEmpty(userIpAddress)){
//            userIpAddress = httpServletRequest.getRemoteAddr();
//        }

        String userId = authentication.getName();
        String userPw = (String) authentication.getCredentials();

        HashParameterMap params = new HashParameterMap();

        //비밀번호는 아이디 검사 후 체크하기위해 지금 parameter에 넣지 않음
        params.setString("userId", userId);
//        params.setString("userId", userId).setString("userIpAddress", userIpAddress).setString("userAgent", userAgent);

        //1. ID 또는 비밀번호가 입력되었는지 확인
        if ( StringExtUtils.isEmpty(userId) || StringExtUtils.isEmpty(userPw) ) {
            throw new InsufficientAuthenticationException("인증정보가 없어 인증에 실패했습니다. 관리자에게 문의하세요. (customAuthenticationProvider)");
        }

        UserVO userVO = this.userService.getUser(params.getParameterMap());

        //2. ID에 맞는 사용자가 있는지 확인
        if( userVO == null ) {
            throw new BadCredentialsException("해당 아이디 정보가 존재하지 않습니다. 입력하신 아이디를 확인하세요. (customAuthenticationProvider)");
        }

        params.setString("userPw", userPw);
        UserVO userPwCheck = this.userService.getUser(params.getParameterMap());

        //3.패스워드가 맞는지 확인
        if(userPwCheck == null){
            throw new BadCredentialsException("비밀번호가 틀렸습니다. 입력하신 아이디와 비밀번호를 확인하세요.");
        }

        GrantedAuthority mockAutority = new SimpleGrantedAuthority("ROLE_USER");
        List<GrantedAuthority> mockAutorities = new ArrayList<>();
        mockAutorities.add(mockAutority);
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userVO.getUserId(), userVO.getUserPw(), mockAutorities);

        return token;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean supports(Class<?> authenticate) {
        return VALID_TOKEN_CLASS.isAssignableFrom(authenticate);
    }

    @SuppressWarnings("rawtypes")
    private static final Class VALID_TOKEN_CLASS = CustomAuthenticationToken.class;
}
