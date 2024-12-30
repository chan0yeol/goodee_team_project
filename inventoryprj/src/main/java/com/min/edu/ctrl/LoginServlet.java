package com.min.edu.ctrl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.min.edu.dto.EmpDto;
import com.min.edu.dto.StockDto;
import com.min.edu.repository.EmpDaoImpl;
import com.min.edu.repository.IEmpDao;

public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 3828164570372890792L;
	private Logger log = Logger.getLogger(this.getClass());

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.info("LoginServlet doGet 로그아웃");
		HttpSession session = req.getSession();
		session.removeAttribute("emp");
		session.invalidate();

		resp.sendRedirect("./index.do");

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.info("LoginServlet doPost 로그인");
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8;");
		String name = req.getParameter("ename");
		int empno = Integer.parseInt(req.getParameter("empno"));

		IEmpDao dao = new EmpDaoImpl();
		EmpDto dto = EmpDto.builder().ename(name).empno(empno).build();
		EmpDto s = dao.login(dto);
//		System.out.println(s);

		if (s != null) {
			
			HttpSession session = req.getSession();
			session.setAttribute("emp", s);
			if(s.getDeptno() == 50 ) {
				resp.sendRedirect("./productAllInfo.do");
			} else if(s.getDeptno() == 60) {
				resp.sendRedirect("./stockAllInfo.do");
			} else if(s.getDeptno() == 70) {
				resp.sendRedirect("./stockOutAllInfo.do");
			}
//			req.getRequestDispatcher("/index.jsp").forward(req, resp);
//			resp.sendRedirect("./index.do");
		} else {
			String fail = "로그인 실패";
			req.setAttribute("fail", fail);
			req.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(req, resp);
		}
	}
}
