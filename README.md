# `JavaSE`购物管理系统

### 1、项目名：

* `looper`购物系统



### 2、项目开发周期规划：

| 阶段名称     | 阶段时间                     | 阶段提交物                                                   |
| ------------ | ---------------------------- | ------------------------------------------------------------ |
| 项目启动     | 2020年04月08日（1天）        | 《项目立项申请书》 、《项目开发周期表》                      |
| 项目实施     | 2020年04月07日  ~14日（6天） | 项目阶段代码                                                 |
| 项目中期评审 | 2020年04月13日（半天）       | 项目阶段审查                                                 |
| 项目结项评审 | 2019年07月14日（1天）        | 项目立项申请书、项目开发周期表、项目汇报`PPT`、项目源代码、项目总结报告、程序员日志。 |



### 3、项目使用的环境以及工具：

| 名称                    | **环境细节**                 |
| ----------------------- | ---------------------------- |
| 系统                    | `Windows 10` 企业版          |
| `JDK` 版本              | `jdk1.8.0_201`               |
| `MySQL` 数据库          | `MySQL Server 5.5`           |
| 数据库连接工具 `jar` 包 | `mysql-connector-java-5.0.6` |
| 开发工具                | `IDEA`                       |
| 开发语言                | `Java`                       |



### 4、项目技术：

1、数据库表设计（主键与外键的关系）；

2、`JDBC`连接数据库；

3、网络编程（客户端与服务端，服务器监听到新客户端的请求，创建线程对象，启动线程）；

4、多线程（同步块，多个客户端访问同一个服务端）；

5、`TCP`连接信息传输的时候使用的创建的实体类：`Data`；

6、对数据库中表的删除使用的在表后边加标志位：`flag`，将信息在查询的时候查询不到。



### 5、项目功能：

1. 顾客登录功能
   * **顾客**分为：**普通用户**和**会员用户**

![1.customerLogin](https://github.com/1004032560/JavaShoppingProject/blob/master/image/1.customerLogin.png?raw=true)



2. 顾客注册功能

![1.customerLogin](https://github.com/1004032560/JavaShoppingProject/blob/master/image/2.customerRegister.png?raw=true)



3. 员工登录功能
   * **员工**分为：**收银员**和**管理员**

![3.employeeLogin](https://github.com/1004032560/JavaShoppingProject/blob/master/image/3.employeeLogin.png?raw=true)
