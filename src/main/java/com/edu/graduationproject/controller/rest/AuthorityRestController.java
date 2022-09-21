package com.edu.graduationproject.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.edu.graduationproject.entity.UserRole;
import com.edu.graduationproject.service.UserRoleService;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
public class AuthorityRestController {
    @Autowired
    UserRoleService userRoleService;


}
