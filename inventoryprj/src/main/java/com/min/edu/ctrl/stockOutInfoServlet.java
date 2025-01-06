package com.min.edu.ctrl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.min.edu.dto.ProductInfoDto;
import com.min.edu.dto.StockDto;
import com.min.edu.repository.IStockOutDao;
import com.min.edu.repository.ProductInfoDao;
import com.min.edu.repository.ProductInfoDaoImpl;
import com.min.edu.repository.StockOutDaoImpl;

public class stockOutInfoServlet extends HttpServlet {

	private static final long serialVersionUID = 3255815765613356062L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		String id = req.getParameter("id"); 
		
		IStockOutDao dao = new StockOutDaoImpl();
		StockDto dto = dao.selectStockOutDetail(Integer.parseInt(id));
		
		ProductInfoDao productDao = new ProductInfoDaoImpl();
		ProductInfoDto product = productDao.product_select2(dto.getProduct_id());
		
		req.setAttribute("stockOut", dto);
		req.setAttribute("product", product);
		req.getRequestDispatcher("/WEB-INF/views/stockOutInfoServlet.jsp").forward(req, resp);
	}
}
