package com.example.travelvalda.Public;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.travelvalda.R;

public class HotelDetailsActivity extends AppCompatActivity {

    private ImageView ivHotelImage;
    private TextView tvHotelName;
    private TextView tvHotelLocation;
    private AlertDialog dialog; // Khai báo dialog ở đây

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail); // Thay thế bằng tên file layout thực tế của bạn

        // Ánh xạ các view từ layout vào biến tương ứng
        ivHotelImage = findViewById(R.id.imageImage); // Ví dụ
        tvHotelName = findViewById(R.id.txtParadiseresort); // Ví dụ
        tvHotelLocation = findViewById(R.id.txtDescription); // Ví dụ
    }

    public void onReserveButtonClick(View view) {
        // Inflate dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_reserve_confirm, null);
        builder.setView(dialogView);

        // Khởi tạo các thành phần trong dialog
        TextView txtReservationDetails = dialogView.findViewById(R.id.txtReservationDetails);
        // Có thể set thông tin đặt phòng từ các TextView trong layout chi tiết homestay vào đây (nếu cần)

        Button btnConfirm = dialogView.findViewById(R.id.btnConfirm);
        Button btnCancel = dialogView.findViewById(R.id.btnCancel);

        // Thiết lập sự kiện onClick cho nút Confirm
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Xử lý xác nhận đặt phòng
                // Ví dụ: Hiển thị thông báo xác nhận, gửi yêu cầu đặt phòng đi
                Toast.makeText(HotelDetailsActivity.this, "Booking confirmed!", Toast.LENGTH_SHORT).show();
                // Đóng dialog sau khi xác nhận thành công (nếu cần)
                dialog.dismiss();
            }
        });

        // Thiết lập sự kiện onClick cho nút Cancel
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Đóng dialog khi người dùng hủy đặt phòng
                dialog.dismiss();
            }
        });

        // Khởi tạo Dialog
        dialog = builder.create();
        // Hiển thị Dialog
        dialog.show();
    }
}
