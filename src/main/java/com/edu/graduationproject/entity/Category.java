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

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "categories")
public class Category implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String image_url;

    // bắt buộc dùng @TemporalType.Date cho các class từ java.util.*
    @Temporal(TemporalType.DATE)
    private Date updated_at;
    @Temporal(TemporalType.DATE)
    private Date created_at = new Date();
    @Temporal(TemporalType.DATE)
    private Date deleted_at;

    @JsonIgnore
    @OneToMany(mappedBy = "category")
    private List<Product> products;

    @JsonIgnore
    @OneToMany(mappedBy = "category")
    private List<SubCategory> subCategories;
}
