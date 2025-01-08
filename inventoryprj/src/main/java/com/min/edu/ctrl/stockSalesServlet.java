package com.min.edu.ctrl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.min.edu.dto.EmpDto;
import com.min.edu.dto.ProductInfoDto;
import com.min.edu.repository.IStockOutDao;
import com.min.edu.repository.StockOutDaoImpl;

public class stockSalesServlet extends HttpServlet {

	private static final long serialVersionUID = 7679747585407609173L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		EmpDto loginDto = (EmpDto) session.getAttribute("emp");
		IStockOutDao dao = new StockOutDaoImpl();
		List<ProductInfoDto> rankList=  dao.selectStockSales();
		req.setAttribute("emp", loginDto);
		req.setAttribute("lists", rankList);
		req.getRequestDispatcher("/WEB-INF/views/stockSales.jsp").forward(req, resp);
	}
}
