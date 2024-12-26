package com.min.edu.ctrl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.min.edu.dto.ProductInfoDto;
import com.min.edu.repository.ProductInfoDao;
import com.min.edu.repository.ProductInfoDaoImpl;

public class productAllInfoServlet extends HttpServlet {

	private static final long serialVersionUID = -187090462902976359L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		ProductInfoDto productDto =(ProductInfoDto)session.getAttribute("productDto");
		ProductInfoDao dao = new ProductInfoDaoImpl();
		List<ProductInfoDto> dto = dao.product_select();
		
		req.setAttribute("productInfo", dto);
		req.getRequestDispatcher("/WEB-INF/views/productAllInfo.jsp").forward(req, resp); 
		
		
	}
}
