package com.min.edu.ctrl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.min.edu.dto.StockDto;
import com.min.edu.repository.IManagerDao;
import com.min.edu.repository.ManagerDaoImpl;

public class ManagerStockOutServlet extends HttpServlet {

	private static final long serialVersionUID = 6578015538862539877L;
	private IManagerDao dao = new ManagerDaoImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<StockDto> lists =  dao.selectStockOutList();
		req.setAttribute("StockOutList", lists);
		req.getRequestDispatcher("/WEB-INF/views/managerStockOut.jsp").forward(req, resp);
	}
}
