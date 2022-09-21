package com.edu.graduationproject.service.impl;

import com.edu.graduationproject.entity.User;
import com.edu.graduationproject.entity.UserRole;
import com.edu.graduationproject.model.UserRoleCount;
import com.edu.graduationproject.repository.UserRepository;
import com.edu.graduationproject.repository.UserRoleRepository;
import com.edu.graduationproject.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleServiceImpl implements UserRoleService {
    @Autowired
    UserRoleRepository userRoleRepo;

    @Autowired
    UserRepository userRepo;

    @Override
    public List<UserRole> findRolesOfAdministrators() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<UserRoleCount> getUserRoleCounts() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<UserRole> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public UserRole create(UserRole auth) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void delete(Integer id) {
        // TODO Auto-generated method stub
        
    }


}
