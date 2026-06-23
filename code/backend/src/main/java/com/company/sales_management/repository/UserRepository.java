package com.company.sales_management.repository;

import com.company.sales_management.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);

    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);

    @Query("SELECT u FROM User u WHERE CAST(:search AS string) IS NULL OR " +
           "LOWER(u.username) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')) OR " +
           "LOWER(u.fullName) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')) OR " +
           "LOWER(u.email) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%'))")
    Page<User> findBySearchTerm(@Param("search") String search, Pageable pageable);
}
