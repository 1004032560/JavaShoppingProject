package com.looper.dao.impl;

import com.looper.dao.CustomerDao;
import com.looper.domain.Customer;
import com.looper.util.JDBCUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDaoImpl implements CustomerDao {

    //添加客户信息(注册)
    @Override
    public boolean addCustomer(String customerName, int customerAge, String customerSex, String customerType, double storeMoney, String customerPassword) {
        boolean flag = false;
        JDBCUtil.getConn();
        try {
            String sql = "insert into customers(customerName,customerAge,customerSex,customerType,storeMoney,customerPassword) values(?,?,?,?,?,?)";
            JDBCUtil.ps = JDBCUtil.conn.prepareStatement(sql);
            JDBCUtil.ps.setString(1,customerName);
            JDBCUtil.ps.setInt(2,customerAge);
            JDBCUtil.ps.setString(3,customerSex);
            JDBCUtil.ps.setString(4,customerType);
            JDBCUtil.ps.setDouble(5,storeMoney);
            JDBCUtil.ps.setString(6,customerPassword);
            JDBCUtil.ps.executeUpdate();
            flag = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtil.getClose();
        }
        return flag;
    }

    //删除客户信息
    @Override
    public boolean deleteCustomerByCustomerId(int customerId, String customerName) {
        boolean flag = false;
        JDBCUtil.getConn();
        try {
            String sql = "delete from customers where customerId=? and customerName=?";
            JDBCUtil.ps = JDBCUtil.conn.prepareStatement(sql);
            JDBCUtil.ps.setInt(1,customerId);
            JDBCUtil.ps.setString(2,customerName);
            JDBCUtil.ps.executeUpdate();
            flag = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        JDBCUtil.getClose();
        return flag;
    }

    //查询全部客户
    @Override
    public List<Customer> findAllCustomer() {
        JDBCUtil.getConn();
        List<Customer> customers = new ArrayList<>();
        try {
            String sql = "select * from customers";
            JDBCUtil.ps = JDBCUtil.conn.prepareStatement(sql);
            ResultSet rs = JDBCUtil.ps.executeQuery();
            while(rs.next()) {
                int id = rs.getInt("customerId");
                String name = rs.getString("customerName");
                int age = rs.getInt("customerAge");
                String sex = rs.getString("customerSex");
                String type = rs.getString("customerType");
                double storeMoney = rs.getDouble("storeMoney");
                String password = rs.getString("customerPassword");
                customers.add(new Customer(id,name,age,sex,type,storeMoney,password));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtil.getClose();
        }
        return customers;
    }

    //按照客户号查询客户
    @Override
    public Customer findCustomer(int customerId) {
        JDBCUtil.getConn();
        Customer customer = null;
        String sql = "select * from customers where customerId=?";
        try {
            JDBCUtil.ps = JDBCUtil.conn.prepareStatement(sql);
            JDBCUtil.ps.setInt(1,customerId);
            ResultSet rs = JDBCUtil.ps.executeQuery();
            while(rs.next()){
                customer = new Customer(rs.getInt("customerId"),rs.getString("customerName"),rs.getInt("customerAge"),rs.getString("customerSex"),rs.getString("customerType"),rs.getDouble("storeMoney"),rs.getString("customerPassword"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtil.getClose();
        }
        return customer;
    }

    //按照客户姓名查询客户
    @Override
    public Customer findCustomer(String customerName) {
        JDBCUtil.getConn();
        Customer customer = null;
        String sql = "select * from customers where customerName=?";
        try {
            JDBCUtil.ps = JDBCUtil.conn.prepareStatement(sql);
            JDBCUtil.ps.setString(1,customerName);
            ResultSet rs = JDBCUtil.ps.executeQuery();
            while(rs.next()){
                customer = new Customer(rs.getInt("customerId"),rs.getString("customerName"),rs.getInt("customerAge"),rs.getString("customerSex"),rs.getString("customerType"),rs.getDouble("storeMoney"),rs.getString("customerPassword"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtil.getClose();
        }
        return customer;
    }

    //按照客户姓名和Password查询客户
    @Override
    public Customer findCustomer(String customerName, String customerPassword) {
        JDBCUtil.getConn();
        Customer customer = null;
        String sql = "select * from customers where customerName=? and customerPassword=?";
        try {
            JDBCUtil.ps = JDBCUtil.conn.prepareStatement(sql);
            JDBCUtil.ps.setString(1,customerName);
            JDBCUtil.ps.setString(2,customerPassword);
            ResultSet rs = JDBCUtil.ps.executeQuery();
            while(rs.next()){
                int id = rs.getInt("customerId");
                String name = rs.getString("customerName");
                String password = rs.getString("customerPassword");
                int age = rs.getInt("customerAge");
                String sex = rs.getString("customerSex");
                String type = rs.getString("customerType");
                double money = rs.getDouble("storeMoney");
                customer = new Customer(id,name,age,sex,type,money,password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtil.getClose();
        }
        return customer;
    }

    //修改客户信息
    @Override
    public boolean modifyCustomer(int customerId, String newName, int newAge, String newSex, String newPassword) {
        boolean flag = false;
        JDBCUtil.getConn();
        try {
            String sql = "update customers set customerName=?, customerAge=?, customerSex=?, customerPassword=? where customerId=?";
            JDBCUtil.ps = JDBCUtil.conn.prepareStatement(sql);
            JDBCUtil.ps.setString(1,newName);
            JDBCUtil.ps.setInt(2,newAge);
            JDBCUtil.ps.setString(3,newSex);
            JDBCUtil.ps.setString(4,newPassword);
            JDBCUtil.ps.setInt(5,customerId);
            JDBCUtil.ps.executeUpdate();
            flag = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtil.getClose();
        }
        return flag;
    }

    //修改客户类型通过客户ID
    @Override
    public boolean modifyCustomerType(int customerId, String customerType) {
        boolean flag = false;
        JDBCUtil.getConn();
        try {
            String sql = "update customers set customerType=? where customerId=?";
            JDBCUtil.ps = JDBCUtil.conn.prepareStatement(sql);
            JDBCUtil.ps.setString(1,customerType);
            JDBCUtil.ps.setInt(2,customerId);
            JDBCUtil.ps.executeUpdate();
            flag = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtil.getClose();
        }
        return flag;
    }

    //修改storeMoney
    @Override
    public boolean modifyStorePrice(int customerId, double storeMoney) {
        boolean flag = false;
        JDBCUtil.getConn();
        try {
            String sql = "update customers set storeMoney=? where customerId=?";
            JDBCUtil.ps = JDBCUtil.conn.prepareStatement(sql);
            JDBCUtil.ps.setDouble(1,storeMoney);
            JDBCUtil.ps.setInt(2,customerId);
            JDBCUtil.ps.executeUpdate();
            flag = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtil.getClose();
        }
        return flag;
    }
}
