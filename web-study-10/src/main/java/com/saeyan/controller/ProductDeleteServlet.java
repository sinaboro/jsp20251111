package com.saeyan.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.saeyan.dao.ProductDAO;
import com.saeyan.dto.ProductVO;

@WebServlet("/productDelete.do")
public class ProductDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ProductDeleteServlet() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//1. code(기본키) 획득
		String code = request.getParameter("code");
		
		//2. DB에서 code 해당하는 값 삭제
		ProductDAO pdao = ProductDAO.getInstance();
		ProductVO vo = pdao.selectProductByCode(code);
		
		//3. request.setAttubte() 저장
	    request.setAttribute("product", vo);
	    
	    //4. forword
	    request.getRequestDispatcher("product/productDelete.jsp")
	    	.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
