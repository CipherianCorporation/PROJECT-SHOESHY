package com.edu.graduationproject.service.impl;

import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.edu.graduationproject.entity.Role;
import com.edu.graduationproject.entity.User;
import com.edu.graduationproject.entity.UserRole;
import com.edu.graduationproject.model.AuthProvider;
import com.edu.graduationproject.model.MailInfo;
import com.edu.graduationproject.repository.RoleRepository;
import com.edu.graduationproject.repository.UserRepository;
import com.edu.graduationproject.repository.UserRoleRepository;
import com.edu.graduationproject.service.MailerService;
import com.edu.graduationproject.service.UserService;

import net.bytebuddy.utility.RandomString;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepo;

    @Autowired
    RoleRepository roleRepo;

    @Autowired
    UserRoleRepository userRoleRepo;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    MailerService mailer;

    @Override
    public Optional<User> findById(Integer id) {
        return userRepo.findById(id);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    @Override
    public User save(User user) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public User update(User user) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deleteByUsername(String id) {
        // TODO Auto-generated method stub

    }

    @Override
    public List<User> findAll() {
        return userRepo.findAll();
    }

    @Override
    public List<User> getAdministators() {
        return userRepo.getAdministrators();
    }

    @Override
    public void register(User user, String url) throws MessagingException {
        // TODO Auto-generated method stub

    }

    @Override
    public void sendVerifyEmail(User user, String url) throws MessagingException {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean verify(String verifyCode) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void processOAuthPostLogin(String username, String email, String image, String oauth2ClientName) {
        // TODO Auto-generated method stub

    }

    @Override
    public void updateAuthenticationTypeOAuth(String username, String oauth2ClientName) {
        // TODO Auto-generated method stub

    }

    @Override
    public void updateAuthenticationTypeDB(String username, String oauth2ClientName) {
        // TODO Auto-generated method stub

    }

}
