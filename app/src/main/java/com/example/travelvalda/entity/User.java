package com.example.travelvalda.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Users")
public class User {
    @PrimaryKey(autoGenerate = true)
    public int userId;
    public String username;
    public String password;
    public String email;
    public String userType;
}
