package com.edu.graduationproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.edu.graduationproject.entity.Order;
import org.springframework.transaction.annotation.Transactional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("SELECT o FROM Order o WHERE o.user.id =?1")
    public List<Order> findByUserId(Integer userId);

    @Query("SELECT o FROM Order o WHERE o.user.username=?1")
    List<Order> findByUsername(String username);

    @Query("SELECT o FROM Order o ORDER BY CASE " +
            "WHEN o.order_status = 'processing' THEN 1 " +
            "WHEN o.order_status = 'cancel' THEN 2 " +
            "ELSE 3 " +
            "END ASC")
    public List<Order> findSortWithStatus();

    @Transactional
    @Modifying
    @Query(value = "UPDATE orders SET order_status = ? WHERE id = ?", nativeQuery = true)
    public int updateStatus(String orderStatus, Long orderId);
}
