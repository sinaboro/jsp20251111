<%@page import="java.util.ArrayList"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Calendar"%>
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

    //정적메소드
	Calendar date = Calendar.getInstance(); //싱글톤 패턴
	
	SimpleDateFormat today = new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat now = new SimpleDateFormat("hh시 mm분 ss초");	
%>

<%= date.getTime() %><br>
오늘은 <%=today.format(date.getTime()) %> 입니다. <br>
지금 시각은 <b><%=now.format(date.getTime()) %> </b>

</body>
</html>