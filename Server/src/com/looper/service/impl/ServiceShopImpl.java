package com.looper.service.impl;

import com.looper.dao.ShopDao;
import com.looper.dao.impl.ShopDaoImpl;
import com.looper.domain.Shop;
import com.looper.service.ServiceShop;

import java.util.List;

public class ServiceShopImpl implements ServiceShop {

    ShopDao shopDao = new ShopDaoImpl();

    @Override
    public boolean addShop(String shopName, String shopAddress, String shopIphone) {
        return shopDao.addShop(shopName, shopAddress, shopIphone);
    }

    @Override
    public boolean deleteShop(int shopId, String shopName) {
        return shopDao.deleteShop(shopId, shopName);
    }

    @Override
    public List<Shop> findAllShop() {
        return shopDao.findAllShop();
    }

    @Override
    public Shop findShop(int shopId, String shopName) {
        return shopDao.findShop(shopId,shopName);
    }

    @Override
    public Shop findShop(int shopId) {
        return shopDao.findShop(shopId);
    }

    @Override
    public boolean findShop(String shopName) {
        return shopDao.findShop(shopName);
    }
}
