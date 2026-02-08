package com.example.movieclub.activity;

import android.net.Uri;
public class MovieModel {
    private String name;
    private Uri imageUri;

    public MovieModel(Uri imageUri, String name) {
        this.imageUri = imageUri;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Uri getImageUri() {
        return imageUri;
    }

    public void setImageUri(Uri imageUri) {
        this.imageUri = imageUri;
    }
}