package com.example.travelvalda.models;


public class User {

    public int userId;
    public String username;
    public String password;
    public String email;
    public String Phone;
    public String userType;

    public User() {
    }
    public User(String username, String password, String email, String phone, String userType) {

        this.username = username;
        this.password = password;
        this.email = email;
        this.Phone = phone;
        this.userType = userType;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public User(int userId, String username, String password, String email, String userType) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.userType = userType;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;

    }
}
