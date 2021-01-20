package com.example.furnico.retrofit.model;

import java.util.ArrayList;

public class BuyOptions {

    ArrayList<Inventory> inventories;
    int productId;
    int quantity;

    public BuyOptions() {
    }

    public BuyOptions(ArrayList<Inventory> inventories, int productId, int quantity) {
        this.inventories = inventories;
        this.productId = productId;
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public ArrayList<Inventory> getInventories() {
        return inventories;
    }

    public void setInventories(ArrayList<Inventory> inventories) {
        this.inventories = inventories;
    }


    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
}


