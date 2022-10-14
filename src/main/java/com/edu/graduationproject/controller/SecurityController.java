package com.edu.graduationproject.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SecurityController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityController.class);

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        }
        return "forward:/";
    }

    @RequestMapping("/security/login/form")
    public String loginForm(Model model) {
        // model.addAttribute("message", "Please login!");
        return "security/login";
    }

    @RequestMapping("/security/login/success")
    public String loginSuccess(Model model) {
        model.addAttribute("message", "Login success!");
        return "security/login";
    }

    @RequestMapping("/security/login/error")
    public String loginError(Model model) {
        model.addAttribute("message", "Wrong credentials or account haven't activated yet!");
        return "security/login";
    }

    @RequestMapping("/security/unauthorized")
    public String unauthoried(Model model) {
        model.addAttribute("message", "Access denied!");
        return "security/login";
    }

    @RequestMapping("/security/logoff/success")
    public String logoffSuccess(Model model) {
        model.addAttribute("message", "You have log out!");
        return "security/login";
    }

    // OAuth2

    @RequestMapping("/oauth2/login/success")
    public String success(OAuth2AuthenticationToken oToken) {
        return "forward:/security/login/success";
    }
}
