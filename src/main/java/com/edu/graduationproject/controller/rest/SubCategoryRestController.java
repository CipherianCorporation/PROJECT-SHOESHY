package com.edu.graduationproject.controller.rest;
import com.edu.graduationproject.entity.Category;
import com.edu.graduationproject.entity.SubCategory;
import com.edu.graduationproject.service.CategoryService;
import com.edu.graduationproject.service.SubCategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin("*")
@RestController
public class SubCategoryRestController {
    @Autowired
    SubCategoryService service;

    @GetMapping("/rest/sub-categories")
    public List<SubCategory> getAll() {
        return service.findAll();
    }

    @GetMapping("/rest/sub-categories/{category_id}")
    public List<SubCategory> getSubCatesByCatesId(@PathVariable Integer category_id) {
        return service.findAllByCatesId(category_id);
    }
}
