package com.company.sales_management.repository;

import com.company.sales_management.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    
    @Query("SELECT c FROM Customer c WHERE CAST(:search AS string) IS NULL OR LOWER(c.name) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')) OR c.phone LIKE CONCAT('%', CAST(:search AS string), '%') ORDER BY c.createdAt DESC")
    List<Customer> searchCustomers(@Param("search") String search);

    @Query("SELECT c FROM Customer c WHERE CAST(:search AS string) IS NULL OR LOWER(c.name) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')) OR c.phone LIKE CONCAT('%', CAST(:search AS string), '%')")
    Page<Customer> findBySearchTerm(@Param("search") String search, Pageable pageable);

    Optional<Customer> findByPhone(String phone);
    
    boolean existsByPhone(String phone);

    boolean existsByPhoneAndIdNot(String phone, Integer id);
}
