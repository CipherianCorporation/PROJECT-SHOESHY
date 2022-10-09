package com.edu.graduationproject.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.edu.graduationproject.entity.User;
import com.edu.graduationproject.repository.UserRepository;
import com.edu.graduationproject.service.ForgotPasswordService;
import com.edu.graduationproject.service.MailerService;

import java.util.Optional;

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
        try {
            Optional<User> userOpt = repo.findByEmail(email);
            if (userOpt.isPresent()){
                User user = userOpt.get();
                user.setReset_pwd_token(token);
                repo.save(user);
            }
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public Optional<User> getByResetPasswordToken(String token) {
        // TODO Auto-generated method stub
        return repo.findByResestPasswordToken(token);
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
    public void sendEmail(String recipientEmail, String link) {
        // TODO Auto-generated method stub
        
    }



}