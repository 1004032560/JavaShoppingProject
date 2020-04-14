package com.looper.test;

import com.looper.dao.OrderInfoDao;
import com.looper.dao.impl.OrderInfoDaoImpl;
import com.looper.domain.OrderInfo;

public class OrderInfoDaoTest {

    public static void main(String[] args) {

        OrderInfoDao orderInfoDao = new OrderInfoDaoImpl();

        for (OrderInfo orderInfo : orderInfoDao.findAllOrderInfo()) {
            System.out.println(orderInfo);
        }

    }

}
