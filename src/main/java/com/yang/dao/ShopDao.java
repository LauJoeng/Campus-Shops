package com.yang.dao;

import com.yang.entity.Shop;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShopDao {

	/**
	 * 分页查询店铺，可输入的条件有:店铺名(模糊),店铺状态，店铺类别，区域id，owner
	 * @param shopCondition
	 * @param rowIndex 从第几行开始取数据
	 * @param pageSize 返回条数
	 * @return
	 */
	List<Shop> queryShopList(@Param("shopCondition")Shop shopCondition,@Param("rowIndex")int rowIndex,
							 @Param("pageSize")int pageSize);

	/**
	 * 返回queryShopList总数
	 * @param shopCondition
	 * @return
	 */
	int queryShopCount(@Param("shopCondition")Shop shopCondition);
	
	int insertShop(Shop shop);

	int updateShop(Shop shop);

	Shop queryByShopId(long shopId);
}
