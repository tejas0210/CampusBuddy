package com.example.campusbuddy.adapters;

import android.content.Context;
import android.location.Address;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.campusbuddy.R;
import com.example.campusbuddy.model.AddressModel;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class SearchMapsAdapter extends RecyclerView.Adapter<SearchMapsAdapter.myViewHolder> {
    ArrayList<AddressModel> list;
    Context context;

    public SearchMapsAdapter(ArrayList<AddressModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public SearchMapsAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_layout, parent, false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchMapsAdapter.myViewHolder holder, int position) {

        // you dont have to show list in layout
        // you have to show single address in layout
        Address address = list.get(position).getAddress(); // here you will get address list
        holder.address.setText(address.getLocality());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder{
        TextView address;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            address = itemView.findViewById(R.id.address);

        }
    }
}
