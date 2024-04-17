package admin.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.dao.StuInfoDAO;
import professor.dao.AttendDAO;
import professor.dao.GradeDAO;
import vo.AttendmentVO;
import vo.CourseVO;
import vo.LectureVO;

public class StuInfoController {
	
	public void doAction(String threeUriParam, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		switch(threeUriParam) {
			case "absenseMgList":
				absenseMgList(request,response);
				break;
			case "absenseMgView":
				absenseMgView(request,response);
				break;
			case "attendMgList":
				attendMgList(request,response);
				break;
			case "attendMgView":
				attendMgView(request,response);
				break;
			case "gradeMgList":
				gradeMgList(request,response);
				break;
			case "gradeMgView":
				gradeMgView(request,response);	
				break;
			default:
				break;
		}
		
//		String threeUri = threeUriParam.split("\\.")[0];
//
//		if(threeUri.equals("absenseMgList")) {
//			absenseMgList(request,response);
//		}else if(threeUri.equals("absenseMgView")) {
//			absenseMgView(request,response);			
//		}else if(threeUri.equals("attendMgList")) {
//			attendMgList(request,response);			
//		}else if(threeUri.equals("attendMgView")) {
//			attendMgView(request,response);			
//		}else if(threeUri.equals("gradeMgList")) {
//			gradeMgList(request,response);			
//		}else if(threeUri.equals("gradeMgView")) {
//			gradeMgView(request,response);			
//		}
		
	}
	public void doPostAction(String threeUriParam, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String threeUri = threeUriParam.split("\\.")[0];

		if(threeUri.equals("absenseMgList")) {
			PostabsenseMgList(request,response);
		}else if(threeUri.equals("absenseMgView")) {
			PostabsenseMgView(request,response);			
		}else if(threeUri.equals("attendMgList")) {
			PostattendMgList(request,response);			
		}else if(threeUri.equals("attendMgView")) {
			PostattendMgView(request,response);			
		}else if(threeUri.equals("gradeMgList")) {
			PostgradeMgList(request,response);			
		}else if(threeUri.equals("gradeMgView")) {
			PostgradeMgView(request,response);			
		}
	}
	
	public void absenseMgList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/admin/stuInfo/absenseMgList.jsp");
		rd.forward(request, response);
		
		
	}
	public void PostabsenseMgList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/admin/stuInfo/absenseMgList.jsp");
		rd.forward(request, response);
		
		
	}
	public void absenseMgView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/admin/stuInfo/absenseMgView.jsp");
		rd.forward(request, response);
		
		
	}
	public void PostabsenseMgView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/admin/stuInfo/absenseMgView.jsp");
		rd.forward(request, response);
		
		
	}
	public void attendMgList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String lstatus = request.getParameter("lstatus");
		//유효성 검사
		if(lstatus == null) {
			lstatus = "6";
		}
		if(!(lstatus.equals("6")||lstatus.equals("4")||lstatus.equals("5"))) {
			lstatus = "6";
		}
		
		request.setAttribute("lstatus", lstatus);
		StuInfoDAO stuInfoDAO = new StuInfoDAO();
		List<LectureVO> lectureList = stuInfoDAO.lstatusFindLecture(lstatus);
		
		request.setAttribute("lectureList", lectureList);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/admin/stuInfo/attendMgList.jsp");
		rd.forward(request, response);
		
	}
	public void PostattendMgList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/admin/stuInfo/attendMgList.jsp");
		rd.forward(request, response);
		
		
	}
	public void attendMgView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String lno = request.getParameter("lno");
		if(lno == null) {
			response.sendRedirect(request.getContextPath());
			return;
		}
		
		//날짜 null체크 null이면 현재날짜 대입
		String attenddayParam = request.getParameter("attendday");
		if(attenddayParam == null) {
			LocalDate date = LocalDate.now(); //오늘 날짜 LocalDate 객체 생성
			DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
			attenddayParam = date.format(dateTimeFormatter);
		}
	
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		
		try {
			LocalDate attendday = LocalDate.parse(attenddayParam, formatter);
			
			formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			
			String stringAttendday = attendday.format(formatter);
			
			StuInfoDAO stuInfoDAO = new StuInfoDAO();
			List<AttendmentVO> attendList = stuInfoDAO.dayLnoFindAttend(stringAttendday, lno);
			
			request.setAttribute("attendList", attendList);
			
			request.setAttribute("attenddayParam", attenddayParam);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/admin/stuInfo/attendMgView.jsp");
		rd.forward(request, response);
		
		
	}
	public void PostattendMgView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String attendno = request.getParameter("attendno");
		String attendyn = request.getParameter("attendyn");
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("UTF-8");
//		attendyn sid 정확한 값만 들어가도록 유효성 검사 미흡 시간관계상 보류
		if(attendno == null || attendyn == null) {
			response.getWriter().append("null");
			response.getWriter().flush();
			return;
		}
		
		StuInfoDAO stuInfoDAO = new StuInfoDAO();
		int result = stuInfoDAO.sidYnInsert(attendno, attendyn);
		if(result <= 0) {
			response.getWriter().append("null");
			response.getWriter().flush();
			return;
		}
		
		
		response.getWriter().append(attendyn);
		response.getWriter().flush();
		
		
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
		StuInfoDAO stuInfoDAO = new StuInfoDAO();
		List<LectureVO> lectureList = stuInfoDAO.lstatusFindLecture(lstatus);
		
		request.setAttribute("lectureList", lectureList);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/admin/stuInfo/gradeMgList.jsp");
		rd.forward(request, response);
		
		
	}
	public void PostgradeMgList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/admin/stuInfo/gradeMgList.jsp");
		rd.forward(request, response);
		
		
	}
	public void gradeMgView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		String lno = request.getParameter("lno");
		if(lno == null) {
			response.sendRedirect(request.getContextPath());
			return;
		}
		StuInfoDAO stuInfoDAO = new StuInfoDAO();
		List<CourseVO> courseList = stuInfoDAO.lnoFindCourse(lno);
		
		request.setAttribute("courseList", courseList);
		
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/admin/stuInfo/gradeMgView.jsp");
		rd.forward(request, response);
		
		
	}
	public void PostgradeMgView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String ano = (String)request.getSession().getAttribute("no");
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
		
		StuInfoDAO stuInfoDAO = new StuInfoDAO();
		String aid = stuInfoDAO.anoFindPname(ano);
		int result = stuInfoDAO.anoCnoCgradeInsert(aid, cno, cgrade);

		if(result <= 0) {
			response.getWriter().append("null");
			response.getWriter().flush();
			return;
		}
		
		
		response.getWriter().append(cgrade);
		response.getWriter().flush();
		
		
	}
	
}
