package com.edu.graduationproject.controller.rest;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.edu.graduationproject.service.RoleService;

@CrossOrigin("*")
@RestController
public class RoleRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoleRestController.class);

    @Autowired
    RoleService roleService;


}