package com.yang.dao;

import com.yang.BaseTest;
import com.yang.entity.Area;
import com.yang.entity.PersonInfo;
import com.yang.entity.Shop;
import com.yang.entity.ShopCategory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ShopDaoTest extends BaseTest {

    @Autowired
    private ShopDao shopDao;

    @Test
    public void testQueryShopList(){
        Shop shop = new Shop();
        PersonInfo personInfo = new PersonInfo();
        personInfo.setUserId(8L);
        shop.setOwner(personInfo);
        ShopCategory sc = new ShopCategory();
        sc.setShopCategoryId(14L);
        shop.setShopCategory(sc);
        List<Shop> shops = shopDao.queryShopList(shop, 0, 5);
        int count = shopDao.queryShopCount(shop);
        System.out.println(shops);
        System.out.println("店铺总数:"+count);
    }

    @Test
    public void testQueryByShopId(){
        long shopId = 15;
        Shop shop = shopDao.queryByShopId(shopId);
        System.out.println("areaId:"+shop.getArea().getAreaId());
        System.out.println("areaName:"+shop.getArea().getAreaName());
    }

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
    public void test(){
        Shop shop = new Shop();
        shop.setShopImg("hahaha");
        int i=0;
        change(i);
        System.out.println(i);
    }

    private void change(int i) {
        i = 1;
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
