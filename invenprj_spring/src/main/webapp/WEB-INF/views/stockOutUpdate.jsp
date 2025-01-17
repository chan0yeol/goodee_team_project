<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>출고 정보 수정</title>
<%@ include file="./header.jsp" %>
</head>
<body>
<main>
	<div class="container">
		<h2>출고 수정</h2>
		<form action="./stockOutUpdate.do" method="post">
			<label for="id">출고아이디</label>
			<input class="form-control mb-3" type="text" id="id"  readonly="readonly" disabled value="${stock.stock_id}">
			<input type="hidden" name="id" value="${stock.stock_id}">
			<label for="mgr">담당자</label>
			<input class="form-control mb-3" type="text" id="mgr" readonly="readonly" disabled value="${stock.stock_mgr}">
			<label for="cost">판매가</label>
			<input class="form-control mb-3" type="text" id="cost" readonly="readonly" disabled value="${stock.stock_cost}">
			<label for="amount">수량</label>
			<input class="form-control mb-3" type="text" id="amount" required="required" name="amount" value="${stock.stock_amount}">
			<button type="submit">수정</button>
			<button onclick="location.href='./stockOutInfo.do?id=${stock.stock_id}'">뒤로가기</button>
		</form>
	</div>
</main>
<script type="text/javascript">
	const frm = document.forms[0];
// 	const frm = document.forms[0][4];
	
	console.log(frm);
	frm.addEventListener('submit',(event) =>{
		event.preventDefault();
		let amount =frm[4].value;
		console.log(amount);
		if(isNaN(amount) || amount.trim().length == 0) {
			Swal.fire('수량을 입력하세요');
		} else if (amount < 0){
				Swal.fire('수량은 0보다 작을 수 없습니다.');			
		} else {
				frm.action = "./stockOutUpdate.do";
				frm.method = "POST";
				frm.submit();
		}
	});
</script>
</body>
</html>