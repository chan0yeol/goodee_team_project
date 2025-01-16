<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>입고정보</title>
<link rel="stylesheet" href="https://code.jquery.com/ui/1.14.1/themes/base/jquery-ui.css">
  <script src="https://code.jquery.com/jquery-3.7.1.js"></script>
  <script src="https://code.jquery.com/ui/1.14.1/jquery-ui.js"></script>
  <script>
  $( function() {
    $( "#datepicker1" ).datepicker({
    	dateFormat :'yy-mm-dd'
    });
    $( "#datepicker2" ).datepicker({
    	dateFormat :'yy-mm-dd'
    });
  } );
  
  </script>
<%@ include file="./header.jsp" %>
</head>
<body>
	<h1>${emp.ename }</h1>
	<main>
		<div class="container">
			<c:if test="${emp.deptno eq 60 && emp.job eq '팀장'}">
				<h2>
					<a href="./ManagerStockIn.do">관리자페이지 이동</a>
				</h2>
			</c:if>

			<!-- Button trigger modal -->
			<button type="button" class="btn border" data-bs-toggle="modal" data-bs-target="#exampleModal">
			  기간조회
			</button>
			
			<!-- Modal -->
			<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
			  <div class="modal-dialog">
			    <div class="modal-content">
			      <div class="modal-header">
			        <h1 class="modal-title fs-5" id="exampleModalLabel">Modal title</h1>
			        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
			      </div>
			      <div class="modal-body">
			        <p>조회 시작일: <input type="text" id="datepicker1" name="start"></p>
			        <p>조회 종료일: <input type="text" id="datepicker2" name="end"></p>
			      </div>
			      <div class="modal-footer">
			        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
			        <button id="dateRangeSelect" type="button" class="btn btn-primary">조회하기</button>
			      </div>
			    </div>
			  </div>
			</div>
			
			
			<div class="position-absolute top-0 end-0">
				<c:if test="${emp.deptno eq 60}">
					<a href="./stockInForm.do" class="btn btn-danger">입고 등록</a>
				</c:if>

				<input type="text" name="mgr" id="mgr" placeholder="사원번호입력"
					required="required">
				<button>검색</button>

			</div>
			<fieldset>
				<legend>기간조회</legend>
				<div id="rDiv">
				</div>
			</fieldset>
			<c:choose>
				<c:when test="${fn:length(StockInList) eq 0 }">
					<h2>정보가 없습니다.</h2>
				</c:when>
				<c:otherwise>
					<h2>입고목록</h2>
					<table class="table">
						<tr>
							<!--  <th></th>-->
							<th>ID</th>
							<th>제품아이디</th>
							<th>담당자</th>
							<th>수량</th>
							<th>날짜</th>
							<th></th>
						</tr>
						<c:forEach items="${StockInList}" var="dto" varStatus="vs">
							<tr>
								<td><a href="./stockInInfo.do?id=${dto.stock_id}">${dto.stock_id}</a></td>
								<td>${dto.product_id}</td>
								<td>${dto.stock_mgr}</td>
								<td>${dto.stock_amount}</td>
								<td>${dto.stock_date}</td>
								<td>
									<form action="./stockInDelete" method="get">
										<input type="submit" value="${dto.stock_id}">
									</form>
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
	document.querySelector("div.position-absolute>button").addEventListener('click', () => {
		var mgr = document.getElementById("mgr").value;
		console.log(mgr);
		//alert(mgr);
		if(isNaN(mgr)|| mgr.length == 0) {
			isNaN(mgr)?Swal.fire('사원번호는 숫자입니다.'):Swal.fire('사원번호를 입력하세요');
		}else{
			location.href='./stockAllInfoMgr.do?mgr='+mgr;	
		}
		
	});
	document.getElementById('dateRangeSelect').addEventListener('click', (event) => {
		event.preventDefault();
		var start = document.querySelector('input[name=start]').value;
		var end = document.querySelector('input[name=end]').value;
		console.log("클릭", start, end);
		fetch('./selectDateRange.do',{
			method:"POST",
			headers: {
				'Content-Type':'application/x-www-form-urlencoded'
			},
			body: new URLSearchParams({
				start : start,
				end : end
			})
		})
		.then((response) =>response.json())
		.then((msg) => {
			console.log(typeof msg, msg);
			let rDiv = document.getElementById('rDiv');
			let table = document.createElement("table");
			table.className = "table";
			msg.forEach((value,index) =>{
				var tr = document.createElement("tr");
				var stockIdTd = document.createElement("td");
				stockIdTd.textContent = value.stock_id;
				tr.appendChild(stockIdTd);
				var productIdTd = document.createElement("td");
				productIdTd.textContent = value.product_id;
				tr.appendChild(productIdTd);
				var mgrTd = document.createElement("td");
				mgrTd.textContent = value.stock_mgr;
				tr.appendChild(mgrTd);
				var costTd = document.createElement("td");
				costTd.textContent = value.stock_cost;
				tr.appendChild(costTd);
				var dateTd = document.createElement("td");
				dateTd.textContent = value.stock_date;
				tr.appendChild(dateTd);
				
				table.appendChild(tr);
			});
			rDiv.appendChild(table);
// 			rDiv.textContent = msg[0].stock_id;
// 			rDiv.textContent = JSON.stringify(msg);
		})
		.catch((error) => { alert("잘못된 요청")});
	})
</script>
</html>