package com.looper.domain;

import java.io.Serializable;
import java.sql.Timestamp;

public class OrderInfo implements Serializable {

    private static final long serialVersionUID = 468366876886154911L;
    private int orderInfoId;
    private Customer customer;
    private Employee employee;
    private int totalNum;
    private double totalPrice;
    private Timestamp dateTime;

    public OrderInfo() {
    }

    public OrderInfo(int orderInfoId, int totalNum, double totalPrice) {
        this.orderInfoId = orderInfoId;
        this.totalNum = totalNum;
        this.totalPrice = totalPrice;
    }

    public OrderInfo(Customer customer, Employee employee, int totalNum, double totalPrice) {
        this.customer = customer;
        this.employee = employee;
        this.totalNum = totalNum;
        this.totalPrice = totalPrice;
    }

    public OrderInfo(Customer customer, Employee employee) {
        this.customer = customer;
        this.employee = employee;
    }

    public OrderInfo(int orderInfoId) {
        this.orderInfoId = orderInfoId;
    }

    public OrderInfo(int orderInfoId, Customer customer, Employee employee) {
        this.orderInfoId = orderInfoId;
        this.customer = customer;
        this.employee = employee;
    }

    public OrderInfo(int orderInfoId, Customer customer, Employee employee, int totalNum, double totalPrice) {
        this.orderInfoId = orderInfoId;
        this.customer = customer;
        this.employee = employee;
        this.totalNum = totalNum;
        this.totalPrice = totalPrice;
    }

    public OrderInfo(int orderInfoId, Customer customer, Employee employee, int totalNum, double totalPrice, Timestamp dateTime) {
        this.orderInfoId = orderInfoId;
        this.customer = customer;
        this.employee = employee;
        this.totalNum = totalNum;
        this.totalPrice = totalPrice;
        this.dateTime = dateTime;
    }

    public Timestamp getDateTime() {
        return dateTime;
    }

    public void setDateTime(Timestamp dateTime) {
        this.dateTime = dateTime;
    }

    public int getOrderInfoId() {
        return orderInfoId;
    }

    public void setOrderInfoId(int orderInfoId) {
        this.orderInfoId = orderInfoId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "总订单信息{" +
                " ID:" + orderInfoId +
                ", 顾客姓名:" + customer.getCustomerName() +
                ", 收银员ID:" + employee.getEmployeeId() +
                ", 购买总数:" + totalNum +
                ", 消费总额:" + totalPrice +
                ", 购物日期:" + dateTime +
                " }";
    }
}
