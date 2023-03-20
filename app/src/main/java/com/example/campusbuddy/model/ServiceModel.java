package com.example.campusbuddy.model;

import android.net.Uri;
import java.util.ArrayList;

public class ServiceModel {
    String userId;

    String serviceType;
    String serviceName;
    String price;
    String imageUri;
    String location;





    public ServiceModel(){

    }
    public ServiceModel(String userId, String serviceType, String serviceName, String price) {
        this.userId = userId;
        this.serviceName = serviceName;
        this.price = price;
        this.serviceType = serviceType;

    }

    public ServiceModel(String userId, String serviceType, String serviceName, String price, String imageUri, String location) {
        this.userId = userId;
        this.serviceType = serviceType;
        this.serviceName = serviceName;
        this.price = price;
        this.imageUri = imageUri;
        this.location = location;
    }


    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }
}
