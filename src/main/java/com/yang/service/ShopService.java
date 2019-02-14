package com.yang.service;

import com.yang.dto.ShopExecution;
import com.yang.entity.Shop;

import java.io.File;
import java.io.InputStream;

public interface ShopService {

    /**
     * 通过shopCondition返回相应数据
     * @param shopCondition
     * @param pageIndex
     * @param pageSize
     * @return
     */
    ShopExecution getShopList(Shop shopCondition,int pageIndex,int pageSize);

    ShopExecution addShop(Shop shop, InputStream shopImgInputStream,String fileName);

    Shop getByShopId(Long shopId);

    ShopExecution modifyShop(Shop shop,InputStream shopImgInputStream,String fileName);
}
