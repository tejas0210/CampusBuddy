package com.example.campusbuddy.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.campusbuddy.R;
import com.example.campusbuddy.adapters.ServiceAdapter;
import com.example.campusbuddy.databinding.ActivityFitnessCentreBinding;
import com.example.campusbuddy.databinding.ActivityInnBinding;
import com.example.campusbuddy.databinding.ActivityLifestyleBinding;
import com.example.campusbuddy.model.ServiceModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class LifestyleActivity extends AppCompatActivity {
    ActivityLifestyleBinding binding;
    ArrayList<ServiceModel> list;
    DatabaseReference reference;
    FirebaseUser user;
    FirebaseDatabase database;
    ServiceAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLifestyleBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Window window = LifestyleActivity.this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(ContextCompat.getColor(LifestyleActivity.this, R.color.arsenic));
        // assigning ID of the toolbar to a variable
        Toolbar toolbar = findViewById(R.id.toolbar);
        // using toolbar as ActionBar
        setSupportActionBar(toolbar);
        setTitle("Lifestyle");

        user = FirebaseAuth.getInstance().getCurrentUser();
        database = FirebaseDatabase.getInstance();

        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(binding.recyclerView.getContext(), DividerItemDecoration.VERTICAL);
//        binding.recyclerView.addItemDecoration(dividerItemDecoration);


//        Toast.makeText(this, "Here now", Toast.LENGTH_SHORT).show();


        // Retrieve Gym data from FirebaseDatabase

        reference = database.getReference("Lifestyle");
        list = new ArrayList<>();

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    ServiceModel service = dataSnapshot.getValue(ServiceModel.class);
                    list.add(service);

                }
                adapter = new ServiceAdapter(list, LifestyleActivity.this);
                binding.recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(LifestyleActivity.this, "Error!!", Toast.LENGTH_SHORT).show();
            }
        });

        //FINISH
    }
}