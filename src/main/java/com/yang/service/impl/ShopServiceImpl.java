package com.yang.service.impl;

import com.yang.dao.ShopDao;
import com.yang.dto.ShopExecution;
import com.yang.entity.Shop;
import com.yang.enums.ShopStateEnum;
import com.yang.exceptions.ShopOperationException;
import com.yang.service.ShopService;
import com.yang.util.ImageUtil;
import com.yang.util.PageCalculator;
import com.yang.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    private ShopDao shopDao;

    @Override
    public ShopExecution getShopList(Shop shopCondition, int pageIndex, int pageSize) {
        int rowIndex = PageCalculator.calculateRowIndex(pageIndex,pageSize);
        List<Shop>shopList = shopDao.queryShopList(shopCondition,rowIndex,pageSize);
        int count = shopDao.queryShopCount(shopCondition);
        ShopExecution se = new ShopExecution();
        if (shopList!=null){
            se.setCount(count);
            se.setShopList(shopList);
        }else {
            se.setState(ShopStateEnum.INNER_ERROR.getState());
        }
        return se;
    }

    @Override
    @Transactional
    public ShopExecution addShop(Shop shop, InputStream shopImgImputStream, String fileName) {
        //空值判断
        if (shop==null){
            return  new ShopExecution(ShopStateEnum.NULL_SHOP);
        }
        try {
            //给店铺信息赋初值
            shop.setEnableStatus(0);
            shop.setCreateTime(new Date());
            shop.setLastEditTime(new Date());
            //添加店铺信息
            int effectNum = shopDao.insertShop(shop);
            if (effectNum <= 0){
                throw new ShopOperationException("店铺创建失败");//只有发生RuntimeException以及它的子类的异常的时候事务才会停止
            }else{
                if (shopImgImputStream != null){
                    try {
                        addShopImg(shop, shopImgImputStream,fileName);
                    }catch (Exception e){
                        throw new ShopOperationException("addShopImg error:"+e.getMessage());
                    }
                    //更新店铺的图片地址
                    effectNum = shopDao.updateShop(shop);
                    if (effectNum <= 0){
                        throw new ShopOperationException("更新图片地址失败");
                    }
                }
            }
        }catch (Exception e){
            throw new ShopOperationException("addShop error:"+e.getMessage());
        }
        return new ShopExecution(ShopStateEnum.CHECK,shop);
    }

    @Override
    public Shop getByShopId(Long shopId) {
        return shopDao.queryByShopId(shopId);
    }

    @Override
    public ShopExecution modifyShop(Shop shop, InputStream shopImgInputStream, String fileName) {
        //判断是否需要处理图片
        if (shop==null || shop.getShopId()==null){
            return new ShopExecution(ShopStateEnum.NULL_SHOP);
        }else {
            try {
                if (shopImgInputStream!=null && fileName!=null && !"".equals(fileName)){
                    Shop tempShop = shopDao.queryByShopId(shop.getShopId());
                    if (tempShop.getShopImg()!=null){
                        ImageUtil.deleteFileOrPath(tempShop.getShopImg());
                    }
                    addShopImg(shop,shopImgInputStream,fileName);
                }
                //更新店铺信息
                shop.setLastEditTime(new Date());
                int effectedNum = shopDao.updateShop(shop);
                if (effectedNum<=0){
                    return new ShopExecution(ShopStateEnum.INNER_ERROR);
                }else{
                    shop = shopDao.queryByShopId(shop.getShopId());
                    return new ShopExecution(ShopStateEnum.SUCCESS,shop);
                }
            } catch (Exception e) {
                throw new ShopOperationException("modify shop error:"+e.getMessage());
            }
        }
    }

    private void addShopImg(Shop shop, InputStream shopImgInputStream,String fileName) {
        //获取shop图片目录的相对路径
        String dest = PathUtil.getShopImagePath(shop.getShopId());
        String shopImgAddr = ImageUtil.generateThumbnail(shopImgInputStream,fileName,dest);
        shop.setShopImg(shopImgAddr);
    }
}
