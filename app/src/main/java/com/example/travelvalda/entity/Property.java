package com.example.travelvalda.entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "Properties",
        foreignKeys = @ForeignKey(entity = User.class,
                parentColumns = "userId",
                childColumns = "ownerId",
                onDelete = ForeignKey.CASCADE))
public class Property {
    @PrimaryKey(autoGenerate = true)
    public int propertyId;
    public int ownerId;
    public String title;
    public String description;
    public double pricePerNight;
    public String location;
}
