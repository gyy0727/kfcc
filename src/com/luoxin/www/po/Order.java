package com.luoxin.www.po;
import java.util.Date;

//订单
public class Order {
    private String seller;//卖家
    private String buyers;//买家
    private String commodity;//商品
    private String quantity;//数量
    private String date;//日期
    private String shippingAddress;//送货地址
    private String buyersTelephone;//电话
    private String state;//是否已出餐
    public Order(){

    }

    public String getState() { 
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getBuyersTelephone() {
        return buyersTelephone;
    }

    public void setBuyersTelephone(String buyersTelephone) {
        this.buyersTelephone = buyersTelephone;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getCommodity() {
        return commodity;
    }

    public void setCommodity(String commodity) {
        this.commodity = commodity;
    }

    public String getBuyers() {
        return buyers;
    }

    public void setBuyers(String buyers) {
        this.buyers = buyers;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    @Override
    public String toString() {
        return "Order{" +
                "seller='" + seller + '\'' +
                ", buyers='" + buyers + '\'' +
                ", commodity='" + commodity + '\'' +
                ", quantity='" + quantity + '\'' +
                ", date=" + date +
                ", shippingAddress='" + shippingAddress + '\'' +
                ", buyersTelephone='" + buyersTelephone + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
