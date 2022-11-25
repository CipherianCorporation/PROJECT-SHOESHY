package com.edu.graduationproject.controller.rest;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.graduationproject.entity.Visitor;
import com.edu.graduationproject.service.VisitorService;

@CrossOrigin("*")
@RestController
public class VisitorRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(VisitorRestController.class);

    @Autowired
    VisitorService visitorService;

    @Autowired
    private SessionRegistry sessionRegistry;

    @GetMapping("/rest/visitors")
    public ResponseEntity<List<Visitor>> getAll() {
        return ResponseEntity.ok(visitorService.findAll());
    }

    @GetMapping("/rest/visitors/count")
    public ResponseEntity<Long> getCount() {
        return ResponseEntity.ok(visitorService.getCount());
    }

    // count all active users from session
    // source: https://www.baeldung.com/spring-security-track-logged-in-users | 6. Using Sessionregistry
    @GetMapping("/rest/visitors/active-users-count")
    public ResponseEntity<Integer> getSessionCount() {
        List<String> tmp = sessionRegistry.getAllPrincipals().stream()
                .filter(u -> !sessionRegistry.getAllSessions(u, false).isEmpty())
                .map(Object::toString)
                .collect(Collectors.toList());
        return ResponseEntity.ok(tmp.size());
    }

}