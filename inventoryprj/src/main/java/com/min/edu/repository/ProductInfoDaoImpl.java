package com.min.edu.repository;

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

}
