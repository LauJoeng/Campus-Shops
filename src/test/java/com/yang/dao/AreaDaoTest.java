package com.yang.dao;

import static org.junit.Assert.assertNotEquals;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.yang.BaseTest;
import com.yang.entity.Area;

public class AreaDaoTest extends BaseTest{
	
	@Autowired
	private AreaDao areaDao;
	
	@Test 
	public void closeAll() throws Exception{ 
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/o2o?useUnicode=true&characterEncoding=utf8","root","1909227160");
		System.out.println(conn);
	}
	
	@Test
	public void testQueryArea() {
		List<Area>areaList = areaDao.queryArea();
		assertNotEquals(0, areaList.size());
	}
}
