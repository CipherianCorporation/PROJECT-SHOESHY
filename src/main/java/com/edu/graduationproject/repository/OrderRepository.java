package com.edu.graduationproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.edu.graduationproject.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("SELECT o FROM Order o WHERE o.user.id =?1")
    public List<Order> findByUserId(Integer userId);

    @Query("SELECT o FROM Order o WHERE o.user.username=?1")
    List<Order> findByUsername(String username);
}
