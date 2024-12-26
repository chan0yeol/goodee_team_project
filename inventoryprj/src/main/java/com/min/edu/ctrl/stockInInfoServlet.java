package com.min.edu.ctrl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.min.edu.dto.ProductInfoDto;
import com.min.edu.dto.StockDto;
import com.min.edu.repository.IStockInDao;
import com.min.edu.repository.ProductInfoDao;
import com.min.edu.repository.ProductInfoDaoImpl;
import com.min.edu.repository.StockInDaoImpl;

public class stockInInfoServlet extends HttpServlet {

	private static final long serialVersionUID = 5819367676274250954L;
	private IStockInDao dao = new StockInDaoImpl();
	private ProductInfoDao productDao = new ProductInfoDaoImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String id = req.getParameter("id");
		System.out.println("idid"+id);
		StockDto stock = dao.stockInDetail(Integer.parseInt(id));
		System.out.println("찾은 stock : "+stock);
		ProductInfoDto product = productDao.product_select2(stock.getProduct_id());
		req.setAttribute("product", product);
		req.setAttribute("stockIn", stock);
		req.getRequestDispatcher("/WEB-INF/views/stockInInfoServlet.jsp").forward(req, resp);
	}
}
