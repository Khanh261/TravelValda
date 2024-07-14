package com.example.travelvalda.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.example.travelvalda.entity.Property;
import java.util.List;

@Dao
public interface PropertyDao {
    @Insert
    void insert(Property property);

    @Query("SELECT * FROM Properties")
    List<Property> getAllProperties();
}
