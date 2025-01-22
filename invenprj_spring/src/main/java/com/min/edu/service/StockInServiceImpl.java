package com.min.edu.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.min.edu.dto.StockDto;
import com.min.edu.repository.IStockInDao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class StockInServiceImpl implements IStockInService {
	
	private final IStockInDao inDao;
	
	@Override
	public List<StockDto> selectAll() {
		return inDao.selectAll();
	}

	@Override
	public List<StockDto> selectAll(String mgr) {
		return inDao.selectAll(mgr);
	}

	@Override
	public int insert(StockDto dto) {
		return inDao.insert(dto);
	}

	@Override
	public StockDto stockInDetail(int id) {
		// TODO Auto-generated method stub
		return inDao.stockInDetail(id);
	}

	@Override
	public StockDto selectById(int id) {
		return inDao.selectById(id);
	}

	@Override
	public int updateStockInAmount(StockDto dto) {
		return inDao.updateStockInAmount(dto);
	}

	@Override
	public List<StockDto> selectDateRange(Map<String, Object> map) {
		return inDao.selectDateRange(map);
	}

	@Override
	public List<StockDto> selectStockInByMgr(String mgr) {
		return inDao.selectStockInByMgr(mgr);
	}

	@Override
	public List<StockDto> selectPageAll(Map<String, Object> map) {
		return inDao.selectPageAll(map);
	}

	@Override
	public int cntStockIn() {
		return inDao.cntStockIn();
	}

	@Override
	public int cntStockInAllMgr(String mgr) {
		return inDao.cntStockInAllMgr(mgr);
	}

}
