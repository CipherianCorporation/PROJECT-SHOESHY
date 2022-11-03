package com.edu.graduationproject.controller.rest;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.graduationproject.entity.User;
import com.edu.graduationproject.service.ExportService;
import com.edu.graduationproject.service.ProductService;

@CrossOrigin("*")
@RestController
public class ExportRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExportRestController.class);

    @Autowired
    ProductService productService;

    @Autowired
    ExportService exportService;
    
    @GetMapping("/admin/users/export-excel")
    public ResponseEntity<String> exportUsersExcel(HttpServletResponse response) {
        try {
            exportService.exportExcel(new User(), "users", response);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/admin/users/export-pdf")
    public ResponseEntity<String> exportUsersPdf(HttpServletResponse response) {
        try {
            exportService.exportPDF(new User(), "users", response);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/admin/users/export-csv")
    public ResponseEntity<String> exportUsersCsv(HttpServletResponse response) {
        try {
            exportService.exportCSV(new User(), "users", response);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
