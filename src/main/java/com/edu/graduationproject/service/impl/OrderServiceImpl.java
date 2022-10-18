package com.edu.graduationproject.service.impl;

import com.edu.graduationproject.entity.Order;
import com.edu.graduationproject.entity.OrderDetails;
import com.edu.graduationproject.repository.OrderDetailRepository;
import com.edu.graduationproject.repository.OrderRepository;
import com.edu.graduationproject.service.OrderService;
import com.edu.graduationproject.service.UserService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepo;

    @Autowired
    UserService userService;

    @Autowired
    OrderDetailRepository orderDetailRepo;

    @Override
    public Order create(JsonNode orderData) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Order findById(long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Order> findByUsername(String username) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Order> findAll() {
        // TODO Auto-generated method stub
        return orderRepo.findAll();
    }

    @Override
    public List<Order> findByUser(int userId) {
        return orderRepo.findByUser(userId);
    }

    @Override
    public List<OrderDetails> findOrderDetailsByOrder(long orderId) {
        return orderDetailRepo.findOrderDetailsByOrder(orderId);
    }


}
