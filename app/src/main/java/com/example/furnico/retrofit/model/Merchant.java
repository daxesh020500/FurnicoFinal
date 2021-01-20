package com.example.furnico.retrofit.model;

public class Merchant {

    private int id;
    private String email;
    private String name;
    private String password;
    private int rating;
    private int rated;

    public Merchant(){};

    public Merchant(int id, String email, String name, String password, int rating, int rated) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.password = password;
        this.rating = rating;
        this.rated = rated;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRated() {
        return rated;
    }

    public void setRated(int rated) {
        this.rated = rated;
    }

    public void Merchant(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
