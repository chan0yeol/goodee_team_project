package com.min.edu.test;

import static org.junit.Assert.*;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Before;
import org.junit.Test;

import com.min.edu.dto.EmpDto;
import com.min.edu.mybatis.SqlSessionFactoryManager;
import com.min.edu.repository.EmpDaoImpl;
import com.min.edu.repository.IEmpDao;

public class EmpLogin_Test {
	
	private IEmpDao dao;
	@Before
	public void createSqlFac() {
		dao = new EmpDaoImpl();
	}
	
	@Test
	public void test() {
		EmpDto dto = EmpDto.builder().empno(7369).ename("SMITH").build();
		EmpDto loginDto = dao.login(dto);
		assertNotNull(loginDto);
	}

}
