<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/shopping.css">
</head>
<body>
	<div id="wrap" align="center">
		<h1>게시글 리스트</h1>
		<table class="list">
			<tr>
				<td colspan="5" style="border: white; text-align: right">
				<a href="BoardServlet?command=board_write_form">게시글 등록</a></td>
			</tr>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일</th>
				<th>조회</th>
			</tr>
		
			<tr class="record">
				<td>${board.num }</td>
				<td>${board.title }</td>		
				<td>${board.name}</td>
				<td><fmt:formatDate value="${board.writedate }" pattern="yyyy-MM-dd"/></td>
				<td>${board.readcount}</td>
			</tr>
		
		</table>
	</div>
</body>
</html>















