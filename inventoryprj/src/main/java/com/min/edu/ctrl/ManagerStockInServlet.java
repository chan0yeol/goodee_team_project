package com.min.edu.ctrl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.min.edu.dto.StockDto;
import com.min.edu.repository.IManagerDao;
import com.min.edu.repository.IStockInDao;
import com.min.edu.repository.ManagerDaoImpl;
import com.min.edu.repository.StockInDaoImpl;

public class ManagerStockInServlet extends HttpServlet {

	private static final long serialVersionUID = 1430797254124932454L;
	private IManagerDao dao = new ManagerDaoImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<StockDto> lists =  dao.selectStockInList();
		req.setAttribute("StockInList", lists);
		req.getRequestDispatcher("/WEB-INF/views/managerStockIn.jsp").forward(req, resp);
	}
}
