package com.saeyan.contoller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.saeyan.dao.ProductDAO;
import com.saeyan.dto.ProductVO;

public class ProductListAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ProductDAO pdao = ProductDAO.getInstance();
		
		List<ProductVO> list  = pdao.selectAllProuducts();
		
		request.setAttribute("productList", list);
		
		request.getRequestDispatcher("product/productList.jsp")
		.forward(request, response);
	}

}
