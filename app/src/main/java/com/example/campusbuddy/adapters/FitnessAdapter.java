package com.example.campusbuddy.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.animation.content.Content;
import com.example.campusbuddy.R;
import com.example.campusbuddy.model.ServiceModel;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class FitnessAdapter extends RecyclerView.Adapter<FitnessAdapter.myViewHolder> {

    ArrayList<ServiceModel> list = new ArrayList<>();
    Context context;

    public FitnessAdapter(ArrayList<ServiceModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public FitnessAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FitnessAdapter.myViewHolder holder, int position) {
        ServiceModel serviceModel = list.get(position);
        holder.sName.setText(serviceModel.getServiceName());
        holder.price.setText(serviceModel.getPrice());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder{
        TextView sName, price;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            sName = itemView.findViewById(R.id.tv_name);
            price = itemView.findViewById(R.id.tv_price);
        }
    }
}
