package com.saeyan.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.saeyan.dao.BoardDAO;
import com.saeyan.dto.BoardVO;

public class BoardDetailAction  implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url = "/board/boardDetail.jsp"; 
		
		BoardDAO bdao = BoardDAO.getInstance();
		
		BoardVO vo = bdao.selectOneByNum();
		request.setAttribute("board", vo);
		
		request.getRequestDispatcher(url)
		.forward(request, response);
	}

}
