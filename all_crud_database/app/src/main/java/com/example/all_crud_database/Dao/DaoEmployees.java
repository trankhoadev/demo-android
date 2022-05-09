package com.example.all_crud_database.Dao;

import com.example.all_crud_database.Model.Employees;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class DaoEmployees {
    public DatabaseReference databaseReference;

    public DaoEmployees() {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(Employees.class.getSimpleName());
    }

    public Task<Void> add(Employees e) {
        return databaseReference.push().setValue(e);
    }

    public Task<Void> update(String key, HashMap<String, Object> hashMap) {
        return databaseReference.child(key).updateChildren(hashMap);
    }

    public Task<Void> delete(String key) {
        return databaseReference.child(key).removeValue();
    }

}
