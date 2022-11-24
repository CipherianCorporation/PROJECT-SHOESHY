package com.edu.graduationproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.edu.graduationproject.entity.Order;
import com.edu.graduationproject.model.IOrderTypeCount;

import org.springframework.transaction.annotation.Transactional;

public interface OrderRepository extends JpaRepository<Order, Long> {
        @Query("SELECT o FROM Order o WHERE o.user.id =?1")
        public List<Order> findByUserId(Integer userId);

        @Query("SELECT o FROM Order o WHERE o.user.username=?1")
        List<Order> findByUsername(String username);

        @Query("SELECT o FROM Order o ORDER BY CASE " +
                        "WHEN o.orderStatus = 'processing' THEN 1 " +
                        "WHEN o.orderStatus = 'cancel' THEN 2 " +
                        "ELSE 3 " +
                        "END ASC")
        public List<Order> findSortWithStatus();

        @Transactional
        @Modifying
        @Query(value = "ALTER TABLE orders NOCHECK CONSTRAINT fk_711 " +
                        "UPDATE orders SET order_status = ? WHERE id = ? " +
                        "ALTER TABLE orders CHECK CONSTRAINT fk_711", nativeQuery = true)
        public int updateStatus(String orderStatus, Long orderId);

        @Query(value = "SELECT * FROM orders WHERE id LIKE %?1%", nativeQuery = true)
        public List<Order> searchByOrderId(Long orderId);

        @Query("SELECT COUNT(o) FROM Order o")
        public Long getCount();

        @Query("SELECT SUM(o.total) FROM Order o")
        public Double getTotalRevenue();

        @Query(value = """
                select 
                count(case o.order_status when 'processing' then 1 else null end) as processingCount,
                count(case o.order_status when 'success' then 1 else null end) as successCount,
                count(case o.order_status when 'cancel' then 1 else null end) as cancelCount
                from orders o
                """, nativeQuery = true)
        public List<IOrderTypeCount> getTypeCount();
}
