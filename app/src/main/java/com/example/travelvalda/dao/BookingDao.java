package com.example.travelvalda.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.example.travelvalda.entity.Booking;
import java.util.List;

@Dao
public interface BookingDao {
    @Insert
    void insert(Booking booking);

    @Query("SELECT * FROM Bookings")
    List<Booking> getAllBookings();
}
