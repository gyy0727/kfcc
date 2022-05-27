package com.luoxin.www.po;


public class User {
    private String username;
    private String password;
    private String telephone;
    private String address;

    public User() {
    }
    public String getUsername() {

        return username;
    }

    public String getPassword() {

        return password;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getAddress() {
        return address;
    }

    public void setTelephone(String telephone) {

        this.telephone = telephone;
    }
    public void setAddress(String address) {

        this.address = address;
    }

    public void setPassword(String password) {

        this.password = password;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    @Override
    public String toString() {
        return "User{" +
                "usename='" + username + '\'' +
                ", password='" + password + '\'' +
                ", telephone=" + telephone +
                ", addess='" + address + '\'' +
                '}';
    }
}
