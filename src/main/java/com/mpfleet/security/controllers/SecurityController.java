package com.mpfleet.security.controllers;

import com.mpfleet.interceptor.annotations.PageTitle;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SecurityController {

    @RequestMapping("/login")
    @PageTitle("Login")
    public String login(){
        return "/security/login";
    }

    @GetMapping("/register")
    @PageTitle("Register")
    public String register(){
        return "/security/register";
    }

    @RequestMapping("/index")
    @PageTitle("Index")
    public String index(){
        return "index";
    }

    @GetMapping("/accessDenied")
    @PageTitle("Access Denied")
    public String accessDenied(){
        return "accessDenied";
    }
}
