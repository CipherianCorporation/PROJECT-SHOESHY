package com.edu.graduationproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.edu.graduationproject.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
	@Modifying
    @Query("SELECT name FROM Category u WHERE u.name=?1")
    public List<Category> findByName(String name);
	
}
