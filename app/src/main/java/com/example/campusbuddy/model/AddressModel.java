package com.example.campusbuddy.model;

import android.location.Address;

import java.util.List;

public class AddressModel {

    Address address;

    public AddressModel(Address address) {
        this.address = address;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
