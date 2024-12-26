package com.min.edu.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

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
	
//	제품 입고목록을 조회하는 기능
//	@Test
	public void selectAll_test() {
		List<StockDto> lists = dao.selectAll();	
		assertNotEquals(0, lists.size());
	}
	
//	제품 입고등록을 요청하는 기능
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
	
//	제품 입고 날짜별 목록을 조회하는 기능
//	@Test
	public void selectDate_test() {
		String start = "2024-12-17";
		String end ="2024-12-18"; 
		List<StockDto> list = dao.selectDateRange(start,end);
		for (StockDto stockDto : list) {
			System.out.println(stockDto);
		}
	}
	
//	제품 입고 담당자별 목록을 검색하는 기능
//  @Test
	public void selectStockByMgr_test() {
		List<StockDto> list = dao.selectStockInByMgr(7369);
		for (StockDto stockDto : list) {
			System.out.println(stockDto);
		}
	}

//  담당자이거나 관리자인 경우 입고수량을 수정하는 기능
//	@Test
	public void updateStockInAmount_test() {
		StockDto dto = new StockDto()
				.builder()
					.stock_amount(17)
					.stock_id("2024002")
				.build();
		int row = dao.updateStockInAmount(dto);
		assertEquals(1, row);
	}
	
	// I106 입고정보 상세조회
	@Test
	public void stockInDetail_test() {
		StockDto dto = dao.stockInDetail(Integer.parseInt("2024010"));
		assertNotNull(dto);
	}
	
}
