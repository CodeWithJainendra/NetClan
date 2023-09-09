package com.shivamsingh.Activities;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.shivamsingh.Adapter.AdminCheckSeller;
import com.shivamsingh.model.Product;
import com.shivamsingh.netclan.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class Creativepreneur extends AppCompatActivity {
    private RecyclerView recyclerView;
    private myadapter adapter;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creativepreneur);

        recyclerView = findViewById(R.id.recview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        databaseReference = FirebaseDatabase.getInstance().getReference().child("products");
        FirebaseRecyclerOptions<AdminCheckSeller> options =
                new FirebaseRecyclerOptions.Builder<AdminCheckSeller>()
                        .setQuery(databaseReference.orderByKey(), AdminCheckSeller.class)
                        .build();

        adapter = new myadapter(options, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}
