package com.example.campusbuddy.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.campusbuddy.R;
import com.example.campusbuddy.databinding.ActivityServiceDetailsBinding;
import com.example.campusbuddy.model.ServiceModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ServiceDetailsActivity extends AppCompatActivity {
    ActivityServiceDetailsBinding binding;
    FirebaseUser user;
    FirebaseDatabase database;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityServiceDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        user = FirebaseAuth.getInstance().getCurrentUser();

        database = FirebaseDatabase.getInstance();
        reference = database.getReference();
//        reference = database.getReference("Gym Service ");



        binding.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userId = user.getUid();
                String sName = binding.etName.getText().toString();
                String price = binding.etPrice.getText().toString();
                ServiceModel service = new ServiceModel(userId, sName, price);
                reference.child("Gym Service").push().setValue(service).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(ServiceDetailsActivity.this, "Added Successfully!!", Toast.LENGTH_SHORT).show();
                            binding.etName.setText("");
                            binding.etPrice.setText("");
                        }
                    }
                });
            }
        });
    }
}