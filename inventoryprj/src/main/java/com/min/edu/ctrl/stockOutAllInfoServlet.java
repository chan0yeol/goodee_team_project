package com.min.edu.ctrl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.min.edu.dto.EmpDto;
import com.min.edu.dto.StockDto;
import com.min.edu.repository.IStockOutDao;
import com.min.edu.repository.StockOutDaoImpl;

public class stockOutAllInfoServlet extends HttpServlet {

	private static final long serialVersionUID = -707914187225598502L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String mgr = req.getParameter("mgr");
		HttpSession session = req.getSession();
		EmpDto loginDto = (EmpDto) session.getAttribute("emp");
		IStockOutDao dao = new StockOutDaoImpl();
		List<StockDto> lists;
		if(mgr != null) {
			lists = dao.selectStockOutByMgr(Integer.parseInt(mgr));
		} else {
			lists = dao.selectStockOutAll();
			
		}
		req.setAttribute("StockOutList", lists);
		req.getRequestDispatcher("/WEB-INF/views/stockOutAllInfo.jsp").forward(req, resp);
	}
}
