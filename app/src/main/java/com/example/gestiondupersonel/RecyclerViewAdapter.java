package com.example.gestiondupersonel;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gestiondupersonel.classes.Employee;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.Objects;

public class RecyclerViewAdapter extends FirestoreRecyclerAdapter<Employee,RecyclerViewAdapter.myViewHolder> {
    public RecyclerViewAdapter.OnItemClickListener listener;
    public RecyclerViewAdapter(@NonNull FirestoreRecyclerOptions<Employee> options) { super(Objects.requireNonNull(options));}

    @SuppressLint("SetTextI18n")
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull Employee model) {
        holder.employeeName.setText(model.getFullName());
        holder.employeeCin.setText(model.getCIN());
    }

    @NonNull
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_employee,parent,false);
        return new RecyclerViewAdapter.myViewHolder(view);
    }


    public class myViewHolder extends RecyclerView.ViewHolder {
        TextView employeeName,employeeCin;
        ImageView edit;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            employeeName = itemView.findViewById(R.id.fullName);
            employeeCin = itemView.findViewById(R.id.CIN);
            edit = itemView.findViewById(R.id.edit_employee);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(position!= RecyclerView.NO_POSITION && listener != null){
                        listener.onItemClick(getSnapshots().getSnapshot(position),position);
                    }
                }
            });
            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onEditClick(getSnapshots().getSnapshot(position),position);
                        }
                    }
                }
            });
        }
    }
    public interface OnItemClickListener{
        void onItemClick(DocumentSnapshot documentSnapshot, int position);
        void onEditClick(DocumentSnapshot documentSnapshot, int position);
    }
    public void setOnItemClickListener(RecyclerViewAdapter.OnItemClickListener listener){
        this.listener=listener;
    }
}
