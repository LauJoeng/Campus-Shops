package com.yang.dao;

import com.yang.BaseTest;
import com.yang.entity.Area;
import com.yang.entity.ShopCategory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ShopCategoryDaoTest extends BaseTest {
    @Autowired
    public ShopCategoryDao shopCategoryDao;

    @Test
    public void testQuery(){
        ShopCategory shopCategory = new ShopCategory();
        shopCategory.setParentId(10L);
        List<ShopCategory>shopCategories = shopCategoryDao.queryShopCategory(shopCategory);
        System.out.println(shopCategories);
    }
}
