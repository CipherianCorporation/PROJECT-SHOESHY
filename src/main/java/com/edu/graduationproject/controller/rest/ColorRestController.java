package com.edu.graduationproject.controller.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.graduationproject.entity.Color;
import com.edu.graduationproject.service.ColorService;

@CrossOrigin("*")
@RestController
public class ColorRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ColorRestController.class);

    @Autowired
    ColorService colorService;

    @GetMapping("/rest/colors")
    public List<Color> getAll() {
        return colorService.findAll();
    }

}
