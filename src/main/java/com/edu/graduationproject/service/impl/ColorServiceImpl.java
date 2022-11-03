package com.edu.graduationproject.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.graduationproject.entity.Color;
import com.edu.graduationproject.repository.ColorRepository;
import com.edu.graduationproject.service.ColorService;



@Service
public class ColorServiceImpl implements ColorService {

    @Autowired
    ColorRepository repo;

    @Override
    public List<Color> findAll() {
        return repo.findAll();
    }

}
