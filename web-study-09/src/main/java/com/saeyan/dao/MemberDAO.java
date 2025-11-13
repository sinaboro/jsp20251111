package com.saeyan.dao;

import java.sql.Connection;
import java.sql.DriverManager;

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
}
