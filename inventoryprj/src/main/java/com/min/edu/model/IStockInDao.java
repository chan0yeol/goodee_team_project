package com.min.edu.model;

import java.util.List;

import com.min.edu.dto.StockDto;

public interface IStockInDao {
	public List<StockDto> selectAll();
}
