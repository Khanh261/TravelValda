package com.example.travelvalda.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travelvalda.Public.HotelDetailsActivity;
import com.example.travelvalda.R;
import com.example.travelvalda.models.Property;

import java.util.List;

public class PropertyAdapter extends RecyclerView.Adapter<PropertyAdapter.ViewHolder> {
    private List<Property> propertyList;

    public PropertyAdapter(List<Property> propertyList){
        this.propertyList = propertyList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.property_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Property property = propertyList.get(position);
        holder.setData(property);
    }

    @Override
    public int getItemCount() {
        return propertyList.size();
    }

    public void setPropertyList(List<Property> propertyList) {
        this.propertyList = propertyList;

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView hotelName;
        private TextView hotelLocation;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            bindingView();
            bindingAction();
        }

        private void bindingView() {
            hotelName = itemView.findViewById(R.id.tvHotelName4);
            hotelLocation = itemView.findViewById(R.id.tvHotelLocation4);

            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    Property property = propertyList.get(position);
                    Intent intent = new Intent(v.getContext(), HotelDetailsActivity.class);
                    intent.putExtra("imageUrl", property.getImageUrl());
                    intent.putExtra("title", property.getTitle());
                    intent.putExtra("description", property.getDescription());
                    intent.putExtra("location", property.getLocation());
                    intent.putExtra("pricePerNight", property.getPricePerNight());
                    v.getContext().startActivity(intent);
                }
            });
        }


        private void bindingAction() {

        }

        public void setData(Property property) {
            hotelName.setText(property.getTitle());
            hotelLocation.setText(property.getLocation());
        }
    }
}
