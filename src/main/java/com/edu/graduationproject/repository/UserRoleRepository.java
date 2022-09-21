package com.edu.graduationproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.edu.graduationproject.entity.User;
import com.edu.graduationproject.entity.UserRole;
import com.edu.graduationproject.model.UserRoleCount;

public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {

}