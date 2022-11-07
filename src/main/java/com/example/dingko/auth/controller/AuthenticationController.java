package com.example.dingko.auth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthenticationController {
    @RequestMapping("/login.do")
    public String forwardLoginPage(){
        System.out.println("request : /auth/login.do -> /auth/login.jsp");
        return "/auth/login";
    }
}
