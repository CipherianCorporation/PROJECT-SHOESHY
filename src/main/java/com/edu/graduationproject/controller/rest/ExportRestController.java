package com.edu.graduationproject.controller.rest;

import com.edu.graduationproject.entity.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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

    //Print excell
    @GetMapping("/admin/orders/export-excel")
    public ResponseEntity<String> exportOrdersExcel(HttpServletResponse response) {
        try {
            exportService.exportExcel(new Order(), "orders", response);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Print pdf
    @GetMapping("/admin/orders/export-pdf")
    public ResponseEntity<String> exportOrdersPdf(HttpServletResponse response) {
        try {
            exportService.exportPDF(new Order(), "orders", response);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

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
