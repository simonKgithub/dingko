package com.example.dingko.common.controller;

import com.example.dingko.common.domain.UserVO;
import com.example.dingko.common.service.UserService;
import com.example.dingko.common.utils.CommonUtil;
import com.example.dingko.common.utils.HashParameterMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/join")
public class JoinMemberController {
    @Autowired
    UserService userService;

    @GetMapping("/member.do")
    public void joinMemberPage(){}

    @PostMapping("/member.do")
    public String joinMemberProcess(UserVO userVO, RedirectAttributes redirectAttributes){
        boolean successProcess = userService.insertUser(userVO);
        redirectAttributes.addFlashAttribute("joinUserId", userVO.getUserId());
//        //캐시삭제
//        response.setHeader("Pragma", "No-Cache");//캐시에 저장된 것을 안불러옴
//        response.setHeader("Cache-Control", "No-Cache");//캐시 없애기
//        response.setDateHeader("Expires", 0);
        return "redirect:/auth/login.do";
    }

    @PostMapping("/getMember.do")
    @ResponseBody
    public UserVO getMember(HttpServletRequest request){
        HashParameterMap params = CommonUtil.setRequestParameterMap();
        return userService.getUser(params.getParameterMap());
    }
}
