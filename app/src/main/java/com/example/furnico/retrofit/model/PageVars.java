package com.example.furnico.retrofit.model;

public class PageVars {
    int category;
    int page;
    int size;

    public PageVars() {
    }

    public PageVars(int category, int page, int size) {
        this.category = category;
        this.page = page;
        this.size = size;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}

