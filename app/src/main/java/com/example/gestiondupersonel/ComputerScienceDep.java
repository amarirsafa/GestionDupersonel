package com.example.gestiondupersonel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.gestiondupersonel.classes.Employee;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;

public class ComputerScienceDep extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<Employee> employeesList;
    private FirebaseFirestore mDataBaseStore;
    private CollectionReference collectionReference;
    private RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_computer_science_dep);
        findViewById(R.id.add_new_employee).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ComputerScienceDep.this,AddEmployee.class);
                startActivity(i);
            }
        });

        mDataBaseStore = FirebaseFirestore.getInstance();
        collectionReference = mDataBaseStore.collection("Company").document("np0SxpArRay4njQeRgH9")
                .collection("Employees");

        recyclerView = findViewById(R.id.recycler_view_employees);
        LinearLayoutManager LLM = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(LLM);

        //employeesList = new ArrayList<>();
        fillInListOfEmployees();


    }
    private void fillInListOfEmployees(){
        Query query = collectionReference.orderBy("salary",Query.Direction.DESCENDING);
        final FirestoreRecyclerOptions<Employee> options = new FirestoreRecyclerOptions.Builder<Employee>()
                .setQuery(query,Employee.class)
                .build();
        adapter = new RecyclerViewAdapter(options);
        recyclerView.setAdapter(adapter);
    }
    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}