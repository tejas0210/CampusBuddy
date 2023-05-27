package com.example.campusbuddy.fragments;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.Toast;
import com.example.campusbuddy.databinding.FragmentDetailsBinding;
import com.example.campusbuddy.views.ServiceDetailsActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class DetailsFragment extends Fragment {
    FragmentDetailsBinding binding;
    FirebaseDatabase database;
    String rating;
    String dbRating="0";
    FirebaseUser user;
    DatabaseReference reference;
    String serviceType;
    String serviceTypeName;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentDetailsBinding.inflate(inflater, container, false);
        database = FirebaseDatabase.getInstance();
        reference = database.getReference();
        user = FirebaseAuth.getInstance().getCurrentUser();


        binding.ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                rating = String.valueOf(v);
//                Toast.makeText(getContext(), rating, Toast.LENGTH_SHORT).show();
            }
        });
        reference.child("userRating").child(user.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dbRating = snapshot.getValue(String.class);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });



        binding.btnRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reference.child(serviceType +" Rating").child(serviceTypeName+" Rating").setValue(rating+dbRating).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(getContext(), "Thanks For Rating!!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        return binding.getRoot();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            serviceType = getArguments().getString("serviceType");
            serviceTypeName = getArguments().getString("serviceTypeName");
        }
    }
}