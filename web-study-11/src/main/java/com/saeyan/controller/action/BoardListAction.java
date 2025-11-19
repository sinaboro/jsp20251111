package com.saeyan.controller.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.saeyan.dao.BoardDAO;
import com.saeyan.dto.BoardVO;

public class BoardListAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 이동할 JSP 페이지 경로를 문자열로 지정함(게시판 목록 화면)
		String url = "/board/boardList.jsp";  

		// BoardDAO 객체를 싱글톤 방식으로 불러옴(DAO 이용 준비)
		BoardDAO bdao = BoardDAO.getInstance();  

		// 게시판의 모든 글 목록을 DB에서 조회하여 리스트로 가져옴
		List<BoardVO> list = bdao.selectAllBoards();  

		// 조회한 게시글 목록을 request 영역에 저장하여 JSP에서 사용 가능하게 함
		request.setAttribute("boardList", list);  

		// 지정한 JSP 페이지로 request와 response를 전달하여 화면을 이동함(서버 내부 이동, URL 변화 없음)
		request.getRequestDispatcher(url)
		.forward(request, response);

		
	}

}
