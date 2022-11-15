package com.edu.graduationproject.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "visitors")
public class Visitor implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "user_info")
    private String userInfo;
    private String ip;
    private String city;
    @Column(name = "full_location")
    private String fullLocation;
    private Double latitude;
    private Double longtitude;
    private String method;
    private String url;
    private String page;
    @Column(name = "query_string")
    private String queryString;
    @Column(name = "referer_page")
    private String refererPage;
    @Column(name = "user_agent")
    private String userAgent;
    @Column(name = "logged_time")
    private LocalDateTime loggedTime;
    @Column(name = "unique_visit")
    private Boolean uniqueVisit;

}
