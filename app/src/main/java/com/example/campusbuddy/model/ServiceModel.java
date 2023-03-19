package com.example.campusbuddy.model;

import android.net.Uri;

import java.util.ArrayList;

public class ServiceModel {
    String userId;

    String serviceType;
    String serviceName;
    String price;
    ArrayList<Uri> list;


    public ServiceModel(){

    }
    public ServiceModel(String userId, String serviceType, String serviceName, String price) {
        this.userId = userId;
        this.serviceName = serviceName;
        this.price = price;
        this.serviceType = serviceType;

    }
    public ServiceModel(String userId, String serviceType, String serviceName, String price, ArrayList<Uri> list) {
        this.userId = userId;
        this.serviceName = serviceName;
        this.price = price;
        this.serviceType = serviceType;
        this.list = list;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public ArrayList<Uri> getList() {
        return list;
    }

    public void setList(ArrayList<Uri> list) {
        this.list = list;
    }
}
