package com.min.edu.ctrl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.min.edu.dto.StockDto;
import com.min.edu.repository.IStockInDao;
import com.min.edu.repository.StockInDaoImpl;

public class stockAllInfoServlet extends HttpServlet {

	private static final long serialVersionUID = 2419004667643823820L;
	private IStockInDao dao = new StockInDaoImpl(); 
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String mgr = req.getParameter("mgr");
		List<StockDto> lists;
		if(mgr == null) {
			lists = dao.selectAll();
		} else {
			lists = dao.selectAll(Integer.parseInt(mgr));
		}
		
		req.setAttribute("StockInList", lists);
		req.getRequestDispatcher("/WEB-INF/views/stockAllInfo.jsp").forward(req, resp);
	}
}
