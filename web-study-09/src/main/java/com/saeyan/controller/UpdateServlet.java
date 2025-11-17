package com.saeyan.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.saeyan.dao.MemberDAO;
import com.saeyan.dto.MemberVO;

@WebServlet("/memberUpdate.do")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UpdateServlet() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userid = request.getParameter("userid");
		
		MemberDAO mdao = MemberDAO.getInstance();
		
		MemberVO mvo = mdao.getMember(userid);
		
		request.setAttribute("mVo", mvo);
		
		//회원정보 수정폼에 mvo값을 전달
		request.getRequestDispatcher("member/memberUpdate.jsp")
			.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");

		//1. 수정정보 가져오기
		MemberVO mvo = new MemberVO();
		
		mvo.setName(request.getParameter("name"));
		mvo.setUserid(request.getParameter("userid"));
		mvo.setPwd(request.getParameter("pwd"));
		mvo.setEmail(request.getParameter("email"));
		mvo.setPhone(request.getParameter("phone"));
		mvo.setAdmin(Integer.parseInt(request.getParameter("admin")));
		
		//2. 수정정보를 DB저장
		MemberDAO mdao = MemberDAO.getInstance();
		mdao.updateMember(mvo);
		
		//3. 수정이 완료되면 화면전환
		response.sendRedirect("login.do");
	}

}



















