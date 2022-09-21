package com.edu.graduationproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edu.graduationproject.entity.Role;

public interface RoleRepository extends JpaRepository<Role, String>{
    
}
