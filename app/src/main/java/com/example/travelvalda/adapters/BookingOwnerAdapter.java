package com.example.travelvalda.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travelvalda.R;
import com.example.travelvalda.models.Booking;

import java.util.List;

public class BookingOwnerAdapter extends RecyclerView.Adapter<BookingOwnerAdapter.ViewHolder> {

    private List<Booking> bookingList;
    private OnBookingActionListener listener;

    public BookingOwnerAdapter(List<Booking> bookingList, OnBookingActionListener listener) {
        this.bookingList = bookingList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_booking_owner, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Booking booking = bookingList.get(position);
        holder.tvBookingDetails.setText("Booking ID: " + booking.getBookingId() +
                "\nProperty ID: " + booking.getPropertyId() +
                "\nStart Date: " + booking.getStartDate() +
                "\nEnd Date: " + booking.getEndDate());

        holder.btnAccept.setOnClickListener(v -> {
            if (listener != null) {
                listener.onAcceptClick(position, booking);
            }
        });

        holder.btnDeny.setOnClickListener(v -> {
            if (listener != null) {
                listener.onDenyClick(position, booking);
            }
        });
    }

    @Override
    public int getItemCount() {
        return bookingList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvBookingDetails;
        Button btnAccept, btnDeny;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvBookingDetails = itemView.findViewById(R.id.tvBookingDetails);
            btnAccept = itemView.findViewById(R.id.btnAccept);
            btnDeny = itemView.findViewById(R.id.btnDeny);
        }
    }

    public interface OnBookingActionListener {
        void onAcceptClick(int position, Booking booking);
        void onDenyClick(int position, Booking booking);
    }
}
