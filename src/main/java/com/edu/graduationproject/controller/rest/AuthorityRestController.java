package com.edu.graduationproject.controller.rest;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.edu.graduationproject.entity.UserRole;
import com.edu.graduationproject.service.UserRoleService;

@CrossOrigin("*")
@RestController
public class AuthorityRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthorityRestController.class);

    @Autowired
    UserRoleService userRoleService;
    

}
