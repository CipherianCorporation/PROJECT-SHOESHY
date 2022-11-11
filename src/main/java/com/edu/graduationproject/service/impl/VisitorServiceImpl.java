package com.edu.graduationproject.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.graduationproject.entity.Visitor;
import com.edu.graduationproject.repository.VisitorRepository;
import com.edu.graduationproject.service.VisitorService;

@Service
public class VisitorServiceImpl implements VisitorService {
    @Autowired
    private VisitorRepository repository;

    @Override
    public Visitor saveVisitorInfo(Visitor visitor) {
        return repository.save(visitor);
    }

    @Override
    public Optional<List<Visitor>> findAllByIp(String ip) {
        return repository.findAllByIp(ip);
    }

    @Override
    public List<Visitor> findAll() {
        return repository.findAll();
    }
}
