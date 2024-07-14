package com.example.travelvalda.entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "Bookings",
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
public class Booking {
    @PrimaryKey(autoGenerate = true)
    public int bookingId;
    public int propertyId;
    public int guestId;
    public String startDate;
    public String endDate;
    public String status;
}
