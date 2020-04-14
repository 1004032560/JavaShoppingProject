package com.looper.service.impl;

import com.looper.dao.CustomerDao;
import com.looper.dao.impl.CustomerDaoImpl;
import com.looper.domain.Customer;
import com.looper.service.ServiceCustomer;

import java.util.List;

public class ServiceCustomerImpl implements ServiceCustomer {

    CustomerDao customerDao = new CustomerDaoImpl();

    @Override
    public boolean addCustomer(String customerName, int customerAge, String customerSex, String customerType, double storeMoney, String customerPassword) {
        return customerDao.addCustomer(customerName,customerAge,customerSex,customerType,storeMoney,customerPassword);
    }

    @Override
    public boolean deleteCustomerByCustomerId(int customerId, String customerName) {
        return customerDao.deleteCustomerByCustomerId(customerId,customerName);
    }

    @Override
    public List<Customer> findAllCustomer() {
        return customerDao.findAllCustomer();
    }

    @Override
    public Customer findCustomer(int customerId) {
        return customerDao.findCustomer(customerId);
    }

    @Override
    public Customer findCustomer(String customerName) {
        return customerDao.findCustomer(customerName);
    }

    @Override
    public Customer findCustomer(String customerName, String customerPassword) {
        return customerDao.findCustomer(customerName,customerPassword);
    }

    @Override
    public boolean modifyCustomer(int customerId, String newName, int newAge, String newSex, String newPassword) {
        return customerDao.modifyCustomer(customerId,newName,newAge,newSex,newPassword);
    }

    @Override
    public boolean modifyCustomerType(int customerId, String customerType) {
        return customerDao.modifyCustomerType(customerId,customerType);
    }

    @Override
    public boolean modifyStorePrice(int customerId, double storeMoney) {
        return customerDao.modifyStorePrice(customerId,storeMoney);
    }
}
