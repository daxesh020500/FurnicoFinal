package com.example.furnico.retrofit.model;

public class Transaction {

    private int id;
    private int quantity;
    private int customerId;
    private int inventoryId;
    private int productId;
    private int price;

    public Transaction() { }

    public Transaction(int id, int quantity, int customerId, int inventoryId, int productId, int price) {
        this.id = id;
        this.quantity = quantity;
        this.customerId = customerId;
        this.inventoryId = inventoryId;
        this.productId = productId;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(int inventoryId) {
        this.inventoryId = inventoryId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
