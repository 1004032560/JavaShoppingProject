package com.looper.test;

import com.looper.dao.CustomerDao;
import com.looper.dao.impl.CustomerDaoImpl;

public class CustomerDaoTest {

    public static void main(String[] args) {

        CustomerDao customerDao = new CustomerDaoImpl();

        //测试添加顾客
        //System.out.println(customerDao.addCustomer("王悦",20,"女"));

        //测试删除顾客
        //System.out.println(customerDao.deleteCustomerByCustomerId(1006,"哈哈哈"));

        //测试显示全部顾客信息
        /*for (Customer customer : customerDao.findAllCustomer()) {
            System.out.println(customer);
        }*/

        //测试按照客户ID查询客户
        //System.out.println(customerDao.findCustomer(1008));

        //测试按照客户Name查询客户
        //System.out.println(customerDao.findCustomer("王悦"));

        //测试修改客户信息
        //System.out.println(customerDao.modifyCustomer(1008,"王悦",18,"女"));

        //测试修改客户类型通过客户ID
        //System.out.println(customerDao.modifyCustomerType(1008,"会员用户"));

        //测试修改客户金额
        //System.out.println(customerDao.modifyStorePrice(1008,2000));

    }

}
