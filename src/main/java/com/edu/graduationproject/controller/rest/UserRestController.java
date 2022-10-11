package com.edu.graduationproject.controller.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.edu.graduationproject.entity.User;
import com.edu.graduationproject.entity.UserRole;
import com.edu.graduationproject.exception.ResourceNotFoundException;
import com.edu.graduationproject.model.AuthProvider;
import com.edu.graduationproject.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@CrossOrigin("*")
@RestController
public class UserRestController {
    @Autowired
    UserService userService;

    @GetMapping("/rest/users")
    public ResponseEntity<List<User>> getAccounts(@RequestParam("admin") Optional<Boolean> admin) {
        if (admin.orElse(false)) {
            return ResponseEntity.ok(userService.getAdministators());
        }
        return ResponseEntity.ok(userService.findAll());
    }
    

    

}