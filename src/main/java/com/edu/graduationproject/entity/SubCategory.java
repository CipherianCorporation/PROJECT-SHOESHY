package com.edu.graduationproject.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "sub_categories")
public class SubCategory implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    // bắt buộc dùng @TemporalType.Date cho các class từ java.util.*
    @Temporal(TemporalType.DATE)
    private Date updated_at;
    @Temporal(TemporalType.DATE)
    private Date created_at = new Date();
    @Temporal(TemporalType.DATE)
    private Date deleted_at;

    @JsonIgnore
    @OneToMany(mappedBy = "subCategory")
    private List<Product> products;
}
