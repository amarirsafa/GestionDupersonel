package com.example.gestiondupersonel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gestiondupersonel.classes.Department;
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
        final String id = i.getStringExtra("id");

        final TextView fullName,CIN,Address,Email,Birthday,HiringDay,Department;
        Button edit;
        fullName = findViewById(R.id.fullName);
        CIN = findViewById(R.id.cin);
        Address = findViewById(R.id.address);
        Email = findViewById(R.id.Email);
        Department = findViewById(R.id.department);
        Birthday = findViewById(R.id.birthday);
        HiringDay = findViewById(R.id.hiringDate);
        mDataBaseStore = FirebaseFirestore.getInstance();
        edit = findViewById(R.id.editEmp);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(EmployeeActivity.this,EditEmployee.class);
                i.putExtra("id",id);
                startActivity(i);
            }
        });
        assert id != null;
        documentReference = mDataBaseStore.collection("Company").document("np0SxpArRay4njQeRgH9")
                .collection("Employees").document(id);
        documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    assert document != null;
                    if (document.exists()) {
                        tempEmployee = document.toObject(Employee.class);
                        assert tempEmployee != null;
                        if(tempEmployee.getFullName() != null){
                            fullName.setText(tempEmployee.getFullName());
                        }
                        if(tempEmployee.getDepartment() != null){
                            Department.setText(tempEmployee.getDepartment());
                        }
                        if(tempEmployee.getCIN() != null){
                            CIN.setText(tempEmployee.getCIN());
                        }
                        if(tempEmployee.getAddress() != null){
                            Address.setText(tempEmployee.getAddress());
                        }
                        if(tempEmployee.getEmail() != null){
                            Email.setText(tempEmployee.getEmail());
                        }

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