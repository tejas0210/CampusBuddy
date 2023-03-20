package com.example.campusbuddy.fragments;

import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.campusbuddy.adapters.ImageAdapter;
import com.example.campusbuddy.databinding.FragmentPhotosBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;

public class PhotosFragment extends Fragment {

    FragmentPhotosBinding binding;
    FirebaseDatabase database;
    DatabaseReference reference;
    ArrayList<Uri> list = new ArrayList<>();
    ImageAdapter adapter;




    public PhotosFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentPhotosBinding.inflate(inflater, container, false);


        database = FirebaseDatabase.getInstance();
        reference = database.getReference("Images");
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                    String imageUri = dataSnapshot.getValue(String.class);
                    list.add(Uri.parse(imageUri));
                }
                adapter = new ImageAdapter(list);
                binding.recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return binding.getRoot();
    }
}