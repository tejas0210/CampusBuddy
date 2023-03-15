package com.example.campusbuddy.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.Toast;

import com.example.campusbuddy.R;
import com.example.campusbuddy.adapters.FitnessAdapter;
import com.example.campusbuddy.databinding.ActivityFitnessCentreBinding;
import com.example.campusbuddy.model.ServiceModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FitnessCentreActivity extends AppCompatActivity {
    ActivityFitnessCentreBinding binding;
    ArrayList<ServiceModel> list;
    DatabaseReference reference;
    FirebaseUser user;
    FirebaseDatabase database;
    FitnessAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ActionBar actionBar;
        actionBar = getSupportActionBar();
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#303030"));
        actionBar.setBackgroundDrawable(colorDrawable);
        binding = ActivityFitnessCentreBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setTitle("Fitness Centres");

        user = FirebaseAuth.getInstance().getCurrentUser();
        database = FirebaseDatabase.getInstance();

        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(binding.recyclerView.getContext(), DividerItemDecoration.VERTICAL);
        binding.recyclerView.addItemDecoration(dividerItemDecoration);


        Toast.makeText(this, "Here now", Toast.LENGTH_SHORT).show();
        reference = database.getReference("Gym Service");
        list = new ArrayList<>();

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    ServiceModel service = dataSnapshot.getValue(ServiceModel.class);
                    list.add(service);

                }
                adapter = new FitnessAdapter(list, FitnessCentreActivity.this);
                binding.recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(FitnessCentreActivity.this, "Error!!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}