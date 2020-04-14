package com.looper.test;

import com.looper.dao.ShopDao;
import com.looper.dao.impl.ShopDaoImpl;

public class StoreDaoTest {

    public static void main(String[] args) {

        ShopDao storeDao = new ShopDaoImpl();

        //测试添加shop
        //storeDao.addShop("hello","云南","13720038067");

        //测试删除shop
        //storeDao.deleteShop(4,"你好不好");

        //测试显示全部shop
        /*List<Shop> stores = storeDao.findAllShop();
        for (Shop shop : stores) {
            System.out.println(shop);
        }*/

        //测试通过Name和Id查找shop
        //System.out.println(storeDao.findShop(2,"ABC"));

        //测试通过Name查找shop是否存在
        //System.out.println(storeDao.findShop("ABC"));

    }

}
