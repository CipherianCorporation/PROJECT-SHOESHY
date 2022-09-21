package com.edu.graduationproject.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.edu.graduationproject.service.OrderService;
import com.fasterxml.jackson.core.JsonProcessingException;

@Controller
public class OrderController {
    @Autowired
    OrderService orderService;


}
