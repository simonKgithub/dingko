package com.example.dingko.auth.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;

public class CustomAuthenticationToken extends AbstractAuthenticationToken {
    /**접속아이피*/
    private String accessId;
    /**로그인 아이디*/
    private Object principal;
    /**비밀번호*/
    private Object credentials;

    /**생성자
     * @param principal 로그인아이디
     * @param credentials 비밀번호
     * @param accessIp 접근 아이피
     *  accessType 접근 유형
     * */
    public CustomAuthenticationToken(Object principal, Object credentials, String accessIp){
        super(null);
        this.principal = principal;
        this.credentials = credentials;
        this.accessId = accessIp;

        setAuthenticated(false);
    }

    public String getAccessId(){
        return accessId;
    }

    @Override
    public Object getCredentials() {
        return credentials;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }

    public void setAccessId(String accessId) {
        this.accessId = accessId;
    }

    public void setPrincipal(Object principal) {
        this.principal = principal;
    }

    public void setCredentials(Object credentials) {
        this.credentials = credentials;
    }

    public void setAuthenticated(boolean isAuthenticated){
        if(isAuthenticated){
            throw new IllegalArgumentException("Cannot set this token to trusted - use constructor containing GrantedAuthority[]s instead");
        }
        super.setAuthenticated(false);
    }
}
