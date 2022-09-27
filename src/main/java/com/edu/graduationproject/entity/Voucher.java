package com.edu.graduationproject.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
@Table(name = "vouchers")
public class Voucher implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String code;
    private String description;
    private Double value;
    // bắt buộc dùng @TemporalType.Date cho các class từ java.util.*
    @Temporal(TemporalType.DATE)
    private Date start_date = new Date();
    @Temporal(TemporalType.DATE)
    private Date end_date;
    @Temporal(TemporalType.DATE)
    private Date updated_at;

    @JsonIgnore
    @OneToMany(mappedBy = "voucher")
    private List<Order> orders;

}
