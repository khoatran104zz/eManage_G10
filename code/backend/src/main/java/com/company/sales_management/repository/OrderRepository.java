package com.company.sales_management.repository;

import com.company.sales_management.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    
    @Query("SELECT o FROM Order o LEFT JOIN o.customer c WHERE " +
           "(:search IS NULL OR LOWER(o.code) LIKE LOWER(CONCAT('%', :search, '%')) OR LOWER(c.name) LIKE LOWER(CONCAT('%', :search, '%'))) " +
           "AND (:status IS NULL OR o.status = :status) " +
           "AND (:startDate IS NULL OR o.createdAt >= :startDate) " +
           "AND (:endDate IS NULL OR o.createdAt <= :endDate)")
    Page<Order> searchOrders(@Param("search") String search, 
                             @Param("status") String status, 
                             @Param("startDate") LocalDateTime startDate, 
                             @Param("endDate") LocalDateTime endDate, 
                             Pageable pageable);

    @Query("SELECT o FROM Order o ORDER BY o.createdAt DESC")
    List<Order> findRecentOrders(Pageable pageable);

    @Query("SELECT COALESCE(SUM(o.total - o.discount), 0.0) FROM Order o WHERE o.status = 'completed'")
    Double calculateTotalRevenue();

    long countByStatus(String status);

    long countByCreatedAtBetween(LocalDateTime start, LocalDateTime end);

    @Query("SELECT COALESCE(SUM(o.total - o.discount), 0.0) FROM Order o WHERE o.createdAt BETWEEN :start AND :end AND o.status = 'completed'")
    Double sumFinalAmountByCreatedAtBetween(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);

    @Query("SELECT CAST(o.createdAt AS date), SUM(o.total - o.discount), COUNT(o) FROM Order o WHERE o.createdAt BETWEEN :start AND :end AND o.status = 'completed' GROUP BY CAST(o.createdAt AS date) ORDER BY CAST(o.createdAt AS date) ASC")
    List<Object[]> getRevenueByDay(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);

    @Query("SELECT oi.product.id, oi.productName, SUM(oi.quantity), SUM(oi.price * oi.quantity) FROM OrderItem oi WHERE oi.order.createdAt BETWEEN :start AND :end AND oi.order.status = 'completed' GROUP BY oi.product.id, oi.productName ORDER BY SUM(oi.quantity) DESC")
    List<Object[]> getTopProducts(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);
}
