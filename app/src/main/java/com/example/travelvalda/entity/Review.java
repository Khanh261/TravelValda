package com.example.travelvalda.entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "Reviews",
        foreignKeys = {
                @ForeignKey(entity = Property.class,
                        parentColumns = "propertyId",
                        childColumns = "propertyId",
                        onDelete = ForeignKey.CASCADE),
                @ForeignKey(entity = User.class,
                        parentColumns = "userId",
                        childColumns = "guestId",
                        onDelete = ForeignKey.CASCADE)
        })
public class Review {
    @PrimaryKey(autoGenerate = true)
    public int reviewId;
    public int propertyId;
    public int guestId;
    public int rating;
    public String comment;
}
