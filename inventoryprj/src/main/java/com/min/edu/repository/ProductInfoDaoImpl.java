package com.min.edu.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;

import com.min.edu.dto.ProductInfoDto;
import com.min.edu.mybatis.SqlSessionFactoryManager;

public class ProductInfoDaoImpl implements ProductInfoDao {

	private Logger log = Logger.getLogger(this.getClass());
	private SqlSessionFactory manager = SqlSessionFactoryManager.getFactory();
	private final String NS = "com.min.edu.repository.ProductInfoDaoImpl.";
	
	@Override
	public int product_insert(ProductInfoDto dao) {
		SqlSession session = manager.openSession();
		int cnt = session.insert(NS+"product_insert",dao);
		session.commit();
		return cnt;
	}
	
	@Override
	public int product_insert2(ProductInfoDto dao) {
		SqlSession session = manager.openSession();
		int cnt = session.insert(NS+"product_insert2",dao);
		session.commit();
		return cnt;
	}
	
	@Override
	public List<ProductInfoDto> product_select() {
		SqlSession session = manager.openSession();
		return session.selectList(NS+"product_select");
	}
	
	@Override
	public ProductInfoDto product_select2(String string) {
		ProductInfoDto dto = null;
		SqlSession session = manager.openSession();
		dto = session.selectOne(NS+"product_select2",string);
		return dto;
	}
	
	@Override
	public List<ProductInfoDto> product_select3(String string) {
		SqlSession session = manager.openSession();
		return session.selectList(NS+"product_select3",string);
	}
	
	@Override
	public List<ProductInfoDto> product_select4(Map<String, Object> map) {
		SqlSession session = manager.openSession();
		return session.selectList(NS+"product_select4");
	}
	

}
