package com.company.sales_management.repository;

import com.company.sales_management.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    
    @Query("SELECT p FROM Product p WHERE " +
           "(:active IS NULL OR p.active = :active) AND " +
           "(:categoryId IS NULL OR p.category.id = :categoryId) AND " +
           "(:brandId IS NULL OR p.brand.id = :brandId) AND " +
           "(CAST(:search AS string) IS NULL OR LOWER(p.name) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')) OR LOWER(p.sku) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')))")
    Page<Product> findAllWithFilters(@Param("search") String search, 
                                     @Param("categoryId") Integer categoryId, 
                                     @Param("brandId") Integer brandId, 
                                     @Param("active") Boolean active, 
                                     Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.active = true AND (CAST(:search AS string) IS NULL OR LOWER(p.name) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')) OR LOWER(p.sku) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')))")
    Page<Product> searchActiveProducts(@Param("search") String search, Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.active = true ORDER BY p.createdAt DESC")
    List<Product> findAllActive();

    Optional<Product> findByIdAndActiveTrue(Integer id);

    boolean existsBySku(String sku);

    boolean existsBySkuAndIdNot(String sku, Integer id);

    long countByActiveTrue();

    @Query("SELECT p FROM Product p WHERE p.active = true AND p.stock <= 10")
    List<Product> findLowStockProducts();
}
