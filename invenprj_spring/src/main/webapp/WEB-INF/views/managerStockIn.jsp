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
	<%
	//Object obj = request.getAttribute("StockInList");
	// List<StockInDto> lists = (List<StockInDto>)obj;
	//List<StockDto> lists = (List<StockDto>) obj;
	//EmpDto s = (EmpDto) session.getAttribute("emp");
	%>
	<h1>${emp.ename}</h1>
	<main>
		<div class="container">
			<h1>관리자페이지</h1>
			<c:if test="${emp.deptno eq 60 && emp.job eq '팀장' }">
				<h2>
					<a href="./ManagerStockIn.do">관리자페이지 이동</a>
				</h2>
			</c:if>
			<c:choose>
				<c:when test="${fn:length(StockInList) eq 0 }">
					<h2>정보가 없습니다.</h2>
				</c:when>
				<c:otherwise>
					<h2>승인대기목록</h2>
<!-- 					<form> -->
<!-- 						<button id="allAcceptBtn">전체</button> -->
						<table>
							<tr>
<!-- 								<th></th> -->
								<th>ID</th>
								<th>제품아이디</th>
								<th>담당자</th>
								<th>수량</th>
								<th>날짜</th>
								<th></th>
							</tr>
							<c:forEach var="dto" items="${StockInList}" varStatus="vs">
								<tr>
<%-- 									<td><input type="checkbox" name="chkId" value="${dto.stock_id}"></td> --%>
<%-- 									<a href="./stockInInfo.do?id=${dto.stock_id}">l</a> --%>
									<td
										onclick="location.href='./stockInInfo.do?id='+${dto.stock_id}">${dto.stock_id}</td>
									<td>${dto.product_id}</td>
									<td>${dto.stock_mgr}</td>
									<td>${dto.stock_amount}</td>
									<td>${dto.stock_date}</td>
									<td>
	<!-- 									<form action="./managerAccept.do" method="post"> -->
											<input type="hidden" value="${dto.stock_id}" name="id">
											<button type="submit" value="${dto.stock_id}" onclick="acceptBtn(this)">승인</button>
	<!-- 									</form> -->
									</td>
								</tr>
							</c:forEach>
						</table>
<!-- 					</form> -->
				</c:otherwise>
			</c:choose>

		</div>
	</main>
</body>
<script type="text/javascript">
// 	document.forms[0].addEventListener('submit',(event)=>{
// 		event.preventDefault();
// 	} )
// 	allAcceptBtn.addEventListener('click', ()=>{
// 		var chkIds = document.querySelectorAll("input[name=chkId]:checked");
// 		console.log(chkIds);
// 		if(chkIds.length == 0) {
// 			Swal.fire('한개 이상의 값을 선택');
// 		} else {
// 			console.log(chkIds);
// 			var jsonData = {};
// 			chkIds.forEach((value, key) =>{
// 				jsonData[key] = value.value;
// 			});
// 			console.log(jsonData);
// 			var paramData = JSON.stringify(jsonData);
// 			console.log(paramData);
			
// 			fetch('./managerAccept.do',{
// 				method:"POST",
// 				headers:{
// 					"Content-Type": "application/json"
// 				},
// 				body: paramData
// 			}).then(response => {
// 				console.log(response);
// 			}).then(data => {
// 				console.log(data);
// 			}).catch(error => {
// 				console.log(error);
// 			});
			
			
// 		}

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
// 	acceptBtns.addEventListener('submit',(event) => {
// 		event.preventDefault();
// 		let stock_id = event.target.children[0].value;
// 		console.log(stock_id);		
		
// 	})
</script>
</html>