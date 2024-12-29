<%@page import="com.min.edu.dto.EmpDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="WEB-INF/views/header.jsp"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%
	EmpDto s = (EmpDto)session.getAttribute("emp");
%>
</head>
<body>
	<h1 style="text-align: center;">'<%= s.getEname() %>'님 환영합니다.</h1>
</body>
</html>