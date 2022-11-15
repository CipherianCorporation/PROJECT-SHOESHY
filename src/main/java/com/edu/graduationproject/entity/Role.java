package com.edu.graduationproject.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "roles")
public class Role implements Serializable {
    @Id
    private String id;
    private String name;

    // bắt buộc dùng @TemporalType.Date cho các class từ java.util.*
    @Temporal(TemporalType.DATE)
    @Column(name="updated_at")
    private Date updatedAt;
    @Temporal(TemporalType.DATE)
    @Column(name="created_at")
    private Date createdAt = new Date();
    @Temporal(TemporalType.DATE)
    @Column(name="deleted_at")
    private Date deletedAt;
}
