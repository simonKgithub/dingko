package com.example.dingko.common.domain;

public enum ROLE {
    ROLE_USER("ROLE_USER"), ROLE_MEMBER("ROLE_MEMBER"), ROLE_ADMIN("ROLE_ADMIN");

    private final String role;

    ROLE(String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }
}
