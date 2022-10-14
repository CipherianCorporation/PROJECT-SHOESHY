package com.edu.graduationproject.controller.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.edu.graduationproject.entity.User;
import com.edu.graduationproject.service.UserService;

@CrossOrigin("*")
@RestController
public class UserRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserRestController.class);

    @Autowired
    UserService userService;

    @GetMapping("/rest/users/principal")
    public ResponseEntity<Object> getAuthenticatedUser(Authentication authentication) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            Optional<User> loggedinUser = userService.findByUsername(authentication.getName());
            map.put("id", loggedinUser.get().getId());
            map.put("username", loggedinUser.get().getUsername());
            map.put("phone", loggedinUser.get().getPhone());
            map.put("email", loggedinUser.get().getEmail());
            map.put("image_url", loggedinUser.get().getImage_url());
            return ResponseEntity.ok(map);
        } catch (NoSuchElementException e) {
            LOGGER.error("there is error when return the user principal info", e);
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/rest/users")
    public ResponseEntity<List<User>> getAccounts(@RequestParam("admin") Optional<Boolean> admin) {
        if (admin.orElse(false)) {
            return ResponseEntity.ok(userService.getAdministators());
        }
        return ResponseEntity.ok(userService.findAll());
    }

}