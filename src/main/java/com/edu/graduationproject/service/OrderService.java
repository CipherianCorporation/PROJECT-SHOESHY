package com.edu.graduationproject.service;


import com.edu.graduationproject.entity.Order;
import com.edu.graduationproject.entity.OrderDetails;
import com.fasterxml.jackson.databind.JsonNode;
import java.util.List;

public interface OrderService {
    Order create(JsonNode orderData);

    Order findById(Long id);

    List<Order> findByUsername(String username);

    List<Order> findAll();

    List<Order> findByUserId(Integer userId);

    List<OrderDetails> findOrderDetailsByOrderId(Long orderId);

    List<Order> findAllSortStatus();

    int updateStatus(String orderStatus, Long orderId);

    List<Order> searchByOrderId(Long orderId);
}