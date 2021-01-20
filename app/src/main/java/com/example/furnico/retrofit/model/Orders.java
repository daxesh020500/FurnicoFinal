package com.example.furnico.retrofit.model;

import java.util.Date;

public class Orders {
    int id;
    Date orderDate;
    int customerId;
    int productId;
    int inventoryId;
    int quantity;

    public Orders() {
    }

    public Orders(int id, Date orderDate, int customerId, int productId, int inventoryId, int quantity) {
        this.id = id;
        this.orderDate = orderDate;
        this.customerId = customerId;
        this.productId = productId;
        this.inventoryId = inventoryId;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(int inventoryId) {
        this.inventoryId = inventoryId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
