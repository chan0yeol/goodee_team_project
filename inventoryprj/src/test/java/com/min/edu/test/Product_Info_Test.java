package com.min.edu.test;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.min.edu.dto.ProductInfoDto;
import com.min.edu.repository.ProductInfoDao;
import com.min.edu.repository.ProductInfoDaoImpl;

public class Product_Info_Test {

	private ProductInfoDao dao;
	
	@Before
	public void createDao() {
		dao = new ProductInfoDaoImpl();
	}
	
//	제품 등록을 요청하는 기능 (직급이 '사원' AND 부서가 '입고' 이면 IS_ACCEPTED가 'N')
	@Test
	public void product_insert_test() {
		ProductInfoDto dto = new ProductInfoDto()
				.builder()
					.product_name("아이폰")
					.product_maker("APPLE")
					.original_cost(1000000)
					.product_amount(0)
				.build();
		int cnt = dao.product_insert(dto);
		System.out.println(dao);
		assertEquals(1, cnt);
	}
	
//	제품 등록을 요청하는 기능 (직급이 '팀장' AND 부서가 '입고' 이면 IS_ACCEPTED가 'Y')
	@Test
	public void product_insert2_test() {
		ProductInfoDto dto = new ProductInfoDto()
				.builder()
					.product_name("아이폰")
					.product_maker("APPLE")
					.original_cost(1200000)
					.product_amount(0)
					.is_accepted("Y")
				.build();
		int cnt = dao.product_insert2(dto);
		System.out.println(dao);
		assertEquals(1, cnt);
	}
	
//	제품 목록을 조회하는 기능
	@Test
	public void product_select_test() {
		List<ProductInfoDto> lists = dao.product_select();
		for (ProductInfoDto vo : lists) {
			System.out.println(vo.toString());
		}
		assertNotEquals(0, lists.size());
	}
	
//	제품의 상세정보를 조회하는 기능
	@Test
	public void product_select2_test() {
		ProductInfoDto dto = dao.product_select2("P22");
		assertNotNull(dto);
	}
	
//	제품검색 및 등록시 제조사를 선택하는 기능
	@Test
	public void product_select3_test() {
		List<ProductInfoDto> lists = dao.product_select3("APPLE");
		assertNotEquals(0, lists.size());
	}
	
//	제품의 이름을 검색하는 기능
	@Test
	public void product_select4_test() {
		List<ProductInfoDto> lists = dao.product_select();
		for (ProductInfoDto vo : lists) {
			System.out.println(vo.toString());
		}
		assertNotEquals(0, lists.size());
	}
	

}
