package com.example.campusbuddy.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.campusbuddy.R;
import com.example.campusbuddy.model.ServiceModel;
import com.example.campusbuddy.views.Fitness_Data_Activity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;

public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.myViewHolder> {
    ArrayList<ServiceModel> list = new ArrayList<>();
    Context context;
    FirebaseUser user;
    DatabaseReference reference;
    FirebaseDatabase database;

    public ServiceAdapter(ArrayList<ServiceModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ServiceAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceAdapter.myViewHolder holder, int position) {
        ServiceModel serviceModel = list.get(position);
        holder.sName.setText(serviceModel.getServiceName());
        holder.location.setText(serviceModel.getLocation());


        String serviceType = serviceModel.getServiceType();

        user = FirebaseAuth.getInstance().getCurrentUser();
        database = FirebaseDatabase.getInstance();
        reference = database.getReference();

        reference.child("userRating").child(user.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                holder.rating.append(String.valueOf(snapshot.getValue()));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, Fitness_Data_Activity.class);
                i.putExtra("serviceType", serviceType);
                i.putExtra("serviceTypeName", serviceModel.getServiceName());
                context.startActivity(i);
            }
        });
    }
    @Override
    public int getItemCount() {
        return list.size();
    }
    public class myViewHolder extends RecyclerView.ViewHolder{
        TextView sName, location,rating;
        ImageView coverImage;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            sName = itemView.findViewById(R.id.tv_name);
            location = itemView.findViewById(R.id.tv_location);
            coverImage= itemView.findViewById(R.id.img);
            rating = itemView.findViewById(R.id.tv_Rating);

        }
    }
}