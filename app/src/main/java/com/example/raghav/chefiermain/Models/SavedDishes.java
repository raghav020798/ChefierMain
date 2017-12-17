package com.example.raghav.chefiermain.Models;

/**
 * Created by Raghav on 15-12-2017.
 */

public class SavedDishes {

    private String dishName;
    private String description;
    private String photoUrl;

    public SavedDishes(String DishName, String Description, String PhotoUrl) {
        this.dishName = DishName;
        this.description = Description;
        this.photoUrl = PhotoUrl;

    }

    public SavedDishes() { }

    public String getDescription() {
        return description;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public String getDishName() {
        return dishName;
    }


}
