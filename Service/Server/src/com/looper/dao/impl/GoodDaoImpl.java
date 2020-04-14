package com.looper.dao.impl;

import com.looper.dao.GoodDao;
import com.looper.domain.Good;
import com.looper.util.JDBCUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GoodDaoImpl implements GoodDao {

    //获取商品信息
    @Override
    public List<Good> allGoods() {
        List<Good> goods = new ArrayList<>();
        JDBCUtil.getConn();
        String sql = "select * from goods";
        try {
            JDBCUtil.ps = JDBCUtil.conn.prepareStatement(sql);
            ResultSet rs = JDBCUtil.ps.executeQuery();
            while (rs.next()){
                int id = rs.getInt("goodId");
                String name = rs.getString("goodName");
                double price = rs.getDouble("goodPrice");
                int count = rs.getInt("goodCount");
                String unit = rs.getString("goodUnit");
                goods.add(new Good(id,name,price,count,unit));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtil.getClose();
        }
        return goods;
    }

    // 查找单个商品
    @Override
    public boolean findGood(int goodId, String goodName) {
        boolean flag = false;
        JDBCUtil.getConn();
        String sql = "select * from goods where goodId=? and goodName=？";
        try {
            JDBCUtil.ps = JDBCUtil.conn.prepareStatement(sql);
            JDBCUtil.ps.setInt(1,goodId);
            JDBCUtil.ps.setString(2,goodName);
            ResultSet rs = JDBCUtil.ps.executeQuery();
            while (rs.next()){
                flag = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtil.getClose();
        }
        return flag;
    }

    @Override
    public Good findGood(int goodId) {
        JDBCUtil.getConn();
        String sql = "select * from goods where goodId=?";
        Good good = null;
        try {
            JDBCUtil.ps = JDBCUtil.conn.prepareStatement(sql);
            JDBCUtil.ps.setInt(1, goodId);
            ResultSet rs = JDBCUtil.ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("goodId");
                String name = rs.getString("goodName");
                double price = rs.getDouble("goodPrice");
                int count = rs.getInt("goodCount");
                String unit = rs.getString("goodUnit");
                good = new Good(id,name,price,count,unit);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.getClose();
        }
        return good;
    }

    //添加商品
    @Override
    public boolean addGood(Good good) {
        boolean flag = false;
        JDBCUtil.getConn();
        String sql = "insert into goods(goodName,goodPrice,goodCount,goodUnit) values(?,?,?,?)";
        try {
            JDBCUtil.ps = JDBCUtil.conn.prepareStatement(sql);
            JDBCUtil.ps.setString(1,good.getGoodName());
            JDBCUtil.ps.setDouble(2,good.getGoodPrice());
            JDBCUtil.ps.setInt(3,good.getGoodCount());
            JDBCUtil.ps.setString(4,good.getGoodUnit());
            JDBCUtil.ps.executeUpdate();
            flag = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtil.getClose();
        }
        return flag;
    }

    //删除商品
    @Override
    public boolean deleteGood(int goodId, String goodName) {
        boolean flag = false;
        JDBCUtil.getConn();
        String sql = "delete from goods where goodId=? and goodName=?";
        try {
            JDBCUtil.ps = JDBCUtil.conn.prepareStatement(sql);
            JDBCUtil.ps.setInt(1,goodId);
            JDBCUtil.ps.setString(2,goodName);
            JDBCUtil.ps.executeUpdate();
            flag = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtil.getClose();
        }
        return flag;
    }

    //修改商品名称
    @Override
    public boolean modifyGoodNameById(int goodId, String newGoodName) {
        boolean flag = false;
        if (findGood(goodId)!=null){
            JDBCUtil.getConn();
            String sql = "update goods set goodName=? where goodId=?";
            try {
                JDBCUtil.ps = JDBCUtil.conn.prepareStatement(sql);
                JDBCUtil.ps.setString(1,newGoodName);
                JDBCUtil.ps.setInt(2,goodId);
                JDBCUtil.ps.executeUpdate();
                flag = true;
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                JDBCUtil.getClose();
            }
        }
        return flag;
    }

    //修改商品价格
    @Override
    public boolean modifyGoodPriceById(int goodId, double newGoodPrice) {
        boolean flag = false;
        if (findGood(goodId)!=null){
            JDBCUtil.getConn();
            String sql = "update goods set goodPrice=? where goodId=?";
            try {
                JDBCUtil.ps = JDBCUtil.conn.prepareStatement(sql);
                JDBCUtil.ps.setDouble(1,newGoodPrice);
                JDBCUtil.ps.setInt(2,goodId);
                JDBCUtil.ps.executeUpdate();
                flag = true;
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                JDBCUtil.getClose();
            }
        }
        return flag;
    }

    //修改商品数量
    @Override
    public boolean modifyGoodCountById(int goodId, int newGoodCount) {
        boolean flag = false;
        if (findGood(goodId)!=null){
            JDBCUtil.getConn();
            String sql = "update goods set goodCount=? where goodId=?";
            try {
                JDBCUtil.ps = JDBCUtil.conn.prepareStatement(sql);
                JDBCUtil.ps.setInt(1,newGoodCount);
                JDBCUtil.ps.setInt(2,goodId);
                JDBCUtil.ps.executeUpdate();
                flag = true;
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                JDBCUtil.getClose();
            }
        }
        return flag;
    }

    //修改商品规格
    @Override
    public boolean modifyGoodUnitById(int goodId, String newGoodUnit) {
        boolean flag = false;
        if (findGood(goodId)!=null){
            JDBCUtil.getConn();
            String sql = "update goods set goodUnit=? where goodId=?";
            try {
                JDBCUtil.ps = JDBCUtil.conn.prepareStatement(sql);
                JDBCUtil.ps.setString(1,newGoodUnit);
                JDBCUtil.ps.setInt(2,goodId);
                JDBCUtil.ps.executeUpdate();
                flag = true;
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                JDBCUtil.getClose();
            }
        }
        return flag;
    }
}
