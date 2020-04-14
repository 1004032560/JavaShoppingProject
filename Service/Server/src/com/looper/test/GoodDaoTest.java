package com.looper.test;

import com.looper.dao.GoodDao;
import com.looper.dao.impl.GoodDaoImpl;

public class GoodDaoTest {

    public static void main(String[] args) {

        GoodDao goodDao = new GoodDaoImpl();

        /*//测试获取所有商品信息
        for (Good good : goodDao.allGoods()) {
            System.out.println(good);
        }*/

        //测试添加货物
        //goodDao.addGood(new Good("狗粮",20,50,"500g"));

        //测试删除货物
        //goodDao.deleteGood(107,"狗粮");

        //修改商品Name
        //System.out.println(goodDao.modifyGoodNameById(105,"巧克力"));

        //修改商品数量
        //System.out.println(goodDao.modifyGoodCountById(105,100));

        //修改商品价格
        //System.out.println(goodDao.modifyGoodPriceById(105,10));

        //修改商品规格
        //System.out.println(goodDao.modifyGoodUnitById(105,"50g"));

    }

}
