package com.example.dingko.auth.security;

import com.example.dingko.common.domain.UserVO;
import com.example.dingko.common.mapper.UserMapper;
import com.example.dingko.common.service.UserService;
import com.example.dingko.common.utils.HashParameterMap;
import com.example.dingko.common.utils.StringExtUtils;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.DefaultSavedRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;

/**
 * 로그인 성공 후 특정한 동작을 하도록 제어하는 곳
 * */
@Slf4j
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    private UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        UserVO details = new UserVO();
        HashParameterMap params = new HashParameterMap();

        //접근 IP 조회
        String userIpAddress = httpServletRequest.getHeader("X-FORWARDED-FOR");
        if (StringExtUtils.isEmpty(userIpAddress)) {
            userIpAddress = httpServletRequest.getRemoteAddr();
            log.info("(Location : CustomAuthenticationSuccessHandler) 접근 IP : " + userIpAddress);
        }
        String userAgent = httpServletRequest.getHeader("User-Agent");
        log.info("(Location : CustomAuthenticationSuccessHandler) userAgent : " + userAgent);

        String userId = authentication.getName();

        //헤더 정보
        Enumeration<String> headerNames = httpServletRequest.getHeaderNames();
        String headers = "";
        while (headerNames.hasMoreElements()) {
            String name = headerNames.nextElement();
            headers += name + " : " + httpServletRequest.getHeader(name) + ", ";
        }
        details.setHeaders(headers);
        log.info("(Location : CustomAuthenticationSuccessHandler) 헤더 정보 : " + headers);

        //사용자 정보
        params.setString("userId", userId);
        UserVO userVO = this.userService.getUser(params.getParameterMap());
        log.info("(Location : CustomAuthenticationSuccessHandler) 유저 정보 : " + userVO);

        //userDetail 추가
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
        token.setDetails(details);

        HttpSession session = httpServletRequest.getSession();
        //어디에 사용?
        DefaultSavedRequest savedRequest = (DefaultSavedRequest) session.getAttribute("SPRING_SECURITY_SAVED_REQUEST");

        httpServletResponse.sendRedirect("/main.do");
    }
}
