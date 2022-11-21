package com.example.dingko.auth.security;

import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
public class CustomAuthenticationLogoutSuccessHandler implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        log.info("(Location : CustomAuthenticationLogoutSuccessHandler) 로그아웃 핸들러");
        /*세션삭제 : logoutHandler에서 이미 삭제를 했으므로 설정할 필요 없음
        if(authentication != null && authentication.getDetails() != null){
            try {
                httpServletRequest.getSession().invalidate();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
         */
        //.do?logout을 달아줘서 model에 logout String을 보내기
        String redirectUrl = "/auth/login.do?logout=true";
        log.info("로그아웃 url : /auth/login.do?logout=true");
        // /auth/login.do 페이지로 리다이렉트
        httpServletResponse.sendRedirect(redirectUrl);
    }
}
