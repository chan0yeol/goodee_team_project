<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="./header.jsp"%>
</head>
<body>
	<h1>
		<c:if test="${fail ne null }">
	  		${fail}
  		</c:if>
	</h1>
	<main class="form-signin w-100 m-auto">
		<div class="container">
			<form action="./login.do" method="post">
				<h1 class="h3 mb-3 fw-normal">Login</h1>

				<div class="form-floating mb-1">
					<input type="text" name="ename" class="form-control"
						id="floatingInput" placeholder="이름"> <label
						for="floatingInput">이름</label>
				</div>
				<div class="form-floating mb-3">
					<input type="password" name="empno" class="form-control"
						id="floatingPassword" placeholder="사원번호"> <label
						for="floatingPassword">사원번호</label>
				</div>

				<button class="btn btn-primary w-100 py-2" type="submit">Login</button>
			</form>
		</div>
	</main>
</body>
<script type="text/javascript">
document.forms[0].addEventListener('submit',(event) => {
	event.preventDefault();
	let eno = document.getElementsByName('empno')[0].value;
	if(isNaN(eno)){
		Swal.fire('사원번호는 숫자만 입력하세요');
	} else{
		let empno = document.getElementsByName('empno')[0].value;
		let ename = document.getElementsByName('ename')[0].value;
		console.log(empno,ename);
		fetch("./login.do", {
			method:"POST",
			headers:{'Content-Type':'application/x-www-form-urlencoded'},
			body: new URLSearchParams({
				empno : empno,
				ename : ename
			})
		})
		.then((response) => response.json())
		.then((result) => {
			console.log(result.isc);
			console.log(result);
			if(result.isc === "true"){
				Swal.fire("로그인 성공").then(()=>{
					location.href=result.url;
				});
			} else{
				Swal.fire("로그인 실패");
			}
			})
		.catch((error) => console.log('Error:', error));				
	}
});
</script>
</html>