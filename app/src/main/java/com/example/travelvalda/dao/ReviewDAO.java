package com.example.travelvalda.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.travelvalda.models.Review;

import java.util.List;

@Dao
public interface ReviewDAO {
    @Insert
    void insertReview(Review review);

    @Query("SELECT * FROM reviews WHERE propertyId = :propertyId")
    List<Review> findReviewsByPropertyId(int propertyId);
}
