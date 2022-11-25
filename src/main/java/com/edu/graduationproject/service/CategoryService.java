package com.edu.graduationproject.service;

import com.edu.graduationproject.entity.Category;

import java.util.List;

public interface CategoryService {

    public List<Category> findAll();
    
    Category save(Category category);
    
    Category update(Category category);
    
    void deleteById(Integer id);

	public List<Category> findByCateName(String name);

}
