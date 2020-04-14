package com.looper.service;

import com.looper.domain.Customer;
import com.looper.domain.Employee;
import com.looper.domain.OrderInfo;

import java.util.List;

public interface ServiceOrderInfo {

    //查询所有销售记录
    List<OrderInfo> findAllOrderInfo();

    //添加一条销售记录
    OrderInfo addOrderInfo(Customer customer, Employee employee, int totalNum, double totalPrice);

    //查找一条消费记录
    OrderInfo findOrderInfo(Customer customer, Employee employee, int totalNum, double totalPrice);

    //查找一条消费记录
    OrderInfo findOrderInfo(int orderInfoId);

    //修改一条消费记录
    boolean modifyOrderInfo(int orderInfoId, int totalNum, double totalPrice);

    //查找某个顾客的消费记录
    List<OrderInfo> findOrderInfo(Customer customer);

    //查找某个员工的销售记录
    List<OrderInfo> findOrderInfo(Employee employee);

}
