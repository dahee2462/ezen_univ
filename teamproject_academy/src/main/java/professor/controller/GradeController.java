package professor.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import professor.dao.AttendDAO;
import professor.dao.GradeDAO;
import vo.CourseVO;
import vo.LectureVO;

public class GradeController {
	public void doAction(String threeUriParam, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		String threeUri = threeUriParam.split("\\.")[0];

		switch(threeUriParam) {
			case "gradeMgList":
				gradeMgList(request,response);
				break;
			case "gradeMgView":
				gradeMgView(request,response);
				break;
		}
		
//		if(threeUri.equals("gradeMgList")) {
//			gradeMgList(request,response);
//		}else if(threeUri.equals("gradeMgView")) {
//			gradeMgView(request,response);			
//		}
		
	}
	public void doPostAction(String threeUriParam, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String threeUri = threeUriParam.split("\\.")[0];

		if(threeUri.equals("gradeMgList")) {
			PostgradeMgList(request,response);
		}else if(threeUri.equals("gradeMgView")) {
			PostgradeMgView(request,response);			
		}
		
	}
	public void gradeMgList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pno = (String)request.getSession().getAttribute("no");
		String lstatus = request.getParameter("lstatus");
		//유효성 검사
		if(lstatus == null) {
			lstatus = "6";
		}
		if(!(lstatus.equals("6")||lstatus.equals("4")||lstatus.equals("5"))) {
			lstatus = "6";
		}
		
		request.setAttribute("lstatus", lstatus);
		GradeDAO gradeDAO = new GradeDAO();
		List<LectureVO> lectureList = gradeDAO.pnoLstatusFindLecture(pno,lstatus);
		
		request.setAttribute("lectureList", lectureList);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/professor/grade/gradeMgList.jsp");
		rd.forward(request, response);
		
	}
	public void PostgradeMgList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/professor/grade/gradeMgList.jsp");
		rd.forward(request, response);
		
		
	}
	public void gradeMgView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// pno도 받아서 쿼리문에서 검증을 해야하지만 시간관계상 보류
		String lno = request.getParameter("lno");
		if(lno == null) {
			response.sendRedirect(request.getContextPath());
			return;
		}
		GradeDAO gradeDAO = new GradeDAO();
		List<CourseVO> courseList = gradeDAO.lnoFindCourse(lno);
		
		request.setAttribute("courseList", courseList);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/professor/grade/gradeMgView.jsp");
		rd.forward(request, response);
		
		
	}
	public void PostgradeMgView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pno = (String)request.getSession().getAttribute("no");
		String cno = request.getParameter("cno");
		String cgrade = request.getParameter("cgrade");
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("UTF-8");
//		cgarde cno 정확한 값만 들어가도록 유효성 검사 미흡 시간관계상 보류
		if(cno == null || cgrade == null) {
			response.getWriter().append("null");
			response.getWriter().flush();
			return;
		}
		
		GradeDAO gradeDAO = new GradeDAO();
		String pname = gradeDAO.pnoFindPname(pno);
		int result = gradeDAO.pnoCnoCgradeInsert(pname, cno, cgrade);

		if(result <= 0) {
			response.getWriter().append("null");
			response.getWriter().flush();
			return;
		}
		
		
		response.getWriter().append(cgrade);
		response.getWriter().flush();
	}
}
