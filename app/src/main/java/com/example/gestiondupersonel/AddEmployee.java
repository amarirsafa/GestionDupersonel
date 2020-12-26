package com.example.gestiondupersonel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gestiondupersonel.classes.Department;
import com.example.gestiondupersonel.classes.Employee;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Date;

public class AddEmployee extends AppCompatActivity {
    private FirebaseFirestore mDataBaseStore;
    private CollectionReference collectionReference;
    private Employee Emp1;

    private Department dep1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_employee);
        Emp1 = new Employee();
        dep1 = new Department();


        mDataBaseStore = FirebaseFirestore.getInstance();
        collectionReference = mDataBaseStore.collection("Company").document("np0SxpArRay4njQeRgH9")
                .collection("Employees");


        findViewById(R.id.add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText fullName = findViewById(R.id.fullName);
                Emp1.setFullName(fullName.getText().toString());
                EditText cin = findViewById(R.id.CIN);
                Emp1.setCIN(cin.getText().toString());
                EditText email = findViewById(R.id.email);
                Emp1.setEmail(email.getText().toString());
                EditText address = findViewById(R.id.address);
                Emp1.setAddress(address.getText().toString());
                EditText hiringDate = findViewById(R.id.hiringDate);
                Emp1.setHiringDate(hiringDate.getText().toString());
                EditText birthday = findViewById(R.id.birthday);
                Emp1.setBirthDay(birthday.getText().toString());
//                dep1.setHeadChef(Emp1);
//                dep1.setNumberOfEmployees(20);
//                dep1.setTitle("Computer Science department");
//                collectionReference.add(dep1);
                collectionReference.add(Emp1).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(AddEmployee.this, "Employee information saved!", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(AddEmployee.this,ComputerScienceDep.class);
                        startActivity(i);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AddEmployee.this, "Opss! Something went wrong", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }
}