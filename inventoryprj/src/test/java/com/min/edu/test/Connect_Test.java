package com.min.edu.test;

import static org.junit.Assert.assertNotNull;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Before;
import org.junit.Test;

import com.min.edu.mybatis.SqlSessionFactoryManager;

public class Connect_Test {
	
	SqlSessionFactory manager;
	
	@Before
	public void createFactory() {
		manager = SqlSessionFactoryManager.getFactory();
	}
	
	@Test
	public void test() {
		SqlSession session = manager.openSession();
		assertNotNull(session);
	}

}
