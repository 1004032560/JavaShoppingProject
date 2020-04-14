package com.looper.domain;

import java.io.Serializable;

public class Customer implements Serializable {

    private static final long serialVersionUID = 1230614019847742696L;
    private int customerId;
    private String customerName;
    private int customerAge;
    private String customerSex;
    private String customerType;
    private double storeMoney;
    private String customerPassword;

    public Customer() {
    }

    public Customer(String customerName) {
        this.customerName = customerName;
    }

    public Customer(int customerId, String customerName, int customerAge, String customerSex, String customerPassword) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerAge = customerAge;
        this.customerSex = customerSex;
        this.customerPassword = customerPassword;
    }

    public Customer(int customerId, double storeMoney) {
        this.customerId = customerId;
        this.storeMoney = storeMoney;
    }

    public Customer(int customerId) {
        this.customerId = customerId;
    }

    public Customer(String customerName, String customerPassword) {
        this.customerName = customerName;
        this.customerPassword = customerPassword;
    }

    public Customer(String customerName, int customerAge, String customerSex, String customerType, double storeMoney, String customerPassword) {
        this.customerName = customerName;
        this.customerAge = customerAge;
        this.customerSex = customerSex;
        this.customerType = customerType;
        this.storeMoney = storeMoney;
        this.customerPassword = customerPassword;
    }

    public Customer(int customerId, String customerName, int customerAge, String customerSex, String customerType, double storeMoney, String customerPassword) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerAge = customerAge;
        this.customerSex = customerSex;
        this.customerType = customerType;
        this.storeMoney = storeMoney;
        this.customerPassword = customerPassword;
    }

    public String getCustomerPassword() {
        return customerPassword;
    }

    public void setCustomerPassword(String customerPassword) {
        this.customerPassword = customerPassword;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getCustomerAge() {
        return customerAge;
    }

    public void setCustomerAge(int customerAge) {
        this.customerAge = customerAge;
    }

    public String getCustomerSex() {
        return customerSex;
    }

    public void setCustomerSex(String customerSex) {
        this.customerSex = customerSex;
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public double getStoreMoney() {
        return storeMoney;
    }

    public void setStoreMoney(double storeMoney) {
        this.storeMoney = storeMoney;
    }

    @Override
    public String toString() {
        return "顾客信息{" +
                " ID:" + customerId +
                ", 姓名:" + customerName +
                ", 年龄:" + customerAge +
                ", 性别:" + customerSex +
                ", 角色:" + customerType +
                ", 余额:" + storeMoney +
                ", 密码:" + customerPassword +
                " }";
    }
}
