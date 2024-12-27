package com.min.edu.repository;

import java.util.List;

import com.min.edu.dto.StockDto;

public interface IStockInDao {
	public List<StockDto> selectAll();
	public List<StockDto> selectAll(int empno);
	public int insert(StockDto dto);
	public List<StockDto> selectDate(String start, String end);
	public List<StockDto> selectByMgr(int empno);
	public StockDto stockInDetail(int id);
	public StockDto selectById(int id);
	// 입고수정 추가(지원)
	public int updateStockInAmount(StockDto dto);
	public List<StockDto> selectDateRange(String start, String end);
	public List<StockDto> selectStockInByMgr(int i);
}
