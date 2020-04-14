package com.looper.test;

import com.looper.dao.OrderItemDao;
import com.looper.dao.impl.OrderItemDaoImpl;
import com.looper.domain.*;

public class OrderItemDaoTest {

    public static void main(String[] args) {

        OrderItemDao orderItemDao = new OrderItemDaoImpl();

        //Good good = new Good(101,"矿泉水",2,50,"400ml");

        //Customer customer = new Customer(1003,"王五",18,"男","会员用户",1000);



        //Shop shop = new Shop(1,"looper","北京","13905665655");

        Employee employee = new Employee(2);

        //OrderInfo orderInfo = new OrderInfo(100002,customer,employee,100,1000);

        //orderItemDao.addBuyGoodItem(good,10,customer,orderInfo);

    }

}
