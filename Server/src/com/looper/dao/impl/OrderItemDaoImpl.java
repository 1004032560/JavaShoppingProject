package com.looper.dao.impl;

import com.looper.dao.OrderItemDao;
import com.looper.domain.Customer;
import com.looper.domain.Good;
import com.looper.domain.OrderInfo;
import com.looper.domain.OrderItem;
import com.looper.util.JDBCUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class OrderItemDaoImpl implements OrderItemDao {

    @Override
    public boolean addBuyGoodItem(Good good, int goodNum, Customer customer, OrderInfo orderInfo) {
        boolean flag = false;
        JDBCUtil.getConn();
        String sql = "insert into orderitem(goodId,orderItemNum,orderItemPrice,customerId,orderInfoId) values(?,?,?,?,?)";

        try {
            JDBCUtil.ps = JDBCUtil.conn.prepareStatement(sql);
            JDBCUtil.ps.setInt(1,good.getGoodId());
            JDBCUtil.ps.setInt(2,goodNum);
            JDBCUtil.ps.setDouble(3,(goodNum*good.getGoodPrice()));
            JDBCUtil.ps.setInt(4,customer.getCustomerId());
            JDBCUtil.ps.setInt(5,orderInfo.getOrderInfoId());
            JDBCUtil.ps.executeUpdate();
            flag = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public List<OrderItem> findOrderItem(Customer customer) {

        List<OrderItem> orderItems = new ArrayList<>();
        JDBCUtil.getConn();
        String sql = "select * from orderitem where customerId=?";
        OrderItem orderItem = null;
        try {
            JDBCUtil.ps = JDBCUtil.conn.prepareStatement(sql);
            JDBCUtil.ps.setInt(1,customer.getCustomerId());
            ResultSet rs = JDBCUtil.ps.executeQuery();
            while(rs.next()){
                int orderItemId = rs.getInt("orderItemId");
                int goodId = rs.getInt("goodId");
                Good good = new GoodDaoImpl().findGood(goodId);
                int orderItemNum = rs.getInt("orderItemNum");
                double orderItemPrice = rs.getDouble("orderItemPrice");
                int customerId = rs.getInt("customerId");
                Customer customer1 = new CustomerDaoImpl().findCustomer(customerId);
                Timestamp dateTime = rs.getTimestamp("orderTime");
                int orderInfoId = rs.getInt("orderInfoId");
                OrderInfo orderInfo = new OrderInfoDaoImpl().findOrderInfo(orderInfoId);
                orderItem = new OrderItem(orderInfoId,good,orderItemNum,orderItemPrice,orderInfo,customer1,dateTime);
                orderItems.add(orderItem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtil.getClose();
        }
        return orderItems;
    }

    @Override
    public List<OrderItem> findOrderItem(Customer customer, OrderInfo orderInfo) {
        List<OrderItem> orderItems = new ArrayList<>();
        JDBCUtil.getConn();
        String sql = "select * from orderitem where customerId=? and orderInfoId=? ";
        OrderItem orderItem = null;
        try {
            JDBCUtil.ps = JDBCUtil.conn.prepareStatement(sql);
            JDBCUtil.ps.setInt(1,customer.getCustomerId());
            JDBCUtil.ps.setInt(2,orderInfo.getOrderInfoId());
            ResultSet rs = JDBCUtil.ps.executeQuery();
            while(rs.next()){
                int orderItemId = rs.getInt("orderItemId");
                int goodId = rs.getInt("goodId");
                Good good = new GoodDaoImpl().findGood(goodId);
                int orderItemNum = rs.getInt("orderItemNum");
                double orderItemPrice = rs.getDouble("orderItemPrice");
                int customerId = rs.getInt("customerId");
                Customer customer1 = new CustomerDaoImpl().findCustomer(customerId);
                Timestamp dateTime = rs.getTimestamp("orderTime");
                int orderInfoId = rs.getInt("orderInfoId");
                OrderInfo orderInfo1 = new OrderInfoDaoImpl().findOrderInfo(orderInfoId);
                orderItem = new OrderItem(orderInfoId,good,orderItemNum,orderItemPrice,orderInfo1,customer1,dateTime);
                orderItems.add(orderItem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtil.getClose();
        }
        return orderItems;
    }

}
