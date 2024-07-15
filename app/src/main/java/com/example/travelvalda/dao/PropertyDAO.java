package com.example.travelvalda.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.travelvalda.models.Property;

import java.util.List;

@Dao
public interface PropertyDAO {

    @Insert
    void insertProperty(Property property);

    @Update
    void updateProperty(Property property);

    @Delete
    void deleteProperty(Property property);

    @Query("SELECT * FROM properties")
    List<Property> getAllProperties();

    @Query("SELECT * FROM properties WHERE propertyId = :propertyId")
    Property getPropertyById(int propertyId);

    @Query("SELECT * FROM properties WHERE location LIKE :location")
    List<Property> findPropertiesByLocation(String location);
}
