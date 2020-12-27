package com.example.gestiondupersonel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gestiondupersonel.classes.Employee;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class EditEmployee extends AppCompatActivity {

    private FirebaseFirestore mDataBaseStore;
    private DocumentReference documentReference;
    private EditText fullName,CIN,Address,Email,birthday,hiringDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_employee);

        Intent i = getIntent();
        final String id = i.getStringExtra("id");
        mDataBaseStore = FirebaseFirestore.getInstance();
        assert id != null;
        fullName = findViewById(R.id.fullName);
        Address = findViewById(R.id.address);
        Email = findViewById(R.id.email);
        CIN = findViewById(R.id.CIN);
        birthday = findViewById(R.id.birthday);
        hiringDay = findViewById(R.id.hiringDate);
        documentReference = mDataBaseStore.collection("Company").document("np0SxpArRay4njQeRgH9")
                .collection("Employees").document(id);
        documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    assert document != null;
                    if (document.exists()) {
                        final Employee tempEmployee = document.toObject(Employee.class);
                        assert tempEmployee != null;
                        if(tempEmployee.getFullName() != null){
                            fullName.setText(tempEmployee.getFullName());
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
                        if(tempEmployee.getBirthDay() != null){
                            birthday.setText(tempEmployee.getBirthDay());
                        }
                        if(tempEmployee.getHiringDate() != null){
                            hiringDay.setText(tempEmployee.getHiringDate());
                        }
                    } else {
                        Toast.makeText(EditEmployee.this, "No such document", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(EditEmployee.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });
        findViewById(R.id.Edit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            assert document != null;
                            if (document.exists()) {
                                //documentReference.update("");
                            } else {
                                Toast.makeText(EditEmployee.this, "No such document", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(EditEmployee.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}