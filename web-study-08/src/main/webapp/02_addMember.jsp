<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%!
	Connection con = null;
	PreparedStatement pstmt = null;
		
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String uid = "test";
	String pass = "1234";
	
	String sql = "insert into member values(?, ?, ?, ?, ?, ?)";
%>    

<html>
	<body>
<%
	//한글처리-깨짐 방지
	request.setCharacterEncoding("utf-8");


	//addMemberForm.jsp 입력한 내용을 가져옴
	String name = request.getParameter("name");
	String userid = request.getParameter("userid");
	String pwd = request.getParameter("pwd");
	String email = request.getParameter("email");
	String phone = request.getParameter("phone");
	int admin = Integer.parseInt(request.getParameter("admin"));
	
	try{
		
		//1. 드라이브 로드
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		//2. 데이타베이스 연결
		con = DriverManager.getConnection(url,uid,pass);
		//out.println(con);
		
		//3. SQL 구문을 실행할 PrepareStatement 객체 생성
		pstmt = con.prepareStatement(sql);
		
		//4. 폼에서 입력한 것을 values(?) 맵핑
		pstmt.setString(1, name); // 문자 타입일 경우 setString 사용
		pstmt.setString(2, userid);
		pstmt.setString(3, pwd);
		pstmt.setString(4, email);
		pstmt.setString(5, phone);
		pstmt.setInt(6, admin);  // 정수 타입일 경우 setInt 사용
		
		//5. 실행
		int result = pstmt.executeUpdate();
		out.println(result);
		
	}catch(Exception e){
		e.printStackTrace();
	}finally{
		if(pstmt != null) pstmt.close();
		if(con != null) con.close();
	}
	
%>
	<h3>회원 가입 성공</h3>
	<a href="01_allMember.jsp">회원 전체 목록 보기</a>
	
	</body>
</html>

