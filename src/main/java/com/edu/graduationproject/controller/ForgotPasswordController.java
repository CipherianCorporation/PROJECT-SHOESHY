package com.edu.graduationproject.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.edu.graduationproject.repository.UserRepository;
import com.edu.graduationproject.service.ForgotPasswordService;
import com.edu.graduationproject.service.MailerService;

@Controller
public class ForgotPasswordController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ForgotPasswordController.class);

    @Autowired
    UserRepository repo;

    @Autowired
    MailerService mailer;

    @Autowired
    ForgotPasswordService passService;

    @GetMapping("/account/forgotpassword/form")
    public String form() {
        return "account/forgot_password";
    }
}
