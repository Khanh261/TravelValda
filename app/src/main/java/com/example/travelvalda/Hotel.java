package com.example.travelvalda;

public class Hotel {

    private String name;
    private int imageResourceId;

    public Hotel(String name, int imageResourceId) {
        this.name = name;
        this.imageResourceId = imageResourceId;
    }

    public String getName() {
        return name;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }
}
