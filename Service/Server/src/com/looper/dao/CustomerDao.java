package com.looper.dao;

import com.looper.domain.Customer;

import java.util.List;

public interface CustomerDao {

    //添加客户信息
    boolean addCustomer(String customerName, int customerAge, String customerSex,String customerType,double storeMoney,String customerPassword);

    //删除客户信息
    boolean deleteCustomerByCustomerId(int customerId, String customerName);

    //查询全部客户
    List<Customer> findAllCustomer();

    //按照客户号查询客户
    Customer findCustomer(int customerId);

    //按照客户姓名查询客户
    Customer findCustomer(String customerName);

    //按照客户姓名查询客户
    Customer findCustomer(String customerName,String customerPassword);

    //修改客户信息
    boolean modifyCustomer(int customerId, String newName, int newAge, String newSex,String newPassword);

    //修改客户类型通过客户ID
    boolean modifyCustomerType(int customerId, String customerType);

    //修改客户金额
    boolean modifyStorePrice(int customerId, double storeMoney);

}
