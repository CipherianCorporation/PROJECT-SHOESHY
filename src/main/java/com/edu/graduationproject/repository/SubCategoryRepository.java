
package com.edu.graduationproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.edu.graduationproject.entity.SubCategory;

public interface SubCategoryRepository extends JpaRepository<SubCategory, Integer> {
    @Query("SELECT u FROM SubCategory u WHERE u.category.id = ?1")
    List<SubCategory> findAllByCatesId(Integer category_id);
}
