<%@page import="com.min.edu.dto.EmpDto"%>
<%@page import="com.min.edu.dto.ProductInfoDto"%>
<%@page import="com.min.edu.dto.StockDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="header.jsp"/>
<%
	StockDto stock = (StockDto)request.getAttribute("stockIn");
	ProductInfoDto product = (ProductInfoDto)request.getAttribute("product");
	EmpDto s = (EmpDto)session.getAttribute("emp");
%>
<main>
	<div class="container">
		<h2>입고 상세조회</h2>
		<table class="table">
			<tr>
				<th>제품ID</th>
				<th>제품명</th>
				<th>입고ID</th>
				<th>구분</th>
				<th>입고날짜</th>
				<th>수량</th>
				<th>담당자</th>
				<th>판매가</th>
				<th>브랜드</th>
			</tr>
			<tr>
				<td><%= product.getProduct_id() %></td>
				<td><%= product.getProduct_name()%></td>
				<td><%= stock.getStock_id()%></td>
				<td><%= stock.getGubun()%></td>
				<td><%= stock.getStock_date()%></td>
				<td><%= stock.getStock_amount()%></td>
				<td><%= stock.getStock_mgr()%></td>
				<td><%= stock.getStock_cost()%></td>
				<td><%= product.getProduct_maker()%></td>
			</tr>
			<tr>
				<%
					if(stock.getStock_mgr() == s.getEmpno() || s.getJob().equals("팀장")){
						%><button>수정</button><%
					}
				%>
				
				<button onclick="history.back()">뒤로가기</button>
			</tr>
		</table>
	</div>
	
</main>
</body>
</html> 