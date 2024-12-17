package com.min.edu.test;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Before;
import org.junit.Test;

import com.min.edu.dto.StockDto;
import com.min.edu.mybatis.SqlSessionFactoryManager;
import com.min.edu.repository.IStockInDao;
import com.min.edu.repository.StockInDaoImpl;

public class StockIn_Test {
	SqlSessionFactory manager;
	
	@Before
	public void createSes() {
		manager = SqlSessionFactoryManager.getFactory();
	}
	@Test
	public void selectAll_test() {
		IStockInDao dao = new StockInDaoImpl();
		List<StockDto> lists = dao.selectAll();	
		for (StockDto d : lists) {
			System.out.println(d);
		}
	}
	

}
