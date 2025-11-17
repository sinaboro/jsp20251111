package com.saeyan.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.saeyan.dao.MemberDAO;
import com.saeyan.dto.MemberVO;

@WebServlet("/join.do")
public class JoinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public JoinServlet() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("member/join.jsp")
			.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 request.setCharacterEncoding("utf-8");
		
		 String name = request.getParameter("name");
		 String userid = request.getParameter("userid");
		 String pwd = request.getParameter("pwd");
		 String email = request.getParameter("email");
		 String phone = request.getParameter("phone");
		 int admin = Integer.parseInt(request.getParameter("admin"));
		 
		 MemberVO mvo = new MemberVO();
		 mvo.setName(name);
		 mvo.setUserid(userid);
		 mvo.setPwd(pwd);
		 mvo.setEmail(email);
		 mvo.setPhone(phone);
		 mvo.setAdmin(admin);
		 
		 MemberDAO mdao = MemberDAO.getInstance();
		 
		 int result = mdao.insertMember(mvo);
		 
		 HttpSession session = request.getSession();
		 if(result == 1) {
			 request.setAttribute("message", "회원가입에 성공했습니다.");
			 session.setAttribute("userid", userid);
//			 session.setAttribute("userid", mvo.getUserid());
			 
		 }else {
			 request.setAttribute("message", "회원가입에 실패했습니다.");
		 }
		 
		 request.getRequestDispatcher("member/login.jsp")
		 	.forward(request, response);
	}

}
