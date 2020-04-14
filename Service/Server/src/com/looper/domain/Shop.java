package com.looper.domain;

import java.io.Serializable;

public class Shop implements Serializable {

    private static final long serialVersionUID = 9219848612966453436L;
    private int shopId;
    private String shopName;
    private String shopAddress;
    private String shopIphone;

    public Shop(int shopId, String shopName, String shopAddress, String shopIphone) {
        this.shopId = shopId;
        this.shopName = shopName;
        this.shopAddress = shopAddress;
        this.shopIphone = shopIphone;
    }

    public Shop() {
    }

    public Shop(int shopId) {
        this.shopId = shopId;
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress;
    }

    public String getShopIphone() {
        return shopIphone;
    }

    public void setShopIphone(String shopIphone) {
        this.shopIphone = shopIphone;
    }

    @Override
    public String toString() {
        return "店铺信息{" +
                " 店铺ID:" + shopId +
                ", 店铺名称:" + shopName +
                ", 店铺地址:" + shopAddress +
                ", 店铺联系电话:" + shopIphone +
                " }";
    }
}
