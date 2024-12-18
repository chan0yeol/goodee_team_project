package com.min.edu.repository;

import java.util.List;

import com.min.edu.dto.StockDto;

public interface IStockInDao {
	public List<StockDto> selectAll();
	public int insert(StockDto dto);
}
