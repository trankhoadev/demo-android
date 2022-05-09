package com.example.all_crud_database;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivty extends AppCompatActivity {
     TextView txtUser, txtPassword;
     Button btnLogin;
     FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_activty);

        txtUser = findViewById(R.id.txtUserLogin);
        txtPassword = findViewById(R.id.txtPasswordLogin);
        btnLogin = findViewById(R.id.btnLogin);
        mAuth = FirebaseAuth.getInstance();

        btnLogin.setOnClickListener(view -> {
            checkLoginUser();
        });
    }

    private void checkLoginUser() {
        String email = txtUser.getText().toString();
        String password = txtPassword.getText().toString();

        if(email.isEmpty()) {
            txtUser.setError("Ten tai khoan khong duoc rong");
            txtUser.requestFocus();
        }
        else if(password.isEmpty()) {
            txtPassword.setError("Mat khau khong duoc rong");
            txtPassword.requestFocus();
        }
        else {
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()) {
                        Toast.makeText(LoginActivty.this, "Login thanh cong", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LoginActivty.this, FinalActivity.class));
                    }
                    else {
                        Toast.makeText(LoginActivty.this, "Login that bai", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}