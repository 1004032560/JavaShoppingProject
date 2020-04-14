package com.looper.dao;

import com.looper.domain.Employee;
import com.looper.domain.Shop;

import java.util.List;

public interface EmployeeDao {

    //新增员工
    boolean addEmployee(Shop shop, String employeeName, int employeeAge, String employeeSex, String employeeAddress, double employeeSalary, String employeeRole);

    //删除员工
    boolean deleteEmployee(int employeeId, String employeeName);

    //修改个人信息
    boolean modifyEmployee(int employeeId, String newName, String newPassword, int newAge, String newSex, String newAddress);

    //查询全部员工
    List<Employee> findAllEmployee();

    //按类型查询全部员工
    List<Employee> findAllEmployee(String employeeRole);

    //按照员工号查询员工
    Employee findEmployee(int employeeId);

    //按照员工Name和Password查询员工
    Employee findEmployee(String employeeName,String employeePassword);

    //角通过员工号修改员工色，同时修改薪资（权限管理）
    boolean modifyEmpRoleAndSalaryByEmployeeId(int employeeId,double newSalary,String newRole);

}