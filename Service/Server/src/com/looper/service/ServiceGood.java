package com.looper.service;

import com.looper.domain.Good;

import java.util.List;

public interface ServiceGood {

    //获取商品信息
    List<Good> allGoods();

    //查找单个商品
    boolean findGood(int goodId,String goodName);

    //查找单个商品并返回
    Good findGood(int goodId);

    //添加商品
    boolean addGood(Good good);

    //删除商品
    boolean deleteGood(int goodId, String goodName);

    //修改商品名称
    boolean modifyGoodNameById(int goodId, String newGoodName);

    //修改商品价格
    boolean modifyGoodPriceById(int goodId, double newGoodPrice);

    //修改商品数量
    boolean modifyGoodCountById(int goodId, int newGoodCount);

    //修改商品规格
    boolean modifyGoodUnitById(int goodId, String newGoodUnit);

}
