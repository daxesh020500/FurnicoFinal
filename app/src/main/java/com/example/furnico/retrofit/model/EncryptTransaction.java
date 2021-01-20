package com.example.furnico.retrofit.model;

public class EncryptTransaction {
    String token;
    Transaction transaction;

    public EncryptTransaction() {
    }

    public EncryptTransaction(String token, Transaction transaction) {
        this.token = token;
        this.transaction = transaction;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }
}
