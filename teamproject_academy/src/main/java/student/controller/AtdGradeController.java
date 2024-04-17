package student.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import student.dao.AtdGradeDAO;
import vo.AttendmentVO;
import vo.LectureVO;
import vo.StudentVO;

public class AtdGradeController {
	public void doAction(String threeUriParam, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		switch(threeUriParam) {
		case "attendcheckList":
			attendcheckList(request,response);
			break;
		case "attendcheckView":
			attendcheckView(request,response);
			break;
		case "cgradeCheck":
			cgradeCheck(request,response);
			break;
	}
	
	}
	public void doPostAction(String threeUriParam, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if(threeUriParam.equals("attendcheckList")) {
			PostattendcheckList(request,response);
		}else if(threeUriParam.equals("attendcheckView")) {
			PostattendcheckView(request,response);			
		}else if(threeUriParam.equals("cgradeCheck")) {
			PostcgradeCheck(request,response);			
		}
		
	}
	//출석 목록 list
	public void attendcheckList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sno = (String)request.getSession().getAttribute("no");
		if(sno == null || (sno != null && sno.equals(""))) {
			response.sendRedirect(request.getContextPath());
			return;
		}
		AtdGradeDAO atdGradeDAO = new AtdGradeDAO();
		List<LectureVO> courseList = atdGradeDAO.selectCourseAll(sno);
		request.setAttribute("courseList", courseList);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/student/atdGrade/attendcheckList.jsp");
		rd.forward(request, response);
		
		
	}
	public void PostattendcheckList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/student/atdGrade/attendcheckList.jsp");
		rd.forward(request, response);
		
		
	}
	//출석 view 
	public void attendcheckView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sno = (String)request.getSession().getAttribute("no");
		if(sno == null || (sno != null && sno.equals(""))) {
			response.sendRedirect(request.getContextPath());
			return;
		}
		String cnoParam = request.getParameter("cno");
		int cno = 0;
		if(cnoParam != null && !cnoParam.equals("")) {
			cno = Integer.parseInt(cnoParam);
		}
		
		//System.out.println("cno(view컨트롤러):"+cno);
		AtdGradeDAO atdGradeDAO = new AtdGradeDAO();
		//강의정보 조회
		LectureVO courseInfo = atdGradeDAO.selectCourseInfo(cno);
		request.setAttribute("courseInfo", courseInfo);
		//출석정보 조회
		List<AttendmentVO> attendList=atdGradeDAO.selectAttendAll(cno, sno);
		request.setAttribute("attendList", attendList);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/student/atdGrade/attendcheckView.jsp");
		rd.forward(request, response);
		
		
	}
	public void PostattendcheckView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/student/atdGrade/attendcheckView.jsp");
		rd.forward(request, response);
	}
	//성적조회 list
	public void cgradeCheck(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sno = (String)request.getSession().getAttribute("no");
		if(sno == null || (sno != null && sno.equals(""))) {
			response.sendRedirect(request.getContextPath());
			return;
		}
		String lyearType = request.getParameter("lyearType");
		String lsemesterType = request.getParameter("lsemesterType");
		if(lyearType == null) {
			lyearType = "";
		}
		if(lsemesterType == null) {
			lsemesterType = "";
		}
		AtdGradeDAO atdGradeDAO = new AtdGradeDAO();
		StudentVO student = atdGradeDAO.selectStuInfo(sno);
		request.setAttribute("student", student);
		List<Map<String, Object>> gradeList = atdGradeDAO.selectgradeAll(sno,lyearType, lsemesterType);
		request.setAttribute("gradeList", gradeList);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/student/atdGrade/cgradeCheck.jsp");
		rd.forward(request, response);
	}
	public void PostcgradeCheck(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/student/atdGrade/cgradeCheck.jsp");
		rd.forward(request, response);
	}
	
}
