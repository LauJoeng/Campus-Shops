package com.yang.service;

import com.yang.BaseTest;
import com.yang.dto.ShopExecution;
import com.yang.entity.Area;
import com.yang.entity.Shop;
import com.yang.entity.ShopCategory;
import com.yang.enums.ShopStateEnum;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.util.Date;

import static net.sf.ezmorph.test.ArrayAssertions.assertEquals;

public class ShopServiceTest extends BaseTest {

    @Autowired
    private ShopService shopService;

    @Test
    public void testAddShop(){
        Shop shop = new Shop();
        Area area = new Area();
        ShopCategory shopCategory = new ShopCategory();
        area.setAreaId(3L);
        shopCategory.setShopCategoryId(14L);
        shop.setOwnerId(8L);
        shop.setArea(area);
        shop.setShopCategory(shopCategory);
        shop.setShopName("测试店铺Service2");
        shop.setShopDesc("test");
        shop.setShopAddr("test");
        shop.setPhone("test");
        shop.setPriority(10);
        shop.setEnableStatus(ShopStateEnum.CHECK.getState());
        shop.setAdvice("审核中");
        File shopImg = new File("C:\\Users\\Yang\\Desktop\\ps图片\\meggy.jpg");
        ShopExecution shopExecution = shopService.addShop(shop, shopImg);
        assertEquals(ShopStateEnum.CHECK.getState(),shopExecution.getState());
    }

}
