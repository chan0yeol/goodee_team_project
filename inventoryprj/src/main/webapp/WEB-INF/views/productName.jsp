<%@page import="com.min.edu.dto.ProductInfoDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="productAllInfo.jsp"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>제품 이름별 조회</title>
<style type="text/css">
	.header {
		text-align: center;
		background-color: #FAE0D4;
	}
</style>
</head>
<%
	List<ProductInfoDto> dto = (List<ProductInfoDto>)request.getAttribute("productDto");
%>
<body>
<div class="container">
	<table>
		<thead class="header">
			<tr>
				<th>제품코드</th>
				<th>이름</th>
				<th>제조사</th>
				<th>원가</th>
				<th>수량</th>
				<th>승인여부</th>
			</tr>
		</thead>
		<tbody>
			<%
				for(ProductInfoDto d : dto) {
					%>
						<tr>
							<td><%=d.getProduct_id()%></td>
							<td><%=d.getProduct_name()%></td>
							<td><%=d.getProduct_maker()%></td>
							<td><%=d.getOriginal_cost()%></td>
							<td><%=d.getProduct_amount()%></td>
							<td><%=d.getIs_accepted()%></td>
						</tr>
					<%
				}
			%>
		</tbody>
	</table>
</div>
</body>
</html>