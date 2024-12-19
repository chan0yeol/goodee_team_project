package com.min.edu.repository;

import java.util.List;
import java.util.Map;

import com.min.edu.dto.ProductInfoDto;

public interface ProductInfoDao {

	public int product_insert(ProductInfoDto dao);
	
	public int product_insert2(ProductInfoDto dao);
	
	public List<ProductInfoDto> product_select();
	
	public ProductInfoDto product_select2(String string);
	
	public List<ProductInfoDto> product_select3(String string);
	
	public List<ProductInfoDto> product_select4();
}
