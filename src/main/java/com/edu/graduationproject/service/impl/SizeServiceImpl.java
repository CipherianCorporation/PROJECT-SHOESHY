package com.edu.graduationproject.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.graduationproject.entity.Size;
import com.edu.graduationproject.repository.SizeRepository;
import com.edu.graduationproject.service.SizeService;



@Service
public class SizeServiceImpl implements SizeService {

    @Autowired
    SizeRepository repo;

    @Override
    public List<Size> findAll() {
        return repo.findAll();
    }

}
