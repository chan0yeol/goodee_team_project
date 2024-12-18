package com.min.edu.test;

import static org.junit.Assert.*;

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
	
	@Test
	public void product_insert_test() {
		ProductInfoDto dto = new ProductInfoDto()
				.builder()
					.product_name("아이폰")
					.product_maker("APPLE")
					.original_cost(1000000)
					.product_amount(0)
					.is_accepted("N")
				.build();
		int cnt = dao.product_insert(dto);
		System.out.println(dao);
		assertEquals(1, cnt);
	}

}
