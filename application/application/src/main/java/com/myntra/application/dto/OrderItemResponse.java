package com.myntra.application.dto;

public class OrderItemResponse {
    
    private Long id;
    private Long itemId;
    private String itemName;
    private String itemImage;
    private String company;
    private Double price;
    private Integer quantity;
    private Double totalPrice;
    
    // Default constructor
    public OrderItemResponse() {}
    
    // Constructor
    public OrderItemResponse(Long id, Long itemId, String itemName, String itemImage, 
                            String company, Double price, Integer quantity, Double totalPrice) {
        this.id = id;
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemImage = itemImage;
        this.company = company;
        this.price = price;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Long getItemId() {
        return itemId;
    }
    
    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }
    
    public String getItemName() {
        return itemName;
    }
    
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
    
    public String getItemImage() {
        return itemImage;
    }
    
    public void setItemImage(String itemImage) {
        this.itemImage = itemImage;
    }
    
    public String getCompany() {
        return company;
    }
    
    public void setCompany(String company) {
        this.company = company;
    }
    
    public Double getPrice() {
        return price;
    }
    
    public void setPrice(Double price) {
        this.price = price;
    }
    
    public Integer getQuantity() {
        return quantity;
    }
    
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    
    public Double getTotalPrice() {
        return totalPrice;
    }
    
    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
