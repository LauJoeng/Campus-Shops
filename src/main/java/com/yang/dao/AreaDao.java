package com.yang.dao;

import java.util.List;

import com.yang.entity.Area;

public interface AreaDao {
	/**
	 * 列出区域列表
	 * @return
	 */
	List<Area>queryArea();
}
