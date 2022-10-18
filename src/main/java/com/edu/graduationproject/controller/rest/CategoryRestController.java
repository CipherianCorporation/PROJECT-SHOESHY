package com.edu.graduationproject.controller.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.graduationproject.entity.Category;
import com.edu.graduationproject.service.CategoryService;

@CrossOrigin("*")
@RestController
public class CategoryRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryRestController.class);

    @Autowired
    CategoryService categoryService;

    @GetMapping("/rest/categories")
    public List<Category> getAll() {
        return categoryService.findAll();
    }

}
