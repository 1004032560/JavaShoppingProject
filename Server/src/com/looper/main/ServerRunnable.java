package com.looper.main;

import com.looper.domain.*;
import com.looper.service.*;
import com.looper.service.impl.*;

import java.io.*;
import java.net.Socket;
import java.util.List;

public class ServerRunnable implements Runnable {

    private Socket socket;

    public ServerRunnable(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        while (true){

            try {

                InputStream is = socket.getInputStream();
                ObjectInputStream ois = new ObjectInputStream(is);
                Object object = ois.readObject();

                Data dataRecv = (Data) object;

                OutputStream os = socket.getOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(os);


                //顾客信息（登录）
                if ("顾客登录".equals(dataRecv.getMsg())) {

                    Customer customer = (Customer) dataRecv.getObject();
                    ServiceCustomer serviceCustomer = new ServiceCustomerImpl();
                    Customer customer1 = serviceCustomer.findCustomer(customer.getCustomerName());
                    if (customer1!=null) {
                        Customer customer2 = serviceCustomer.findCustomer(customer.getCustomerName(),customer.getCustomerPassword());
                        if (customer2!=null){
                            Data dataSend = new Data("该用户存在",customer2);
                            oos.writeObject(dataSend);
                        }else{
                            Data dataSend = new Data("用户名或密码错误!");
                            oos.writeObject(dataSend);
                        }
                    }else {
                        Data dataSend = new Data("该用户不存在!");
                        oos.writeObject(dataSend);
                    }

                }
                //用户注册
                else if ("顾客注册".equals(dataRecv.getMsg())) {
                    Customer customer = (Customer) dataRecv.getObject();
                    ServiceCustomer serviceCustomer = new ServiceCustomerImpl();
                    boolean res = serviceCustomer.addCustomer(customer.getCustomerName(), customer.getCustomerAge(), customer.getCustomerSex(), customer.getCustomerType(), customer.getStoreMoney(),customer.getCustomerPassword());
                    if (res) {
                        Data dataSend = new Data("注册成功!");
                        oos.writeObject(dataSend);
                    }else {
                        Data dataSend = new Data("注册失败!");
                        oos.writeObject(dataSend);
                    }
                }
                //查询顾客（ID查找）
                else if ("查询顾客".equals(dataRecv.getMsg())) {
                    Customer customer = (Customer) dataRecv.getObject();
                    ServiceCustomer serviceCustomer = new ServiceCustomerImpl();
                    Customer customer1 = serviceCustomer.findCustomer(customer.getCustomerId());
                    if (customer1!=null) {
                        Data dataSend = new Data("该用户存在",customer1);
                        oos.writeObject(dataSend);
                    }else {
                        Data dataSend = new Data("该用户不存在！");
                        oos.writeObject(dataSend);
                    }
                }
                //按用户名查询用户
                else if ("按用户名查询用户".equals(dataRecv.getMsg())) {
                    Customer customer = (Customer) dataRecv.getObject();
                    ServiceCustomer serviceCustomer = new ServiceCustomerImpl();
                    Customer customer1 = serviceCustomer.findCustomer(customer.getCustomerName());
                    if (customer1!=null) {
                        Data dataSend = new Data("该用户存在",customer1);
                        oos.writeObject(dataSend);
                    }else {
                        Data dataSend = new Data("该用户不存在！");
                        oos.writeObject(dataSend);
                    }
                }
                //修改用户余额
                else if ("修改用户余额".equals(dataRecv.getMsg())) {
                    Customer customer = (Customer) dataRecv.getObject();
                    ServiceCustomer serviceCustomer = new ServiceCustomerImpl();
                    boolean flag = serviceCustomer.modifyStorePrice(customer.getCustomerId(),customer.getStoreMoney());
                    if (flag) {
                        Data dataSend = new Data("修改成功!");
                        oos.writeObject(dataSend);
                    }else {
                        Data dataSend = new Data("修改失败!");
                        oos.writeObject(dataSend);
                    }
                }
                //修改顾客类型
                else if ("323".equals(dataRecv.getMsg())){
                    Customer customer = (Customer) dataRecv.getObject();
                    ServiceCustomer serviceCustomer = new ServiceCustomerImpl();
                    boolean flag = true;
                    if (dataRecv.getNum()==1){
                        serviceCustomer.modifyCustomerType(customer.getCustomerId(),"会员用户");
                    }else{
                        serviceCustomer.modifyCustomerType(customer.getCustomerId(),"普通用户");
                    }
                    if (flag){
                        Data dataSend = new Data("修改成功");
                        oos.writeObject(dataSend);
                    }else{
                        Data dataSend = new Data("修改失败");
                        oos.writeObject(dataSend);
                    }
                }
                //修改顾客全部信息
                else if ("修改顾客全部信息".equals(dataRecv.getMsg())){
                    Customer customer = (Customer) dataRecv.getObject();
                    ServiceCustomer serviceCustomer = new ServiceCustomerImpl();
                    boolean flag = serviceCustomer.modifyCustomer(customer.getCustomerId(),customer.getCustomerName(),customer.getCustomerAge(),customer.getCustomerSex(),customer.getCustomerPassword());
                    if (flag){
                        Data dataSend = new Data("修改成功");
                        oos.writeObject(dataSend);
                    }else{
                        Data dataSend = new Data("修改失败");
                        oos.writeObject(dataSend);
                    }
                }
                //查看所有顾客信息
                else if ("313".equals(dataRecv.getMsg())){
                    //查看所有顾客信息
                    ServiceCustomer serviceCustomer = new ServiceCustomerImpl();
                    List<Customer> customers = serviceCustomer.findAllCustomer();
                    for (Customer customer : customers) {
                        Data dataSend = new Data(customers.size(), "ok", customer);
                        oos.writeObject(dataSend);
                    }
                }



                //查看所有员工信息
                else if ("312".equals(dataRecv.getMsg())){
                    //查看所有员工信息
                    ServiceEmployee serviceEmployee = new ServiceEmployeeImpl();
                    List<Employee> employees = serviceEmployee.findAllEmployee();
                    for (Employee employee : employees) {
                        //获取信息
                        Data dataSend = new Data(employees.size(), "ok", employee);
                        //写数据给服务器端
                        oos.writeObject(dataSend);
                    }
                }
                //查询员工（ID查找）
                else if ("查询员工".equals(dataRecv.getMsg())){
                    Employee employee = (Employee) dataRecv.getObject();
                    ServiceEmployee serviceEmployee = new ServiceEmployeeImpl();
                    Employee employee1 = serviceEmployee.findEmployee(employee.getEmployeeId());
                    Data dataSend = new Data(employee1);
                    oos.writeObject(dataSend);
                }
                //修改员工信息（ID查找）
                else if ("修改员工信息".equals(dataRecv.getMsg())){
                    Employee employee = (Employee) dataRecv.getObject();
                    ServiceEmployee serviceEmployee = new ServiceEmployeeImpl();
                    boolean flag = serviceEmployee.modifyEmployee(employee.getEmployeeId(),employee.getEmployeeName(),employee.getEmployeePassword(),employee.getEmployeeAge(),employee.getEmployeeSex(),employee.getEmployeeAddress());
                    if (flag){
                        Data dataSend = new Data("修改成功");
                        oos.writeObject(dataSend);
                    }else{
                        Data dataSend = new Data("修改失败");
                        oos.writeObject(dataSend);
                    }
                }
                //员工信息（登录）
                else if ("3".equals(dataRecv.getMsg())){
                    Employee employee = (Employee) dataRecv.getObject();
                    ServiceEmployee serviceEmployee = new ServiceEmployeeImpl();

                    Employee employee1 = serviceEmployee.findEmployee(employee.getEmployeeName(),employee.getEmployeePassword());

                    if (employee1!=null){
                        Data dataSend = new Data("该员工存在",employee1);
                        oos.writeObject(dataSend);
                    }else{
                        Data dataSend = new Data("用户名或密码错误！");
                        oos.writeObject(dataSend);
                    }
                }
                //修改员工类型
                else if ("322".equals(dataRecv.getMsg())){
                    Employee employee = (Employee) dataRecv.getObject();
                    ServiceEmployee serviceEmployee = new ServiceEmployeeImpl();
                    boolean flag = false;
                    if (dataRecv.getNum()==1){
                        flag = serviceEmployee.modifyEmpRoleAndSalaryByEmployeeId(employee.getEmployeeId(),8000,"admin");
                    }else{
                        flag = serviceEmployee.modifyEmpRoleAndSalaryByEmployeeId(employee.getEmployeeId(),6000,"common");
                    }
                    if (flag){
                        Data dataSend = new Data("修改成功");
                        oos.writeObject(dataSend);
                    }else{
                        Data dataSend = new Data("修改失败");
                        oos.writeObject(dataSend);
                    }
                }
                //添加新员工
                else if ("34".equals(dataRecv.getMsg())){
                    Employee employee = (Employee) dataRecv.getObject();
                    ServiceEmployee serviceEmployee = new ServiceEmployeeImpl();
                    boolean flag = serviceEmployee.addEmployee(employee.getShop(),employee.getEmployeeName(),employee.getEmployeeAge(),employee.getEmployeeSex(),employee.getEmployeeAddress(),employee.getEmployeeSalary(),employee.getEmployeeRole());
                    if (flag){
                        Data dataSend = new Data("添加成功");
                        oos.writeObject(dataSend);
                    }else{
                        Data dataSend = new Data("添加失败");
                        oos.writeObject(dataSend);
                    }
                }
                //查询所有收银员
                else if ("查询所有收银员".equals(dataRecv.getMsg())){
                    Employee employee1 = (Employee) dataRecv.getObject();
                    //查看所有员工信息
                    ServiceEmployee serviceEmployee = new ServiceEmployeeImpl();
                    List<Employee> employees = serviceEmployee.findAllEmployee(employee1.getEmployeeRole());
                    for (Employee employee : employees) {
                        Data dataSend = new Data(employees.size(), "ok", employee);
                        oos.writeObject(dataSend);
                    }
                }



                //查询该顾客所有订单
                else if ("查询该顾客所有订单".equals(dataRecv.getMsg())) {
                    Customer customer = (Customer) dataRecv.getObject();
                    ServiceOrderInfo serviceOrderInfo = new ServiceOrderInfoImpl();
                    List<OrderInfo> orderInfos = serviceOrderInfo.findOrderInfo(customer);;
                    for (OrderInfo orderInfo : orderInfos) {
                        Data dataSend = new Data(orderInfos.size(),"ok",orderInfo);
                        oos.writeObject(dataSend);
                    }
                }
                //查询该员工所有销售订单
                else if ("查询该员工所有订单".equals(dataRecv.getMsg())) {
                    Employee employee = (Employee) dataRecv.getObject();
                    ServiceOrderInfo serviceOrderInfo = new ServiceOrderInfoImpl();
                    List<OrderInfo> orderInfos = serviceOrderInfo.findOrderInfo(employee);
                    for (OrderInfo orderInfo : orderInfos) {
                        Data dataSend = new Data(orderInfos.size(),"ok",orderInfo);
                        oos.writeObject(dataSend);
                    }

                }
                //查询所有总订单
                else if ("查询所有总订单".equals(dataRecv.getMsg())) {
                    ServiceOrderInfo serviceOrderInfo = new ServiceOrderInfoImpl();
                    List<OrderInfo> orderInfos = serviceOrderInfo.findAllOrderInfo();
                    for (OrderInfo orderInfo : orderInfos) {
                        Data dataSend = new Data(orderInfos.size(),"ok",orderInfo);
                        oos.writeObject(dataSend);
                    }
                }


                //查询该顾客所有详细订单
                else if ("查询该顾客所有详细订单".equals(dataRecv.getMsg())) {
                    Customer customer = (Customer) dataRecv.getObject();
                    ServiceOrderItem serviceOrderItem = new ServiceOrderItemImpl();
                    List<OrderItem> orderItems = serviceOrderItem.findOrderItem(customer);
                    for (OrderItem orderItem : orderItems) {
                        Data dataSend = new Data(orderItems.size(),"ok",orderItem);
                        oos.writeObject(dataSend);
                    }
                }
                //查询该顾客一次的详细订单
                else if ("本次详细订单".equals(dataRecv.getMsg())) {
                    OrderItem orderItem = (OrderItem) dataRecv.getObject();
                    ServiceOrderItem serviceOrderItem = new ServiceOrderItemImpl();
                    List<OrderItem> orderItems = serviceOrderItem.findOrderItem(orderItem.getCustomer(),orderItem.getOrderInfoId());
                    for (OrderItem orderItem1 : orderItems) {
                        Data dataSend = new Data(orderItems.size(),"ok",orderItem1);
                        oos.writeObject(dataSend);
                    }
                }
                //添加购物订单
                else if ("购物订单".equals(dataRecv.getMsg())) {
                    OrderItem orderItem = (OrderItem)dataRecv.getObject();
                    ServiceOrderInfo serviceOrderInfo = new ServiceOrderInfoImpl();
                    ServiceOrderItem serviceOrderItem = new ServiceOrderItemImpl();

                    boolean flag = serviceOrderItem.addBuyGoodItem(orderItem.getGood(),orderItem.getOrderItemNum(),orderItem.getCustomer(),orderItem.getOrderInfoId());

                    if (flag){
                        Data dataSend = new Data("添加成功!");
                        oos.writeObject(dataSend);
                    }else{
                        Data dataSend = new Data("添加失败!");
                        oos.writeObject(dataSend);
                    }
                }

                //添加OrderInfo
                else if ("添加OrderInfo".equals(dataRecv.getMsg())) {
                    OrderInfo orderInfo = (OrderInfo)dataRecv.getObject();
                    ServiceOrderInfo serviceOrderInfo = new ServiceOrderInfoImpl();
                    OrderInfo orderInfo1 = serviceOrderInfo.addOrderInfo(orderInfo.getCustomer(),orderInfo.getEmployee(),orderInfo.getTotalNum(),orderInfo.getTotalPrice());
                    if (orderInfo1!=null){
                        Data dataSend = new Data("添加成功",orderInfo1);
                        oos.writeObject(dataSend);
                    }else{
                        Data dataSend = new Data("添加失败");
                        oos.writeObject(dataSend);
                    }
                }
                //修改总订单数量和金额
                else if ("修改总订单数量和金额".equals(dataRecv.getMsg())) {
                    OrderInfo orderInfo = (OrderInfo)dataRecv.getObject();
                    ServiceOrderInfo serviceOrderInfo = new ServiceOrderInfoImpl();
                    boolean flag = serviceOrderInfo.modifyOrderInfo(orderInfo.getOrderInfoId(),orderInfo.getTotalNum(),orderInfo.getTotalPrice());
                    if (flag){
                        Data dataSend = new Data("修改成功");
                        oos.writeObject(dataSend);
                    }else{
                        Data dataSend = new Data("修改失败");
                        oos.writeObject(dataSend);
                    }
                }




                //查询所有店铺
                else if ("查询所有店铺".equals(dataRecv.getMsg())) {
                    ServiceShop serviceShop = new ServiceShopImpl();
                    List<Shop> shops = serviceShop.findAllShop();
                    for (Shop shop : shops) {
                        Data dataSend = new Data(shops.size(),"ok",shop);
                        oos.writeObject(dataSend);
                    }
                }
                //查询店铺
                else if ("查询店铺".equals(dataRecv.getMsg())) {
                    Shop shop = (Shop) dataRecv.getObject();
                    ServiceShop serviceShop = new ServiceShopImpl();
                    shop = serviceShop.findShop(shop.getShopId());
                    if (shop!=null){
                        Data dataSend = new Data("该店铺存在",shop);
                        oos.writeObject(dataSend);
                    }else{
                        Data dataSend = new Data("该店铺不存在");
                        oos.writeObject(dataSend);
                    }
                }



                //查看所有商品信息
                else if ("商品信息".equals(dataRecv.getMsg())){
                    //查看所有商品信息
                    ServiceGood serviceGood = new ServiceGoodImpl();
                    for (Good allGood : serviceGood.allGoods()) {
                        //获取信息
                        Data dataSend = new Data(serviceGood.allGoods().size(), "ok", allGood);
                        //写数据给服务器端
                        oos.writeObject(dataSend);
                    }

                }
                //查询单个商品
                else if ("单个商品".equals(dataRecv.getMsg())){

                    Good good = (Good) dataRecv.getObject();
                    ServiceGood serviceGood = new ServiceGoodImpl();
                    good = serviceGood.findGood(good.getGoodId());
                    if (good!=null) {
                        Data dataSend = new Data("该商品存在",good);
                        //写数据给服务器端
                        oos.writeObject(dataSend);
                    }else {
                        Data dataSend = new Data("该商品不存在",good);
                        //写数据给服务器端
                        oos.writeObject(dataSend);
                    }

                }
                //添加新商品
                else if ("33".equals(dataRecv.getMsg())){
                    Good good = (Good) dataRecv.getObject();
                    ServiceGood serviceGood = new ServiceGoodImpl();
                    boolean flag = serviceGood.addGood(good);
                    if (flag){
                        Data dataSend = new Data("添加成功");
                        oos.writeObject(dataSend);
                    }else{
                        Data dataSend = new Data("添加失败");
                        oos.writeObject(dataSend);
                    }
                }
                //修改商品Name
                else if ("修改商品名称".equals(dataRecv.getMsg())){
                    Good good = (Good) dataRecv.getObject();
                    ServiceGood serviceGood = new ServiceGoodImpl();
                    boolean flag = serviceGood.modifyGoodNameById(good.getGoodId(),good.getGoodName());
                    if (flag){
                        Data dataSend = new Data("修改成功");
                        oos.writeObject(dataSend);
                    }else{
                        Data dataSend = new Data("修改失败");
                        oos.writeObject(dataSend);
                    }
                }
                //修改商品Price
                else if ("修改商品价格".equals(dataRecv.getMsg())){
                    Good good = (Good) dataRecv.getObject();
                    ServiceGood serviceGood = new ServiceGoodImpl();
                    boolean flag = serviceGood.modifyGoodPriceById(good.getGoodId(),good.getGoodPrice());
                    if (flag){
                        Data dataSend = new Data("修改成功");
                        oos.writeObject(dataSend);
                    }else{
                        Data dataSend = new Data("修改失败");
                        oos.writeObject(dataSend);
                    }
                }
                //修改商品Count
                else if ("修改商品数量".equals(dataRecv.getMsg())){
                    Good good = (Good) dataRecv.getObject();
                    ServiceGood serviceGood = new ServiceGoodImpl();
                    boolean flag = serviceGood.modifyGoodCountById(good.getGoodId(),good.getGoodCount());
                    if (flag){
                        Data dataSend = new Data("修改成功");
                        oos.writeObject(dataSend);
                    }else{
                        Data dataSend = new Data("修改失败");
                        oos.writeObject(dataSend);
                    }
                }


                //test
                else if ("test".equals(dataRecv.getMsg())){

                    Data dataSend = new Data("ok123");
                    oos.writeObject(dataSend);

                }


                //退出(关闭流)
                else if ("退出".equals(dataRecv.getMsg())){

                    Data dataSend = new Data("Over");
                    oos.writeObject(dataSend);

                    oos.close();
                    os.close();
                    ois.close();
                    is.close();

                    break;

                }


            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }
}
