package com.example.gestiondupersonel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.gestiondupersonel.classes.Employee;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class EmployeeActivity extends AppCompatActivity {

    private FirebaseFirestore mDataBaseStore;
    private DocumentReference documentReference;
    private Employee tempEmployee;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee);

        Intent i = getIntent();
        String emp = i.getStringExtra("id");

        mDataBaseStore = FirebaseFirestore.getInstance();
        documentReference = mDataBaseStore.collection("Company").document("np0SxpArRay4njQeRgH9")
                .collection("Employees").document("emp");
        documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    assert document != null;
                    if (document.exists()) {
                        tempEmployee = document.toObject(Employee.class);
                    } else {
                        Toast.makeText(EmployeeActivity.this, "No such document", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(EmployeeActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });





    }
}