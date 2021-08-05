package com.zemoso.tipster.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tipsterlogin")
public class LoginController {
    @GetMapping("/showLoginPage")
    public String showLoginPage(){
        return "adminLogin";
    }
    @GetMapping("/accessDeniedPage")
    public String showAccessDeniedPage(){
        return "accessDenied";
    }
}
