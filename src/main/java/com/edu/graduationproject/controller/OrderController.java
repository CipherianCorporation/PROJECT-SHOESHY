package com.edu.graduationproject.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.edu.graduationproject.service.OrderService;
import com.fasterxml.jackson.core.JsonProcessingException;

@Controller
public class OrderController {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    OrderService orderService;

    @RequestMapping("/order/checkout")
    public String checkout() {
        return "order/checkout";
    }

    @RequestMapping("/order/list")
    public String list(Model model, HttpServletRequest request) {
        String username = request.getRemoteUser();
        model.addAttribute("orders", orderService.findByUsername(username));
        return "order/list";
    }

    @RequestMapping("/order/detail/{id}")
    public String detail(@PathVariable("id") Long id, Model model) throws JsonProcessingException {
        // System.out.println(new
        // ObjectMapper().writeValueAsString(orderService.findById(id).getOrder_details()));
        model.addAttribute("order", orderService.findById(id));
        return "order/detail";
    }
}
