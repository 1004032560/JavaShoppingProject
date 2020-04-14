package com.looper.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JDBCUtil {

    private static String driver="com.mysql.jdbc.Driver";
    private static String user = "root";
    private static String password = "123looper.";
    private static String url="jdbc:mysql://localhost:3306/managementsystem? characterEncoding=utf8";


    public static Connection conn = null;
    public static PreparedStatement ps = null;

    public static void getConn(){

        try {
            //加载驱动
            Class.forName(driver);
            //获取数据库
            conn = DriverManager.getConnection(url,user,password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void getClose(){
        try {
            //关闭连接
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
