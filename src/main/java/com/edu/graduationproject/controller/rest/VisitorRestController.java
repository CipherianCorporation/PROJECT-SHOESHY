package com.edu.graduationproject.controller.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/rest/visitors")
    public ResponseEntity<List<Visitor>> getAll() {
        return ResponseEntity.ok(visitorService.findAll());
    }

    @GetMapping("/rest/visitors/count")
    public ResponseEntity<Long> getCount() {
        return ResponseEntity.ok(visitorService.getCount());
    }

}