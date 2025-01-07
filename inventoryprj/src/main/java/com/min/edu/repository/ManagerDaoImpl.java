package com.min.edu.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;

import com.min.edu.dto.ProductInfoDto;
import com.min.edu.dto.StockDto;
import com.min.edu.mybatis.SqlSessionFactoryManager;

public class ManagerDaoImpl implements IManagerDao {

	private Logger log = Logger.getLogger(this.getClass());
	private SqlSessionFactory manager = SqlSessionFactoryManager.getFactory();
	private final String NS = "com.min.edu.repository.ManagerDaoImpl.";
	
	@Override
	public List<ProductInfoDto> selectProductList() {
		SqlSession session = manager.openSession();
		return session.selectList(NS+"selectProductList");
	}

	@Override
	public int updateProductRequest(ProductInfoDto dto) {
		SqlSession session = manager.openSession(true);
		return session.update(NS+"updateProductRequest",dto);
	}

	@Override
	public List<StockDto> selectStockInList() {
		SqlSession session = manager.openSession();
		return session.selectList(NS+"selectStockInList");
	}

	@Override
	public int updateStockInRequest(StockDto dto) {
		SqlSession session = manager.openSession(true);
		return session.update(NS+"updateStockInRequest",dto);
	}

	@Override
	public List<StockDto> selectStockOutList() {
		SqlSession session = manager.openSession();
		return session.selectList(NS+"selectStockOutList");
	}

	@Override
	public int updateStockOutRequest(StockDto dto) {
		SqlSession session = manager.openSession(true);
		return session.update(NS+"updateStockRequest",dto);
	}

	@Override
	public StockDto findByStockId(String id) {
		SqlSession session = manager.openSession();
		return session.selectOne(NS+"findByStockId",id);
	}

}
