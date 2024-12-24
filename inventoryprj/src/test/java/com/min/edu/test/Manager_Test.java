package com.min.edu.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.min.edu.dto.ProductInfoDto;
import com.min.edu.dto.StockDto;
import com.min.edu.repository.IManagerDao;
import com.min.edu.repository.ManagerDaoImpl;

public class Manager_Test {

	private IManagerDao dao;

	@Before
	public void createDao() {
		dao = new ManagerDaoImpl();
	}

//	제품 등록 대기중인 목록을 조회한다.
//	@Test
	public void selectProductList_test() {
		List<ProductInfoDto> lists = dao.selectProductList();
		for (ProductInfoDto vo : lists) {
			System.out.println(vo.toString());
		}
		assertNotEquals(0, lists.size());
	};

//	제품 등록 요청이 들어오면 승인하거나 거절할 수 있다. 
//	@Test
	public void updateProductRequest_test() {
		ProductInfoDto dto = new ProductInfoDto()
				.builder()
					.is_accepted("Y")
					.product_id("P42")
				.build();
		int row = dao.updateProductRequest(dto);
		assertEquals(1, row);
	};

//	입고 등록 대기중인 목록을 조회한다
//	@Test
	public void selectStockInList_test() {
		List<StockDto> lists = dao.selectStockInList();
		for (StockDto vo : lists) {
			System.out.println(vo.toString());
		}
		assertNotEquals(0, lists.size());
	};

//	입고 등록 요청이 들어오면 승인하거나 거절할 수 있다.
//	@Test
	public void updateStockInRequest_test() {
		StockDto dto = new StockDto()
				.builder()
					.is_accepted("Y")
					.stock_id("2024002")
				.build();
		int row = dao.updateStockInRequest(dto);
		assertEquals(1, row);
	};

//	출고 등록 대기중인 목록을 조회한다
//	@Test
	public void selectStockOutList_test() {
		List<StockDto> lists = dao.selectStockOutList();
		for (StockDto vo : lists) {
			System.out.println(vo.toString());
		}
		assertNotEquals(0, lists.size());
	};

//	출고 등록 요청이 들어오면 승인하거나 거절할 수 있다. 
//	@Test
	public void updateStockOutRequest_test() {
		StockDto dto = new StockDto()
				.builder()
					.is_accepted("Y")
					.stock_id("2024007")
				.build();
		int row = dao.updateStockOutRequest(dto);
		assertEquals(1, row);
	};

}
