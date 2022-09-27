package com.edu.graduationproject.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.edu.graduationproject.model.OrderStatus;
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

    @Enumerated(EnumType.STRING)
    private OrderStatus order_status;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "voucher_id")
    private Voucher voucher;

    private Double total;

    // bắt buộc dùng @TemporalType.Date cho các class từ java.util.*
    @Temporal(TemporalType.DATE)
    private Date updated_at;
    @Temporal(TemporalType.DATE)
    private Date created_at = new Date();
    @Temporal(TemporalType.DATE)
    private Date deleted_at;

    @JsonIgnore
    @OneToMany(mappedBy = "order")
    private List<OrderDetails> order_details;
}
