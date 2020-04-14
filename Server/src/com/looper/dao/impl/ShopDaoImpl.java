package com.looper.dao.impl;

import com.looper.dao.ShopDao;
import com.looper.domain.Shop;
import com.looper.util.JDBCUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShopDaoImpl implements ShopDao {

    @Override
    public boolean addShop(String shopName, String shopAddress, String shopIphone) {
        boolean flag = false;
        JDBCUtil.getConn();
        try {
            String sql = "insert into shops(shopName,shopAddress,shopIphone) values(?,?,?)";
            JDBCUtil.ps = JDBCUtil.conn.prepareStatement(sql);
            JDBCUtil.ps.setString(1,shopName);
            JDBCUtil.ps.setString(2,shopAddress);
            JDBCUtil.ps.setString(3,shopIphone);
            JDBCUtil.ps.executeUpdate();
            flag = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtil.getClose();
        }
        return flag;
    }

    @Override
    public boolean deleteShop(int shopId, String shopName) {
        boolean flag = false;
        JDBCUtil.getConn();
        try {
            String sql = "delete from shops where shopId=? and shopName=?";
            JDBCUtil.ps = JDBCUtil.conn.prepareStatement(sql);
            JDBCUtil.ps.setInt(1,shopId);
            JDBCUtil.ps.setString(2,shopName);
            JDBCUtil.ps.executeUpdate();
            flag = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        JDBCUtil.getClose();
        return flag;
    }

    @Override
    public List<Shop> findAllShop() {
        JDBCUtil.getConn();
        List<Shop> shops = new ArrayList<>();
        try {
            String sql = "select * from shops";
            JDBCUtil.ps = JDBCUtil.conn.prepareStatement(sql);
            ResultSet rs = JDBCUtil.ps.executeQuery();
            while(rs.next()) {
                int id = rs.getInt("shopId");
                String name = rs.getString("shopName");
                String address = rs.getString("shopAddress");
                String iphone = rs.getString("shopIphone");
                shops.add(new Shop(id,name,address,iphone));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtil.getClose();
        }
        return shops;
    }

    @Override
    public Shop findShop(int shopId, String shopName) {
        JDBCUtil.getConn();
        Shop shop = null;
        String sql = "select * from shops where shopName=? and shopId=?";
        try {
            JDBCUtil.ps = JDBCUtil.conn.prepareStatement(sql);
            JDBCUtil.ps.setString(1,shopName);
            JDBCUtil.ps.setInt(2,shopId);
            ResultSet rs = JDBCUtil.ps.executeQuery();
            while(rs.next()){
                shop = new Shop(rs.getInt("shopId"),rs.getString("shopName"),rs.getString("shopAddress"),rs.getString("shopIphone"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtil.getClose();
        }
        return shop;
    }

    @Override
    public Shop findShop(int shopId) {
        JDBCUtil.getConn();
        Shop shop = null;
        String sql = "select * from shops where shopId=?";
        try {
            JDBCUtil.ps = JDBCUtil.conn.prepareStatement(sql);
            JDBCUtil.ps.setInt(1,shopId);
            ResultSet rs = JDBCUtil.ps.executeQuery();
            while(rs.next()){
                shop = new Shop(rs.getInt("shopId"),rs.getString("shopName"),rs.getString("shopAddress"),rs.getString("shopIphone"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtil.getClose();
        }
        return shop;
    }

    @Override
    public boolean findShop(String shopName) {
        boolean flag = false;
        JDBCUtil.getConn();
        try {
            String sql = "select * from shops where shopName=?";
            JDBCUtil.ps = JDBCUtil.conn.prepareStatement(sql);
            JDBCUtil.ps.setString(1,shopName);
            ResultSet rs = JDBCUtil.ps.executeQuery();
            while(rs.next()){
                flag = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        JDBCUtil.getClose();
        return flag;
    }
}
