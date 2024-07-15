package com.example.travelvalda.models;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.example.travelvalda.models.Property;
import com.example.travelvalda.models.User;

@Entity(tableName = "reviews",
        foreignKeys = {
                @ForeignKey(entity = Property.class,
                        parentColumns = "propertyId",
                        childColumns = "propertyId"),
                @ForeignKey(entity = User.class,
                        parentColumns = "userId",
                        childColumns = "guestId")
        })
public class Review {
    @PrimaryKey(autoGenerate = true)
    public int reviewId;
    public int propertyId;
    public int guestId;
    public int rating;
    public String comment;

    public Review() {
    }

    public Review(int reviewId, int propertyId, int guestId, int rating, String comment) {
        this.reviewId = reviewId;
        this.propertyId = propertyId;
        this.guestId = guestId;
        this.rating = rating;
        this.comment = comment;
    }

    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public int getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(int propertyId) {
        this.propertyId = propertyId;
    }

    public int getGuestId() {
        return guestId;
    }

    public void setGuestId(int guestId) {
        this.guestId = guestId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
