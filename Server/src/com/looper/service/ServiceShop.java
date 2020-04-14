package com.looper.service;

import com.looper.domain.Shop;

import java.util.List;

public interface ServiceShop {

    //增加分店
    boolean addShop(String shopName, String shopAddress, String shopIphone);

    //删除分店
    boolean deleteShop(int shopId, String shopName);

    //查询全部分店
    List<Shop> findAllShop();

    //按照分店ID查询分店
    Shop findShop(int shopId,String shopName);

    //按照分店ID查询分店
    Shop findShop(int shopId);

    //按照Name查询分店
    boolean findShop(String shopName);

}
