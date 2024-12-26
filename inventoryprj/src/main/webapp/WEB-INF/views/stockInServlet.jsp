<%@page import="com.min.edu.dto.EmpDto"%>
<%@page import="com.min.edu.dto.StockDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="header.jsp"/>
<%
// 	Object obj = request.getAttribute("StockInList");
// 	// List<StockInDto> lists = (List<StockInDto>)obj;
// 	List<StockDto> lists = (List<StockDto>)obj; 
	
%>
<main>
	<div class="container">
		<fieldset>
			<legend>입고 insert form</legend>
			<form method="post" action="./stockInSave">
				<input type="text" name="product_id" placeholder="제품번호"> <br />
				<!--  <input type="text" name="stock_mgr" placeholder="담당자사원번호"> <br /> -->
				<input type="text" name="stock_amount" placeholder="수량"><br />
				
				<input type="submit" value="insert">
			</form>
		</fieldset>
	</div>
</main>
</body>
</html>