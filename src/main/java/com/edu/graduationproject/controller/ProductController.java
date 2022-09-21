package com.edu.graduationproject.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.edu.graduationproject.entity.Product;
import com.edu.graduationproject.service.ProductService;

@Controller
public class ProductController {

    @Autowired
    ProductService service;


}