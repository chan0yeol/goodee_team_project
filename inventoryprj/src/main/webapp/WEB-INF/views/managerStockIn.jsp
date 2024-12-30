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
	<h1>관리자페이지</h1>
		<%
			if(s.getEmpno() == 60 && s.getJob().equals("팀장")) {
				%> <h2><a href="./ManagerStockIn.do">관리자페이지 이동</a> </h2><%
			}
		%>
		
		<div class="position-absolute top-0 end-0">
		
				<input type="number" name="mgr" id="mgr" placeholder="사원번호입력" required="required">
				<button>검색</button>
			
		</div>
		<% 
			if(lists.size() == 0) {
			%> <h2>정보가 없습니다.</h2><%		
			} else{
				%><h2>승인대기목록</h2>
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
					%>
					
						<tr>
							<td>
								<input type="checkbox" name="" id="">
							</td><a href="./stockInInfo.do?id=<%= dto.getStock_id()%>"></a>
							<td onclick="location.href='./stockInInfo.do?id='+<%= dto.getStock_id()%>"><%= dto.getStock_id()%></td>
							<td><%= dto.getProduct_id()%></td>
							<td><%= dto.getStock_mgr()%></td>
							<td><%= dto.getStock_amount()%></td>
							<td><%= dto.getStock_date() %></td>
							<td>
								<form action="./ManagerAccept.do" method="post">
									<input type="hidden" value="<%=dto.getStock_id()%>" name="id">
									<button type="submit" value="<%=dto.getStock_id()%>">승인</button>
								</form>
							</td>
						</tr>
					
					 <%
				}
			%>
		</table>
	</div>
</main>
</body>
</html>