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

	@Override
	public Category save(Category category) {
		return repo.save(category);
	}

	@Override
	public Category update(Category category) {
		return repo.save(category);
	}

	@Override
	public void deleteById(Integer id) {
		repo.deleteById(id);
		
	}

	@Override
	public List<Category> findByCateName(String name) {
		return repo.findByName(name);
	}


}
