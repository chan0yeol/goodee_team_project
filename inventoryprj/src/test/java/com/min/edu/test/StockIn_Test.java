package com.min.edu.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Before;
import org.junit.Test;

import com.min.edu.dto.StockDto;
import com.min.edu.mybatis.SqlSessionFactoryManager;
import com.min.edu.repository.IStockInDao;
import com.min.edu.repository.StockInDaoImpl;

public class StockIn_Test {
	SqlSessionFactory manager;
	IStockInDao dao;
	@Before
	public void createSes() {
		manager = SqlSessionFactoryManager.getFactory();
		dao = new StockInDaoImpl();
	}
//	@Test
	public void selectAll_test() {
		List<StockDto> lists = dao.selectAll();	
		assertNotEquals(0, lists.size());
	}
//	@Test
	public void insert_test() {
		StockDto dto = StockDto.builder()
				.stock_amount(3)
				.stock_mgr(7369)
				.product_id("P23")
				.build();
		int row = dao.insert(dto);
		assertEquals(1, row);
	}
	
//	@Test
	public void selectDate_test() {
		String start = "2024-12-17";
		String end ="2024-12-18"; 
		List<StockDto> list = dao.selectDateRange(start,end);
		for (StockDto stockDto : list) {
			System.out.println(stockDto);
		}
	}
	
	@Test
	public void selectStockByMgr_test() {
		List<StockDto> list = dao.selectStockInByMgr(7369);
		for (StockDto stockDto : list) {
			System.out.println(stockDto);
		}
	}

}
