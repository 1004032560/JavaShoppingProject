package com.looper.service.impl;

import com.looper.dao.CustomerDao;
import com.looper.dao.EmployeeDao;
import com.looper.dao.impl.CustomerDaoImpl;
import com.looper.dao.impl.EmployeeDaoImpl;
import com.looper.domain.Employee;
import com.looper.domain.Shop;
import com.looper.service.ServiceCustomer;
import com.looper.service.ServiceEmployee;

import java.util.List;

public class ServiceEmployeeImpl implements ServiceEmployee {

    EmployeeDao employeeDao = new EmployeeDaoImpl();

    @Override
    public boolean addEmployee(Shop shop, String employeeName, int employeeAge, String employeeSex, String employeeAddress, double employeeSalary, String employeeRole) {
        return employeeDao.addEmployee(shop,employeeName,employeeAge,employeeSex,employeeAddress,employeeSalary,employeeRole);
    }

    @Override
    public boolean deleteEmployee(int employeeId, String employeeName) {
        return employeeDao.deleteEmployee(employeeId,employeeName);
    }

    @Override
    public boolean modifyEmployee(int employeeId, String newName, String newPassword, int newAge, String newSex, String newAddress) {
        return employeeDao.modifyEmployee(employeeId,newName,newPassword,newAge,newSex,newAddress);
    }

    @Override
    public List<Employee> findAllEmployee() {
        return employeeDao.findAllEmployee();
    }

    @Override
    public List<Employee> findAllEmployee(String employeeRole) {
        return employeeDao.findAllEmployee(employeeRole);
    }


    @Override
    public Employee findEmployee(int employeeId) {
        return employeeDao.findEmployee(employeeId);
    }

    @Override
    public Employee findEmployee(String employeeName, String employeePassword) {
        return employeeDao.findEmployee(employeeName,employeePassword);
    }

    @Override
    public boolean modifyEmpRoleAndSalaryByEmployeeId(int employeeId, double newSalary, String newRole) {
        return employeeDao.modifyEmpRoleAndSalaryByEmployeeId(employeeId,newSalary,newRole);
    }
}
