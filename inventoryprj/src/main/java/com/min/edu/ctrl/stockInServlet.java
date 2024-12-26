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
import com.min.edu.dto.StockDto;
import com.min.edu.repository.IStockInDao;
import com.min.edu.repository.ProductInfoDao;
import com.min.edu.repository.ProductInfoDaoImpl;
import com.min.edu.repository.StockInDaoImpl;

public class stockInServlet extends HttpServlet {

	private static final long serialVersionUID = 3033241611205633538L;
	private IStockInDao stockInDao = new StockInDaoImpl();
	private	ProductInfoDao productDao = new ProductInfoDaoImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<ProductInfoDto> lists = productDao.product_select();
		req.setAttribute("productList", lists);
		req.getRequestDispatcher("/WEB-INF/views/stockInServlet.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8;");
		HttpSession session = req.getSession();
		EmpDto loginDto = (EmpDto) session.getAttribute("emp");
		String product_id = req.getParameter("product_id");
		String amount = req.getParameter("stock_amount");
		int row;
		if(loginDto.getJob().equals("팀장")) {
			StockDto inDto = StockDto.builder()
					.stock_amount(Integer.parseInt(amount))
					.stock_mgr(loginDto.getEmpno())
					.is_accepted("Y")
					.product_id(product_id).build();
			row = stockInDao.insert(inDto);
		} else {
			StockDto inDto = StockDto.builder()
					.stock_amount(Integer.parseInt(amount))
					.stock_mgr(loginDto.getEmpno())
					.is_accepted("N")
					.product_id(product_id).build();
			row = stockInDao.insert(inDto);
		}
		
		 
		if(row == 1) {
			resp.sendRedirect("./stockAllInfo.do");
		} else {
			resp.getWriter().print("<script>alert('등록요청실패');location.href='./stockInServlet.do'</script>");
		}
	}
}
