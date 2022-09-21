package com.edu.graduationproject.service.impl;

import java.util.List;

import com.edu.graduationproject.entity.Category;
import com.edu.graduationproject.repository.CategoryRepository;
import com.edu.graduationproject.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository repo;

    @Override
    public List<Category> findAll() {
        return repo.findAll();
    }

}
