package com.example.raghav.chefiermain.Models;

/**
 * Created by Raghav on 15-12-2017.
 */

public class Orders {

    private String quantity;

    private String dish;

    private String address;

    public Orders() {
    }

    public Orders(String quantity, String dish, String address) {
        this.quantity = quantity;
        this.dish = dish;
        this.address = address;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getDish() {
        return dish;
    }

    public String getAddress() {
        return address;
    }
}
