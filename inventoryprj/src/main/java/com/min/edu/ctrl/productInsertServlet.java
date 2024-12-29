package com.min.edu.ctrl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.min.edu.dto.ProductInfoDto;
import com.min.edu.repository.ProductInfoDao;
import com.min.edu.repository.ProductInfoDaoImpl;

public class productInsertServlet extends HttpServlet {

	private static final long serialVersionUID = -7303824575715046299L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		ProductInfoDao dao = new ProductInfoDaoImpl();
		List<ProductInfoDto> dto = dao.product_select();
		
		req.setAttribute("productInfo", dto);
		req.getRequestDispatcher("/WEB-INF/views/productInsertServlet.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		ProductInfoDto productInfo = (ProductInfoDto)session.getAttribute("productInfoDto");
		
		String product_name = req.getParameter("product_name");
		String product_maker = req.getParameter("product_maker");
		String original_cost = req.getParameter("original_cost");
		String product_amount = req.getParameter("product_amount");
		
		ProductInfoDto dto = new ProductInfoDto().builder()
					.product_name(product_name)
					.product_maker(product_maker)
					.original_cost(Integer.parseInt(original_cost))
					.product_amount(Integer.parseInt(product_amount))
					.build();
		
		ProductInfoDao dao = new ProductInfoDaoImpl();
		int cnt = dao.product_insert(dto);
		
		if(cnt == 1) {
			resp.sendRedirect("./productAllInfo.do");
		}
		else {
			resp.getWriter().print("<script>alert('제품등록에 실패했습니다.); location.href='./productInsertServlet.do'</script>");
		}
		
	}
}
