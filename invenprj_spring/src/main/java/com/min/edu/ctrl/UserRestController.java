package com.min.edu.ctrl;

import java.util.Map;

import javax.servlet.http.HttpSession;

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
	@PostMapping("/login.do")
	public String loginChk(@RequestParam Map<String, Object> map, HttpSession session) {
		log.info("{}", map);
		EmpDto loginDto = empService.login(map);
		String rtnURL = "";
		if(loginDto != null) {
			session.setAttribute("emp", loginDto);
			session.setMaxInactiveInterval(60*10*6);
			if(loginDto.getDeptno() == 50 ) {
				rtnURL += "./stockInAllInfo.do";
			} else if(loginDto.getDeptno() == 60) {
				rtnURL += "./stockInAllInfo.do";
			} else if(loginDto.getDeptno() == 70) {
				rtnURL += "./stockOutAllInfo.do";
			}
		}
//		log.info("{}",loginDto);
		return loginDto != null?"{\"isc\":\"true\",\"url\":\""+rtnURL+"\"}":"{\"isc\":\"false\"}";
//		return null;
	}
}
