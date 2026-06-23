package com.company.sales_management.repository;

import com.company.sales_management.entity.Supplier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Integer> {
    
    @Query("SELECT s FROM Supplier s WHERE CAST(:search AS string) IS NULL OR LOWER(s.name) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')) OR s.phone LIKE CONCAT('%', CAST(:search AS string), '%') ORDER BY s.createdAt DESC")
    List<Supplier> searchSuppliers(@Param("search") String search);

    @Query("SELECT s FROM Supplier s WHERE CAST(:search AS string) IS NULL OR LOWER(s.name) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')) OR s.phone LIKE CONCAT('%', CAST(:search AS string), '%')")
    Page<Supplier> findBySearchTerm(@Param("search") String search, Pageable pageable);
}
