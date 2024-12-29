package com.min.edu.ctrl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.min.edu.dto.StockDto;
import com.min.edu.repository.IStockOutDao;
import com.min.edu.repository.StockOutDaoImpl;

public class stockInAllInfoServlet extends HttpServlet {

	private static final long serialVersionUID = 3977037155404599157L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		StockDto stockDto = (StockDto)session.getAttribute("stockDto");
		IStockOutDao dao = new StockOutDaoImpl();
		List<StockDto> dto = dao.selectStockInventory();
		
		req.setAttribute("stockDto", dto);
		req.getRequestDispatcher("/WEB-INF/views/stockInAllInfo.jsp").forward(req, resp);
	}
}
