package com.edu.graduationproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edu.graduationproject.entity.OrderDetails;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetails, Long> {
    @Query("SELECT d FROM OrderDetails d WHERE d.order.id =?1")
    public List<OrderDetails> findOrderDetailsByOrder(long orderId);
}
