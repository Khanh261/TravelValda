package com.example.travelvalda.owner;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travelvalda.R;
import com.example.travelvalda.models.Booking;
import com.example.travelvalda.models.BookingAdapter;

import java.util.ArrayList;
import java.util.List;

public class OwnerDashboardActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private BookingAdapter bookingAdapter;
    private List<Booking> bookingList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_dashboard);

        // Khởi tạo dữ liệu cứng
        bookingList = new ArrayList<>();
        bookingList.add(new Booking(1, 1, 1, "2024-08-01", "2024-08-05", "Pending"));
        bookingList.add(new Booking(2, 2, 1, "2024-08-10", "2024-08-15", "Accepted"));

        // Khởi tạo RecyclerView và Adapter
        recyclerView = findViewById(R.id.recyclerViewBookings);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        bookingAdapter = new BookingAdapter(bookingList);
        recyclerView.setAdapter(bookingAdapter);
    }
}
