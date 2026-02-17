package com.example.gif.auth.domain.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/admin/login")
    public String adminLogin(HttpServletRequest request) {
        request.getSession().setAttribute("loginType", "admin");
        return "redirect:/oauth2/authorization/google";
    }

    @GetMapping("client/login")
    public String clientLogin(HttpServletRequest request) {
        request.getSession().setAttribute("loginType", "client");
        return "redirect:/oauth2/authorization/google";
    }
}
