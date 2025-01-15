package com.min.edu.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.min.edu.dto.EmpDto;
import com.min.edu.repository.IEmpDao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmpServiceImpl implements IEmpService {
	private final IEmpDao dao;

	@Override
	public EmpDto login(Map<String, Object> map) {
		return dao.login(map);
	}

}
