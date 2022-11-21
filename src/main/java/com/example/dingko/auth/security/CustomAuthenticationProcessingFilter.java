package com.example.dingko.auth.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAuthenticationProcessingFilter extends AbstractAuthenticationProcessingFilter {
    /**로그인 아이디 파라미터 이름*/
    public static final String SECURITY_FORM_USERNAME_KEY = "username";
    /**로그인 비밀번호 파라미터 이름*/
    public static final String SECURITY_FORM_PASSWORD_KEY = "password";
    /**default filter processes url*/
    public static final String DEFAULT_FILTER_PROCESSES_URL = "/auth/login.do";

    public CustomAuthenticationProcessingFilter(){
        super(DEFAULT_FILTER_PROCESSES_URL);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {
        AbstractAuthenticationToken authRequest = createCustomAuthenticationToken(httpServletRequest);
        setDetails(httpServletRequest, authRequest);

        return this.getAuthenticationManager().authenticate(authRequest);
    }

    /**인증정보 토큰 생성*/
    private AbstractAuthenticationToken createCustomAuthenticationToken(HttpServletRequest httpServletRequest){
        String username = obtainUsername(httpServletRequest);
        String password = obtainPassword(httpServletRequest);
        String accessIp = httpServletRequest.getRemoteAddr();

        //인증정보 토큰 생성
        return new CustomAuthenticationToken(username, password, accessIp);
    }

    protected String obtainUsername(HttpServletRequest httpServletRequest){
        return httpServletRequest.getParameter(SECURITY_FORM_USERNAME_KEY);
    }
    protected String obtainPassword(HttpServletRequest httpServletRequest){
        return httpServletRequest.getParameter(SECURITY_FORM_PASSWORD_KEY);
    }

    /**
     * Provided so that subclasses may configure what is put into the authentication request's details property.
     *
     * @param httpServletRequest that an authentication request is being created for
     * @param authRequest the authentication request object that should have its details set
     * */
    protected void setDetails(HttpServletRequest httpServletRequest, AbstractAuthenticationToken authRequest){
        authRequest.setDetails(authenticationDetailsSource.buildDetails(httpServletRequest));
    }
}
