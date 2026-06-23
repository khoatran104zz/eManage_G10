package com.company.sales_management.repository;

import com.company.sales_management.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    
    @Query("SELECT e FROM Employee e WHERE CAST(:search AS string) IS NULL OR LOWER(e.name) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')) OR e.phone LIKE CONCAT('%', CAST(:search AS string), '%') ORDER BY e.createdAt DESC")
    List<Employee> searchEmployees(@Param("search") String search);

    @Query("SELECT e FROM Employee e WHERE " +
           "(CAST(:search AS string) IS NULL OR LOWER(e.name) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')) OR e.phone LIKE CONCAT('%', CAST(:search AS string), '%')) AND " +
           "(:branchId IS NULL OR e.branch.id = :branchId)")
    Page<Employee> findAllWithFilters(@Param("search") String search, @Param("branchId") Integer branchId, Pageable pageable);
}
