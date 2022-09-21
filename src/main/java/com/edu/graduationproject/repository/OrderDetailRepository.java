package com.edu.graduationproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edu.graduationproject.entity.OrderDetails;

public interface OrderDetailRepository extends JpaRepository<OrderDetails, Long> {
}
