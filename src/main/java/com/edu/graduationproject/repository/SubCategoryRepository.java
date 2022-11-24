
package com.edu.graduationproject.repository;

import java.util.HashMap;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.edu.graduationproject.entity.SubCategory;
import com.edu.graduationproject.model.IProductSoldBySubCategoryCount;

public interface SubCategoryRepository extends JpaRepository<SubCategory, Integer> {
    @Query("SELECT u FROM SubCategory u WHERE u.category.id = ?1")
    List<SubCategory> findAllByCatesId(Integer category_id);

    @Query(value = """
            SELECT DISTINCT s.name AS subCategory, SUM(p.sold) AS productSold FROM products p
            INNER JOIN sub_categories s ON p.sub_category_id = s.id
            GROUP BY s.name
            """, nativeQuery = true)
    List<IProductSoldBySubCategoryCount> getProductSold();
}
