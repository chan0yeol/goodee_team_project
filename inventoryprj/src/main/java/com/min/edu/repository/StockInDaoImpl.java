package com.min.edu.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	@Override
	public int insert(StockDto dto) {
		SqlSession session = manager.openSession(true);
		int row = session.insert(NS+"insert",dto);
		return row;
	}
	@Override
	public List<StockDto> selectDate(String start, String end) {
		Map<String, String> obj = new HashMap();
		obj.put("start", start);
		obj.put("end", end);
		System.out.println(obj.get(start)+"+"+obj.get(end));
		SqlSession session = manager.openSession();
		return session.selectList(NS+"selectDate",obj);
	}
	

}
