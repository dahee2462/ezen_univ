package student.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import student.dao.MainDAO;
import vo.BoardVO;
import vo.StudentVO;


public class MainController {
	public void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//sno
		String sno = (String)request.getSession().getAttribute("no");
		//세션이 없을경우 화면 초기화
		if(sno == null || (sno != null && sno.equals(""))) {
			response.sendRedirect(request.getContextPath());
		} else {
			MainDAO mainDAO = new MainDAO();
		
			//수강과목 목록
			List<Map<String, Object>> courseList = mainDAO.selectCourseAll(sno);
			request.setAttribute("courseList", courseList);
			//공지사항 목록
			List<BoardVO> board = mainDAO.FindBoard();
			request.setAttribute("board",board);
				
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/student/stuMain.jsp");
			rd.forward(request, response);
		}
	}
	public void doPostAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/student/stuMain.jsp");
		rd.forward(request, response);
	}
	
}
