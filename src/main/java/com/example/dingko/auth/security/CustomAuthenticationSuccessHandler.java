package com.example.dingko.auth.security;

import com.example.dingko.common.domain.UserVO;
import com.example.dingko.common.service.UserService;
import com.example.dingko.common.utils.HashParameterMap;
import com.example.dingko.common.utils.StringExtUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.DefaultSavedRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;

@Log4j2
public class CustomAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    @Autowired
    private UserService userService;

    /**CustomAuthenticationProvider에서 토큰을 이쪽으로 넘겨줘서 CustomLoginSuccessHandler 역할을 해주는 것 같음*/
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        HashParameterMap params = new HashParameterMap();

        //접근 IP 조회
        String userIpAddress = httpServletRequest.getHeader("X-FORWARDED-FOR");
        if (StringExtUtils.isEmpty(userIpAddress)) {
            userIpAddress = httpServletRequest.getRemoteAddr();
            log.info("(Location : CustomAuthenticationSuccessHandler) 접근 IP : " + userIpAddress);
        }
        String userAgent = httpServletRequest.getHeader("User-Agent");
        String userId = authentication.getName();

        //헤더 정보
        Enumeration<String> headerNames = httpServletRequest.getHeaderNames();
        String headers = "";
        while (headerNames.hasMoreElements()) {
            String name = headerNames.nextElement();
            headers += name + " : " + httpServletRequest.getHeader(name) + ", ";
        }
        log.info("(Location : CustomAuthenticationSuccessHandler) 헤더 정보 : " + headers);

        //사용자 정보
        params.setString("userId", userId);
        UserVO userVO = this.userService.getUser(params.getParameterMap());
        log.info("(Location : CustomAuthenticationSuccessHandler) 유저 정보 : " + userVO);

        //userDetail 추가
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
        token.setDetails(userVO);

        HttpSession session = httpServletRequest.getSession();
        DefaultSavedRequest savedRequest = (DefaultSavedRequest) session.getAttribute("SPRING_SECURITY_SAVED_REQUEST");

        httpServletResponse.sendRedirect("/main.do");
    }
}
