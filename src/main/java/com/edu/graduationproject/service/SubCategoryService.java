package com.edu.graduationproject.service;

import java.util.List;

import com.edu.graduationproject.entity.SubCategory;
import com.edu.graduationproject.model.IProductSoldBySubCategoryCount;

public interface SubCategoryService {
    public List<SubCategory> findAll();

    public List<SubCategory> findAllByCatesId(Integer category_id);

    public List<IProductSoldBySubCategoryCount> getProductSold();

	SubCategory save(SubCategory subcategory);
	
	SubCategory update(SubCategory subcategory);
	
	void deleteById(Integer id);
	
	
}
