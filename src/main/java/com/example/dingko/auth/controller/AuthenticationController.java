package com.example.dingko.auth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthenticationController {
    @RequestMapping("/login.do")
    public String forwardLoginPage(String logout, String error, Model model){
        System.out.println("request : /auth/login.do -> /auth/login.jsp");

        if(error != null){
            model.addAttribute("error", "아이디 혹은 비밀번호 오류입니다.");
        }
        if(logout != null){
            model.addAttribute("logout", "로그아웃이 완료되었습니다.");
        }

        return "/auth/login";
    }
}
