package com.min.edu.service;

import java.util.List;
import java.util.Map;

import com.min.edu.dto.StockDto;

public interface IStockInService {
	public List<StockDto> selectAll();
	public List<StockDto> selectAll(int empno);
	public int insert(StockDto dto);
	public StockDto stockInDetail(int id);
	public StockDto selectById(int id);
	// 입고수정 추가(지원)
	public int updateStockInAmount(StockDto dto);
	public List<StockDto> selectDateRange(Map<String, Object> map);
	public List<StockDto> selectStockInByMgr(int i);
	
	// 페이징
	List<StockDto> selectPageAll(Map<String, Object> map);
	// 전체글 갯수
	int cntStockIn();
}
