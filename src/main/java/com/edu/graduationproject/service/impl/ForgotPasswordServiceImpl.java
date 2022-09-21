package com.edu.graduationproject.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.edu.graduationproject.entity.User;
import com.edu.graduationproject.exception.ResourceNotFoundException;
import com.edu.graduationproject.model.MailInfo;
import com.edu.graduationproject.repository.UserRepository;
import com.edu.graduationproject.service.ForgotPasswordService;
import com.edu.graduationproject.service.MailerService;

@Service
public class ForgotPasswordServiceImpl implements ForgotPasswordService {

    @Autowired
    UserRepository repo;

    @Autowired
    MailerService mailerService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void updateResetPasswordToken(String token, String email) throws Exception {
        // TODO Auto-generated method stub
        
    }

    @Override
    public User getByResetPasswordToken(String token) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void updatePassword(User account, String newPass) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void sendEmail(String recipientEmail, String link) {
        // TODO Auto-generated method stub
        
    }



}