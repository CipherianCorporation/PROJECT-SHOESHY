package com.edu.graduationproject.controller;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import com.edu.graduationproject.entity.User;
import com.edu.graduationproject.utils.Utility;
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

import java.io.UnsupportedEncodingException;
import java.util.Locale;
import java.util.Optional;

@Controller
public class ForgotPasswordController {

//    @Autowired
//    JavaMailSender mailSender;

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

    @PostMapping("/forgot_password")
    public String processForgotPassword(HttpServletRequest request, Model model){
        String email = request.getParameter("email");
        String token = RandomString.make(30);

        try {
            passService.updateResetPasswordToken(token, email);
            String resetPasswordLink = Utility.getSiteURL(request) + "/reset_password?token=" + token;
            sendMail(email,resetPasswordLink);
            model.addAttribute("message","Chúng tôi đã gửi liên kết để đặt lại đến email cảu bạn. Vui lòng kiểm tra!!!");
        }  catch (UnsupportedEncodingException | MessagingException  e) {
            model.addAttribute("error", "Error while sending email");
            throw new RuntimeException(e);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            throw new RuntimeException(e);
        }
        return null;
    }

    public void sendMail(String recipientEmail,String link) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message  = mailSender.createMineMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom("contact@gmail.com","hshs");
        helper.setTo(recipientEmail);
        String subject = "Đây là link để bạn lấy lại mật khẩu!!!";

        String content = "<p>Xin chảo</p>"
                +"<p>Bạn đã yêu cầu đặt lại mật khẩu của mình.</p>"
                +"<p>Nhấp vào liên kết bên dưới để dổi mật khậu của bạn.</p>"
                +"<p><a href="+link+">Đổi mật khẩu của tôi</p>"
                +"<p>Bỏ qua Email này nếu bạn nhớ mật khẩu của mình,"
                + "hoặc bạn chưa đưa ra yêu cầu.</p>";

        helper.setSubject(subject);
        helper.setText(content,true);
        mailSender.send(message);
    }

    @GetMapping("/account/resetpassword/form")
    public String showResetPasswordForm(@Param(value="token") String token,Model model){
        Optional<User> userOpt = passService.getByResetPasswordToken(token);
        model.addAttribute("token", token);
        if (userOpt.isPresent() == false){
            model.addAttribute("message","Mã không hợp lệ");
            return "message";
        }
        return "account/reset_password";
    }

    @PostMapping("/reset_password")
    public String processResetPassword(HttpServletRequest request, Model model){
        String token = request.getParameter("token");
        String password = request.getParameter("password");

        Optional<User> userOtp = passService.getByResetPasswordToken(token);
        model.addAttribute("message","Đặt lại mật khẩu của bạn");

        if (userOtp.isPresent() == false){
            model.addAttribute("message","Mã không hợp lệ");
            return "message";
        }else{
            passService.updatePassword(userOtp.get(),password);
            model.addAttribute("message","Bạn đã đổi mật khẩu thành công");
        }
        return "message";
    }
}
