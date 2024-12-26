<%@page import="com.min.edu.dto.EmpDto"%>
<%@page import="com.min.edu.dto.StockDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="header.jsp"/>
<%
	Object obj = request.getAttribute("StockInList");
	// List<StockInDto> lists = (List<StockInDto>)obj;
	List<StockDto> lists = (List<StockDto>)obj; 
	EmpDto s = (EmpDto)session.getAttribute("emp");
	
%>
<h1><%= s.getEname() %></h1>
<main>
	<div class="container">
		<%
			if(s.getDeptno() == 60) {
				%> 
					<div class="position-absolute top-0 end-0">
						<a href="./stockInServlet.do" class="btn btn-danger">제품 등록</a>
					</div>
				<%
			}
		%>
		
		<h2>StockInAll</h2>
		<table>
			<tr>	
				<th></th>
				<th>ID</th>
				<th>제품아이디</th>
				<th>담당자</th>
				<th>수량</th>
				<th>날짜</th>
				<th></th>
			</tr>
			<%
				for(StockDto dto : lists) {
					%> <tr>
						<td>
							<input type="checkbox" name="" id="">
						</td>
						<td><%= dto.getStock_id()%></td>
						<td><%= dto.getProduct_id()%></td>
						<td><%= dto.getStock_mgr()%></td>
						<td><%= dto.getStock_amount()%></td>
						<td><%= dto.getStock_date() %></td>
						<td>
							<form action="./stockInDelete" method="DELETE">
								<input type="submit" value="<%=dto.getStock_id()%>">
							</form>
						</td>
					</tr> <%
				}
			%>
		</table>
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