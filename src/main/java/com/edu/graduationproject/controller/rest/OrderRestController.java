package com.edu.graduationproject.controller.rest;

import java.util.List;

import com.edu.graduationproject.entity.OrderDetails;

import com.edu.graduationproject.model.OrderStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.edu.graduationproject.entity.Order;
import com.edu.graduationproject.service.OrderService;

@CrossOrigin("*")
@RestController
public class OrderRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderRestController.class);

    @Autowired
    OrderService orderService;

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
    public List<Order> findOderSortStatus(){
        return orderService.findAllSortStatus();
    }

    @PutMapping("/rest/order/order-status/{orderId}")
    public int updateOrder(@PathVariable("orderId") Long orderId, @RequestBody Order order){
        OrderStatus orderStatus ;
        return orderService.updateStatus(String.valueOf(order.getOrder_status()),orderId);
    }

}
