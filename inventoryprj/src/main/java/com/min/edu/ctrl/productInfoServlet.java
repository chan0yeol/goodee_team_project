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

public class productInfoServlet extends HttpServlet {

	private static final long serialVersionUID = -2523457890996085543L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		ProductInfoDao dao = new ProductInfoDaoImpl();
		ProductInfoDto dto = dao.product_select2(id);
		
		if(dto != null) {
			req.setAttribute("productDto", dto);
			req.getRequestDispatcher("/WEB-INF/views/productInfo.jsp").forward(req, resp); 
		}
		else {
			resp.getWriter().write("조회안됨");
		}
	}
	
}
