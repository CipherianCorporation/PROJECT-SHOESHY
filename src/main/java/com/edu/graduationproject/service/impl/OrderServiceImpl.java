package com.edu.graduationproject.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.graduationproject.entity.Order;
import com.edu.graduationproject.entity.OrderDetails;
import com.edu.graduationproject.entity.Product;
import com.edu.graduationproject.entity.User;
import com.edu.graduationproject.repository.OrderDetailRepository;
import com.edu.graduationproject.repository.OrderRepository;
import com.edu.graduationproject.service.OrderService;
import com.edu.graduationproject.service.ProductService;
import com.edu.graduationproject.service.UserService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepo;

    @Autowired
    UserService userService;

    @Autowired
    OrderDetailRepository orderDetailRepo;

    @Autowired
    ProductService productService;

    @Override
    public Order create(JsonNode orderData) {
        ObjectMapper mapper = new ObjectMapper();
        Order order = mapper.convertValue(orderData, Order.class);
        User user = userService.findByUsername(order.getUser().getUsername()).get();
        order.setCreatedAt(new Date());
        order.setUser(user);
        orderRepo.save(order);
        List<OrderDetails> list = mapper
                .convertValue(orderData.get("order_details"), new TypeReference<List<OrderDetails>>() {
                })
                .stream().peek(o -> o.setOrder(order)).collect(Collectors.toList());

        // increment product sold to 1
        list.forEach((detail) -> {
            Product product = productService.findById(detail.getProduct().getId());
            Long oldSold = product.getSold();
            product.setSold(++oldSold);
        });
        orderDetailRepo.saveAll(list);
        return order;
    }

    @Override
    public Order findById(Long id) {
        return orderRepo.findById(id).get();
    }

    @Override
    public List<Order> findByUsername(String username) {
        return orderRepo.findByUsername(username);
    }

    @Override
    public List<Order> findAll() {
        return orderRepo.findAll();
    }

    @Override
    public List<Order> findByUserId(Integer userId) {
        return orderRepo.findByUserId(userId);
    }

    @Override
    public List<OrderDetails> findOrderDetailsByOrderId(Long orderId) {
        return orderDetailRepo.findOrderDetailsByOrderId(orderId);
    }

    @Override
    public List<Order> findAllSortStatus() {
        return orderRepo.findSortWithStatus();
    }

    @Override
    public int updateStatus(String orderStatus, Long orderId) {
        return orderRepo.updateStatus(orderStatus, orderId);
    }

    @Override
    public List<Order> searchByOrderId(Long orderId) {
        return orderRepo.searchByOrderId(orderId);
    }

    @Override
    public Long getCount() {
        return orderRepo.getCount();
    }

    @Override
    public Double getTotalRevenue() {
        return orderRepo.getTotalRevenue();
    }
}
