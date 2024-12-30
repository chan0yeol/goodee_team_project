<%@page import="com.min.edu.dto.EmpDto"%>
<%@page import="com.min.edu.dto.StockDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="header.jsp"/>
<%
	Object obj = request.getAttribute("StockInList");
	// List<StockInDto> lists = (List<StockInDto>)obj;
	List<StockDto> lists = (List<StockDto>)obj; 
	EmpDto s = (EmpDto)session.getAttribute("emp");
	
%>
<h1><%= s.getEname() %></h1>
<main>
	<div class="container">
		<%
			if(s.getEmpno() == 60 && s.getJob().equals("팀장")) {
				%> <h2><a href="./ManagerStockIn.do">관리자페이지 이동</a> </h2><%
			}
		%>
		
		<div class="position-absolute top-0 end-0">
		<%
			if(s.getDeptno() == 60) {
				%> 
						<a href="./stockInServlet.do" class="btn btn-danger">제품 등록</a>
				<%
			}
		%>
				<input type="number" name="mgr" id="mgr" placeholder="사원번호입력" required="required">
				<button>검색</button>
			
		</div>
		<% 
			if(lists.size() == 0) {
			%> <h2>정보가 없습니다.</h2><%		
			} else{
				%><h2>입고목록</h2>
					<table>
						<tr>	
							<th></th>
							<th>ID</th>
							<th>제품아이디</th>
							<th>담당자</th>
							<th>수량</th>
							<th>날짜</th>
							<th></th>
						</tr> 
				<%
			}
		%>		
		
			<%
				for(StockDto dto : lists) {
					%> <tr>
						<td>
							<input type="checkbox" name="" id="">
						</td><a href="./stockInInfo.do?id=<%= dto.getStock_id()%>"></a>
						<td onclick="location.href='./stockInInfo.do?id='+<%= dto.getStock_id()%>"><%= dto.getStock_id()%></td>
						<td><%= dto.getProduct_id()%></td>
						<td><%= dto.getStock_mgr()%></td>
						<td><%= dto.getStock_amount()%></td>
						<td><%= dto.getStock_date() %></td>
						<td>
							<form action="./stockInDelete" method="get">
								<input type="submit" value="<%=dto.getStock_id()%>">
							</form>
						</td>
					</tr> <%
				}
			%>
		</table>
	</div>
</main>
</body>
<script type="text/javascript">
	
	document.querySelector("div.position-absolute>button").addEventListener('click', () => {
		var mgr = document.getElementById("mgr").value;
		console.log(mgr);
		//alert(mgr);
		if(mgr == '') {
			alert('사원번호 입력하세요');
		}else{
			location.href='./stockAllInfo.do?mgr='+mgr;	
		}
		
	})
</script>
</html>