package com.example.travelvalda.Public;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travelvalda.R;
import com.example.travelvalda.adapters.BookingAdapter;
import com.example.travelvalda.adapters.RealtimeDatabaseBookingAdapter;
import com.example.travelvalda.models.Booking;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class HotelDetailsActivity extends AppCompatActivity {
    private TextView txtCheckInDate, txtCheckOutDate;
    private List<Booking> bookingList = new ArrayList<>();
    private BookingAdapter bookingAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        bookingAdapter = new BookingAdapter(bookingList);
        recyclerView.setAdapter(bookingAdapter);

        Intent intent = getIntent();

        String imageUrl = intent.getStringExtra("imageUrl");
        String title = intent.getStringExtra("title");
        String description = intent.getStringExtra("description");
        String location = intent.getStringExtra("location");
        String price = intent.getStringExtra("pricePerNight");
        String propertyId = intent.getStringExtra("propertyId");

        ImageView imageView = findViewById(R.id.imageImage);
        TextView tvTitle = findViewById(R.id.txtParadiseresort);
        TextView tvDescription = findViewById(R.id.txtDescription);
        TextView tvLocation = findViewById(R.id.txtLocation);
        TextView tvPricePerNight = findViewById(R.id.txtPrice);
        txtCheckInDate = findViewById(R.id.txtCheckInDate);
        txtCheckOutDate = findViewById(R.id.txtCheckOutDate);
        ImageButton backButton = findViewById(R.id.btnAkariconsOne);

        Picasso.get().load(imageUrl).placeholder(R.drawable.home).into(imageView);
        tvTitle.setText(title);
        tvDescription.setText(description);
        tvLocation.setText(location);
        tvPricePerNight.setText(price);

        backButton.setOnClickListener(v -> finish());
        findViewById(R.id.btnCheckIn).setOnClickListener(v -> showDatePicker(true));
        findViewById(R.id.btnCheckOut).setOnClickListener(v -> showDatePicker(false));
    }

    public void onReserveButtonClick(View view) {
        createBooking();
    }

    private void createBooking() {
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        //get property of this house
        Booking booking = new Booking();
        booking.setGuestId(userId);
        booking.setStartDate(txtCheckInDate.getText().toString());
        booking.setEndDate(txtCheckOutDate.getText().toString());
        booking.setStatus("Pending");

        RealtimeDatabaseBookingAdapter firestoreBookingAdapter = new RealtimeDatabaseBookingAdapter();
        firestoreBookingAdapter.createBooking(booking, new RealtimeDatabaseBookingAdapter.Callback() {
            @Override
            public void onSuccess(String bookingId) {
                booking.setBookingId(bookingId);
                bookingList.add(booking);
                bookingAdapter.notifyDataSetChanged();
                Toast.makeText(HotelDetailsActivity.this, "Booking request sent!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(String message) {
                Toast.makeText(HotelDetailsActivity.this, "Failed to send booking request: " + message, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void showDatePicker(boolean isCheckIn) {
        MaterialDatePicker<Long> datePicker = MaterialDatePicker.Builder.datePicker()
                .setTitleText(isCheckIn ? "Select check-in date" : "Select check-out date")
                .build();
        datePicker.addOnPositiveButtonClickListener(selection -> {
            String selectedDate = datePicker.getHeaderText();
            if (isCheckIn) {
                txtCheckInDate.setText(selectedDate);
                Toast.makeText(this, "Check-in date: " + selectedDate, Toast.LENGTH_SHORT).show();
            } else {
                txtCheckOutDate.setText(selectedDate);
                Toast.makeText(this, "Check-out date: " + selectedDate, Toast.LENGTH_SHORT).show();
            }
        });

        datePicker.show(getSupportFragmentManager(), isCheckIn ? "CHECK_IN_PICKER" : "CHECK_OUT_PICKER");
    }
}
