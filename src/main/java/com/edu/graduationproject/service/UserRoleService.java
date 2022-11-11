package com.edu.graduationproject.service;

import java.util.List;
import java.util.Map;

import com.edu.graduationproject.entity.UserRole;
import com.edu.graduationproject.model.UserRoleCount;

public interface UserRoleService {
    List<UserRole> findRolesOfAdministrators();

    List<UserRoleCount> getUserRoleCounts();

    List<UserRole> findAll();

    UserRole create(UserRole auth);

    void delete(Integer id);
}
