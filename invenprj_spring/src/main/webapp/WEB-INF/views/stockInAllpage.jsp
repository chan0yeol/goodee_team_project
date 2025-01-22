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
    	dateFormat :'yy-mm-dd',
    });
    $( "#datepicker2" ).datepicker({
    	dateFormat :'yy-mm-dd',
    });
  });
  
  </script>
<%@ include file="./header.jsp" %>
</head>
<body>
	<h1>${emp.ename }</h1>
	<main>
		<div class="container">
			<c:if test="${emp.deptno eq 60 && emp.job eq '팀장'}">
				<h2>
					<a href="./managerStockIn.do">관리자페이지 이동</a>
				</h2>
			</c:if>

			<!-- modal Button -->
			<button type="button" id="rangeBtn" class="btn border" data-bs-toggle="modal" data-bs-target="#exampleModal">
			  기간조회
			</button>
			
			<!-- modal -->
			<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
			  <div class="modal-dialog">
			    <div class="modal-content">
			      <div class="modal-header">
			        <h1 class="modal-title fs-5" id="exampleModalLabel">기간 조회</h1>
			        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
			      </div>
			      <div class="modal-body">
			      	<div>
				      	<button id="week" class="btn btn-light">1주일</button>
				      	<button id="month" class="btn btn-light">한달</button>
				      	<button id="year" class="btn btn-light">1년</button>
			      	</div>
			      	<div>
	      				<label for="datepicker1">조회 시작일 :</label> 
	      				<input type="text" id="datepicker1" class="form-control" name="start">
			      	</div>
			      	<div>
			      		<label for="datepicker2">조회 시작일 :</label>
			      		<input type="text" id="datepicker2" class="form-control" name="end">
			      	</div>
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
	<div style="text-align:center; margin:0 auto;">
			<ul class="pagination pagination-lg">
				<!-- 앞에 조건에 따른 이동 표시 << <   -->
				<c:if test="${page.page > page.countPage}">
					<li>
						<a href="./stockInAllpage.do?page=1">&lt;&lt;</a>
					</li>
				</c:if>
				<c:if test="${page.page > 1 }">
					<c:choose>
						<c:when test="${(page.stagePage - page.countPage) < 0}">
							<li>
								<a href="./stockInAllpage.do?page=1">&lt;</a>
							</li>	
						</c:when>
						<c:otherwise>
							<li>
 								<a href="./stockInAllpage.do?page=${(page.stagePage - page.countPage)}">&lt;</a>
							</li>	
						</c:otherwise>
					</c:choose>
				</c:if>
				<!-- 페이지 이동 숫자 -->
				  <c:forEach var="i" begin="${page.stagePage}" end="${page.endPage}" step="1">
				  	<li ${i == page.page ?"class='active'":"" }>
				  		<a href="./stockInAllpage.do?page=${i}">${i}</a>
				  	</li>	
				  </c:forEach>
				<!-- 뒤에 조건에 따른 이동 표시 > >>   -->
				<fmt:parseNumber var="num1" integerOnly="true" value="${(page.totalPage-1)/page.countPage}" />
				<fmt:parseNumber var="num2" integerOnly="true" value="${(page.page-1)/page.countPage}"  />
				<c:if test="${num1>num2 }">
					<li>
				  		<a href="./stockInAllpage.do?page=${page.stagePage+page.countPage}">&gt;</a>
				  	</li>	
				</c:if>
				<c:if test="${page.endPage<page.totalPage}">
					<li>
				  		<a href="./stockInAllpage.do?page=${page.totalPage}">&gt;&gt;</a>
				  	</li>	
				</c:if>
			</ul>
		</div>
		<div>
		totalCount:	${page.totalCount}
		</div>
		</div>
	</main>
</body>
<script type="text/javascript">
	window.onload = function() {
		nowDate();
	}
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
	
	function nowDate() {
		// 오늘 날짜 'yyyy-mm-dd'  
		let date = new Date();
		let year = date.getFullYear();
		let month = (date.getMonth()+1).toString().padStart(2,'0');
		let day = date.getDate();
		var nowDate = year+'-'+month+'-'+day;
		document.getElementById('datepicker1').value = nowDate;
		document.getElementById('datepicker2').value = nowDate;
		console.log('nowDate() 실행');
		return nowDate;
	}
	function formatDate(year, mon, date){
		mon = mon.toString().padStart(2,'0');
		date = date.toString().padStart(2,'0');
		return  year+'-'+mon+'-'+date;
	}
	// 1주일 전 
	document.getElementById('week').addEventListener('click',() => {
		var d = new Date();
		document.getElementById('datepicker1').value = formatDate(d.getFullYear(), d.getMonth()+1,d.getDate()-7);
		console.log('week event');
	});
	
	// 1개월 전
	document.getElementById('month').addEventListener('click',() => {
		var d = new Date();
		let mon = d.getMonth() == 0 ? 12:d.getMonth();
		let year = mon == 12?d.getFullYear()-1:d.getFullYear();
		document.getElementById('datepicker1').value = formatDate(year, mon,d.getDate());
	});
	// 1년 전
	
	document.getElementById('year').addEventListener('click',() => {
		var d = new Date();
		document.getElementById('datepicker1').value = formatDate(d.getFullYear()-1, d.getMonth()+1,d.getDate());
	});

	
	
	document.getElementById('dateRangeSelect').addEventListener('click', (event) => {
		event.preventDefault();
		var start = document.querySelector('input[name=start]').value;
		var end = document.querySelector('input[name=end]').value;
		console.log("클릭", start, end);
		console.log("클릭", start.replaceAll('-',''), end.replaceAll('-',''));
		if(start.replaceAll('-','') > end.replaceAll('-','')){
			Swal.fire('시작값이 더 클 수 없어요');
			return;
		} 
		if(start.length == 0 || end.length == 0) {
			Swal.fire('날짜를 선택하세요');
			return;			
		}
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
			var rDiv = document.getElementById('rDiv');
			rDiv.innerHTML ="";
			if(msg.length == 0) {
				Swal.fire('조회값이 없어요');
				return;
			} else{
				// 받은 json 값 Create Dom -----
				
				let table = document.createElement("table");
				table.className = "table";
				let theadTr = document.createElement('tr');
				let theadTd = document.createElement('td');
				theadTd.textContent = "ID";
				theadTr.appendChild(theadTd);
				theadTd = document.createElement('td');
				theadTd.textContent = "제품아이디";
				theadTr.appendChild(theadTd);
				theadTd = document.createElement('td');
				theadTd.textContent = "담당자";
				theadTr.appendChild(theadTd);
				theadTd = document.createElement('td');
				theadTd.textContent = "수량";
				theadTr.appendChild(theadTd);
				theadTd = document.createElement('td');
				theadTd.textContent = "날짜";
				theadTr.appendChild(theadTd);
				table.appendChild(theadTr);
				
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
					var amountTd = document.createElement("td");
					amountTd.textContent = value.stock_amount;
					tr.appendChild(amountTd);
					var dateTd = document.createElement("td");
					dateTd.textContent = value.stock_date;
					tr.appendChild(dateTd);
					
					table.appendChild(tr);
				});
				
				rDiv.appendChild(table);
				// 받은 json 값 Create Dom ----- END -----------------------------
//	 			rDiv.textContent = msg[0].stock_id;
//	 			rDiv.textContent = JSON.stringify(msg);
			}
		})
		.catch((error) => { alert("잘못된 요청")});
	})
</script>
</html>