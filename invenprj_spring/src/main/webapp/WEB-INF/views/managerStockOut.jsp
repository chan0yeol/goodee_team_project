<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자페이지</title>
<%@ include file="./header.jsp"%>
</head>
<body>
123123
	<h1>${emp.ename}</h1>
	<main>
		<div class="container">
			<h1>관리자페이지</h1>
			<c:choose>
				<c:when test="${fn:length(StockOutList) eq 0 }">
					<h2>정보가 없습니다.</h2>
				</c:when>
				<c:otherwise>
					<h2>승인대기목록</h2>
					<table>
						<tr>
							<th>ID</th>
							<th>제품아이디</th>
							<th>담당자</th>
							<th>수량</th>
							<th>날짜</th>
							<th></th>
						</tr>
						<c:forEach var="dto" items="${StockOutList}" varStatus="vs">
							<tr>
<%-- 									<a href="./stockOutInfo.do?id=${dto.stock_id}">l</a> --%>
								<td
									onclick="location.href='./stockOutInfo.do?id='+${dto.stock_id}">${dto.stock_id}</td>
								<td>${dto.product_id}</td>
								<td>${dto.stock_mgr}</td>
								<td>${dto.stock_amount}</td>
								<td>${dto.stock_date}</td>
								<td>
<!-- 									<form action="./managerAccept.do" method="post"> -->
										<input type="hidden" value="${dto.stock_id}" name="id">
										<button type="button" onclick="acceptBtn(this)"value="${dto.stock_id}">승인</button>
<!-- 									</form> -->
								</td>
							</tr>
						</c:forEach>
					</table>
				</c:otherwise>
			</c:choose>

		</div>
	</main>
</body>
<script type="text/javascript">
// 	document.forms[0].addEventListener('submit',(event) => {
// 		event.preventDefault();
// 	});
	function acceptBtn(val){
		console.log(val.value);
		var id = val.value;
		console.log(id);
		fetch('./managerAccept.do?id='+id)
		.then((response) => response.text())
		.then((data) => {
			console.log(data);
			console.log(val.closest('tr'));
			if(data === 'true') {
				val.closest('tr').remove();
			}
		})
		.catch(error => alert('잘못된 요청'));
	}
</script>
</html>