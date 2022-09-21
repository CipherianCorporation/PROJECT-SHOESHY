package com.edu.graduationproject.service.impl;

import com.edu.graduationproject.entity.Role;
import com.edu.graduationproject.repository.RoleRepository;

import com.edu.graduationproject.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleRepository repo;


    @Override
    public List<Role> findAll() {
        return repo.findAll();
    }
}
