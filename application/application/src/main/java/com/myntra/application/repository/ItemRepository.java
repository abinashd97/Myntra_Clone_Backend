package com.myntra.application.repository;

import com.myntra.application.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    
    // Find all items
    List<Item> findAll();
    
    // Find items by company
    List<Item> findByCompany(String company);
    
    // Find items by price range
    List<Item> findByCurrentPriceBetween(Double minPrice, Double maxPrice);
    
    // Find items with minimum rating
    List<Item> findByRatingStarsGreaterThanEqual(Double minRating);
    
    // Search items by name or company
    List<Item> findByItemNameContainingIgnoreCaseOrCompanyContainingIgnoreCase(String itemName, String company);
    
    // Find items by category
    List<Item> findByCategory(String category);
    
    // Find items by category and search query
    List<Item> findByCategoryAndItemNameContainingIgnoreCaseOrCompanyContainingIgnoreCase(String category, String itemName, String company);
}
