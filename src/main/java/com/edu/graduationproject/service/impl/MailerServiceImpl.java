package com.edu.graduationproject.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.edu.graduationproject.model.MailInfo;
import com.edu.graduationproject.service.MailerService;

@Service
public class MailerServiceImpl implements MailerService {
    @Autowired
    JavaMailSender sender;

    List<MailInfo> list = new ArrayList<>();

    @Override
    public void send(MailInfo mail) throws MessagingException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void send(String to, String subject, String body) throws MessagingException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void queue(MailInfo mail) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void queue(String to, String subject, String body) {
        // TODO Auto-generated method stub
        
    }

}
