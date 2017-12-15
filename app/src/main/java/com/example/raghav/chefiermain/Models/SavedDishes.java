package com.example.raghav.chefiermain.Models;

/**
 * Created by Raghav on 15-12-2017.
 */

public class SavedDishes {

    public String DishName;
    public String Description;
    public String PhotoUrl;

    public SavedDishes(String dishName, String description, String photoUrl) {
        DishName = dishName;
        Description = description;
        PhotoUrl = photoUrl;

    }

    public SavedDishes() { }
}
