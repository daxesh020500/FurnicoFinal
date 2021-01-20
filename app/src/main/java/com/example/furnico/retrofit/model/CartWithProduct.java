package com.example.furnico.retrofit.model;

public class CartWithProduct {

    private int id;
    private int quantity;
    private int customerId;
    private int productId;
    private String name;
    private int category;
    private String color;
    private int dimensions;
    private int warrantyPeriod;
    private String image;
    private String description;
    private String keywords;
    private int bestPrice;

    public CartWithProduct(Cart cart, Product product) {
        this.id = cart.getId();
        this.quantity = cart.getQuantity();
        this.customerId = cart.getCustomerId();
        this.productId = cart.getProductId();
        this.name = product.getName();
        this.category = product.getCategory();
        this.color = product.getColor();
        this.dimensions = product.getDimensions();
        this.warrantyPeriod = product.getWarrantyPeriod();
        this.image = product.getImage();
        this.description = product.getDescription();
        this.keywords = product.getKeywords();
        this.bestPrice = product.getBestPrice();
    }
}

