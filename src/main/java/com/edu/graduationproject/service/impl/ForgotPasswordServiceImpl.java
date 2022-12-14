package com.edu.graduationproject.service.impl;

import com.edu.graduationproject.service.UserService;
import com.edu.graduationproject.utils.Utility;
import java.io.UnsupportedEncodingException;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.edu.graduationproject.entity.User;
import com.edu.graduationproject.model.MailInfo;
import com.edu.graduationproject.repository.UserRepository;
import com.edu.graduationproject.service.ForgotPasswordService;
import com.edu.graduationproject.service.MailerService;
import com.edu.graduationproject.service.UserService;
import com.edu.graduationproject.utils.CommonUtils;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Optional;

@Service
public class ForgotPasswordServiceImpl implements ForgotPasswordService {

    @Autowired
    UserRepository repo;

    @Autowired
    MailerService mailerService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserService userService;

    @Autowired
    MailerService mailer;

    @Override
    public void updateResetPasswordToken(String token, String email) throws Exception {
        try {
            Optional<User> userOpt = repo.findByEmail(email);
            if (userOpt.isPresent()) {
                User user = userOpt.get();
                user.setReset_pwd_token(token);
                repo.save(user);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<User> getByResetPasswordToken(String token) {
        return repo.findByResetPasswordToken(token);
    }

    @Override
    public void updatePassword(User user, String newPass) {
        // TODO Auto-generated method stub
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodePassword = passwordEncoder.encode(newPass);
        user.setPassword(encodePassword);
        user.setReset_pwd_token(null);
        repo.save(user);
    }

    @Override
    public String sendEMailForgotPassword(String token, String email, HttpServletRequest request) {
        try {
            Optional<User> userOtp = userService.findByEmail(email);
            if (!userOtp.isPresent()) {
                return "T??i kho???n c???a b???n kh??ng t???n t???i";
            } else {
                this.updateResetPasswordToken(token, email);
                String resetPasswordLink = CommonUtils.getSiteURL(request) + "/reset_password?token=" + token;
                this.sendEmail(email, resetPasswordLink);
                return "Ch??ng t??i ???? g???i li??n k???t ????? ?????t l???i ?????n email c???u b???n. Vui l??ng ki???m tra!!!";
            }

        } catch (UnsupportedEncodingException | MessagingException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String resetPassword(String token, String password) {
        Optional<User> userOtp = this.getByResetPasswordToken(token);
        if (!userOtp.isPresent()) {
            return "M?? kh??ng h???p l???";
        } else {
            this.updatePassword(userOtp.get(), password);
            return "B???n ???? ?????i m???t kh???u th??nh c??ng";
        }
    }

    @Override
    public void sendEmail(String recipientEmail, String link) throws MessagingException, UnsupportedEncodingException {
        MailInfo mailInfo = new MailInfo();
        mailInfo.setTo(recipientEmail);
        mailInfo.setSubject("ShoeShy - Link ???y l???i m???t kh???u!!!");
        String content = "<p>Xin ch??o</p>"
                + "<p>B???n ???? y??u c???u ?????t l???i m???t kh???u c???a m??nh.</p>"
                + "<p>Nh???p v??o li??n k???t b??n d?????i ????? d???i m???t kh???u c???a b???n.</p>"
                + "<p><a href=\"" + link + "\">?????i m???t kh???u c???a t??i</p>"
                + "<p>B??? qua Email n??y n???u b???n nh??? m???t kh???u c???a m??nh,"
                + "ho???c b???n ch??a ????a ra y??u c???u.</p>";
        mailInfo.setBody(content);
        mailerService.queue(mailInfo);
    }

}