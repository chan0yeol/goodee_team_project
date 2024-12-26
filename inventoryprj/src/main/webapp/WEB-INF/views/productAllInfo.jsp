<%@page import="com.min.edu.dto.ProductInfoDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>제품 정보전체조회</title>
</head>
<%
	List<ProductInfoDto> productInfo = (List<ProductInfoDto>)request.getAttribute("productInfo");
%>
<body>
<form method="post" action="./productInfo.do">
	<div>
		<input type="text" id="id" name="id" placeholder="조회할 제품코드 입력">
		<input type="submit" value="조회">
	</div>
</form>
<form method="post" action="./productMaker.do">
	<div>
		<input type="text" id="maker" name="maker" placeholder="조회할 제품제조사 입력">
		<input type="submit" value="조회">
	</div>
</form>
<form method="post" action="./productMaker.do">
	<div>
		<input type="text" id="name" name="name" placeholder="조회할 제품이름 입력">
		<input type="submit" value="조회">
	</div>
</form>


<div class="container">
	<table class="table table-striped">
		<thead class="table-warning">
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
				for(ProductInfoDto dto : productInfo) {
					%>
						<tr>
							<td><%=dto.getProduct_id()%></td>
							<td><%=dto.getProduct_name()%></td>
							<td><%=dto.getProduct_maker()%></td>
							<td><%=dto.getOriginal_cost()%></td>
							<td><%=dto.getProduct_amount()%></td>
							<td><%=dto.getIs_accepted()%></td>
						</tr>
					<%
				}
			%>
		</tbody>
		<tfoot>
		</tfoot>
	</table>
</div>

</body>
</html>