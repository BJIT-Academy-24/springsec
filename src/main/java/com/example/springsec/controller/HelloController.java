package com.example.springsec.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping("/")
    public String home(HttpServletRequest request) {
        return "This is the Session ID: "+ request.getSession().getId();
    }
}
