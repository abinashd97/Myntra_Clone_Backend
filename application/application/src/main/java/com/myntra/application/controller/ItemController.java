package com.myntra.application.controller;

import com.myntra.application.model.Item;
import com.myntra.application.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/items")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ItemController {

    @Autowired
    private ItemRepository itemRepository;

    @GetMapping
    public ResponseEntity<List<Item>> getAllItems() {
        List<Item> items = itemRepository.findAll();
        return ResponseEntity.ok(items);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Item> getItemById(@PathVariable Long id) {
        return itemRepository.findById(id)
                .map(item -> ResponseEntity.ok(item))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/company/{company}")
    public ResponseEntity<List<Item>> getItemsByCompany(@PathVariable String company) {
        List<Item> items = itemRepository.findByCompany(company);
        return ResponseEntity.ok(items);
    }

    @GetMapping("/price-range")
    public ResponseEntity<List<Item>> getItemsByPriceRange(
            @RequestParam Double minPrice, 
            @RequestParam Double maxPrice) {
        List<Item> items = itemRepository.findByCurrentPriceBetween(minPrice, maxPrice);
        return ResponseEntity.ok(items);
    }

    @GetMapping("/rating/{minRating}")
    public ResponseEntity<List<Item>> getItemsByMinRating(@PathVariable Double minRating) {
        List<Item> items = itemRepository.findByRatingStarsGreaterThanEqual(minRating);
        return ResponseEntity.ok(items);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Item>> searchItems(@RequestParam String query) {
        List<Item> items = itemRepository.findByItemNameContainingIgnoreCaseOrCompanyContainingIgnoreCase(query, query);
        return ResponseEntity.ok(items);
    }

    @GetMapping("/search/suggestions")
    public ResponseEntity<List<String>> getSearchSuggestions(@RequestParam String query) {
        List<Item> items = itemRepository.findByItemNameContainingIgnoreCaseOrCompanyContainingIgnoreCase(query, query);
        List<String> suggestions = items.stream()
                .map(item -> item.getItemName())
                .distinct()
                .limit(5)
                .collect(java.util.stream.Collectors.toList());
        return ResponseEntity.ok(suggestions);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Item>> getItemsByCategory(@PathVariable String category) {
        List<Item> items = itemRepository.findByCategory(category);
        return ResponseEntity.ok(items);
    }

    @GetMapping("/category/{category}/search")
    public ResponseEntity<List<Item>> searchItemsByCategory(@PathVariable String category, @RequestParam String query) {
        List<Item> items = itemRepository.findByCategoryAndItemNameContainingIgnoreCaseOrCompanyContainingIgnoreCase(category, query, query);
        return ResponseEntity.ok(items);
    }

    @GetMapping("/search/exact")
    public ResponseEntity<List<Item>> searchExactItem(@RequestParam String itemName) {
        List<Item> allItems = itemRepository.findAll();
        List<Item> exactMatches = allItems.stream()
                .filter(item -> item.getItemName().equalsIgnoreCase(itemName.trim()))
                .collect(java.util.stream.Collectors.toList());
        return ResponseEntity.ok(exactMatches);
    }
}
