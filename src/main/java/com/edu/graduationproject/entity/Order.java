package com.edu.graduationproject.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.edu.graduationproject.model.PaypalPaymentMethod;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "orders")
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String address;

    @Enumerated(EnumType.STRING)
    private PaypalPaymentMethod payment_method;

    @ManyToOne
    @JoinColumn(name = "order_status")
    private OrderStatus orderStatus;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "voucher_id")
    private Voucher voucher;

    private Double total;

    // bắt buộc dùng @TemporalType.Date cho các class từ java.util.*
    @Temporal(TemporalType.DATE)
    @Column(name = "updated_at")
    private Date updatedAt;
    @Temporal(TemporalType.DATE)
    @Column(name = "created_at")
    private Date createdAt = new Date();
    @Temporal(TemporalType.DATE)
    @Column(name = "deleted_at")
    private Date deletedAt;

    @JsonIgnore
    @OneToMany(mappedBy = "order")
    private List<OrderDetails> order_details;
}
