package com.yang.service;

import com.yang.entity.ShopCategory;

import java.util.List;

public interface ShopCategoryService {
    List<ShopCategory>getShopCategoryList(ShopCategory shopCategoryCondition);
}
