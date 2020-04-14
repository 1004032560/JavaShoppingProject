package com.looper.domain;

import java.io.Serializable;
import java.util.Date;

public class Employee implements Serializable {
    private static final long serialVersionUID = -7221894678884917158L;
    private int employeeId;
    private Shop shop;
    private String employeeName;
    private String employeePassword;
    private int employeeAge;
    private String employeeSex;
    private String employeeAddress;
    private Date employeeDate;
    private double employeeSalary;
    private String employeeRole;

    public Employee() {
    }

    public Employee(String employeeName, String employeePassword) {
        this.employeeName = employeeName;
        this.employeePassword = employeePassword;
    }

    public Employee(String employeeRole) {
        this.employeeRole = employeeRole;
    }

    public Employee(int employeeId) {
        this.employeeId = employeeId;
    }

    public Employee(Shop shop, String employeeName, int employeeAge, String employeeSex, String employeeAddress, double employeeSalary, String employeeRole) {
        this.shop = shop;
        this.employeeName = employeeName;
        this.employeeAge = employeeAge;
        this.employeeSex = employeeSex;
        this.employeeAddress = employeeAddress;
        this.employeeSalary = employeeSalary;
        this.employeeRole = employeeRole;
    }

    public Employee(int employeeId, String employeeName, String employeePassword, int employeeAge, String employeeSex, String employeeAddress) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.employeePassword = employeePassword;
        this.employeeAge = employeeAge;
        this.employeeSex = employeeSex;
        this.employeeAddress = employeeAddress;
    }

    public Employee(int employeeId, Shop shop, String employeeName, String employeePassword, int employeeAge, String employeeSex, String employeeAddress, Date employeeDate, double employeeSalary, String employeeRole) {
        this.employeeId = employeeId;
        this.shop = shop;
        this.employeeName = employeeName;
        this.employeePassword = employeePassword;
        this.employeeAge = employeeAge;
        this.employeeSex = employeeSex;
        this.employeeAddress = employeeAddress;
        this.employeeDate = employeeDate;
        this.employeeSalary = employeeSalary;
        this.employeeRole = employeeRole;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeePassword() {
        return employeePassword;
    }

    public void setEmployeePassword(String employeePassword) {
        this.employeePassword = employeePassword;
    }

    public int getEmployeeAge() {
        return employeeAge;
    }

    public void setEmployeeAge(int employeeAge) {
        this.employeeAge = employeeAge;
    }

    public String getEmployeeSex() {
        return employeeSex;
    }

    public void setEmployeeSex(String employeeSex) {
        this.employeeSex = employeeSex;
    }

    public String getEmployeeAddress() {
        return employeeAddress;
    }

    public void setEmployeeAddress(String employeeAddress) {
        this.employeeAddress = employeeAddress;
    }

    public Date getEmployeeDate() {
        return employeeDate;
    }

    public void setEmployeeDate(Date employeeDate) {
        this.employeeDate = employeeDate;
    }

    public double getEmployeeSalary() {
        return employeeSalary;
    }

    public void setEmployeeSalary(double employeeSalary) {
        this.employeeSalary = employeeSalary;
    }

    public String getEmployeeRole() {
        return employeeRole;
    }

    public void setEmployeeRole(String employeeRole) {
        this.employeeRole = employeeRole;
    }

    @Override
    public String toString() {
        return "员工信息{" +
                " ID:" + employeeId +
                ", 所在店铺信息" + shop +
                "; 姓名:'" + employeeName +
                ", 密码:" + employeePassword +
                ", 年龄:" + employeeAge +
                ", 性别:" + employeeSex +
                ", 地址:" + employeeAddress +
                ", 入职日期:" + employeeDate +
                ", 薪资:" + employeeSalary +
                ", 职位:" + employeeRole +
                " }";
    }
}
