package com.example.travelvalda.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.travelvalda.dao.BookingDAO;
import com.example.travelvalda.dao.PropertyDAO;
import com.example.travelvalda.dao.ReviewDAO;
import com.example.travelvalda.dao.UserDAO;
import com.example.travelvalda.models.Booking;
import com.example.travelvalda.models.Property;
import com.example.travelvalda.models.User;
import com.example.travelvalda.models.Review;

@Database(entities = {User.class, Property.class, Booking.class, Review.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDAO userDao();
    public abstract PropertyDAO propertyDao();
    public abstract BookingDAO bookingDao();
    public abstract ReviewDAO reviewDao();
}
