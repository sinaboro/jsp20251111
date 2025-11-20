package com.saeyan.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.saeyan.dao.BoardDAO;
import com.saeyan.dto.BoardVO;

public class BoardWriteAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 값 가져오기 단계
	 	String name = request.getParameter("name");
		String pass = request.getParameter("pass");
		String email = request.getParameter("email");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		BoardVO vo = new BoardVO();
		vo.setName(name);
		vo.setPass(pass);
		vo.setEmail(email);
		vo.setTitle(title);
		vo.setContent(content);
		
		//2. DB저장하는 단계....DAO통해서
		BoardDAO dao = BoardDAO.getInstance();
		
		dao.insertBoard(vo);
		
		//3. 화면 전환 ??? 왜?? 도배 방지
		// Post->Redirect-> Get(PRG 패턴)
		
		response.sendRedirect("BoardServlet?command=board_list");
		
//		new BoardListAction().execute(request, response);
	}

}
