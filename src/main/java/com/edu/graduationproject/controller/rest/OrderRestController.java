package com.edu.graduationproject.controller.rest;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.edu.graduationproject.entity.OrderDetails;

import com.edu.graduationproject.model.OrderStatus;
import com.edu.graduationproject.service.ExportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.edu.graduationproject.entity.Order;
import com.edu.graduationproject.entity.OrderDetails;
import com.edu.graduationproject.service.OrderService;
import com.fasterxml.jackson.databind.JsonNode;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@CrossOrigin("*")
@RestController
public class OrderRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderRestController.class);

    @Autowired
    OrderService orderService;

    @Autowired
    ExportService exportService;

    @GetMapping("/rest/orders")
    public ResponseEntity<List<Order>> getOrders() {
        return ResponseEntity.ok(orderService.findAll());
    }

    @PostMapping("/rest/orders")
    public ResponseEntity<Order> create(@RequestBody JsonNode orderData) {
        if (orderData.get("total").asDouble() == 0) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(orderService.create(orderData));
    }

    @GetMapping("/rest/order/list")
    public List<Order> findAllOrder() {
        return orderService.findAll();
    }

    @GetMapping("/rest/order/list/{userId}")
    public List<Order> findAllOrderByUser(@PathVariable("userId") Integer userId) {
        return orderService.findByUserId(userId);
    }

    @GetMapping("/rest/order/detail/{orderId}")
    public List<OrderDetails> findOrderDetail(@PathVariable("orderId") Long orderId) {
        return orderService.findOrderDetailsByOrderId(orderId);
    }

    @GetMapping("/rest/order/sortstatus")
    public List<Order> findOderSortStatus() {
        return orderService.findAllSortStatus();
    }

    @PutMapping("/rest/order/orderstatus/{orderId}")
    public int updateOrder(@PathVariable("orderId") Long orderId, @RequestBody Order order) {
        return orderService.updateStatus(String.valueOf(order.getOrderStatus().getName()), orderId);
    }

}
