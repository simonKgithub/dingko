package com.example.dingko.common.domain;

import lombok.Data;
import org.apache.ibatis.type.Alias;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@Alias("userVO")
public class UserVO implements UserDetails {
    private String userId;
    private String userPw;
    private String userNm;
    private String userPhoneNum;
    private String userEmail;
    private String userYn;
    private String fstRgttId;
    private String fstRgstDt;
    private String fnlAmdId;
    private String fnlMofDt;
    /**로그인 IP*/
    private String loginIpAddress;
    /**request헤더문자열*/
    private String headers;
    /**인증정보*/
    private String auth;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String roleGrant = auth;

        GrantedAuthority myGrant = new SimpleGrantedAuthority(roleGrant);

        List<GrantedAuthority> authorities = new ArrayList<>();

        authorities.add(myGrant);

        return authorities;
    }

    @Override
    public String getPassword() {
        return this.userPw;
    }

    @Override
    public String getUsername() {
        return this.userId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
