package com.saeyan.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.saeyan.dao.MemberDAO;

@WebServlet("/idCheck.do")
public class IdCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public IdCheckServlet() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//userid 받아서, DB에서 같은 userid있는 지 체크해서 있으면 중복,
		//없으면 사용 가능
		String userid = request.getParameter("userid");
		
		MemberDAO mdao = MemberDAO.getInstance();
		
		int result = mdao.confirmID(userid);
		
		request.setAttribute("userid", userid);
		request.setAttribute("result", result);
		
		request.getRequestDispatcher("member/idCheck.jsp")
			.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
