<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="./header.jsp" %>
</head>
<body>
<%
// 	EmpDto s = (EmpDto)session.getAttribute("emp");
// 	List<ProductInfoDto> lists = (List<ProductInfoDto>)request.getAttribute("productList");
%>
<main>
	<div class="container">
		이름 : ${emp.ename} 사원번호 : ${emp.empno} 직급 : ${emp.job}
		<h3>입고 insert form</h3>
		 <div>
			<form method="post" action="./stockInInsert.do">
				<!-- <input class="form-control" type="text" name="product_id" placeholder="제품번호" required="required"> <br /> -->
				<select name="product_id" class="form-select mb-3">
					<c:forEach items="${productList}" var="dto" varStatus="vs">
						<option value="${dto.product_id}">${dto.product_name}</option>
					</c:forEach>
				</select>
				<!--  <input type="text" name="stock_mgr" placeholder="담당자사원번호"> <br /> -->
				<input class="form-control" type="text" name="stock_amount" placeholder="수량"><br />
				<input type="submit" value="insert">
			</form>
		</div>
	</div>
	
</main>
</body>
<script type="text/javascript">
	console.log(document.forms[0]);
	document.forms[0].addEventListener('submit', (event) => {
		event.preventDefault();
		let amount = document.getElementsByName('stock_amount')[0].value;
		Number(amount);
		console.log(isNaN(amount));
		
		if(isNaN(amount) || amount==='') {
			Swal.fire('수량에는 숫자만 입력 가능합니다.');
		} else{
			if(amount < 0) {
				Swal.fire('수량을 확인하세요');
			} else{
				document.forms[0].method ="POST";
				document.forms[0].action ="./stockInInsert.do";
				document.forms[0].submit();
			}
		}
	});
</script>
</html>