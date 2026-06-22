package com.company.sales_management.repository;

import com.company.sales_management.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    
    @Query("SELECT c FROM Category c WHERE :search IS NULL OR LOWER(c.name) LIKE LOWER(CONCAT('%', :search, '%')) ORDER BY c.createdAt DESC")
    List<Category> searchCategories(@Param("search") String search);

    List<Category> findByNameContainingIgnoreCase(String name);

    boolean existsByName(String name);
}
