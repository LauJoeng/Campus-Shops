package com.yang.service;

import com.yang.dto.ShopExecution;
import com.yang.entity.Shop;

import java.io.File;

public interface ShopService {
    ShopExecution addShop(Shop shop, File shopImg);
}
