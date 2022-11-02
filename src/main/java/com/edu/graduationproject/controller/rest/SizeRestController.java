package com.edu.graduationproject.controller.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.graduationproject.entity.Size;
import com.edu.graduationproject.service.SizeService;

@CrossOrigin("*")
@RestController
public class SizeRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SizeRestController.class);

    @Autowired
    SizeService sizeService;

    @GetMapping("/rest/sizes")
    public List<Size> getAll() {
        return sizeService.findAll();
    }

}
