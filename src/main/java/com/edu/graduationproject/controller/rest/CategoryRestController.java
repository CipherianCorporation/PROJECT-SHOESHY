package com.edu.graduationproject.controller.rest;

import com.edu.graduationproject.entity.Category;
import com.edu.graduationproject.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin("*")
@RestController
public class CategoryRestController {
    @Autowired
    CategoryService categoryService;

    @GetMapping("/rest/categories")
    public List<Category> getAll() {
        return categoryService.findAll();
    }

}
