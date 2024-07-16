package com.example.travelvalda.Public;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.travelvalda.R;
import com.example.travelvalda.dao.UserDAO;
import com.example.travelvalda.models.SharedPrefsUtil;
import com.example.travelvalda.models.Users;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

public class ProfileActivity extends AppCompatActivity {
    private TextView NameTextView;
    private TextInputLayout fullNameTextInputLayout;
    private TextInputLayout emailTextInputLayout;
    private TextInputLayout phoneTextInputLayout;
    private Button btnUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String userId = SharedPrefsUtil.getUserId(this);
        setContentView(R.layout.activity_profile);

        NameTextView = findViewById(R.id.txtName);
        fullNameTextInputLayout = findViewById(R.id.txtFullName);
        emailTextInputLayout = findViewById(R.id.txtEmail);
        phoneTextInputLayout = findViewById(R.id.txtPhone);
        btnUpdate = findViewById(R.id.btnUpdate);

        if (userId != null) {
            UserDAO userDAO = new UserDAO();
            userDAO.getUserProfile(userId, new UserDAO.FirestoreCallbackUser() {
                @Override
                public void onCallback(List<Users> userProfileList) {
                    if (!userProfileList.isEmpty()) {
                        Users user = userProfileList.get(0); // Assuming userId is unique, so list will have only one user
                        fullNameTextInputLayout.getEditText().setText(user.getUserName());
                        NameTextView.setText(user.getUserName());
                        emailTextInputLayout.getEditText().setText(user.getEmail());
                        phoneTextInputLayout.getEditText().setText(user.getPhone());
                    }
                }
            });
        }

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newFullName = fullNameTextInputLayout.getEditText().getText().toString().trim();
                String newEmail = emailTextInputLayout.getEditText().getText().toString().trim();
                String newPhone = phoneTextInputLayout.getEditText().getText().toString().trim();

                if (userId != null) {
                    UserDAO userDAO = new UserDAO();
                    userDAO.updateUserProfile(userId, newFullName, newEmail, newPhone, new UserDAO.FirestoreCallbackUpdate() {
                        @Override
                        public void onUpdateSuccess() {
                            Toast.makeText(ProfileActivity.this, "Profile updated successfully", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onUpdateFailure(String error) {
                            Toast.makeText(ProfileActivity.this, "Failed to update profile: " + error, Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
}
