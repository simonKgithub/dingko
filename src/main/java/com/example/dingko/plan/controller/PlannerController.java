package com.example.dingko.plan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/plan")
public class PlannerController {
    @GetMapping("/calendar.do")
    public void calendarView(){}
}
