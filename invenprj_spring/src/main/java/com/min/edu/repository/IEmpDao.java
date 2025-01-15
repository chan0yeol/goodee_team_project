package com.min.edu.repository;

import java.util.Map;

import com.min.edu.dto.EmpDto;

public interface IEmpDao {
	public EmpDto login(Map<String, Object> map);
}
