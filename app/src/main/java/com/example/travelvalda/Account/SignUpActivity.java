package com.example.travelvalda.Account;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.travelvalda.DashBoard;
import com.example.travelvalda.R;
import com.example.travelvalda.models.User;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class SignUpActivity extends AppCompatActivity {
    FirebaseAuth auth;
    Button btnSignUp, btnBackLogin;
    TextInputLayout txtName, txtUserName, txtEmail, txtPhoneNo, txtPassword;

    private void bindingView(){
        btnSignUp = findViewById(R.id.btnSignUp);
        btnBackLogin = findViewById(R.id.btnLogin);
        txtName = findViewById(R.id.txtName);
        txtUserName = findViewById(R.id.txtUserName);
        txtEmail = findViewById(R.id.txtEmail);
        txtPhoneNo = findViewById(R.id.txtPhoneNo);
        txtPassword = findViewById(R.id.txtPassword);
    }
    private void bindingAction(){
        btnSignUp.setOnClickListener(this::onBtnSignUp);
    }

    private void onBtnSignUp(View view) {
        String name = txtName.getEditText().getText().toString();
        String userName = txtUserName.getEditText().getText().toString();
        String email = txtEmail.getEditText().getText().toString();
        String phone = txtPhoneNo.getEditText().getText().toString();
        String password = txtPassword.getEditText().getText().toString();

        if (name.isEmpty() || userName.isEmpty() || email.isEmpty() || phone.isEmpty() || password.isEmpty()) {
            Toast.makeText(SignUpActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
        } else {
            checktEmail(email);
        }
    }

    private void checktEmail(String email)
    {
        auth = FirebaseAuth.getInstance();
        auth.fetchSignInMethodsForEmail(email)
                .addOnCompleteListener(task ->{
                    if (task.isSuccessful()) {
                        if (task.getResult().getSignInMethods().size() > 0) {
                            Toast.makeText(SignUpActivity.this, "Email already exists", Toast.LENGTH_SHORT).show();
                        } else {
                            signUpUser();
                        }
                    } else {
                        Toast.makeText(SignUpActivity.this, "Error checking email existence: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

    }
    private void signUpUser() {
        auth = FirebaseAuth.getInstance();

        String name = txtName.getEditText().getText().toString();
        String userName = txtUserName.getEditText().getText().toString();
        String email = txtEmail.getEditText().getText().toString();
        String phone = txtPhoneNo.getEditText().getText().toString();
        String password = txtPassword.getEditText().getText().toString();

        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        String userId = auth.getCurrentUser().getUid();
                        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference().child("Users").child(userId);

                        User newUser = new User(userName, password, phone, email, "1");
                        usersRef.setValue(newUser);

                        Toast.makeText(SignUpActivity.this, "Sign up successfull", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                            Toast.makeText(SignUpActivity.this, "Email already exists", Toast.LENGTH_SHORT).show();
                        } else if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                            Toast.makeText(SignUpActivity.this, "Invalid email", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(SignUpActivity.this, "Sign up failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


    private void onBtnBackLogin(View view) {
        Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        auth = FirebaseAuth.getInstance();

        bindingView();
        bindingAction();
    }
}