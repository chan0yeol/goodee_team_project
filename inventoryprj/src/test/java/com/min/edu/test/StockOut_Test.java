package com.min.edu.test;

import static org.junit.Assert.*;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Before;
import org.junit.Test;

import com.min.edu.dto.StockDto;
import com.min.edu.mybatis.SqlSessionFactoryManager;
import com.min.edu.repository.IStockOutDao;
import com.min.edu.repository.StockOutDaoImpl;

public class StockOut_Test {
	
	SqlSessionFactory manager;
	IStockOutDao dao;

	@Before
	public void createDao() {
		manager = SqlSessionFactoryManager.getFactory();
		dao = new StockOutDaoImpl();
	}
	
//	제품 출고등록을 요청하는 기능
//	@Test
	public void insertStockOut_test() {
		StockDto dto = StockDto.builder()
				.stock_amount(3)
				.stock_mgr(7369)
				.stock_cost(1000000)
				.product_id("P25")
				.build();
		int row = dao.insertStockOut(dto);
		assertEquals(1, row);
	}
	
//	제품 출고목록을 조회하는 기능
//	@Test
	public void selectStockOutAll_test() {
		List<StockDto> lists = dao.selectStockOutAll();	
		assertNotEquals(0, lists.size());
	}
	
//	제품 출고 날짜별 목록을 조회하는 기능
//	@Test
	public void selectOutDateRange_test() {
		String start = "2024-12-16";
		String end ="2024-12-16"; 
		List<StockDto> list = dao.selectOutDateRange(start,end);
		for (StockDto stockDto : list) {
			System.out.println(stockDto);
		}
	}
	
//	제품 출고 담당자별 목록을 검색하는 기능
//	@Test
	public void selectStockOutByMgr_test() {
		List<StockDto> list = dao.selectStockOutByMgr(7369);
		for (StockDto stockDto : list) {
			System.out.println(stockDto);
		}
	}
	
//	담당자이거나 관리자인경우 출고수량과 배송지를 수정하는 기능
//	- 1)출고수량 수정
//	@Test
	public void updateStockOutAmount_test() {
		StockDto dto = new StockDto()
				.builder()
					.stock_amount(17)
					.stock_id("2024007")
				.build();
		int row = dao.updateStockOutAmount(dto);
		assertEquals(1, row);
	}
//	- 2)배송지 수정
//	@Test
	public void updateStockOutDelivery_test() {
		StockDto dto = new StockDto()
				.builder()
					.stock_addr("구디아카데미")
					.stock_id("2024007")
				.build();
		int row = dao.updateStockOutDelivery(dto);
		assertEquals(1, row);
	}

//	입출고 재고조회
//	@Test
	public void selectStockInventory_test() {
		List<StockDto> lists = dao.selectStockInventory();
		assertNotEquals(0, lists.size());
	}
	
// 출고수량 순 목록조회
//	@Test
	public void selectStockAmount_test() {
		List<StockDto> lists = dao.selectStockAmount();
		assertNotEquals(0, lists.size());
	}
	
	
// 매출액 순 목록조회
//	@Test
	public void selectStockSales_test() {
		List<StockDto> lists = dao.selectStockSales();
		assertNotEquals(0, lists.size());
	}
	
}
