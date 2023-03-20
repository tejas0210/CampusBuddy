package com.example.campusbuddy.model;

import android.net.Uri;

public class ImageModel {
    Uri imageUri;

    public ImageModel(){}

    public ImageModel(Uri imageUri) {
        this.imageUri = imageUri;
    }

    public Uri getImageUri() {
        return imageUri;
    }

    public void setImageUri(Uri imageUri) {
        this.imageUri = imageUri;
    }
}
