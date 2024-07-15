package com.example.travelvalda.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.travelvalda.models.User;

import java.util.List;

@Dao
public interface UserDAO {
    @Insert
    void insertUser(User user);

    @Query("SELECT * FROM users WHERE userId = :userId")
    User getUserById(int userId);

    //get all users
    @Query("SELECT * FROM users")
    List<User> getAllUsers();
}
