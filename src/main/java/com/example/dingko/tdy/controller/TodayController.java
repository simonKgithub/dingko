package com.example.dingko.tdy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tdy")
public class TodayController {
    @GetMapping("/today.do")
    public void todayView(){}
}
