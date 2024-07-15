package com.example.travelvalda.dao;

import androidx.room.Insert;
import androidx.room.Query;

import com.example.travelvalda.models.User;

public interface UserDAO {
    @Insert
    void insertUser(User user);

    @Query("SELECT * FROM users WHERE userId = :userId")
    User getUserById(int userId);
}
