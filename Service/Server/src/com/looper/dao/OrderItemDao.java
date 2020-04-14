package com.looper.dao;

import com.looper.domain.*;

import java.util.List;

public interface OrderItemDao {

    //将购买商品ID、数量、顾客ID、订单信息添加到
    boolean addBuyGoodItem(Good good, int goodNum, Customer customer, OrderInfo orderInfo);

    //查询某个商品的销售量
    //int findGoodSaleNum(int goodId,String goodName);

    //查询某个用户的购买记录
    List<OrderItem> findOrderItem(Customer customer);

    //查询某个用户某次的购买记录
    List<OrderItem> findOrderItem(Customer customer,OrderInfo orderInfo);


}
