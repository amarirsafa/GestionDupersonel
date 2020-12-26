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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_employee);

        Intent i = getIntent();
        final String id = i.getStringExtra("id");
        mDataBaseStore = FirebaseFirestore.getInstance();
        assert id != null;
        documentReference = mDataBaseStore.collection("Company").document("np0SxpArRay4njQeRgH9")
                .collection("Employees").document(id);
        findViewById(R.id.editEmp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Employee[] tempEmployee = new Employee[0];
                final EditText fullName = findViewById(R.id.fullName);
                final EditText CIN = findViewById(R.id.CIN);
                final EditText Email = findViewById(R.id.email);
                final EditText Address = findViewById(R.id.address);
                documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            assert document != null;
                            if (document.exists()) {
                                tempEmployee[0] = document.toObject(Employee.class);
                                assert tempEmployee[0] != null;
                                if(tempEmployee[0].getFullName() != null){
                                    fullName.setText(tempEmployee[0].getFullName());
                                }
                                if(tempEmployee[0].getCIN() != null){
                                    CIN.setText(tempEmployee[0].getCIN());
                                }
                                if(tempEmployee[0].getAddress() != null){
                                    Address.setText(tempEmployee[0].getAddress());
                                }
                                if(tempEmployee[0].getEmail() != null){
                                    Email.setText(tempEmployee[0].getEmail());
                                }

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