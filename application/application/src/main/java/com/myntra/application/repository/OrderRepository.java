package com.myntra.application.repository;

import com.myntra.application.model.Order;
import com.myntra.application.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    
    // Find all orders for a user
    List<Order> findByUserOrderByOrderDateDesc(User user);
    
    // Find all orders for a user by user ID
    List<Order> findByUserIdOrderByOrderDateDesc(Long userId);
    
    // Find order by order number
    Order findByOrderNumber(String orderNumber);
    
    // Find order with items by ID
    @Query("SELECT o FROM Order o LEFT JOIN FETCH o.orderItems WHERE o.id = :id")
    Optional<Order> findByIdWithItems(@Param("id") Long id);
    
    // Find all orders for a user with items
    @Query("SELECT DISTINCT o FROM Order o LEFT JOIN FETCH o.orderItems WHERE o.user = :user ORDER BY o.orderDate DESC")
    List<Order> findByUserWithItemsOrderByOrderDateDesc(@Param("user") User user);
}
