package com.looper.service.impl;

import com.looper.dao.OrderInfoDao;
import com.looper.dao.impl.OrderInfoDaoImpl;
import com.looper.domain.Customer;
import com.looper.domain.Employee;
import com.looper.domain.OrderInfo;
import com.looper.service.ServiceOrderInfo;

import java.util.List;

public class ServiceOrderInfoImpl implements ServiceOrderInfo {

    OrderInfoDao orderInfoDao = new OrderInfoDaoImpl();

    @Override
    public List<OrderInfo> findAllOrderInfo() {
        return orderInfoDao.findAllOrderInfo();
    }

    @Override
    public OrderInfo addOrderInfo(Customer customer, Employee employee, int totalNum, double totalPrice) {
        return orderInfoDao.addOrderInfo(customer,employee,totalNum,totalPrice);
    }

    @Override
    public OrderInfo findOrderInfo(Customer customer, Employee employee, int totalNum, double totalPrice) {
        return orderInfoDao.findOrderInfo(customer,employee,totalNum,totalPrice);
    }

    @Override
    public OrderInfo findOrderInfo(int orderInfoId) {
        return orderInfoDao.findOrderInfo(orderInfoId);
    }

    @Override
    public boolean modifyOrderInfo(int orderInfoId, int totalNum, double totalPrice) {
        return orderInfoDao.modifyOrderInfo(orderInfoId,totalNum,totalPrice);
    }

    @Override
    public List<OrderInfo> findOrderInfo(Customer customer) {
        return orderInfoDao.findOrderInfo(customer);
    }

    @Override
    public List<OrderInfo> findOrderInfo(Employee employee) {
        return orderInfoDao.findOrderInfo(employee);
    }

}
