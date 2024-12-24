package com.min.edu.repository;

import java.util.List;

import com.min.edu.dto.StockDto;

public interface IStockOutDao {

	public int insertStockOut(StockDto dto);

	public List<StockDto> selectStockOutAll();

	public List<StockDto> selectOutDateRange(String start, String end);

	public List<StockDto> selectStockOutByMgr(int i);
	
	public int updateStockOutAmount(StockDto dto);
	public int updateStockOutDelivery(StockDto dto);
}
