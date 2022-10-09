package com.edu.graduationproject.service;

import com.edu.graduationproject.entity.User;

import java.util.Optional;

public interface ForgotPasswordService {
    void updateResetPasswordToken(String token, String email) throws Exception;

    Optional<User> getByResetPasswordToken(String token);

    void updatePassword(User account, String newPass);

    void sendEmail(String recipientEmail, String link);
}
