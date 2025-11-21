package com.saeyan.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.saeyan.dao.BoardDAO;
import com.saeyan.dto.BoardVO;

public class BoardCheckAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url = null;
		
		//1. 입력창 입력한 password
		String num = request.getParameter("num");
		String pass = request.getParameter("pass");
		
		//2. DB에서 조회
		BoardDAO dao = BoardDAO.getInstance();
		
		BoardVO vo = dao.selectOneByNum(Integer.parseInt(num));
		
		//3. 입력받은 것과 DB있는 pass 비교
		if(pass.equals(vo.getPass())) {
			url = "/board/checkSuccess.jsp";
		}else {
			url = "/board/boardCheckPass.jsp";
			request.setAttribute("message", "비밀번호가 틀렸습니다.");
		}
		
		request.getRequestDispatcher(url)
		.forward(request, response);
		
	}

}
