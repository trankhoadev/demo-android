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

public class RegistryActivity extends AppCompatActivity {
    Button btnSignUp;
    TextView txtUserName, txtPassword, txtRepassword;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registry);

        btnSignUp = findViewById(R.id.btnRegistry);
        txtUserName = findViewById(R.id.txtUserReg);
        txtPassword = findViewById(R.id.txtPasswordReg);
        txtRepassword = findViewById(R.id.txtRePasswordReg);
        mAuth = FirebaseAuth.getInstance();

        btnSignUp.setOnClickListener(view -> {
            checkSignupUser();
        });
    }

    private void checkSignupUser() {
        String userName = txtUserName.getText().toString();
        String password = txtPassword.getText().toString();
        String rePassword = txtRepassword.getText().toString();

        if(userName.isEmpty()) {
            txtUserName.setError("Ten khong dc rong");
            txtUserName.requestFocus();
        }
        else if(password.isEmpty()) {
            txtPassword.setError("MK khong dc rong");
            txtPassword.requestFocus();
        }
        else if(rePassword.isEmpty()) {
            txtRepassword.setError("Nhap lai mk khong dc rong");
            txtRepassword.requestFocus();
        }
        else if (!password.equals(rePassword)) {
            txtRepassword.setError("Nhap lai mk khong giong");
            txtRepassword.requestFocus();
        }
        else {
            mAuth.createUserWithEmailAndPassword(userName, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()) {
                        Toast.makeText(RegistryActivity.this, "Tao tai khoan thanh cong", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(RegistryActivity.this, FinalActivity.class));
                    }
                    else {
                        Toast.makeText(RegistryActivity.this, "Tao tai khoan loi: " + task.getException().toString() , Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}