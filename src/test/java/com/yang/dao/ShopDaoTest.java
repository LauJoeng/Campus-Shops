package com.yang.dao;

import com.yang.BaseTest;
import com.yang.entity.Area;
import com.yang.entity.PersonInfo;
import com.yang.entity.Shop;
import com.yang.entity.ShopCategory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

import static org.junit.Assert.assertEquals;

public class ShopDaoTest extends BaseTest {

    @Autowired
    private ShopDao shopDao;

    @Test
    public void testInsertShop(){
        Shop shop = new Shop();
        Area area = new Area();
        ShopCategory shopCategory = new ShopCategory();
        area.setAreaId(3L);
        shopCategory.setShopCategoryId(14L);
        shop.setOwnerId(8L);
        shop.setArea(area);
        shop.setShopCategory(shopCategory);
        shop.setShopName("测试店铺");
        shop.setShopDesc("test");
        shop.setShopAddr("test");
        shop.setPhone("test");
        shop.setShopImg("test");
        shop.setPriority(10);
        shop.setCreateTime(new Date());
        shop.setEnableStatus(1);
        shop.setAdvice("审核中");
        int effectedNum = shopDao.insertShop(shop);
        assertEquals(1,effectedNum);
    }


    @Test
    public void testUpdateShop(){
        Shop shop = new Shop();
        Area area = new Area();
        ShopCategory shopCategory = new ShopCategory();
        area.setAreaId(4L);
        shopCategory.setShopCategoryId(22L);
        shop.setOwnerId(9L);
        shop.setArea(area);
        shop.setShopCategory(shopCategory);
        shop.setShopName("测试更新店铺");
        shop.setShopDesc("test1");
        shop.setShopAddr("test1");
        shop.setPhone("test1");
        shop.setShopImg("test1");
        shop.setPriority(11);
        shop.setCreateTime(new Date());
        shop.setEnableStatus(1);
        shop.setAdvice("");
        shop.setShopId(29L);
        shop.setLastEditTime(new Date());
        int effectedNum = shopDao.updateShop(shop);
        assertEquals(1,effectedNum);
    }
}
