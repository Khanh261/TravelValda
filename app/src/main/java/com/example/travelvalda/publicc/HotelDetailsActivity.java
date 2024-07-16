package com.example.travelvalda.publicc;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.travelvalda.R;

public class HotelDetailsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail); // Đảm bảo tên layout là chính xác và tồn tại trong thư mục res/layout
    }

    public void onReserveButtonClick(View view) {
        showReservationDialog();
    }

    private void showReservationDialog() {
        // Tạo AlertDialog
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_reserve_confirm, null);
        dialogBuilder.setView(dialogView);

        // Ánh xạ các thành phần trong dialog.xml

        Button btnConfirm = dialogView.findViewById(R.id.btnConfirm);
        Button btnCancel = dialogView.findViewById(R.id.btnCancel);



        // Thiết lập các sự kiện cho các nút trong dialog
        final AlertDialog alertDialog = dialogBuilder.create();
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Xử lý khi nhấn nút Xác nhận
                // Có thể thực hiện các hành động liên quan đến đặt phòng ở đây
                alertDialog.dismiss(); // Đóng dialog sau khi xử lý
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Xử lý khi nhấn nút Hủy
                alertDialog.dismiss(); // Đóng dialog sau khi xử lý
            }
        });

        // Hiển thị dialog
        alertDialog.show();
    }
}
