package com.example.dingko.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@Controller
public class IndexController {
    @RequestMapping(value={"/", "/index.do"})
    public String viewIndex() {
        System.out.println("request : {'/', 'index.do'} -> redirect:/auth/login.do ");

        return "redirect:/auth/login.do";
    }
}