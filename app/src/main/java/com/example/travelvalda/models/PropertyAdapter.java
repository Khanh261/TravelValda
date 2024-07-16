package com.example.travelvalda.models;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travelvalda.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class PropertyAdapter extends RecyclerView.Adapter<PropertyAdapter.PropertyViewHolder> {

    private List<Property> properties = new ArrayList<>();

    public void setProperties(List<Property> properties) {
        this.properties = properties;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PropertyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.property_item, parent, false);
        return new PropertyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PropertyViewHolder holder, int position) {
        Property property = properties.get(position);
        holder.tvTitle.setText(property.getTitle());
        holder.tvDescription.setText(property.getDescription());
        holder.tvPricePerNight.setText("$" + property.getPricePerNight());
        holder.tvLocation.setText(property.getLocation());

        // Load image using Picasso library
        Picasso.get().load(property.getImageUrl()).placeholder(R.drawable.home).into(holder.ivPropertyImage);
    }

    @Override
    public int getItemCount() {
        return properties.size();
    }

    static class PropertyViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvDescription, tvPricePerNight, tvLocation;
        ImageView ivPropertyImage;

        public PropertyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            tvPricePerNight = itemView.findViewById(R.id.tvPricePerNight);
            tvLocation = itemView.findViewById(R.id.tvLocation);
            ivPropertyImage = itemView.findViewById(R.id.ivPropertyImage);
        }
    }
}
