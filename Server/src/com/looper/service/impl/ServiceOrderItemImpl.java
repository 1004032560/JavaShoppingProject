package com.looper.service.impl;

import com.looper.dao.OrderItemDao;
import com.looper.dao.impl.OrderItemDaoImpl;
import com.looper.domain.Customer;
import com.looper.domain.Good;
import com.looper.domain.OrderInfo;
import com.looper.domain.OrderItem;
import com.looper.service.ServiceOrderItem;

import java.util.List;

public class ServiceOrderItemImpl implements ServiceOrderItem {

    OrderItemDao orderItemDao = new OrderItemDaoImpl();

    @Override
    public boolean addBuyGoodItem(Good good, int goodNum, Customer customer, OrderInfo orderInfo) {
        return orderItemDao.addBuyGoodItem(good,goodNum,customer,orderInfo);
    }

    @Override
    public List<OrderItem> findOrderItem(Customer customer) {
        return orderItemDao.findOrderItem(customer);
    }

    @Override
    public List<OrderItem> findOrderItem(Customer customer, OrderInfo orderInfo) {
        return orderItemDao.findOrderItem(customer,orderInfo);
    }

}
