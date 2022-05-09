package com.example.all_crud_database;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.all_crud_database.Dao.DaoEmployees;
import com.example.all_crud_database.Model.Employees;
import com.google.firebase.auth.FirebaseAuth;

public class FinalActivity extends AppCompatActivity {
    TextView txtFirstname, txtLastname;
    Button btnSubmit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);

        txtFirstname = findViewById(R.id.txtFirstname);
        txtLastname = findViewById(R.id.txtLastname);
        btnSubmit = findViewById(R.id.btnSubmit);
        DaoEmployees daoEmployees = new DaoEmployees();

        btnSubmit.setOnClickListener(view -> {
            checkSubmitData();
            Employees e = new Employees(txtFirstname.getText().toString(), txtLastname.getText().toString());
            daoEmployees.add(e).addOnSuccessListener(suc -> {
                Toast.makeText(this, "Them thanh cong", Toast.LENGTH_SHORT).show();
            }).addOnFailureListener(err -> {
                Toast.makeText(this, "Them that bai", Toast.LENGTH_SHORT).show();
            });
        });
    }

    public void checkSubmitData() {
        String firstName = txtFirstname.getText().toString();
        String lastName = txtLastname.getText().toString();

        if(firstName.isEmpty()) {
            txtFirstname.setError("Firstname not null");
            txtFirstname.requestFocus();
        }
        else if (lastName.isEmpty()) {
            txtLastname.setError("Lastname not null");
            txtLastname.requestFocus();
        }
    }
}