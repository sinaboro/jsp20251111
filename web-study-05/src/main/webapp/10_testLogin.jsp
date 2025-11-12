<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
//우리 서버 가입한 회원 정보
String id = "test";
String pwd = "test1234";
String name = "홍길동";

String id2 = request.getParameter("id");
String pwd2 = request.getParameter("pwd");

if(id.equals(id2) && pwd.equals(pwd2)){
	session.setAttribute("loginUser", name);
	response.sendRedirect("10_main.jsp");
}else{
	response.sendRedirect("10_loginForm.jsp");
}

%>