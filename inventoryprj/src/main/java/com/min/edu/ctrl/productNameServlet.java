package com.min.edu.ctrl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.min.edu.dto.ProductInfoDto;
import com.min.edu.repository.ProductInfoDao;
import com.min.edu.repository.ProductInfoDaoImpl;

public class productNameServlet extends HttpServlet {

	private static final long serialVersionUID = 3527542150133045621L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		ProductInfoDao dao = new ProductInfoDaoImpl();
		List<ProductInfoDto> dto = dao.product_select4(name);
		
		if(dto != null) {
			req.setAttribute("productDto", dto);
			req.getRequestDispatcher("/WEB-INF/views/productName.jsp").forward(req, resp);
		}
		else {
			resp.getWriter().write("조회안됨");
		}
	}
	
}
