package com.min.edu.ctrl;

import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.min.edu.dto.EmpDto;
import com.min.edu.service.IEmpService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserRestController {
	private final IEmpService empService;
	@PostMapping("/loginChk.do")
	public String loginChk(@RequestParam Map<String, Object> map) {
		log.info("{}", map);
		EmpDto loginDto = empService.login(map);
//		log.info("{}",loginDto);
		return loginDto != null?"{\"isc\":\"true\"}":"{\"isc\":\"false\"}";
//		return null;
	}
}
