package com.min.edu.repository;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.min.edu.dto.EmpDto;
import com.min.edu.mybatis.SqlSessionFactoryManager;

public class EmpDaoImpl implements IEmpDao {
	
	private SqlSessionFactory manager = SqlSessionFactoryManager.getFactory();
	private final String NS = "com.min.edu.repository.EmpDaoImpl.";
	@Override
	public EmpDto login(EmpDto dto) {		
		SqlSession session = manager.openSession();
		EmpDto emp = session.selectOne(NS+"loginChk",dto);
		System.out.println(emp);
		return emp;
	}

}
