package com.example.campusbuddy.model;

public class ServiceModel {
    String userId;

    String serviceType;
    String serviceName;
    String price;


    public ServiceModel(){

    }
    public ServiceModel(String userId, String serviceType, String serviceName, String price) {
        this.userId = userId;
        this.serviceName = serviceName;
        this.price = price;
        this.serviceType = serviceType;

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
}
