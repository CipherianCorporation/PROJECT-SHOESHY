package com.edu.graduationproject.controller.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.graduationproject.entity.Role;
import com.edu.graduationproject.service.RoleService;

import java.util.List;

@CrossOrigin("*")
@RestController
public class RoleRestController {
    @Autowired
    RoleService roleService;


}