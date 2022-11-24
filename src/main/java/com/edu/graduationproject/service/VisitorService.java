package com.edu.graduationproject.service;

import java.util.List;
import java.util.Optional;

import com.edu.graduationproject.entity.Visitor;

public interface VisitorService {
    Visitor saveVisitorInfo(Visitor visitor);

    Optional<List<Visitor>> findAllByIp(String ip);

    List<Visitor> findAll();

    Long getCount();
}
