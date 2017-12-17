package com.example.raghav.chefiermain.Models;

/**
 * Created by Raghav on 15-12-2017.
 */

public class SavedDishes {

    public String DishName;
    public String Description;
    public String PhotoUrl;

    public SavedDishes(String DishName, String Description, String PhotoUrl) {
        this.DishName = DishName;
        this.Description = Description;
        this.PhotoUrl = PhotoUrl;

    }

    public SavedDishes() { }

    public String getDishName() {
        return DishName;
    }


}
