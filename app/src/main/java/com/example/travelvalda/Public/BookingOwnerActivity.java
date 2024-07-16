package com.example.travelvalda.Public;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travelvalda.R;
import com.example.travelvalda.adapters.BookingOwnerAdapter;
import com.example.travelvalda.dao.BookingDAO;
import com.example.travelvalda.models.Booking;
import com.example.travelvalda.models.SharedPrefsUtil;

import java.util.ArrayList;
import java.util.List;

public class BookingOwnerActivity extends AppCompatActivity {

    private RecyclerView recyclerViewBookings;
    private BookingOwnerAdapter bookingAdapter;
    private List<Booking> bookingList;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_owner);

        // Thiết lập RecyclerView
        recyclerViewBookings = findViewById(R.id.recyclerViewBookings);
        recyclerViewBookings.setLayoutManager(new LinearLayoutManager(this));
        View imageView = findViewById(R.id.ivBack);
        imageView.setOnClickListener(this::onClickBack);
        bookingList = new ArrayList<>();
        bookingAdapter = new BookingOwnerAdapter(bookingList, new BookingOwnerAdapter.OnBookingActionListener() {
            @Override
            public void onAcceptClick(int position, Booking booking) {
                // Xử lý logic khi nhấn Accept
                acceptBooking(booking);
            }

            @Override
            public void onDenyClick(int position, Booking booking) {
                // Xử lý logic khi nhấn Deny
                denyBooking(booking);
            }
        });
        recyclerViewBookings.setAdapter(bookingAdapter);

        // Load danh sách booking từ Firebase
        loadBookingsForOwner();
    }

    private void onClickBack(View view) {
        Intent intent = new Intent(this, HomePageActivity.class);
        startActivity(intent);
    }

    private void loadBookingsForOwner() {
        // Lấy id của người dùng hiện tại (ví dụ)
        String currentUserId = SharedPrefsUtil.getUserId(this);

        // Lấy danh sách booking của các property của owner
        BookingDAO bookingDAO = new BookingDAO();
        bookingDAO.getBookingsForOwner(currentUserId, new BookingDAO.FirestoreCallback() {
            @Override
            public void onCallback(List<Booking> bookings) {
                if (bookings != null) {
                    bookingList.clear();
                    bookingList.addAll(bookings);
                    bookingAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(BookingOwnerActivity.this, "No bookings found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCallback(boolean b) {
                if (b) {
                    Toast.makeText(BookingOwnerActivity.this, "Load bookings success", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(BookingOwnerActivity.this, "Load bookings failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void acceptBooking(Booking booking) {
        BookingDAO bookingDAO = new BookingDAO();
        bookingDAO.acceptBooking(booking.getBookingId(), new BookingDAO.FirestoreCallback() {
            @Override
            public void onCallback(List<Booking> bookingList) {

            }

            @Override
            public void onCallback(boolean success) {
                if (success) {
                    Toast.makeText(BookingOwnerActivity.this, "Accepted Booking: " + booking.getBookingId(), Toast.LENGTH_SHORT).show();
                    // Nếu muốn cập nhật trạng thái booking trong danh sách hiển thị, bạn có thể làm như sau:
                    // booking.setStatus("accept");
                    // bookingAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(BookingOwnerActivity.this, "Failed to accept booking", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void denyBooking(Booking booking) {
        BookingDAO bookingDAO = new BookingDAO();
        bookingDAO.denyBooking(booking.getBookingId(), new BookingDAO.FirestoreCallback() {
            @Override
            public void onCallback(List<Booking> bookingList) {

            }

            @Override
            public void onCallback(boolean success) {
                if (success) {
                    Toast.makeText(BookingOwnerActivity.this, "Denied Booking: " + booking.getBookingId(), Toast.LENGTH_SHORT).show();
                    // Nếu muốn cập nhật trạng thái booking trong danh sách hiển thị, bạn có thể làm như sau:
                    // booking.setStatus("deny");
                    // bookingAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(BookingOwnerActivity.this, "Failed to deny booking", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
