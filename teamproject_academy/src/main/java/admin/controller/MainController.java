package admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.dao.MainDAO;
import vo.BoardVO;

public class MainController {
	public void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		MainDAO mainDAO = new MainDAO();
		List<BoardVO> mainList = mainDAO.selectAll();
		
		request.setAttribute("mainList", mainList);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/admin/admMain.jsp");
		rd.forward(request, response);
	}
	
	public void doPostAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/admin/admMain.jsp");
		rd.forward(request, response);
	}
}
