<%@page import="com.min.edu.dto.StockDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="header.jsp"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>재고조회 게시판</title>
</head>
<%
	List<StockDto> stockDto = (List<StockDto>)request.getAttribute("stockDto");
%>
<body>
<div class="container">
	<table>
		<thead class="header">
			<tr>
				<th>제품코드</th>
				<th>입고수량</th>
				<th>출고수량</th>
				<th>재고</th>
			</tr>
		</thead>
		<tbody>
			<%
				for(StockDto dto : stockDto) {
					int 입고수량=0;
					int 출고수량=0;
					
					if("I".equals(dto.getGubun())) {
						입고수량 = dto.getStock_amount();
					}
					else if("O".equals(dto.getGubun())) {
						출고수량 = dto.getStock_amount();
					}
					
					int 재고 = 입고수량-출고수량;
					
					%>
						<tr>
							<td><%=dto.getProduct_id()%></td>
							<td><%=입고수량%></td>
							<td><%=출고수량%></td>
							<td><%=재고%></td>
						</tr>
					<%
				}
			%>
		</tbody>
	</table>
</div>
</body>
</html>