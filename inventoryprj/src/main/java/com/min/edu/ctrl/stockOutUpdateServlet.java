package com.min.edu.ctrl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.min.edu.dto.StockDto;
import com.min.edu.repository.IStockOutDao;
import com.min.edu.repository.StockOutDaoImpl;

public class stockOutUpdateServlet extends HttpServlet {

	private static final long serialVersionUID = -5213778528238455355L;
	private IStockOutDao dao = new StockOutDaoImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String id = req.getParameter("id");
		StockDto dto = dao.selectStockOutDetail(Integer.parseInt(id));
		req.setAttribute("stock", dto);
		req.getRequestDispatcher("/WEB-INF/views/stockOutUpdate.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String id = req.getParameter("id");
		String amount = req.getParameter("amount");
		StockDto dto = dao.selectStockOutDetail(Integer.parseInt(id));
		dto.setStock_amount(Integer.parseInt(amount));
		int row = dao.updateStockOutAmount(dto);
		if(row == 1) {
			System.out.println("성공");
			resp.sendRedirect("./stockOutInfo.do?id="+id);
//			req.getRequestDispatcher("/WEB-INF/views/stockOutInfoServlet.jsp").forward(req, resp);
		} else {
			System.out.println("실패");
		}
	}
	
}
