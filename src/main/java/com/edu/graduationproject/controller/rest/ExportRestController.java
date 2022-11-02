package com.edu.graduationproject.controller.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.edu.graduationproject.entity.Product;
import com.edu.graduationproject.service.ExportService;
import com.edu.graduationproject.service.ProductService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@CrossOrigin("*")
@RestController
public class ExportRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExportRestController.class);

    @Autowired
    ProductService productService;

    @Autowired
    ExportService exportService;


    //Print csv
    @GetMapping("/admin/orders/export-csv")
    public ResponseEntity<String> exportOrdersCsv(HttpServletResponse response) {
        try {
            exportService.exportCSV(new Order(), "orders", response);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
