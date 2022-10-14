package com.edu.graduationproject.controller;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import com.edu.graduationproject.entity.User;
import com.edu.graduationproject.utils.Utility;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.edu.graduationproject.repository.UserRepository;
import com.edu.graduationproject.service.ForgotPasswordService;
import com.edu.graduationproject.service.MailerService;

import net.bytebuddy.utility.RandomString;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.UnsupportedEncodingException;
import java.util.Locale;
import java.util.Optional;

@Controller
public class ForgotPasswordController {

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

    @PostMapping("/account/forgotpassword")
    public String processForgotPassword(HttpServletRequest request, Model model) throws MessagingException{
        String email = request.getParameter("email");
        String token = RandomString.make(30);
        String message = passService.sendMailForgotPassword(token,email,request);
        model.addAttribute("message",message);
        return "account/forgot_password";
    }



    @GetMapping("/reset_password")
    public String showResetPasswordForm(@RequestParam("token") String token, Model model){
        Optional<User> userOpt = passService.getByResetPasswordToken(token);
        model.addAttribute("token", token);
        if (userOpt.isPresent() == false){
            model.addAttribute("message","Mã không hợp lệ");
            return "account/reset_password_form";
        }
        return "account/reset_password_form";
    }

    @PostMapping("/reset_password")
    public String processResetPassword(HttpServletRequest request, Model model){
        String token = request.getParameter("token");
        String password = request.getParameter("password");

//        Optional<User> userOtp = passService.getByResetPasswordToken(token);
//        model.addAttribute("message","Đặt lại mật khẩu của bạn");
//
//        if (!userOtp.isPresent()){
//            model.addAttribute("message","Mã không hợp lệ");
//            return "account/reset_password_form";
//        }else{
//
//            passService.updatePassword(userOtp.get(),password);
//            model.addAttribute("message","Bạn đã đổi mật khẩu thành công");
//        }
        String message = passService.resetPassWord(token,password);
        model.addAttribute("message",message);
        return "account/reset_password_form";
    }
}
