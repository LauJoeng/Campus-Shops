package com.yang.service;

import com.yang.BaseTest;
import com.yang.dto.ShopExecution;
import com.yang.entity.Area;
import com.yang.entity.PersonInfo;
import com.yang.entity.Shop;
import com.yang.entity.ShopCategory;
import com.yang.enums.ShopStateEnum;
import jdk.internal.util.xml.impl.Input;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import static net.sf.ezmorph.test.ArrayAssertions.assertEquals;

public class ShopServiceTest extends BaseTest {

    @Autowired
    private ShopService shopService;

    @Test
    public void testQueryShopList(){
        Shop shop = new Shop();
        PersonInfo personInfo = new PersonInfo();
        personInfo.setUserId(8L);
        shop.setOwner(personInfo);
        ShopCategory sc = new ShopCategory();
        sc.setShopCategoryId(14L);
        shop.setShopCategory(sc);
        ShopExecution se = shopService.getShopList(shop, 1, 2);
        System.out.println(se);
    }

    @Test
    public void testModifyShop() throws FileNotFoundException {
        Shop shop = shopService.getByShopId(51L);
        shop.setShopName("修改后的店铺名");
        File shopImg = new File("C:\\Users\\Yang\\Desktop\\ps图片\\meggy2.jpg");
        InputStream is = new FileInputStream(shopImg);
        ShopExecution shopExecution = shopService.modifyShop(shop, is, "meggy2.jpg");
        System.out.println("新图片地址为:"+shopExecution.getShop().getShopImg());
    }

    @Test
    public void testAddShop() throws FileNotFoundException {
        Shop shop = new Shop();
        Area area = new Area();
        ShopCategory shopCategory = new ShopCategory();
        area.setAreaId(3L);
        shopCategory.setShopCategoryId(14L);
        shop.setOwnerId(8L);
        shop.setArea(area);
        shop.setShopCategory(shopCategory);
        shop.setShopName("测试店铺Service4");
        shop.setShopDesc("test");
        shop.setShopAddr("test");
        shop.setPhone("test");
        shop.setPriority(10);
        shop.setEnableStatus(ShopStateEnum.CHECK.getState());
        shop.setAdvice("审核中");
        File shopImg = new File("C:\\Users\\Yang\\Desktop\\ps图片\\meggy.jpg");
        InputStream is = new FileInputStream(shopImg);
        ShopExecution shopExecution = shopService.addShop(shop, is,shopImg.getName());
        assertEquals(ShopStateEnum.CHECK.getState(),shopExecution.getState());
    }

}
