package com.example.travelvalda.models;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travelvalda.R;

import java.util.List;

public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.BookingViewHolder> {

    private List<Booking> bookingList;

    public BookingAdapter(List<Booking> bookingList) {
        this.bookingList = bookingList;
    }

    @NonNull
    @Override
    public BookingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_owner_booking_detail, parent, false);
        return new BookingViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BookingViewHolder holder, int position) {
        Booking booking = bookingList.get(position);
        holder.bind(booking);
    }

    @Override
    public int getItemCount() {
        return bookingList.size();
    }

    public static class BookingViewHolder extends RecyclerView.ViewHolder {
        private TextView txtStartDate;
        private TextView txtEndDate;
        private TextView txtStatus;

        public BookingViewHolder(@NonNull View itemView) {
            super(itemView);
            txtStartDate = itemView.findViewById(R.id.txtStartDate);
            txtEndDate = itemView.findViewById(R.id.txtEndDate);
            txtStatus = itemView.findViewById(R.id.txtStatus);
        }

        public void bind(Booking booking) {
            txtStartDate.setText("Start Date: " + booking.getStartDate());
            txtEndDate.setText("End Date: " + booking.getEndDate());
            txtStatus.setText("Status: " + booking.getStatus());
        }
    }
}
