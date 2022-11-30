package com.example.dingko.plan.controller;

import com.example.dingko.plan.service.PlannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/plan")
public class PlannerController {
    @Autowired
    PlannerService plannerService;

    @GetMapping("/calendar.do")
    public void calendarView(Model model){
        model.addAttribute("calendar", this.plannerService.getCalendar());
    }
}
