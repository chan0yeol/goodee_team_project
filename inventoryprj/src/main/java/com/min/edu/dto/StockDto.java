package com.min.edu.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class StockDto {
	private String stock_id,
	gubun,
	stock_date,
	is_accepted,
	product_id;
	private int stock_amount,
	stock_mgr,
	stock_cost;
}
