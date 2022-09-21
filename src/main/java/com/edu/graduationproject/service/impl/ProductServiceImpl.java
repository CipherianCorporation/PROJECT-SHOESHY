package com.edu.graduationproject.service.impl;

import java.util.List;

import com.edu.graduationproject.entity.Product;
import com.edu.graduationproject.repository.ProductRepository;
import com.edu.graduationproject.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository repo;

    @Override
    public List<Product> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Product> findAll(Sort sort) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Product findById(Integer id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Product> findByCategoryId(Integer cid) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Product> searchProducts(String query) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Product create(Product product) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Product update(Product product) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void delete(Integer id) {
        // TODO Auto-generated method stub
        
    }



}
