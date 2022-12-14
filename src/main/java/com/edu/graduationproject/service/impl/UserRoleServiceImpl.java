package com.edu.graduationproject.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.graduationproject.entity.User;
import com.edu.graduationproject.entity.UserRole;
import com.edu.graduationproject.model.IUserRoleCount;
import com.edu.graduationproject.repository.UserRepository;
import com.edu.graduationproject.repository.UserRoleRepository;
import com.edu.graduationproject.service.UserRoleService;

@Service
public class UserRoleServiceImpl implements UserRoleService {
    @Autowired
    UserRoleRepository userRoleRepo;

    @Autowired
    UserRepository userRepo;

    public List<UserRole> findRolesOfAdministrators() {
        List<User> accounts = userRepo.getAdministrators();
        return userRoleRepo.authoritiesOf(accounts);
    }

    @Override
    public List<UserRole> findAll() {
        return userRoleRepo.findAll();
    }

    @Override
    public UserRole create(UserRole auth) {
        return userRoleRepo.save(auth);
    }

    @Override
    public void delete(Integer id) {
        userRoleRepo.deleteById(id);
    }

    @Override
    public List<IUserRoleCount> getUserRoleCounts() {
        return userRoleRepo.getUserRoleCount();
    }

}
