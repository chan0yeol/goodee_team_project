<%@page import="com.min.edu.dto.EmpDto"%>
<%@page import="com.min.edu.dto.StockDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="header.jsp"/>
<%
	StockDto stock = (StockDto)request.getAttribute("stock");
	EmpDto s = (EmpDto)session.getAttribute("emp");
%>
<main>
	<div class="container">
		<h2>입고 수정</h2>
		<form action="./stockInUpdate.do" method="post">
			<label for="id">입고아이디</label>
			<input class="form-control mb-3" type="text" id="id"  readonly="readonly" disabled value="<%=stock.getStock_id()%>">
			<input type="hidden" name="id" value="<%=stock.getStock_id()%>">
			<label for="mgr">담당자</label>
			<input class="form-control mb-3" type="text" id="mgr" readonly="readonly" disabled value="<%=stock.getStock_mgr()%>">
			<label for="cost">판매가</label>
			<input class="form-control mb-3" type="text" id="cost" readonly="readonly" disabled value="<%=stock.getStock_cost()%>">
			<label for="amount">수량</label>
			<input class="form-control mb-3" type="text" id="amount" required="required" name="amount" value="<%=stock.getStock_amount()%>">
			<button type="submit">수정</button>
			<button onclick="location.href='./stockInInfo.do?id=<%=stock.getStock_id()%>'">뒤로가기</button>
		</form>
		
		
		
	</div>
	
</main>
</body>
</html> 