package com.example.dingko.auth.security;

import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
public class CustomAuthenticationLogoutSuccessHandler implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        log.info("(Location : CustomAuthenticationLogoutSuccessHandler) 로그아웃 핸들러");
        String redirectUrl = "/auth/login.do";
        log.info("로그아웃 url : /auth/login.do");
        httpServletResponse.sendRedirect(redirectUrl);
    }
}
