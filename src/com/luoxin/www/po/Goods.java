package com.luoxin.www.po;

import java.awt.*;

public class Goods {
    private String restaurantName;
    private String name;
    private String price;

    public Goods(){

    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "restaurantName='" + restaurantName + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
