package com.saeyan.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.saeyan.dao.ProductDAO;
import com.saeyan.dto.ProductVO;


@WebServlet("/productUpdate.do")
public class ProductUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ProductUpdateServlet() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//1. code값 가져오기
		String code = request.getParameter("code");
		
		//2. ProductDAO 통해서 code값 전체 가져오기
		ProductDAO pdao = ProductDAO.getInstance();
		
		ProductVO vo  = pdao.selectProductByCode(code);
		
		//3. request.setAttbute()저장
		request.setAttribute("product", vo);
		
		//4. forword(productUpdate.jsp) 이동
		request.getRequestDispatcher("product/productUpdate.jsp")
		.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
