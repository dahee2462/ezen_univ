package professor.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import professor.dao.MainDAO;
import vo.BoardVO;
import vo.LectureVO;

public class MainController {
	public void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String pno = (String)request.getSession().getAttribute("no");
		
		MainDAO mainDAO = new MainDAO();
		
		List<LectureVO> lecture = mainDAO.pnoFindLecture(pno);
		request.setAttribute("lecture",lecture);
		
		List<BoardVO> board = mainDAO.FindBoard();
		request.setAttribute("board",board);
		
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/professor/profMain.jsp");
		rd.forward(request, response);
		
		
	}
	public void doPostAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/professor/profMain.jsp");
		rd.forward(request, response);
	}
}
