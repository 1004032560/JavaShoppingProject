package com.looper.dao.impl;

import com.looper.dao.CustomerDao;
import com.looper.dao.EmployeeDao;
import com.looper.dao.OrderInfoDao;
import com.looper.domain.Customer;
import com.looper.domain.Data;
import com.looper.domain.Employee;
import com.looper.domain.OrderInfo;
import com.looper.util.JDBCUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class OrderInfoDaoImpl implements OrderInfoDao {

    @Override
    public List<OrderInfo> findAllOrderInfo() {
        CustomerDao customerDao = new CustomerDaoImpl();
        EmployeeDao employeeDao = new EmployeeDaoImpl();

        List<OrderInfo> orderInfos = new ArrayList<>();
        JDBCUtil.getConn();

        String sql = "select * from orderinfo";
        try {
            JDBCUtil.ps = JDBCUtil.conn.prepareStatement(sql);
            ResultSet rs = JDBCUtil.ps.executeQuery();
            while(rs.next()){
                int id = rs.getInt("orderInfoId");
                int customerId = rs.getInt("customerId");
                Customer customer = customerDao.findCustomer(customerId);
                int employeeId = rs.getInt("employeeId");
                Employee employee = employeeDao.findEmployee(employeeId);
                int totalNum = rs.getInt("totalNum");
                double totalPrice = rs.getDouble("totalPrice");
                Timestamp dateTime = rs.getTimestamp("dateTime");
                orderInfos.add(new OrderInfo(id,customer,employee,totalNum,totalPrice,dateTime));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderInfos;
    }

    @Override
    public OrderInfo addOrderInfo(Customer customer, Employee employee, int totalNum, double totalPrice) {
        boolean flag = false;
        JDBCUtil.getConn();
        String sql = "insert into orderinfo(customerId,employeeId,totalNum,totalPrice) values(?,?,?,?)";
        OrderInfo orderInfo = null;
        try {
            JDBCUtil.ps = JDBCUtil.conn.prepareStatement(sql);
            JDBCUtil.ps.setInt(1,customer.getCustomerId());
            JDBCUtil.ps.setInt(2,employee.getEmployeeId());
            JDBCUtil.ps.setInt(3,totalNum);
            JDBCUtil.ps.setDouble(4,totalPrice);
            JDBCUtil.ps.executeUpdate();

            String sql1 = "select * from orderinfo where customerId=?";

            JDBCUtil.ps = JDBCUtil.conn.prepareStatement(sql1);
            JDBCUtil.ps.setInt(1,customer.getCustomerId());
            ResultSet rs = JDBCUtil.ps.executeQuery();

            while(rs.next()){
                int orderInfoId = rs.getInt("orderInfoId");
                int customerId = rs.getInt("customerId");
                int employeeId = rs.getInt("employeeId");
                int allNum = rs.getInt("totalNum");
                double allPrice = rs.getDouble("totalPrice");
                Timestamp dateTime = rs.getTimestamp("dateTime");
                orderInfo = new OrderInfo(orderInfoId,new CustomerDaoImpl().findCustomer(customerId),new EmployeeDaoImpl().findEmployee(employeeId),allNum,allPrice,dateTime);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtil.getClose();
        }
        return orderInfo;
    }

    @Override
    public OrderInfo findOrderInfo(Customer customer, Employee employee, int totalNum, double totalPrice) {
        JDBCUtil.getConn();
        String sql = "select * from orderinfo where customerId=? and employeeId=? and totalNum=? and totalPrice=?";
        OrderInfo orderInfo = null;
        try {
            JDBCUtil.ps = JDBCUtil.conn.prepareStatement(sql);
            JDBCUtil.ps.setInt(1,customer.getCustomerId());
            JDBCUtil.ps.setInt(2,employee.getEmployeeId());
            JDBCUtil.ps.setInt(3,totalNum);
            JDBCUtil.ps.setDouble(4,totalPrice);
            ResultSet rs = JDBCUtil.ps.executeQuery();
            while(rs.next()){
                int orderInfoId = rs.getInt("orderInfoId");
                int customerId = rs.getInt("customerId");
                int employeeId = rs.getInt("employeeId");
                int allNum = rs.getInt("totalNum");
                double allPrice = rs.getDouble("totalPrice");
                Timestamp dateTime = rs.getTimestamp("dateTime");
                orderInfo = new OrderInfo(orderInfoId,new CustomerDaoImpl().findCustomer(customerId),new EmployeeDaoImpl().findEmployee(employeeId),allNum,allPrice,dateTime);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtil.getClose();
        }
        return orderInfo;
    }

    @Override
    public boolean modifyOrderInfo(int orderInfoId, int totalNum, double totalPrice) {
        boolean flag = false;
        JDBCUtil.getConn();
        String sql = "update orderinfo set totalNum=?,totalPrice=? where orderInfoId=?";
        try {
            JDBCUtil.ps = JDBCUtil.conn.prepareStatement(sql);
            JDBCUtil.ps.setInt(1,totalNum);
            JDBCUtil.ps.setDouble(2,totalPrice);
            JDBCUtil.ps.setInt(3,orderInfoId);
            JDBCUtil.ps.executeUpdate();
            flag = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public OrderInfo findOrderInfo(int orderInfoId) {
        JDBCUtil.getConn();
        String sql = "select * from orderinfo where customerId=?";
        OrderInfo orderInfo = null;
        try {
            JDBCUtil.ps = JDBCUtil.conn.prepareStatement(sql);
            JDBCUtil.ps.setInt(1,orderInfoId);
            ResultSet rs = JDBCUtil.ps.executeQuery();
            while(rs.next()){
                int orderInfoId1 = rs.getInt("orderInfoId");
                int customerId = rs.getInt("customerId");
                int employeeId = rs.getInt("employeeId");
                int allNum = rs.getInt("totalNum");
                double allPrice = rs.getDouble("totalPrice");
                Timestamp dateTime = rs.getTimestamp("dateTime");
                orderInfo = new OrderInfo(orderInfoId1,new CustomerDaoImpl().findCustomer(customerId),new EmployeeDaoImpl().findEmployee(employeeId),allNum,allPrice,dateTime);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtil.getClose();
        }
        return orderInfo;
    }

    @Override
    public List<OrderInfo> findOrderInfo(Customer customer) {
        List<OrderInfo> orderInfos = new ArrayList<>();
        JDBCUtil.getConn();
        String sql = "select * from orderinfo where customerId=?";
        OrderInfo orderInfo = null;
        try {
            JDBCUtil.ps = JDBCUtil.conn.prepareStatement(sql);
            JDBCUtil.ps.setInt(1,customer.getCustomerId());
            ResultSet rs = JDBCUtil.ps.executeQuery();
            while(rs.next()){
                int orderInfoId = rs.getInt("orderInfoId");
                int customerId = rs.getInt("customerId");
                int employeeId = rs.getInt("employeeId");
                int allNum = rs.getInt("totalNum");
                double allPrice = rs.getDouble("totalPrice");
                Timestamp dateTime = rs.getTimestamp("dateTime");
                orderInfo = new OrderInfo(orderInfoId,new CustomerDaoImpl().findCustomer(customerId),new EmployeeDaoImpl().findEmployee(employeeId),allNum,allPrice,dateTime);
                orderInfos.add(orderInfo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtil.getClose();
        }
        return orderInfos;
    }

    @Override
    public List<OrderInfo> findOrderInfo(Employee employee) {
        List<OrderInfo> orderInfos = new ArrayList<>();
        JDBCUtil.getConn();
        String sql = "select * from orderinfo where employeeId=?";
        OrderInfo orderInfo = null;
        try {
            JDBCUtil.ps = JDBCUtil.conn.prepareStatement(sql);
            JDBCUtil.ps.setInt(1,employee.getEmployeeId());
            ResultSet rs = JDBCUtil.ps.executeQuery();
            while(rs.next()){
                int orderInfoId = rs.getInt("orderInfoId");
                int customerId = rs.getInt("customerId");
                int employeeId = rs.getInt("employeeId");
                int allNum = rs.getInt("totalNum");
                double allPrice = rs.getDouble("totalPrice");
                Timestamp dateTime = rs.getTimestamp("dateTime");
                orderInfo = new OrderInfo(orderInfoId,new CustomerDaoImpl().findCustomer(customerId),new EmployeeDaoImpl().findEmployee(employeeId),allNum,allPrice,dateTime);
                orderInfos.add(orderInfo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtil.getClose();
        }
        return orderInfos;
    }
}
