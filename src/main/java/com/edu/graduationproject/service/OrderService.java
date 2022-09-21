package com.edu.graduationproject.service;


import com.edu.graduationproject.entity.Order;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;

public interface OrderService {
    Order create(JsonNode orderData);

    Order findById(long id);

    List<Order> findByUsername(String username);

    List<Order> findAll();
}