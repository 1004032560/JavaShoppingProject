package com.looper.test;

import com.looper.dao.EmployeeDao;
import com.looper.dao.ShopDao;
import com.looper.dao.impl.EmployeeDaoImpl;
import com.looper.dao.impl.ShopDaoImpl;

public class EmployeeDaoTest {

    public static void main(String[] args) {

        EmployeeDao employeeDao = new EmployeeDaoImpl();

        ShopDao shopDao = new ShopDaoImpl();

        //测试添加信息
        //employeeDao.addEmployee(shopDao.findShop(1,"looper"),"jkl","123456",20,"男","武汉");

        //测试删除信息
        //employeeDao.deleteEmployee(6,"sf");

        //测试修改信息
        //employeeDao.modifyEmployee(3,"iop","123456",25,"男","美国");

        //测试查询全部员工
        /*for (Employee employee : employeeDao.findAllEmployee()) {
            System.out.println(employee);
        }*/

        //测试按照ID查询员工
        //System.out.println(employeeDao.findEmployeeByEmployeeId(4));

        //测试修改员工薪资
        //System.out.println(employeeDao.modifyEmpRoleAndSalaryByEmployeeId(4,7000));

    }

}
