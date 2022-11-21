package com.example.dingko.common.controller;

import com.example.dingko.common.utils.CommonUtil;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
    @RequestMapping("/main.do")
    public void main(){
//        UserDetails ud = CommonUtil.getSessionUserInfo();
//        String userId = ud.getUsername();
    }
}
