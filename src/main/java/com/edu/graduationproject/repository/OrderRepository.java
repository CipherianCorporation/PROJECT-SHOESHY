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

    // @Query("SELECT o FROM Order o ORDER BY CASE " +
    // "WHEN o.order_status = 'processing' THEN 1 " +
    // "WHEN o.order_status = 'cancel' THEN 2 " +
    // "ELSE 3 "+
    // "END ASC")
    // public List<Order> findSortWithStatus();
    //
    // @Transactional
    // @Modifying
    // @Query(value = "UPDATE orders SET order_status = ? WHERE id = ?",
    // nativeQuery = true)
    // public int updateStatus(String orderStatus, Long orderId);

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
    // @Query("ALTER TABLE orders NOCHECK CONSTRAINT fk_711 UPDATE Order o SET
    // o.orderStatus.name = ?1 WHERE o.id = ?2 ALTER TABLE orders CHECK CONSTRAINT
    // fk_711")
    public int updateStatus(String orderStatus, Long orderId);

}
