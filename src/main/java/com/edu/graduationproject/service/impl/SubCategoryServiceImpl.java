package com.edu.graduationproject.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.graduationproject.entity.SubCategory;
import com.edu.graduationproject.repository.SubCategoryRepository;
import com.edu.graduationproject.service.SubCategoryService;

@Service
public class SubCategoryServiceImpl implements SubCategoryService {

    @Autowired
    SubCategoryRepository repo;

    @Override
    public List<SubCategory> findAll() {
        return repo.findAll();
    }

    @Override
    public List<SubCategory> findAllByCatesId(Integer category_id) {
        return repo.findAllByCatesId(category_id);
    }

	@Override
	public SubCategory save(SubCategory subcategory) {
		return repo.save(subcategory);
	}

	@Override
	public SubCategory update(SubCategory subcategory) {
		return repo.save(subcategory);
	}

	@Override
	public void deleteById(Integer id) {
		repo.deleteById(id);
	}

	
}
