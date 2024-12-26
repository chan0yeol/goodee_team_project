<%@page import="com.min.edu.dto.ProductInfoDto"%>
<%@page import="com.min.edu.dto.EmpDto"%>
<%@page import="com.min.edu.dto.StockDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="header.jsp"/>
<%
	EmpDto s = (EmpDto)session.getAttribute("emp");
	List<ProductInfoDto> lists = (List<ProductInfoDto>)request.getAttribute("productList");
%>
<main>
	<div class="container">
		이름 : <%= s.getEname() %> 사원번호 : <%= s.getEname() %> 직급 : <%=s.getJob() %>
		<h3>입고 insert form</h3>
		 <div>
			<form method="post" action="./stockInServlet.do">
				<!-- <input class="form-control" type="text" name="product_id" placeholder="제품번호" required="required"> <br /> -->
				<select name="product_id" class="form-select mb-3">
					<%
						for(ProductInfoDto dto : lists) {
							%> 
								<option value="<%=dto.getProduct_id()%>"><%= dto.getProduct_name() %></option>
							<%
						}
					%>
					<option value=""></option>
				</select>
				<!--  <input type="text" name="stock_mgr" placeholder="담당자사원번호"> <br /> -->
				<input class="form-control" type="number" name="stock_amount" placeholder="수량" required="required"><br />
				
				<input type="submit" value="insert">
			</form>
		</div>
	</div>
	
</main>
</body>
</html> 