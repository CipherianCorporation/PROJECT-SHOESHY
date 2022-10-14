package com.edu.graduationproject.service.impl;

import com.edu.graduationproject.service.UserService;
import com.edu.graduationproject.utils.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.edu.graduationproject.entity.User;
import com.edu.graduationproject.repository.UserRepository;
import com.edu.graduationproject.service.ForgotPasswordService;
import com.edu.graduationproject.service.MailerService;

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

    @Override
    public String sendMailForgotPassword(String token, String email, HttpServletRequest request) {
        try {
            Optional<User> userOtp = userService.findByEmail(email);
            if (!userOtp.isPresent()){
                return "Tài khoản của bạn không tồn tại";
            }else {
                this.updateResetPasswordToken(token, email);
                String resetPasswordLink = Utility.getSiteURL(request) + "/reset_password?token=" + token;
                sendMail(email,resetPasswordLink);
                return "Chúng tôi đã gửi liên kết để đặt lại đến email cảu bạn. Vui lòng kiểm tra!!!";
            }

        }  catch (UnsupportedEncodingException | MessagingException  e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String resetPassWord(String token, String password) {
        Optional<User> userOtp = this.getByResetPasswordToken(token);
        if (!userOtp.isPresent()){
            return "Mã không hợp lệ";
        }else{
            this.updatePassword(userOtp.get(),password);
            return "Bạn đã đổi mật khẩu thành công";
        }
    }


    public void sendMail(String recipientEmail,String link) throws MessagingException, UnsupportedEncodingException {
        String subject = "Đây là link để bạn lấy lại mật khẩu!!!";

        String content = "<p>Xin chào</p>"
                +"<p>Bạn đã yêu cầu đặt lại mật khẩu của mình.</p>"
                +"<p>Nhấp vào liên kết bên dưới để dổi mật khậu của bạn.</p>"
                +"<p><a href=\""+link+"\">Đổi mật khẩu của tôi</p>"
                +"<p>Bỏ qua Email này nếu bạn nhớ mật khẩu của mình,"
                + "hoặc bạn chưa đưa ra yêu cầu.</p>";

        mailer.send(recipientEmail,subject,content);
    }


}