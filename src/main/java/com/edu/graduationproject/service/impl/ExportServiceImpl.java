package com.edu.graduationproject.service.impl;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.graduationproject.entity.Order;
import com.edu.graduationproject.entity.Product;
import com.edu.graduationproject.entity.User;
import com.edu.graduationproject.service.ExportService;
import com.edu.graduationproject.service.OrderService;
import com.edu.graduationproject.service.ProductService;
import com.edu.graduationproject.service.UserService;
import com.edu.graduationproject.utils.CsvExporter;
import com.edu.graduationproject.utils.ExcelExporter;
import com.edu.graduationproject.utils.PdfExporter;

@Service
public class ExportServiceImpl implements ExportService {

    @Autowired
    ProductService productService;

    @Autowired
    UserService userService;

    @Autowired
    OrderService orderService;

    @Override
    public void exportExcel(Object entity, String fileAndSheetName, HttpServletResponse response) throws IOException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void exportPDF(Object entity, String fileAndTitleName, HttpServletResponse response) throws IOException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void exportCSV(Object entity, String fileAndTitleName, HttpServletResponse response) throws IOException {
        // TODO Auto-generated method stub
        
    }

   
}
