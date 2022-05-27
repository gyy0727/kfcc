package com.luoxin.www.po;

public class Restaurant {
    private String restaurantName;
    private String restaurantAddress;
    private String restaurantTelephone;
    private String restaurantMannagerName;
    public Restaurant(){

    }
    public String getRestaurantTelephone() {
        return restaurantTelephone;
    }

    public void setRestaurantTelephone(String restaurantTelephone) {
        this.restaurantTelephone = restaurantTelephone;
    }

    public String getRestaurantAddress() {
        return restaurantAddress;
    }

    public void setRestaurantAddress(String restaurantAddress) {
        this.restaurantAddress = restaurantAddress;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getRestaurantMannagerName() {
        return restaurantMannagerName;
    }

    public void setRestaurantMannagerName(String restaurantMannagerName) {
        this.restaurantMannagerName = restaurantMannagerName;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "restaurantName='" + restaurantName + '\'' +
                ", restaurantAddress='" + restaurantAddress + '\'' +
                ", restaurantTelephone='" + restaurantTelephone + '\'' +
                ", restaurantMannagerName='" + restaurantMannagerName + '\'' +
                '}';
    }
}
