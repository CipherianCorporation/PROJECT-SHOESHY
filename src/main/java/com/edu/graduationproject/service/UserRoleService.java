package com.edu.graduationproject.service;

import java.util.List;
import java.util.Map;

import com.edu.graduationproject.entity.UserRole;
import com.edu.graduationproject.model.IUserRoleCount;

public interface UserRoleService {
    List<UserRole> findRolesOfAdministrators();

    List<IUserRoleCount> getUserRoleCounts();

    List<UserRole> findAll();

    UserRole create(UserRole auth);

    void delete(Integer id);
}
