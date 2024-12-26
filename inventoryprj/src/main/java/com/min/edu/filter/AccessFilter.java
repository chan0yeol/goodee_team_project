package com.min.edu.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.min.edu.dto.EmpDto;

public class AccessFilter extends HttpFilter implements Filter {

	private static final long serialVersionUID = -5546043096359317691L;
	private List<String> excludeURL;
	
	@Override
	public void init(FilterConfig config) throws ServletException {
		String pattern = config.getInitParameter("excludeURL");
		excludeURL = Arrays.asList(pattern.split(","));
		System.out.println("제외 요청 주소 리스트" + excludeURL);
	}
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		String path = request.getServletPath();
		if(!excludeURL.contains(path)) {
			// 로그인 확인 
			HttpSession session = request.getSession();
			EmpDto loginDto = (EmpDto) session.getAttribute("emp");
			if(loginDto == null ) {
				request.getRequestDispatcher("WEB-INF/views/index.jsp").forward(req, res);
			} else {
				chain.doFilter(req, res);
			}
		} else {
			req.setCharacterEncoding("UTF-8");
			chain.doFilter(req, res);
		}
	}
}
