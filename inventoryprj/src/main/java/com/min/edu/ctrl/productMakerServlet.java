package com.min.edu.ctrl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.min.edu.dto.ProductInfoDto;
import com.min.edu.repository.ProductInfoDao;
import com.min.edu.repository.ProductInfoDaoImpl;

public class productMakerServlet extends HttpServlet {

	private static final long serialVersionUID = -5461045864200749521L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String maker = req.getParameter("maker");
		ProductInfoDao dao = new ProductInfoDaoImpl();
		List<ProductInfoDto> dto = dao.product_select3(maker);
		
		if(dto != null) {
			req.setAttribute("productDto", dto);
			req.getRequestDispatcher("/WEB-INF/views/productMaker.jsp").forward(req, resp); 
		}
		else {
			resp.getWriter().write("조회안됨");
		}
	}
}
