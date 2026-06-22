package com.company.sales_management.repository;

import com.company.sales_management.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
    
    @Query("SELECT oi.productName AS name, SUM(oi.quantity) AS quantity " +
           "FROM OrderItem oi JOIN oi.order o " +
           "WHERE o.status = 'completed' " +
           "GROUP BY oi.productName " +
           "ORDER BY SUM(oi.quantity) DESC")
    List<Object[]> findTopSellingProducts();
}
