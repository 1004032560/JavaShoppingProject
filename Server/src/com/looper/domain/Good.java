package com.looper.domain;

import java.io.Serializable;

public class Good implements Serializable {

    private static final long serialVersionUID = 7189892396827867611L;
    private int goodId;
    private String goodName;
    private double goodPrice;
    private int goodCount;
    private String goodUnit;

    public Good() {
    }

    public Good(int goodId) {
        this.goodId = goodId;
    }

    public Good(int goodId, String goodName) {
        this.goodId = goodId;
        this.goodName = goodName;
    }

    public Good(int goodId, double goodPrice) {
        this.goodId = goodId;
        this.goodPrice = goodPrice;
    }

    public Good(int goodId, int goodCount) {
        this.goodId = goodId;
        this.goodCount = goodCount;
    }

    public Good(String goodName, double goodPrice, int goodCount, String goodUnit) {
        this.goodName = goodName;
        this.goodPrice = goodPrice;
        this.goodCount = goodCount;
        this.goodUnit = goodUnit;
    }

    public Good(int goodId, String goodName, double goodPrice, int goodCount, String goodUnit) {
        this.goodId = goodId;
        this.goodName = goodName;
        this.goodPrice = goodPrice;
        this.goodCount = goodCount;
        this.goodUnit = goodUnit;
    }

    public int getGoodId() {
        return goodId;
    }

    public void setGoodId(int goodId) {
        this.goodId = goodId;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public double getGoodPrice() {
        return goodPrice;
    }

    public void setGoodPrice(double goodPrice) {
        this.goodPrice = goodPrice;
    }

    public int getGoodCount() {
        return goodCount;
    }

    public void setGoodCount(int goodCount) {
        this.goodCount = goodCount;
    }

    public String getGoodUnit() {
        return goodUnit;
    }

    public void setGoodUnit(String goodUnit) {
        this.goodUnit = goodUnit;
    }

    @Override
    public String toString() {
        return "\t\t" + goodId +
                "\t\t" + goodName +
                "\t\t\t" + goodPrice +
                "\t\t\t" + goodCount +
                "\t\t\t" + goodUnit ;
    }
}
