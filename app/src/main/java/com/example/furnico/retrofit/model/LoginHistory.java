package com.example.furnico.retrofit.model;
import java.util.Date;

public class LoginHistory {

    private Integer id;
    private Integer customerid;
    private Date loginDate;
    private boolean loginSuccessful;

    public LoginHistory() {
    }

    public LoginHistory(int customerid, Date loginDate, boolean loginSuccessful) {
        this.customerid = customerid;
        this.loginDate = loginDate;
        this.loginSuccessful = loginSuccessful;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getCustomerid() {
        return customerid;
    }

    public void setCustomerid(int customerid) {
        this.customerid = customerid;
    }

    public Date getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }

    public boolean isLoginSuccessful() {
        return loginSuccessful;
    }

    public void setLoginSuccessful(boolean loginSuccessful) {
        this.loginSuccessful = loginSuccessful;
    }
}
