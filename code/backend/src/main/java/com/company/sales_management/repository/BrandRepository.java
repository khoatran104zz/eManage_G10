package com.company.sales_management.repository;

import com.company.sales_management.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Integer> {
    
    @Query("SELECT b FROM Brand b WHERE CAST(:search AS string) IS NULL OR LOWER(b.name) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')) ORDER BY b.createdAt DESC")
    List<Brand> searchBrands(@Param("search") String search);

    List<Brand> findByNameContainingIgnoreCase(String name);

    boolean existsByName(String name);
}
