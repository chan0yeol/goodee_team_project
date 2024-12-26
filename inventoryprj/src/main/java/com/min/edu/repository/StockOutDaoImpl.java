package com.min.edu.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.min.edu.dto.StockDto;
import com.min.edu.mybatis.SqlSessionFactoryManager;

public class StockOutDaoImpl implements IStockOutDao {

	private SqlSessionFactory manager = SqlSessionFactoryManager.getFactory();
	private String NS = "com.min.edu.repository.StockOutDaoImpl.";
	
	@Override
	public int insertStockOut(StockDto dto) {
		SqlSession session = manager.openSession();
		return session.insert(NS+"insertStockOut",dto);
	}

	@Override
	public List<StockDto> selectStockOutAll() {
		SqlSession session = manager.openSession();
		return session.selectList(NS+"selectStockOutAll");
	}

	@Override
	public List<StockDto> selectOutDateRange(String start, String end) {
		SqlSession session = manager.openSession();
		Map<String, String> obj = new HashMap<String, String>()
				{{put("start", start); put("end", end);}};
		System.out.println(obj.get(start) + "+" + obj.get(end));
		return session.selectList(NS+"selectOutDateRange",obj);
	}

	@Override
	public List<StockDto> selectStockOutByMgr(int i) {
		SqlSession session = manager.openSession();
		return session.selectList(NS+"selectStockOutByMgr",i);
	}
	
	@Override
	public int updateStockOutAmount(StockDto dto) {
		SqlSession session = manager.openSession(true);
		return session.update(NS+"updateStockOutAmount",dto);
	}
	
	@Override
	public int updateStockOutDelivery(StockDto dto) {
		SqlSession session = manager.openSession(true);
		return session.update(NS+"updateStockOutDelivery",dto);
	}
	
	// 입출고 재고조회
	@Override
	public List<StockDto> selectStockInventory() {
		SqlSession session = manager.openSession();
		return session.selectList(NS+"selectStockInventory");
	}

	// 출고수량 순 목록조회
	@Override
	public List<StockDto> selectStockAmount() {
		SqlSession session = manager.openSession();
		return session.selectList(NS+"selectStockAmount");
	}	
	
	// 매출액 순 목록조회
	@Override
	public List<StockDto> selectStockSales() {
		SqlSession session = manager.openSession();
		return session.selectList(NS+"selectStockSales");
	}
	
}
