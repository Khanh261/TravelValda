package com.example.travelvalda.Public;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.travelvalda.R;
import com.squareup.picasso.Picasso;

public class HotelDetailsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();

        // Assuming the image is passed as a URL. If it's a resource ID, adjust accordingly.
        String imageUrl = intent.getStringExtra("imageUrl");
        String title = intent.getStringExtra("title");
        String description = intent.getStringExtra("description");
        String location = intent.getStringExtra("location");
        String price = intent.getStringExtra("pricePerNight");

        ImageView imageView = findViewById(R.id.imageImage);
        TextView tvTitle = findViewById(R.id.txtParadiseresort);
        TextView tvDescription = findViewById(R.id.txtDescription);
        TextView tvLocation = findViewById(R.id.txtLocation);
        TextView tvPricePerNight = findViewById(R.id.txtPrice);

        // Load image using Picasso
        Picasso.get().load(imageUrl).placeholder(R.drawable.home).into(imageView);
        tvTitle.setText(title);
        tvDescription.setText(description);
        tvLocation.setText(location);
        tvPricePerNight.setText(price);
    }



    public void onReserveButtonClick(View view) {
        showReservationDialog();
    }

    private void showReservationDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_reserve_confirm, null);
        dialogBuilder.setView(dialogView);


        Button btnConfirm = dialogView.findViewById(R.id.btnConfirm);
        Button btnCancel = dialogView.findViewById(R.id.btnCancel);



        final AlertDialog alertDialog = dialogBuilder.create();
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        alertDialog.show();
    }
}
