package com.edu.graduationproject.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.graduationproject.model.UserRoleCount;
import com.edu.graduationproject.service.UserRoleService;

@CrossOrigin("*")
@RestController
public class UserRoleRestController {
    @Autowired
    UserRoleService service;


}
