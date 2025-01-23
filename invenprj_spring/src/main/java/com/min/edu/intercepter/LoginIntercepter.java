package com.min.edu.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.AsyncHandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoginIntercepter implements AsyncHandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		log.info("[Intercepter] 인터셉터 시작 로그인 session 확인 존재/true 없으면/false logout.do 호출");
		if(request.getSession().getAttribute("emp") == null) {
			log.info("[Intercepter] 로그인 정보 없음");
			response.sendRedirect("./login.do");
			return false;
		}
		return true;
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		log.info("[Intercepter] 인터셉터 종료");
		AsyncHandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}
}
