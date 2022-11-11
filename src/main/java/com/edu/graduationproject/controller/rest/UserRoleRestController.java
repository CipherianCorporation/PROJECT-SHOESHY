package com.edu.graduationproject.controller.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.graduationproject.model.UserRoleCount;
import com.edu.graduationproject.service.UserRoleService;

@CrossOrigin("*")
@RestController
public class UserRoleRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserRoleRestController.class);

    @Autowired
    UserRoleService service;

    @GetMapping("/rest/user-roles/count-users-by-role")
    public ResponseEntity<List<UserRoleCount>> getUserRoleCounts() {
        return ResponseEntity.ok(service.getUserRoleCounts());
    }
}
