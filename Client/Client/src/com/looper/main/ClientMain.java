package com.looper.main;

import com.looper.domain.*;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ClientMain {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        Socket socket = new Socket("127.0.0.1", 5900);
        Scanner scanner = new Scanner(System.in);
        ViewInfo view = new ViewInfo();

        loop:
        while (true) {
            System.out.println("******************************欢迎光临*******************************");
            System.out.println();
            System.out.println("                            1. 顾客登录");
            System.out.println("                            2. 顾客注册");
            System.out.println("                            3. 员工登录");
            System.out.println("                            0. 退出");
            System.out.println("-----------------------------------------------------------------");
            System.out.print("请输入选择:");
            String sel = scanner.next();
            System.out.println();

            Data dataSend = null;
            Data dataRecv = null;
            Object object = null;
            OutputStream os = null;
            ObjectOutputStream oos = null;
            InputStream is = null;
            ObjectInputStream ois = null;

            //顾客登录
            if ("1".equals(sel)) {
                System.out.println("*****************************顾客登录****************************");
                System.out.print("请输入用户名:");
                String name = scanner.next();
                System.out.print("请输入密码:");
                String password = scanner.next();
                System.out.println();

                Customer customer = new Customer(name, password);

                dataSend = new Data("顾客登录", customer);
                os = socket.getOutputStream();
                oos = new ObjectOutputStream(os);
                oos.writeObject(dataSend);

                is = socket.getInputStream();
                ois = new ObjectInputStream(is);
                object = ois.readObject();
                dataRecv = (Data) object;

                if ("该用户存在".equals(dataRecv.getMsg())) {

                    customer = (Customer) dataRecv.getObject();

                    while (true) {
                        System.out.println("**************************欢迎\"" + customer.getCustomerName() + "\"登录***************************");
                        System.out.println("                            1. 购物");
                        System.out.println("                            2. 查看个人信息");
                        System.out.println("                            3. 修改个人信息");
                        System.out.println("                            4. 查看个人消费记录");
                        System.out.println("                            5. 查看个人详细消费记录");
                        System.out.println("                            0. 退出");
                        System.out.println("-----------------------------------------------------------------");
                        System.out.print("请输入选择:");

                        String sel1 = scanner.next();

                        if ("1".equals(sel1)) {
                            System.out.println();
                            System.out.println("**************************欢迎\"" + customer.getCustomerName() + "\"光临购物店***************************");

                            double allPrice = 0;
                            int allCount = 0;
                            int times = 0;
                            Employee employee = null;
                            OrderInfo orderInfo = null;
                            Good buyGood = null;
                            List<Employee> employees = new ArrayList<>();

                            while (true) {

                                dataSend = new Data("商品信息");
                                os = socket.getOutputStream();
                                oos = new ObjectOutputStream(os);
                                oos.writeObject(dataSend);

                                is = socket.getInputStream();
                                ois = new ObjectInputStream(is);
                                object = ois.readObject();
                                dataRecv = (Data) object;

                                //输出商品信息
                                System.out.println("======================================商品信息=====================================");
                                System.out.println("\t\t商品ID"+"\t\t商品名称"+"\t\t商品单价"+"\t\t商品数量"+"\t\t商品规格");
                                Good good2 = (Good)dataRecv.getObject();
                                System.out.println(good2);
                                for (int i = 0; i < dataRecv.getNum() - 1; i++) {
                                    object = ois.readObject();
                                    dataRecv = (Data) object;
                                    //输出商品
                                    Good good = (Good) dataRecv.getObject();
                                    System.out.println(good);
                                }

                                System.out.println("================================================================================");
                                System.out.print("请输入需要的商品编号:");
                                int goodId = scanner.nextInt();

                                dataSend = new Data("单个商品", new Good(goodId));
                                os = socket.getOutputStream();
                                oos = new ObjectOutputStream(os);
                                oos.writeObject(dataSend);

                                is = socket.getInputStream();
                                ois = new ObjectInputStream(is);
                                dataRecv = (Data) ois.readObject();
                                buyGood = (Good) dataRecv.getObject();

                                OrderItem orderItem = null;
                                if ("该商品存在".equals(dataRecv.getMsg())) {
                                    System.out.print("请输入需要的商品数量:");
                                    int goodCount = scanner.nextInt();

                                    if (buyGood.getGoodPrice() * goodCount <= customer.getStoreMoney()) {

                                        if (goodCount <= buyGood.getGoodCount()) {

                                            if (times == 0) {
                                                //获取售货员信息
                                                dataSend = new Data("查询所有收银员", new Employee("common"));
                                                os = socket.getOutputStream();
                                                oos = new ObjectOutputStream(os);
                                                oos.writeObject(dataSend);

                                                is = socket.getInputStream();
                                                ois = new ObjectInputStream(is);
                                                dataRecv = (Data) ois.readObject();
                                                Employee employee2 = (Employee)dataRecv.getObject();
                                                System.out.println();
                                                System.out.println("====================收银员信息====================");
                                                System.out.println("收银员ID"+"\t\t收银员姓名"+"\t\t收银员所在店铺");
                                                System.out.println(employee2.getEmployeeId()+"\t\t"+employee2.getEmployeeName()+"\t\t\t\t"+employee2.getShop().getShopName());
                                                //System.out.println(employee2);
                                                employees.add(employee2);
                                                for (int i = 0; i < dataRecv.getNum() - 1; i++) {
                                                    dataRecv = (Data) ois.readObject();
                                                    Employee employee1 = (Employee) dataRecv.getObject();
                                                    employees.add(employee1);
                                                    System.out.println(employee1.getEmployeeId()+"\t\t"+employee1.getEmployeeName()+"\t\t\t"+employee1.getShop().getShopName());
                                                }
                                                System.out.println("=================================================");

                                                while (true) {
                                                    //选择收银员
                                                    System.out.print("请选择收银员\n" +
                                                            "输入收银员ID:");
                                                    int empId = scanner.nextInt();

                                                    for (Employee employee1 : employees) {
                                                        if (empId == employee1.getEmployeeId()) {
                                                            //添加总订单
                                                            dataSend = new Data("添加OrderInfo", new OrderInfo(customer, employee1, allCount, allPrice));
                                                            os = socket.getOutputStream();
                                                            oos = new ObjectOutputStream(os);
                                                            oos.writeObject(dataSend);

                                                            is = socket.getInputStream();
                                                            ois = new ObjectInputStream(is);
                                                            dataRecv = (Data) ois.readObject();

                                                            employee = employee1;

                                                            if ("添加成功".equals(dataRecv.getMsg())) {
                                                                orderInfo = (OrderInfo) dataRecv.getObject();
                                                            } else {
                                                                System.out.println(dataRecv.getMsg());
                                                            }
                                                            times += 1;
                                                        }
                                                    }
                                                    if (times == 0) {
                                                        view.chooseErr();
                                                    } else {
                                                        break;
                                                    }
                                                }
                                            }

                                            allPrice += buyGood.getGoodPrice() * goodCount;
                                            allCount += goodCount;

                                            //修改商品数量
                                            dataSend = new Data("修改商品数量", new Good(buyGood.getGoodId(), (buyGood.getGoodCount() - goodCount)));
                                            System.out.println((buyGood.getGoodCount() - goodCount));
                                            os = socket.getOutputStream();
                                            oos = new ObjectOutputStream(os);
                                            oos.writeObject(dataSend);
                                            is = socket.getInputStream();
                                            ois = new ObjectInputStream(is);
                                            dataRecv = (Data) ois.readObject();

                                            //添加分订单
                                            orderItem = new OrderItem(buyGood, goodCount, buyGood.getGoodPrice() * goodCount, orderInfo, customer);
                                            dataSend = new Data("购物订单", orderItem);
                                            os = socket.getOutputStream();
                                            oos = new ObjectOutputStream(os);
                                            oos.writeObject(dataSend);

                                            is = socket.getInputStream();
                                            ois = new ObjectInputStream(is);
                                            dataRecv = (Data) ois.readObject();

                                            System.out.println("\t商品名称:" + buyGood.getGoodName() + "\t商品数量:" + goodCount + "\t小计:" + buyGood.getGoodPrice() * goodCount + "¥;\t已加入购物车");

                                        } else {
                                            System.out.println("商品数量不足！");
                                        }
                                    } else {
                                        System.out.println("您的余额已不足！");
                                    }
                                } else {
                                    System.out.println("该商品不存在,您输入有误！");
                                }

                                System.out.print("是否继续购买?(y/n):");
                                String str = scanner.next();
                                if ("y".equals(str)) {
                                    System.out.println("-----------------------------------------------------------------");
                                } else {
                                    break;
                                }
                            }
                            if (times == 1) {
                                System.out.println();
                                System.out.println("**************************\"" + customer.getCustomerName() + "\"本次消费记录***************************");
                                System.out.println();
                                System.out.println("收银员ID:" + employee.getEmployeeId() + "          收银员姓名:" + employee.getEmployeeName());
                                System.out.println();
                                dataSend = new Data("本次详细订单", new OrderItem(orderInfo, customer));
                                os = socket.getOutputStream();
                                oos = new ObjectOutputStream(os);
                                oos.writeObject(dataSend);

                                is = socket.getInputStream();
                                ois = new ObjectInputStream(is);
                                dataRecv = (Data) ois.readObject();
                                System.out.println(dataRecv.getObject());
                                for (int i = 0; i < dataRecv.getNum() - 1; i++) {
                                    dataRecv = (Data) ois.readObject();
                                    OrderItem orderItem = (OrderItem) dataRecv.getObject();
                                    System.out.println(orderItem);
                                }
                                if ("会员用户".equals(customer.getCustomerType())) {
                                    System.out.println();
                                    System.out.println("您是本店会员,消费一律八折!");
                                    System.out.println("总消费金额:" + allPrice);
                                    allPrice = allPrice * 0.8;
                                    System.out.println("实际应支付金额:" + allPrice * 0.8);
                                    System.out.println("总消购买数量:" + allCount);
                                    System.out.println();
                                    System.out.println("-----------------------------------------------------------------");
                                    System.out.println();
                                    dataSend = new Data("修改用户余额", new Customer(customer.getCustomerId(), customer.getStoreMoney() - (allPrice * 0.8)));
                                    //修改用户余额
                                    os = socket.getOutputStream();
                                    oos = new ObjectOutputStream(os);
                                    oos.writeObject(dataSend);
                                    is = socket.getInputStream();
                                    ois = new ObjectInputStream(is);
                                    dataRecv = (Data) ois.readObject();

                                    //修改总订单数量和金额
                                    dataSend = new Data("修改总订单数量和金额", new OrderInfo(orderInfo.getOrderInfoId(), allCount, allPrice));
                                    os = socket.getOutputStream();
                                    oos = new ObjectOutputStream(os);
                                    oos.writeObject(dataSend);

                                    is = socket.getInputStream();
                                    ois = new ObjectInputStream(is);
                                    dataRecv = (Data) ois.readObject();
                                    System.out.println();
                                } else {
                                    System.out.println();
                                    System.out.println("总消费金额:" + allPrice);
                                    System.out.println("实际应支付金额:" + allPrice);
                                    System.out.println("总消购买数量:" + allCount);
                                    System.out.println();
                                    System.out.println("-----------------------------------------------------------------");
                                    System.out.println();
                                    dataSend = new Data("修改用户余额", new Customer(customer.getCustomerId(), customer.getStoreMoney() - allPrice));

                                    //修改用户余额
                                    os = socket.getOutputStream();
                                    oos = new ObjectOutputStream(os);
                                    oos.writeObject(dataSend);
                                    is = socket.getInputStream();
                                    ois = new ObjectInputStream(is);
                                    dataRecv = (Data) ois.readObject();

                                    //修改总订单数量和金额
                                    dataSend = new Data("修改总订单数量和金额", new OrderInfo(orderInfo.getOrderInfoId(), allCount, allPrice));
                                    os = socket.getOutputStream();
                                    oos = new ObjectOutputStream(os);
                                    oos.writeObject(dataSend);

                                    is = socket.getInputStream();
                                    ois = new ObjectInputStream(is);
                                    dataRecv = (Data) ois.readObject();
                                    System.out.println();
                                }
                            }
                        }

                        else if ("2".equals(sel1)) {
                            dataSend = new Data("查询顾客", new Customer(customer.getCustomerId()));
                            os = socket.getOutputStream();
                            oos = new ObjectOutputStream(os);
                            oos.writeObject(dataSend);

                            is = socket.getInputStream();
                            ois = new ObjectInputStream(is);
                            dataRecv = (Data) ois.readObject();
                            customer = (Customer) dataRecv.getObject();
                            System.out.println(customer);
                        }

                        else if ("3".equals(sel1)) {
                            System.out.print("请输入新名称:");
                            String newName = scanner.next();
                            System.out.print("请输入新密码:");
                            String newPassword = scanner.next();
                            System.out.print("请输入新年龄:");
                            int newAge = scanner.nextInt();
                            System.out.print("请输入新性别:");
                            String newSex = scanner.next();
                            dataSend = new Data("修改顾客全部信息", new Customer(customer.getCustomerId(), newName, newAge, newSex, newPassword));
                            os = socket.getOutputStream();
                            oos = new ObjectOutputStream(os);
                            oos.writeObject(dataSend);

                            is = socket.getInputStream();
                            ois = new ObjectInputStream(is);
                            dataRecv = (Data) ois.readObject();
                            System.out.println(dataRecv.getMsg());
                            System.out.println();
                        }

                        else if ("4".equals(sel1)) {
                            dataSend = new Data("查询该顾客所有订单", customer);
                            os = socket.getOutputStream();
                            oos = new ObjectOutputStream(os);
                            oos.writeObject(dataSend);

                            is = socket.getInputStream();
                            ois = new ObjectInputStream(is);
                            dataRecv = (Data) ois.readObject();
                            System.out.println(dataRecv.getObject());

                            for (int i = 0; i < dataRecv.getNum() - 1; i++) {
                                dataRecv = (Data) ois.readObject();
                                OrderInfo orderInfo = (OrderInfo) dataRecv.getObject();
                                System.out.println(orderInfo);
                            }
                            System.out.println();
                        }

                        else if ("5".equals(sel1)) {
                            dataSend = new Data("查询该顾客所有详细订单", customer);
                            os = socket.getOutputStream();
                            oos = new ObjectOutputStream(os);
                            oos.writeObject(dataSend);
                            is = socket.getInputStream();
                            ois = new ObjectInputStream(is);
                            dataRecv = (Data) ois.readObject();
                            System.out.println(dataRecv.getMsg());
                            System.out.println(dataRecv.getObject());

                            for (int i = 0; i < dataRecv.getNum() - 1; i++) {
                                dataRecv = (Data) ois.readObject();
                                OrderItem orderItem = (OrderItem) dataRecv.getObject();
                                System.out.println(orderItem);
                            }
                            System.out.println();
                        }

                        else if ("0".equals(sel1)) {
                            view.signOut();
                            dataSend = new Data("退出");
                            os = socket.getOutputStream();
                            oos = new ObjectOutputStream(os);
                            oos.writeObject(dataSend);

                            is = socket.getInputStream();
                            ois = new ObjectInputStream(is);
                            dataRecv = (Data) ois.readObject();
                            System.out.println(dataRecv.getMsg());

                            oos.close();
                            os.close();
                            ois.close();
                            is.close();
                            break loop;
                        }

                    }
                }

                else if ("用户名或密码错误!".equals(dataRecv.getMsg())){
                    System.out.println(dataRecv.getMsg());
                    System.out.println();
                }

                else if ("该用户不存在!".equals(dataRecv.getMsg())){
                    System.out.println(dataRecv.getMsg());
                    System.out.println();
                }
            }

            //顾客注册
            else if ("2".equals(sel)) {

                System.out.println("*****************************注册用户****************************");
                System.out.print("请输入用户名:");
                String name = scanner.next();

                dataSend = new Data("按用户名查询用户", new Customer(name));
                os = socket.getOutputStream();
                oos = new ObjectOutputStream(os);
                oos.writeObject(dataSend);

                is = socket.getInputStream();
                ois = new ObjectInputStream(is);
                object = ois.readObject();
                dataRecv = (Data) object;
                if ("该用户存在".equals(dataRecv.getMsg())){
                    System.out.println("该用户名已存在!");
                }else{
                    System.out.print("请输入密码:");
                    String password = scanner.next();
                    System.out.print("请输入年龄:");
                    int age = scanner.nextInt();
                    System.out.print("请输入性别:");
                    String sex = scanner.next();
                    System.out.println();
                    System.out.println("会员一次费用20¥,开通会员之后,每次消费八折!(注意：会员费将从存入金额中扣除)");
                    System.out.println();
                    System.out.print("请选择是否开通会员(y/n):");
                    String type = scanner.next();
                    Customer customer = null;
                    if ("y".equals(type)) {
                        System.out.print("请输入存入金额数:");
                        double storeMoney = scanner.nextDouble();
                        customer = new Customer(name, age, sex, "会员用户", storeMoney - 20, password);
                    } else {
                        System.out.print("请输入存入金额数:");
                        double storeMoney = scanner.nextDouble();
                        customer = new Customer(name, age, sex, "普通用户", storeMoney, password);
                    }

                    System.out.println();

                    object = customer;
                    dataSend = new Data("顾客注册", object);
                    os = socket.getOutputStream();
                    oos = new ObjectOutputStream(os);
                    oos.writeObject(dataSend);

                    is = socket.getInputStream();
                    ois = new ObjectInputStream(is);
                    object = ois.readObject();
                    dataRecv = (Data) object;
                    if ("注册成功".equals(dataRecv.getMsg())){
                        System.out.println(dataRecv.getMsg());
                        System.out.println();
                    }else{
                        System.out.println(dataRecv.getMsg());
                        System.out.println();
                    }
                }
            }

            //员工登陆
            else if ("3".equals(sel)) {
                System.out.println("*****************************员工登录****************************");
                System.out.print("请输入用户名:");
                String name = scanner.next();
                System.out.print("请输入密码:");
                String password = scanner.next();
                System.out.println();

                Employee employee = new Employee(name, password);

                dataSend = new Data(sel, employee);
                os = socket.getOutputStream();
                oos = new ObjectOutputStream(os);
                oos.writeObject(dataSend);

                is = socket.getInputStream();
                ois = new ObjectInputStream(is);
                dataRecv = (Data) ois.readObject();

                if ("该员工存在".equals(dataRecv.getMsg())) {
                    employee = (Employee) dataRecv.getObject();

                    while (true) {

                        //管理员
                        if ("admin".equals(employee.getEmployeeRole())) {
                            System.out.println("**************************欢迎\"" + employee.getEmployeeName() + "\"管理员***************************");
                            System.out.println("                            1. 查看信息");
                            System.out.println("                            2. 修改信息");
                            System.out.println("                            3. 添加新商品");
                            System.out.println("                            4. 添加新员工");
                            System.out.println("                            0. 退出");
                            System.out.println("-----------------------------------------------------------------");
                            System.out.print("请输入选择:");
                            String sel1 = scanner.next();
                            System.out.println();

                            if ("1".equals(sel1)) {
                                while (true) {
                                    System.out.println("**************************欢迎\"" + employee.getEmployeeName() + "\"管理员查看信息***************************");
                                    System.out.println("                            1. 查看所有商品信息");
                                    System.out.println("                            2. 查看所有员工信息");
                                    System.out.println("                            3. 查看所有顾客信息");
                                    System.out.println("                            4. 通过ID查找商品信息");
                                    System.out.println("                            5. 通过ID查找员工信息");
                                    System.out.println("                            6. 通过ID查找顾客信息");
                                    System.out.println("                            7. 查看所有顾客总消费情况和所有员工总销售情况");
                                    System.out.println("                            8. 通过顾客ID查看顾客总消费情况");
                                    System.out.println("                            9. 通过员工ID查看员工总销售情况");
                                    System.out.println("                            0. 返回上一级");
                                    System.out.println("-----------------------------------------------------------------");
                                    System.out.print("请输入选择:");
                                    String sel2 = scanner.next();
                                    System.out.println();

                                    if ("1".equals(sel2)) {
                                        dataSend = new Data("商品信息");
                                        os = socket.getOutputStream();
                                        oos = new ObjectOutputStream(os);
                                        oos.writeObject(dataSend);

                                        is = socket.getInputStream();
                                        ois = new ObjectInputStream(is);
                                        dataRecv = (Data) ois.readObject();
                                        System.out.println("======================================商品信息=====================================");
                                        System.out.println("\t\t商品ID"+"\t\t商品名称"+"\t\t商品单价"+"\t\t商品数量"+"\t\t商品规格");
                                        System.out.println(dataRecv.getObject());
                                        for (int i = 0; i < dataRecv.getNum() - 1; i++) {
                                            dataRecv = (Data) ois.readObject();
                                            ;
                                            //输出商品
                                            Good good = (Good) dataRecv.getObject();
                                            System.out.println(good);
                                        }
                                        System.out.println();
                                    }
                                    else if ("2".equals(sel2)) {

                                        dataSend = new Data(sel + sel1 + sel2);
                                        os = socket.getOutputStream();
                                        oos = new ObjectOutputStream(os);
                                        oos.writeObject(dataSend);

                                        is = socket.getInputStream();
                                        ois = new ObjectInputStream(is);
                                        dataRecv = (Data) ois.readObject();
                                        //System.out.println("\t\t商品编号\t\t"+"商品名称\t\t"+"商品价格\t\t"+"商品数量\t\t"+"商品规格\t\t");
                                        System.out.println(dataRecv.getObject());

                                        for (int i = 0; i < dataRecv.getNum() - 1; i++) {
                                            dataRecv = (Data) ois.readObject();
                                            ;
                                            //输出商品
                                            Employee employee1 = (Employee) dataRecv.getObject();
                                            System.out.println(employee1);
                                        }
                                        System.out.println();
                                    }
                                    else if ("3".equals(sel2)) {

                                        dataSend = new Data(sel + sel1 + sel2);
                                        os = socket.getOutputStream();
                                        oos = new ObjectOutputStream(os);
                                        oos.writeObject(dataSend);

                                        is = socket.getInputStream();
                                        ois = new ObjectInputStream(is);
                                        dataRecv = (Data) ois.readObject();
                                        System.out.println(dataRecv.getObject());

                                        for (int i = 0; i < dataRecv.getNum() - 1; i++) {
                                            dataRecv = (Data) ois.readObject();
                                            ;
                                            Customer customer = (Customer) dataRecv.getObject();
                                            System.out.println(customer);
                                        }
                                        System.out.println();
                                    }
                                    else if ("4".equals(sel2)) {
                                        System.out.print("请输入商品编号:");
                                        int goodId = scanner.nextInt();
                                        dataSend = new Data("单个商品", new Good(goodId));
                                        os = socket.getOutputStream();
                                        oos = new ObjectOutputStream(os);
                                        oos.writeObject(dataSend);

                                        is = socket.getInputStream();
                                        ois = new ObjectInputStream(is);
                                        dataRecv = (Data) ois.readObject();
                                        Good good = (Good) dataRecv.getObject();
                                        if (good != null) {
                                            System.out.println(good);
                                        } else {
                                            System.out.println("该商品不存在!");
                                        }
                                        System.out.println();
                                    }
                                    else if ("5".equals(sel2)) {
                                        System.out.print("请输入员工ID:");
                                        int employeeId = scanner.nextInt();
                                        dataSend = new Data("查询员工", new Employee(employeeId));
                                        os = socket.getOutputStream();
                                        oos = new ObjectOutputStream(os);
                                        oos.writeObject(dataSend);

                                        is = socket.getInputStream();
                                        ois = new ObjectInputStream(is);
                                        dataRecv = (Data) ois.readObject();
                                        Employee employee1 = (Employee) dataRecv.getObject();
                                        if (employee1 != null) {
                                            System.out.println(employee1);
                                        } else {
                                            System.out.println("该员工不存在!");
                                        }
                                        System.out.println();
                                    }
                                    else if ("6".equals(sel2)) {
                                        System.out.print("请输入顾客ID:");
                                        int customerId = scanner.nextInt();
                                        dataSend = new Data("查询顾客", new Customer(customerId));
                                        os = socket.getOutputStream();
                                        oos = new ObjectOutputStream(os);
                                        oos.writeObject(dataSend);

                                        is = socket.getInputStream();
                                        ois = new ObjectInputStream(is);
                                        dataRecv = (Data) ois.readObject();
                                        Customer customer = (Customer) dataRecv.getObject();
                                        if (customer != null) {
                                            System.out.println(customer);
                                        } else {
                                            System.out.println("该顾客不存在!");
                                        }
                                        System.out.println();
                                    }
                                    else if ("7".equals(sel2)) {
                                        dataSend = new Data("查询所有总订单");
                                        os = socket.getOutputStream();
                                        oos = new ObjectOutputStream(os);
                                        oos.writeObject(dataSend);

                                        is = socket.getInputStream();
                                        ois = new ObjectInputStream(is);
                                        dataRecv = (Data) ois.readObject();
                                        System.out.println(dataRecv.getObject());

                                        for (int i = 0; i < dataRecv.getNum() - 1; i++) {
                                            dataRecv = (Data) ois.readObject();
                                            OrderInfo orderInfo = (OrderInfo) dataRecv.getObject();
                                            System.out.println(orderInfo);
                                        }
                                        System.out.println();
                                    }
                                    else if ("8".equals(sel2)) {
                                        System.out.print("请输入顾客ID:");
                                        int customerId = scanner.nextInt();
                                        dataSend = new Data("查询顾客", new Customer(customerId));
                                        os = socket.getOutputStream();
                                        oos = new ObjectOutputStream(os);
                                        oos.writeObject(dataSend);

                                        is = socket.getInputStream();
                                        ois = new ObjectInputStream(is);
                                        dataRecv = (Data) ois.readObject();
                                        Customer customer = (Customer) dataRecv.getObject();
                                        if (customer != null) {
                                            dataSend = new Data("查询该顾客所有订单", customer);
                                            os = socket.getOutputStream();
                                            oos = new ObjectOutputStream(os);
                                            oos.writeObject(dataSend);

                                            is = socket.getInputStream();
                                            ois = new ObjectInputStream(is);
                                            dataRecv = (Data) ois.readObject();
                                            System.out.println(dataRecv.getObject());

                                            for (int i = 0; i < dataRecv.getNum() - 1; i++) {
                                                dataRecv = (Data) ois.readObject();
                                                OrderInfo orderInfo = (OrderInfo) dataRecv.getObject();
                                                System.out.println(orderInfo);
                                            }
                                        } else {
                                            System.out.println("该顾客不存在!");
                                        }
                                        System.out.println();
                                    }
                                    else if ("9".equals(sel2)) {
                                        System.out.print("请输入员工ID:");
                                        int employeeId = scanner.nextInt();
                                        dataSend = new Data("查询员工", new Employee(employeeId));
                                        os = socket.getOutputStream();
                                        oos = new ObjectOutputStream(os);
                                        oos.writeObject(dataSend);

                                        is = socket.getInputStream();
                                        ois = new ObjectInputStream(is);
                                        dataRecv = (Data) ois.readObject();
                                        Employee employee1 = (Employee) dataRecv.getObject();
                                        if (employee1 != null) {
                                            System.out.println(employee1);
                                            dataSend = new Data("查询该员工所有订单", employee1);
                                            os = socket.getOutputStream();
                                            oos = new ObjectOutputStream(os);
                                            oos.writeObject(dataSend);

                                            is = socket.getInputStream();
                                            ois = new ObjectInputStream(is);
                                            dataRecv = (Data) ois.readObject();
                                            System.out.println(dataRecv.getObject());

                                            for (int i = 0; i < dataRecv.getNum() - 1; i++) {
                                                dataRecv = (Data) ois.readObject();
                                                OrderInfo orderInfo = (OrderInfo) dataRecv.getObject();
                                                System.out.println(orderInfo);
                                            }
                                        } else {
                                            System.out.println("该员工不存在!");
                                        }
                                        System.out.println();
                                    }
                                    else if ("0".equals(sel2)) {
                                        System.out.println("返回成功");
                                        System.out.println();
                                        break;
                                    } else {
                                        System.out.println("输入有误,当前没有该选项,请重新选择！");
                                        System.out.println();
                                    }
                                }
                            } else if ("2".equals(sel1)) {
                                while (true) {
                                    System.out.println("**************************欢迎\"" + employee.getEmployeeName() + "\"管理员修改信息***************************");
                                    System.out.println("                            1. 通过ID修改商品信息");
                                    System.out.println("                            2. 通过ID修改员工角色");
                                    System.out.println("                            3. 通过ID修改顾客角色");
                                    System.out.println("                            0. 返回上一级");
                                    System.out.println("-----------------------------------------------------------------");
                                    System.out.print("请输入选择:");
                                    String sel2 = scanner.next();
                                    System.out.println();

                                    if ("1".equals(sel2)) {
                                        System.out.print("请输入商品ID:");
                                        int goodId = scanner.nextInt();
                                        dataSend = new Data("单个商品", new Good(goodId));
                                        os = socket.getOutputStream();
                                        oos = new ObjectOutputStream(os);
                                        oos.writeObject(dataSend);

                                        is = socket.getInputStream();
                                        ois = new ObjectInputStream(is);
                                        dataRecv = (Data) ois.readObject();
                                        Good good = (Good) dataRecv.getObject();
                                        if (good != null) {
                                            while (true) {
                                                System.out.println("**************************欢迎\"" + employee.getEmployeeName() + "\"管理员修改信息***************************");
                                                System.out.println("                            1. 修改商品名称");
                                                System.out.println("                            2. 修改商品价格");
                                                System.out.println("                            3. 修改商品数量");
                                                System.out.println("                            0. 返回上一级");
                                                System.out.println("-----------------------------------------------------------------");
                                                System.out.print("请输入选择:");
                                                String sel3 = scanner.next();
                                                System.out.println();

                                                if ("1".equals(sel3)) {
                                                    System.out.print("请输入新名称:");
                                                    String newName = scanner.next();
                                                    dataSend = new Data("修改商品名称", new Good(goodId, newName));
                                                    os = socket.getOutputStream();
                                                    oos = new ObjectOutputStream(os);
                                                    oos.writeObject(dataSend);
                                                    is = socket.getInputStream();
                                                    ois = new ObjectInputStream(is);
                                                    dataRecv = (Data) ois.readObject();
                                                    System.out.println(dataRecv.getMsg());
                                                    System.out.println();
                                                } else if ("2".equals(sel3)) {
                                                    System.out.print("请输入新名称:");
                                                    double newPrice = scanner.nextDouble();
                                                    dataSend = new Data("修改商品价格", new Good(goodId, newPrice));
                                                    os = socket.getOutputStream();
                                                    oos = new ObjectOutputStream(os);
                                                    oos.writeObject(dataSend);
                                                    is = socket.getInputStream();
                                                    ois = new ObjectInputStream(is);
                                                    dataRecv = (Data) ois.readObject();
                                                    System.out.println(dataRecv.getMsg());
                                                    System.out.println();
                                                } else if ("3".equals(sel3)) {
                                                    System.out.print("请输入新数量:");
                                                    int newCount = scanner.nextInt();
                                                    dataSend = new Data("修改商品数量", new Good(goodId, newCount));
                                                    os = socket.getOutputStream();
                                                    oos = new ObjectOutputStream(os);
                                                    oos.writeObject(dataSend);
                                                    is = socket.getInputStream();
                                                    ois = new ObjectInputStream(is);
                                                    dataRecv = (Data) ois.readObject();
                                                    System.out.println(dataRecv.getMsg());
                                                    System.out.println();
                                                } else if ("0".equals(sel3)) {
                                                    System.out.println("成功返回上一级！");
                                                    System.out.println();
                                                    break;
                                                } else {
                                                    System.out.println("输入有误,当前没有该选项,请重新选择！");
                                                    System.out.println();
                                                }
                                            }
                                        } else {
                                            System.out.println("该商品不存在!");
                                            System.out.println();
                                        }
                                    }
                                    else if ("2".equals(sel2)) {
                                        System.out.print("请输入员工ID:");
                                        int employeeId = scanner.nextInt();
                                        dataSend = new Data("查询员工", new Employee(employeeId));
                                        os = socket.getOutputStream();
                                        oos = new ObjectOutputStream(os);
                                        oos.writeObject(dataSend);

                                        is = socket.getInputStream();
                                        ois = new ObjectInputStream(is);
                                        dataRecv = (Data) ois.readObject();
                                        Employee employee1 = (Employee) dataRecv.getObject();
                                        if (employee1 != null) {
                                            System.out.print("请选择员工新角色 " +
                                                    "1.管理员 (薪资8000)" +
                                                    "2.收银员 (薪资6000)" +
                                                    "输入1或者2:");
                                            int num = scanner.nextInt();
                                            if (num == 1 || num == 2) {
                                                dataSend = new Data(num, sel + sel1 + sel2, employee1);
                                                os = socket.getOutputStream();
                                                oos = new ObjectOutputStream(os);
                                                oos.writeObject(dataSend);
                                                is = socket.getInputStream();
                                                ois = new ObjectInputStream(is);
                                                dataRecv = (Data) ois.readObject();
                                                System.out.println(dataRecv.getMsg());
                                            } else {
                                                System.out.println("选择有误!");
                                            }
                                        } else {
                                            System.out.println("该员工不存在!");
                                        }
                                        System.out.println();
                                    }
                                    else if ("3".equals(sel2)) {
                                        System.out.print("请输入顾客ID:");
                                        int customerId = scanner.nextInt();
                                        dataSend = new Data("查询顾客", new Customer(customerId));
                                        os = socket.getOutputStream();
                                        oos = new ObjectOutputStream(os);
                                        oos.writeObject(dataSend);

                                        is = socket.getInputStream();
                                        ois = new ObjectInputStream(is);
                                        dataRecv = (Data) ois.readObject();
                                        Customer customer = (Customer) dataRecv.getObject();
                                        if (customer != null) {
                                            System.out.print("请选择顾客新角色 " +
                                                    "1.会员 2.非会员(输入1或者2):");
                                            int num = scanner.nextInt();
                                            if (num == 1 || num == 2) {
                                                dataSend = new Data(num, sel + sel1 + sel2, customer);
                                                os = socket.getOutputStream();
                                                oos = new ObjectOutputStream(os);
                                                oos.writeObject(dataSend);
                                                is = socket.getInputStream();
                                                ois = new ObjectInputStream(is);
                                                dataRecv = (Data) ois.readObject();
                                                System.out.println(dataRecv.getMsg());
                                                //System.out.println(dataRecv.getObject());
                                            } else {
                                                System.out.println("选择有误!");
                                            }
                                        } else {
                                            System.out.println("该顾客不存在!");
                                        }
                                        System.out.println();
                                    }
                                    else if ("0".equals(sel2)) {
                                        System.out.println("成功返回上一级！");
                                        System.out.println();
                                        break;
                                    } else {
                                        System.out.println("输入有误,当前没有该选项,请重新选择！");
                                        System.out.println();
                                    }

                                }
                            } else if ("3".equals(sel1)) {
                                System.out.print("请输入商品名称:");
                                String goodName = scanner.next();
                                System.out.print("请输入商品单价:");
                                double goodPrice = scanner.nextDouble();
                                System.out.print("请输入商品数量:");
                                int goodNum = scanner.nextInt();
                                System.out.print("请输入商品规格:");
                                String goodUnit = scanner.next();

                                dataSend = new Data(sel + sel1, new Good(goodName, goodPrice, goodNum, goodUnit));
                                os = socket.getOutputStream();
                                oos = new ObjectOutputStream(os);
                                oos.writeObject(dataSend);

                                is = socket.getInputStream();
                                ois = new ObjectInputStream(is);
                                dataRecv = (Data) ois.readObject();
                                System.out.println(dataRecv.getMsg());
                                System.out.println();
                            } else if ("4".equals(sel1)) {
                                System.out.print("请输入员工姓名:");
                                String empName = scanner.next();

                                System.out.println("**************************所有分店信息***************************");
                                dataSend = new Data("查询所有店铺");
                                os = socket.getOutputStream();
                                oos = new ObjectOutputStream(os);
                                oos.writeObject(dataSend);

                                is = socket.getInputStream();
                                ois = new ObjectInputStream(is);
                                dataRecv = (Data) ois.readObject();
                                System.out.println(dataRecv.getObject());
                                for (int i = 0; i < dataRecv.getNum() - 1; i++) {
                                    dataRecv = (Data) ois.readObject();
                                    Shop shop = (Shop) dataRecv.getObject();
                                    System.out.println(shop);
                                }
                                System.out.print("请选择员工上班的分店ID:");
                                int shopId = scanner.nextInt();

                                dataSend = new Data("查询店铺", new Shop(shopId));
                                os = socket.getOutputStream();
                                oos = new ObjectOutputStream(os);
                                oos.writeObject(dataSend);

                                is = socket.getInputStream();
                                ois = new ObjectInputStream(is);
                                dataRecv = (Data) ois.readObject();
                                Shop shop = (Shop) dataRecv.getObject();

                                if ("该店铺存在".equals(dataRecv.getMsg())) {
                                    System.out.print("请输入员工年龄:");
                                    int empAge = scanner.nextInt();
                                    System.out.print("请输入员工性别:");
                                    String empSex = scanner.next();
                                    System.out.print("请输入员工地址:");
                                    String empAddress = scanner.next();
                                    System.out.print("请选择员工新角色\n" +
                                            "1.管理员 (薪资8000)\n" +
                                            "2.收银员 (薪资6000)\n" +
                                            "输入1或者2:");
                                    int num = scanner.nextInt();
                                    if (num == 1) {
                                        dataSend = new Data(sel + sel1, new Employee(shop, empName, empAge, empSex, empAddress, 8000, "admin"));
                                        os = socket.getOutputStream();
                                        oos = new ObjectOutputStream(os);
                                        oos.writeObject(dataSend);

                                        is = socket.getInputStream();
                                        ois = new ObjectInputStream(is);
                                        dataRecv = (Data) ois.readObject();
                                        System.out.println(dataRecv.getMsg());
                                    } else if (num == 2) {
                                        dataSend = new Data(sel + sel1, new Employee(shop, empName, empAge, empSex, empAddress, 6000, "common"));
                                        os = socket.getOutputStream();
                                        oos = new ObjectOutputStream(os);
                                        oos.writeObject(dataSend);

                                        is = socket.getInputStream();
                                        ois = new ObjectInputStream(is);
                                        dataRecv = (Data) ois.readObject();
                                        System.out.println(dataRecv.getMsg());
                                    } else {
                                        System.out.println("输入有误,当前没有该选项,请重新选择！");
                                    }
                                } else {
                                    System.out.println("输入店铺不存在,请重新选择");
                                }
                                System.out.println();
                            } else if ("0".equals(sel1)) {

                                view.signOut();

                                dataSend = new Data("退出");
                                os = socket.getOutputStream();
                                oos = new ObjectOutputStream(os);
                                oos.writeObject(dataSend);

                                is = socket.getInputStream();
                                ois = new ObjectInputStream(is);
                                dataRecv = (Data) ois.readObject();
                                System.out.println(dataRecv.getMsg());

                                oos.close();
                                os.close();
                                ois.close();
                                is.close();

                                break loop;
                            } else {
                                System.out.println("输入有误,当前没有该选项,请重新选择！");
                                System.out.println();
                            }

                        }

                        //收银员
                        else if ("common".equals(employee.getEmployeeRole())) {
                            System.out.println();
                            System.out.println("**************************欢迎\"" + employee.getEmployeeName() + "\"收银员***************************");
                            System.out.println("                            1. 查看个人信息");
                            System.out.println("                            2. 修改个人信息");
                            System.out.println("                            3. 查看个人销售记录");
                            System.out.println("                            0. 退出");
                            System.out.println("-----------------------------------------------------------------");
                            System.out.print("请输入选择:");
                            String sel1 = scanner.next();
                            System.out.println();

                            if ("1".equals(sel1)) {
                                System.out.println(employee);
                                System.out.println();
                            } else if ("2".equals(sel1)) {
                                System.out.print("请输入新名称:");
                                String empName = scanner.next();
                                System.out.print("请输入新密码:");
                                String empPassword = scanner.next();
                                System.out.print("请输入新年龄:");
                                int empAge = scanner.nextInt();
                                System.out.print("请输入新性别:");
                                String empSex = scanner.next();
                                System.out.print("请输入新地址:");
                                String empAddress = scanner.next();

                                dataSend = new Data("修改员工信息", new Employee(employee.getEmployeeId(), empName, empPassword, empAge, empSex, empAddress));
                                os = socket.getOutputStream();
                                oos = new ObjectOutputStream(os);
                                oos.writeObject(dataSend);

                                is = socket.getInputStream();
                                ois = new ObjectInputStream(is);
                                dataRecv = (Data) ois.readObject();
                                System.out.println(dataRecv.getMsg());
                                System.out.println();
                            } else if ("3".equals(sel1)) {

                                dataSend = new Data("查询该员工所有订单", employee);
                                os = socket.getOutputStream();
                                oos = new ObjectOutputStream(os);
                                oos.writeObject(dataSend);

                                is = socket.getInputStream();
                                ois = new ObjectInputStream(is);
                                dataRecv = (Data) ois.readObject();
                                System.out.println(dataRecv.getObject());

                                for (int i = 0; i < dataRecv.getNum() - 1; i++) {
                                    dataRecv = (Data) ois.readObject();
                                    OrderInfo orderInfo = (OrderInfo) dataRecv.getObject();
                                    System.out.println(orderInfo);
                                }
                                System.out.println();
                            } else if ("0".equals(sel1)) {
                                view.signOut();

                                dataSend = new Data("退出");
                                os = socket.getOutputStream();
                                oos = new ObjectOutputStream(os);
                                oos.writeObject(dataSend);

                                is = socket.getInputStream();
                                ois = new ObjectInputStream(is);
                                dataRecv = (Data) ois.readObject();
                                System.out.println(dataRecv.getMsg());

                                oos.close();
                                os.close();
                                ois.close();
                                is.close();

                                break loop;
                            } else {
                                System.out.println("输入有误,当前没有该选项,请重新选择！");
                                System.out.println();
                            }

                        }

                    }

                }

                else if ("用户名或密码错误！".equals(dataRecv.getMsg())) {
                    System.out.println(dataRecv.getMsg());
                }

            }

            //退出
            else if ("0".equals(sel)) {
                view.signOut();

                dataSend = new Data("退出");
                os = socket.getOutputStream();
                oos = new ObjectOutputStream(os);
                oos.writeObject(dataSend);

                is = socket.getInputStream();
                ois = new ObjectInputStream(is);
                dataRecv = (Data) ois.readObject();
                System.out.println(dataRecv.getMsg());

                oos.close();
                os.close();
                ois.close();
                is.close();

                break loop;
            }

            //选择有误
            else {
                view.chooseErr();
            }

        }

    }

}
