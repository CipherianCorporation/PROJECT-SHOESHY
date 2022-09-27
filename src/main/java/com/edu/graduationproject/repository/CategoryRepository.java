package com.edu.graduationproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edu.graduationproject.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
