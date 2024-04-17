package professor.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import professor.dao.AttendDAO;
import vo.AttendmentVO;
import vo.LectureVO;

public class AttendController {
	public void doAction(String threeUriParam, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		String threeUri = threeUriParam.split("\\.")[0];

		switch(threeUriParam) {
			case "attendMgList":
				attendMgList(request,response);
				break;
			case "attendMgView":
				attendMgView(request,response);
				break;
		}
		
//		if(threeUri.equals("attendMgList")) {
//			attendMgList(request,response);
//		}else if(threeUri.equals("attendMgView")) {
//			attendMgView(request,response);			
//		}
		
	}
	public void doPostAction(String threeUriParam, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String threeUri = threeUriParam.split("\\.")[0];
		
		
		
		

		if(threeUri.equals("attendMgList")) {
			PostattendMgList(request,response);
		}else if(threeUri.equals("attendMgView")) {
			PostattendMgView(request,response);			
		}
		
	}
	public void attendMgList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		AttendDAO attendDAO = new AttendDAO();
		List<LectureVO> lectureList = attendDAO.pnoLstatusFindLecture(pno,lstatus);
		
		request.setAttribute("lectureList", lectureList);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/professor/attend/attendMgList.jsp");
		rd.forward(request, response);
		
		
	}
	public void PostattendMgList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/professor/attend/attendMgList.jsp");
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
			
			AttendDAO attendDAO = new AttendDAO();
			List<AttendmentVO> attendList = attendDAO.dayLnoFindAttend(stringAttendday, lno);
			
			request.setAttribute("attendList", attendList);
			
			request.setAttribute("attenddayParam", attenddayParam);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/professor/attend/attendMgView.jsp");
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
		
		AttendDAO attendDAO = new AttendDAO();
		int result = attendDAO.sidYnInsert(attendno, attendyn);
		if(result <= 0) {
			response.getWriter().append("null");
			response.getWriter().flush();
			return;
		}
		
		
		response.getWriter().append(attendyn);
		response.getWriter().flush();
	}
}
