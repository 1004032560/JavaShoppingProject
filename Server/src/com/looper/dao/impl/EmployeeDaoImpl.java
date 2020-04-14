package com.looper.dao.impl;

import com.looper.dao.EmployeeDao;
import com.looper.dao.ShopDao;
import com.looper.domain.Employee;
import com.looper.domain.Shop;
import com.looper.util.JDBCUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDaoImpl implements EmployeeDao {

    @Override
    public boolean addEmployee(Shop shop, String employeeName, int employeeAge, String employeeSex, String employeeAddress, double employeeSalary, String employeeRole) {
        boolean flag = false;
        JDBCUtil.getConn();
        try {
            String sql = "insert into employees(shopId,employeeName,employeeAge,employeeSex,employeeAddress,employeeSalary,employeeRole) values(?,?,?,?,?,?,?)";
            JDBCUtil.ps = JDBCUtil.conn.prepareStatement(sql);
            JDBCUtil.ps.setInt(1,shop.getShopId());
            JDBCUtil.ps.setString(2,employeeName);
            JDBCUtil.ps.setInt(3,employeeAge);
            JDBCUtil.ps.setString(4,employeeSex);
            JDBCUtil.ps.setString(5,employeeAddress);
            JDBCUtil.ps.setDouble(6,employeeSalary);
            JDBCUtil.ps.setString(7,employeeRole);
            JDBCUtil.ps.executeUpdate();
            flag = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtil.getClose();
        }
        return flag;
    }

    @Override
    public boolean deleteEmployee(int employeeId, String employeeName) {
        boolean flag = false;
        JDBCUtil.getConn();
        try {
            String sql = "delete from employees where employeeId=? and employeeName=?";
            JDBCUtil.ps = JDBCUtil.conn.prepareStatement(sql);
            JDBCUtil.ps.setInt(1,employeeId);
            JDBCUtil.ps.setString(2,employeeName);
            JDBCUtil.ps.executeUpdate();
            flag = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        JDBCUtil.getClose();
        return flag;
    }

    @Override
    public boolean modifyEmployee(int employeeId, String newName, String newPassword, int newAge, String newSex, String newAddress) {
        boolean flag = false;
        JDBCUtil.getConn();
        try {
            String sql = "update employees set employeeName=?, employeePassword=?, employeeAge=?, employeeSex=?, employeeAddress=? where employeeId=?";
            JDBCUtil.ps = JDBCUtil.conn.prepareStatement(sql);
            JDBCUtil.ps.setString(1,newName);
            JDBCUtil.ps.setString(2,newPassword);
            JDBCUtil.ps.setInt(3,newAge);
            JDBCUtil.ps.setString(4,newSex);
            JDBCUtil.ps.setString(5,newAddress);
            JDBCUtil.ps.setInt(6,employeeId);
            JDBCUtil.ps.executeUpdate();
            flag = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtil.getClose();
        }
        return flag;
    }

    @Override
    public List<Employee> findAllEmployee() {
        ShopDao shopDao = new ShopDaoImpl();
        Shop shop = new Shop();
        JDBCUtil.getConn();
        List<Employee> employees = new ArrayList<>();
        try {
            String sql = "select * from employees";
            JDBCUtil.ps = JDBCUtil.conn.prepareStatement(sql);
            ResultSet rs = JDBCUtil.ps.executeQuery();
            while(rs.next()) {
                int id = rs.getInt("employeeId");
                int shopId = rs.getInt("shopId");
                shop = shopDao.findShop(shopId);
                String name = rs.getString("employeeName");
                String password = rs.getString("employeePassword");
                int age = rs.getInt("employeeAge");
                String sex = rs.getString("employeeSex");
                String address = rs.getString("employeeAddress");
                Timestamp dateTime = rs.getTimestamp("employeeDate");
                double salary = rs.getDouble("employeeSalary");
                String role = rs.getString("employeeRole");
                employees.add(new Employee(id,shop,name,password,age,sex,address,dateTime,salary,role));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtil.getClose();
        }
        return employees;
    }

    @Override
    public List<Employee> findAllEmployee(String employeeRole) {
        ShopDao shopDao = new ShopDaoImpl();
        Shop shop = new Shop();
        JDBCUtil.getConn();
        List<Employee> employees = new ArrayList<>();
        try {
            String sql = "select * from employees where employeeRole=?";
            JDBCUtil.ps = JDBCUtil.conn.prepareStatement(sql);
            JDBCUtil.ps.setString(1,employeeRole);
            ResultSet rs = JDBCUtil.ps.executeQuery();
            while(rs.next()) {
                int id = rs.getInt("employeeId");
                int shopId = rs.getInt("shopId");
                shop = shopDao.findShop(shopId);
                String name = rs.getString("employeeName");
                String password = rs.getString("employeePassword");
                int age = rs.getInt("employeeAge");
                String sex = rs.getString("employeeSex");
                String address = rs.getString("employeeAddress");
                Timestamp dateTime = rs.getTimestamp("employeeDate");
                double salary = rs.getDouble("employeeSalary");
                String role = rs.getString("employeeRole");
                employees.add(new Employee(id,shop,name,password,age,sex,address,dateTime,salary,role));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtil.getClose();
        }
        return employees;
    }

    @Override
    public Employee findEmployee(int employeeId) {
        ShopDao shopDao = new ShopDaoImpl();
        Shop shop = new Shop();
        JDBCUtil.getConn();
        Employee employee = null;
        try {
            String sql = "select * from employees where employeeId=?";
            JDBCUtil.ps = JDBCUtil.conn.prepareStatement(sql);
            JDBCUtil.ps.setInt(1,employeeId);
            ResultSet rs = JDBCUtil.ps.executeQuery();
            while(rs.next()) {
                int id = rs.getInt("employeeId");
                int shopId = rs.getInt("shopId");
                shop = shopDao.findShop(shopId);
                String name = rs.getString("employeeName");
                String password = rs.getString("employeePassword");
                int age = rs.getInt("employeeAge");
                String sex = rs.getString("employeeSex");
                String address = rs.getString("employeeAddress");
                Timestamp dateTime = rs.getTimestamp("employeeDate");
                double salary = rs.getDouble("employeeSalary");
                String role = rs.getString("employeeRole");
                employee = new Employee(id,shop,name,password,age,sex,address,dateTime,salary,role);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtil.getClose();
        }
        return employee;
    }

    @Override
    public Employee findEmployee(String employeeName, String employeePassword) {
        ShopDao shopDao = new ShopDaoImpl();
        Shop shop = new Shop();
        JDBCUtil.getConn();
        Employee employee = null;
        try {
            String sql = "select * from employees where employeeName=? and employeePassword=?";
            JDBCUtil.ps = JDBCUtil.conn.prepareStatement(sql);
            JDBCUtil.ps.setString(1,employeeName);
            JDBCUtil.ps.setString(2,employeePassword);
            ResultSet rs = JDBCUtil.ps.executeQuery();
            while(rs.next()) {
                int id = rs.getInt("employeeId");
                int shopId = rs.getInt("shopId");
                shop = shopDao.findShop(shopId);
                String name = rs.getString("employeeName");
                String password = rs.getString("employeePassword");
                int age = rs.getInt("employeeAge");
                String sex = rs.getString("employeeSex");
                String address = rs.getString("employeeAddress");
                Timestamp dateTime = rs.getTimestamp("employeeDate");
                double salary = rs.getDouble("employeeSalary");
                String role = rs.getString("employeeRole");
                employee = new Employee(id,shop,name,password,age,sex,address,dateTime,salary,role);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtil.getClose();
        }
        return employee;
    }

    @Override
    public boolean modifyEmpRoleAndSalaryByEmployeeId(int employeeId, double newSalary, String newRole) {
        boolean flag = false;
        JDBCUtil.getConn();
        try {
            String sql = "update employees set employeeSalary=? , employeeRole=? where employeeId=?";
            JDBCUtil.ps = JDBCUtil.conn.prepareStatement(sql);
            JDBCUtil.ps.setDouble(1,newSalary);
            JDBCUtil.ps.setString(2,newRole);
            JDBCUtil.ps.setInt(3,employeeId);
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
