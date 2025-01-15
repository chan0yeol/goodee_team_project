package com.min.edu.service;

import java.util.Map;

import com.min.edu.dto.EmpDto;

public interface IEmpService {
	public EmpDto login(Map<String, Object> map);
}
