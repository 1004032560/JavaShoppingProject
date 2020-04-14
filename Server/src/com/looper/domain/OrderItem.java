package com.looper.domain;

import java.io.Serializable;
import java.sql.Timestamp;

public class OrderItem implements Serializable {

    private static final long serialVersionUID = 5228575380048888301L;
    private int orderItemId;
    private Good good;
    private int orderItemNum;
    private double orderItemPrice;
    private OrderInfo orderInfoId;
    private Customer customer;
    private Employee employee;
    private Timestamp timestamp;

    public OrderItem(int orderItemId, Good good, int orderItemNum, double orderItemPrice, OrderInfo orderInfoId, Customer customer, Timestamp timestamp) {
        this.orderItemId = orderItemId;
        this.good = good;
        this.orderItemNum = orderItemNum;
        this.orderItemPrice = orderItemPrice;
        this.orderInfoId = orderInfoId;
        this.customer = customer;
        this.timestamp = timestamp;
    }

    public OrderItem(OrderInfo orderInfoId, Customer customer) {
        this.orderInfoId = orderInfoId;
        this.customer = customer;
    }

    public OrderItem(int orderItemId, Good good, int orderItemNum, double orderItemPrice, OrderInfo orderInfoId, Customer customer, Employee employee, Timestamp timestamp) {
        this.orderItemId = orderItemId;
        this.good = good;
        this.orderItemNum = orderItemNum;
        this.orderItemPrice = orderItemPrice;
        this.orderInfoId = orderInfoId;
        this.customer = customer;
        this.employee = employee;
        this.timestamp = timestamp;
    }

    public OrderItem() {
    }

    public OrderItem(int orderItemNum) {
        this.orderItemNum = orderItemNum;
    }

    public OrderItem(Good good, int orderItemNum, Customer customer) {
        this.good = good;
        this.orderItemNum = orderItemNum;
        this.customer = customer;
    }

    public OrderItem(int orderItemId, Good good) {
        this.orderItemId = orderItemId;
        this.good = good;
    }

    public OrderItem(Good good, int orderItemNum, OrderInfo orderInfoId, Customer customer) {
        this.good = good;
        this.orderItemNum = orderItemNum;
        this.orderInfoId = orderInfoId;
        this.customer = customer;
    }

    public OrderItem(int orderItemId, Good good, int orderItemNum, double orderItemPrice, OrderInfo orderInfoId, Customer customer, Employee employee) {
        this.orderItemId = orderItemId;
        this.good = good;
        this.orderItemNum = orderItemNum;
        this.orderItemPrice = orderItemPrice;
        this.orderInfoId = orderInfoId;
        this.customer = customer;
        this.employee = employee;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public int getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(int orderItemId) {
        this.orderItemId = orderItemId;
    }

    public Good getGood() {
        return good;
    }

    public void setGood(Good good) {
        this.good = good;
    }

    public int getOrderItemNum() {
        return orderItemNum;
    }

    public void setOrderItemNum(int orderItemNum) {
        this.orderItemNum = orderItemNum;
    }

    public double getOrderItemPrice() {
        return good.getGoodPrice()*orderItemNum;
    }

    public void setOrderItemPrice(double orderItemPrice) {
        this.orderItemPrice = orderItemPrice;
    }

    public OrderInfo getOrderInfoId() {
        return orderInfoId;
    }

    public void setOrderInfoId(OrderInfo orderInfoId) {
        this.orderInfoId = orderInfoId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "订单详细信息{" +
                " ID:" + orderItemId +
                ", 商品ID:" + good.getGoodId() +
                ", 商品名称:" + good.getGoodName() +
                ", 购买数量:" + orderItemNum +
                ", 总金额:" + orderItemPrice +
                ", 总订单ID" + orderInfoId +
                ", 顾客ID" + customer.getCustomerId() +
                ", 顾客姓名" + customer.getCustomerName() +
                ", employee=" + employee +
                ", timestamp=" + timestamp +
                " }";
    }
}
