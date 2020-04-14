package com.looper.service.impl;

import com.looper.dao.GoodDao;
import com.looper.dao.impl.GoodDaoImpl;
import com.looper.domain.Good;
import com.looper.service.ServiceGood;

import java.util.List;

public class ServiceGoodImpl implements ServiceGood {

    GoodDao goodDao = new GoodDaoImpl();

    @Override
    public List<Good> allGoods() {
        return goodDao.allGoods();
    }

    @Override
    public boolean findGood(int goodId, String goodName) {
        return goodDao.findGood(goodId,goodName);
    }

    @Override
    public Good findGood(int goodId) {
        return goodDao.findGood(goodId);
    }

    @Override
    public boolean addGood(Good good) {
        return goodDao.addGood(good);
    }

    @Override
    public boolean deleteGood(int goodId, String goodName) {
        return goodDao.deleteGood(goodId,goodName);
    }

    @Override
    public boolean modifyGoodNameById(int goodId, String newGoodName) {
        return goodDao.modifyGoodNameById(goodId,newGoodName);
    }

    @Override
    public boolean modifyGoodPriceById(int goodId, double newGoodPrice) {
        return goodDao.modifyGoodPriceById(goodId,newGoodPrice);
    }

    @Override
    public boolean modifyGoodCountById(int goodId, int newGoodCount) {
        return goodDao.modifyGoodCountById(goodId,newGoodCount);
    }

    @Override
    public boolean modifyGoodUnitById(int goodId, String newGoodUnit) {
        return goodDao.modifyGoodUnitById(goodId,newGoodUnit);
    }
}
