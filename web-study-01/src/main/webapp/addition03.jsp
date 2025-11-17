<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		Object num1 = request.getAttribute("n1");
		Object num2 = request.getAttribute("n2");
		
		int add =  (Integer)request.getAttribute("sum");
	%>
	
	<%= num1 %> + <%=num2 %> = <%= add %>
</body>
</html>