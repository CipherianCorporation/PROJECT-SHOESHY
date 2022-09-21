package com.edu.graduationproject.controller.rest;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.graduationproject.entity.Order;
import com.edu.graduationproject.entity.Product;
import com.edu.graduationproject.entity.User;
import com.edu.graduationproject.service.ExportService;
import com.edu.graduationproject.service.ProductService;

@CrossOrigin("*")
@RestController
public class ExportRestController {

    @Autowired
    ProductService productService;

    @Autowired
    ExportService exportService;


}
