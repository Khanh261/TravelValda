package com.example.travelvalda.Public;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travelvalda.R;
import com.example.travelvalda.dao.BookingDAO;
import com.example.travelvalda.models.Booking;
import com.example.travelvalda.models.SharedPrefsUtil;

import java.util.ArrayList;
import java.util.List;

public class BookingHistoryActivity extends AppCompatActivity {

    private RecyclerView recyclerViewBookings;
    private com.example.travelvalda.adapters.BookingAdapter bookingAdapter;
    private List<Booking> bookingList;

    private ImageView imageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_history);

        // Thiết lập RecyclerView
        recyclerViewBookings = findViewById(R.id.recyclerViewBookings);
        recyclerViewBookings.setLayoutManager(new LinearLayoutManager(this));

        imageView = findViewById(R.id.ivBack);
        imageView.setOnClickListener(this::onClickBack);

        bookingList = new ArrayList<>();
        bookingAdapter = new com.example.travelvalda.adapters.BookingAdapter(bookingList);
        recyclerViewBookings.setAdapter(bookingAdapter);

        // Lấy id của người dùng hiện tại từ SharedPrefsUtil
        String currentUserId = SharedPrefsUtil.getUserId(this);
        Toast.makeText(this, currentUserId, Toast.LENGTH_SHORT).show();

        // Lấy dữ liệu từ Firebase chỉ với các booking có guestId trùng với id của người dùng hiện tại
        BookingDAO bookingDAO = new BookingDAO();
        bookingDAO.getBookingsForCurrentUser(currentUserId, new BookingDAO.FirestoreCallback() {
            @Override
            public void onCallback(List<Booking> bookings) {
                if (bookings != null) {
                    bookingList.clear();
                    bookingList.addAll(bookings);
                    bookingAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(BookingHistoryActivity.this, "No bookings found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCallback(boolean b) {

            }
        });
    }

    private void onClickBack(View view) {
        Intent intent = new Intent(this, HomePageActivity.class);
        startActivity(intent);
    }
}
