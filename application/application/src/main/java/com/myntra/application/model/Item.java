package com.myntra.application.model;

import jakarta.persistence.*;

@Entity
@Table(name = "items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String image;

    @Column(nullable = false)
    private String company;

    @Column(nullable = false)
    private String itemName;

    @Column(nullable = false)
    private Double currentPrice;

    @Column(nullable = false)
    private Double originalPrice;

    @Column(nullable = false)
    private Integer discountPercentage;

    @Column(nullable = false)
    private Double ratingStars;

    @Column(nullable = false)
    private Integer ratingCount;

    @Column(nullable = false)
    private String category;

    // Default constructor
    public Item() {}

    // Constructor with all fields
    public Item(String image, String company, String itemName, Double currentPrice, 
                Double originalPrice, Integer discountPercentage, Double ratingStars, Integer ratingCount, String category) {
        this.image = image;
        this.company = company;
        this.itemName = itemName;
        this.currentPrice = currentPrice;
        this.originalPrice = originalPrice;
        this.discountPercentage = discountPercentage;
        this.ratingStars = ratingStars;
        this.ratingCount = ratingCount;
        this.category = category;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(Double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public Double getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(Double originalPrice) {
        this.originalPrice = originalPrice;
    }

    public Integer getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(Integer discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public Double getRatingStars() {
        return ratingStars;
    }

    public void setRatingStars(Double ratingStars) {
        this.ratingStars = ratingStars;
    }

    public Integer getRatingCount() {
        return ratingCount;
    }

    public void setRatingCount(Integer ratingCount) {
        this.ratingCount = ratingCount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
