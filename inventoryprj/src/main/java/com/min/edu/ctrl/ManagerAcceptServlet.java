package com.min.edu.ctrl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.min.edu.dto.StockDto;
import com.min.edu.repository.IManagerDao;
import com.min.edu.repository.IStockInDao;
import com.min.edu.repository.ManagerDaoImpl;
import com.min.edu.repository.StockInDaoImpl;

public class ManagerAcceptServlet extends HttpServlet {

	private static final long serialVersionUID = -5912400475595569390L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		String id = req.getParameter("id");
		IManagerDao dao = new ManagerDaoImpl();
		StockDto updateDto = dao.findByStockId(id);
		updateDto.setIs_accepted("Y");
		
//		StockDto dto = StockDto.builder().stock_id(id).is_accepted("Y").build();
//		int row = dao.updateStockInRequest(dto);
		int row = dao.updateStockOutRequest(updateDto);
		if(row == 1) {
			resp.getWriter().print("<script>alert('성공'); location.href='./ManagerStockIn.do'</script>");
		} else {
			resp.getWriter().print("<script>alert('실패'); location.href='./ManagerStockIn.do'</script>");
		}
		
	}
}
