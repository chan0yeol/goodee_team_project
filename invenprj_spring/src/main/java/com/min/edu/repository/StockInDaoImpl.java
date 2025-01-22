package com.min.edu.repository;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.min.edu.dto.StockDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class StockInDaoImpl implements IStockInDao {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	private final String NS = "com.min.edu.repository.StockInDaoImpl.";
	
	@Override
	public List<StockDto> selectAll() {
		return sqlSession.selectList(NS+"selectStockInAll");
	}

	@Override
	public List<StockDto> selectAll(String mgr) {
		return sqlSession.selectList(NS+"selectStockInByMgr",mgr);
	}

	@Override
	public int insert(StockDto dto) {
		return sqlSession.insert(NS+"insert",dto);
	}

	@Override
	public StockDto stockInDetail(int id) {
		return sqlSession.selectOne(NS+"stockInDetail",id);
	}

	@Override
	public StockDto selectById(int id) {
		return sqlSession.selectOne(NS+"selectById",id);
	}

	@Override
	public int updateStockInAmount(StockDto dto) {
		return sqlSession.update(NS+"updateStockInAmount",dto);
	}

	@Override
	public List<StockDto> selectDateRange(Map<String, Object> map) {
		return sqlSession.selectList(NS+"selectDateRange", map);
	}

	@Override
	public List<StockDto> selectStockInByMgr(String mgr) {
		return sqlSession.selectList(NS+"selectStockInByMgr",mgr);
	}

	@Override
	public List<StockDto> selectPageAll(Map<String, Object> map) {
		return sqlSession.selectList(NS+"selectPageAll",map);
	}

	@Override
	public int cntStockIn() {
		return sqlSession.selectOne(NS+"cntStockInAll");
	}

	@Override
	public int cntStockInAllMgr(String mgr) {
		return sqlSession.selectOne(NS+"cntStockInAllMgr",mgr);
	}


}
