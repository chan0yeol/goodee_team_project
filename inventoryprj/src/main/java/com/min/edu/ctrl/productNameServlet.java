package com.min.edu.ctrl;

import java.io.IOException;
<<<<<<< HEAD
import java.util.HashMap;
import java.util.List;
import java.util.Map;
=======
import java.util.List;
>>>>>>> branch 'jiwon' of https://github.com/chan0yeol/goodee_team_project.git

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
<<<<<<< HEAD
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", name);
		
		ProductInfoDao dao = new ProductInfoDaoImpl();
		List<ProductInfoDto> lists = dao.product_select4(map);
		
		if(lists != null) {
			req.setAttribute("productDto", lists);
			req.getRequestDispatcher("/WEB-INF/views/productName.jsp").forward(req, resp);
		}
		else {
			resp.getWriter().write("조회안됨");
		}
=======
		ProductInfoDao dao = new ProductInfoDaoImpl();
		List<ProductInfoDto> dto = dao.product_select4(name);
>>>>>>> branch 'jiwon' of https://github.com/chan0yeol/goodee_team_project.git
		
		if(dto != null) {
			req.setAttribute("productDto", dto);
			req.getRequestDispatcher("/WEB-INF/views/productName.jsp").forward(req, resp);
		}
		else {
			resp.getWriter().write("조회안됨");
		}
	}
	
}
