package com.min.edu.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import com.min.edu.dto.EmpDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ManagerIntercepter implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		log.info("[ManagerIntercepter] : 관리자 인터셉터 시작");
		EmpDto loginDto = (EmpDto) request.getSession().getAttribute("emp");
		if(loginDto == null || !(loginDto.getJob().equals("팀장"))) {
			log.info("[ManagerIntercepter] 관리자권한 없음");
			log.info("요청 계정 정보 - {} ",loginDto);
			log.info("요청 url : {}",request.getRequestURL());
			log.info("요청 ip : {}",request.getRemoteAddr());
			log.info("요청 User-Agent : {}",request.getHeader("User-Agent"));
			response.sendRedirect("./logout.do");
			return false;
		}
		return true;
	}
}
