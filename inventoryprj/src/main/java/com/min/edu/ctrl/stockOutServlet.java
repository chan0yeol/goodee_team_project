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
import com.min.edu.repository.IStockOutDao;
import com.min.edu.repository.ProductInfoDao;
import com.min.edu.repository.ProductInfoDaoImpl;
import com.min.edu.repository.StockInDaoImpl;
import com.min.edu.repository.StockOutDaoImpl;

public class stockOutServlet extends HttpServlet {

	private static final long serialVersionUID = -1774239864183231875L;
	private ProductInfoDao dao = new ProductInfoDaoImpl();
	private IStockOutDao stockOutDao = new StockOutDaoImpl(); 
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<ProductInfoDto> lists = dao.product_select();
		req.setAttribute("productList", lists);
		req.getRequestDispatcher("/WEB-INF/views/stockOutServlet.jsp").forward(req, resp);
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
			row = stockOutDao.insertStockOut(inDto);
		} else {
			StockDto inDto = StockDto.builder()
					.stock_amount(Integer.parseInt(amount))
					.stock_mgr(loginDto.getEmpno())
					.is_accepted("N")
					.product_id(product_id).build();
			row = stockOutDao.insertStockOut(inDto);
		}
		if(row == 1) {
			resp.sendRedirect("./stockOutAllInfo.do");
		} else {
			resp.getWriter().print("<script>alert('등록요청실패');location.href='./stockOutAllInfo.do'</script>");
		}
	}
	
}
