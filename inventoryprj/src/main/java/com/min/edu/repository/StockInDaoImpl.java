package com.min.edu.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.min.edu.dto.StockDto;
import com.min.edu.mybatis.SqlSessionFactoryManager;

public class StockInDaoImpl implements IStockInDao {

	private SqlSessionFactory manager = SqlSessionFactoryManager.getFactory();
	private String NS = "com.min.edu.repository.StockInDaoImpl.";
	@Override
	public List<StockDto> selectAll() {
		SqlSession session = manager.openSession();
		return session.selectList(NS+"selectStockInAll");
	}

}
