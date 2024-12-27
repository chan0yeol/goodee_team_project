package com.min.edu.ctrl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.min.edu.dto.StockDto;
import com.min.edu.repository.IStockInDao;
import com.min.edu.repository.StockInDaoImpl;

public class stockInUpdateServlet extends HttpServlet {

	private static final long serialVersionUID = -3163693867498523722L;
	private IStockInDao dao = new StockInDaoImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String id = req.getParameter("id");
		StockDto dto = dao.selectById(Integer.parseInt(id));
		req.setAttribute("stock", dto);
		req.getRequestDispatcher("/WEB-INF/views/stockInUpdate.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		String id = req.getParameter("id");
		StockDto dto = dao.selectById(Integer.parseInt(id));
		System.out.println(dto);
		System.out.println(id);
		String amount = req.getParameter("amount");
		System.out.println(amount);
		dto.setStock_amount(Integer.parseInt(amount));
		int row = dao.updateStockInAmount(dto);
		if(row == 1) {
			resp.sendRedirect("./stockInInfo.do?id="+id);
		} else {
			resp.getWriter().print("<script>alert('수정 실패'); location.href='stockInUpdate.do?id="+id+"';</script>");
		}
	}
}
