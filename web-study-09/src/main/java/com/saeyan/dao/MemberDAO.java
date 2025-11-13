package com.saeyan.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
 * DB연결을 담당하는 클래스
 * 싱글톤으로 객체 하나만 생성해서 공유
 */
public class MemberDAO {

	//1. 싱글톤 패턴 : 객체를 하나만 생성
	private static MemberDAO instance = new MemberDAO();
	
	//2. 외부에서 생성자 호출 못하도록 private 
	private MemberDAO() {
	}
	
	//3. 외부에서 사용할 수 있도록 단일 인스턴스 제공
	public static MemberDAO getInstance() {
		return instance;
	}
	
	//4. DB 연결 메소드(JDBC)
	public Connection getConnection() {
		
		Connection con = null;
		
		try {
			//드라이브 로드
			Class.forName("oracle.jdbc.driver.OracleDriver"); 
			
			// 연결 정보
			con = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe",
					"test",
					"1234"
					); 
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return con;
	}
	
	//사용자 인증시 사용하는 메소드
	public int userCheck(String userid, String pwd) {
		int result = -1;
		
		String sql = "select pwd from member where userid =  ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = getConnection();  //디비연결
			pstmt = con.prepareStatement(sql); //sql 구문 전송.. sql 에러있는 없니 체크?
			pstmt.setString(1, userid);
			
			rs = pstmt.executeQuery();  //실행 및 결과 반환
			
			if(rs.next()) {
				//가져올 데이타 있니?
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
				con.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return 0;
	}
	
}
