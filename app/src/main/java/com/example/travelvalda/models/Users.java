package com.example.travelvalda.models;

public class Users {
    private String userId;
    private String name;
    private String userName;
    private String phone;
    private String email;
    private int roleId;

    // Constructor
    public Users(String name, String userName, String phone, String email, int roleId) {
        this.name = name;
        this.userName = userName;
        this.phone = phone;
        this.email = email;
        this.roleId = roleId;
    }
    public Users() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
}
