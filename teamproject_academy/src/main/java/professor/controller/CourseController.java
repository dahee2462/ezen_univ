package professor.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import professor.dao.CourseDAO;
import vo.LectureVO;

public class CourseController {
	public void doAction(String threeUriParam, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//String threeUri = threeUriParam.split("\\.")[0];
		
		switch(threeUriParam) {
			case "couList":
				couList(request, response);
				break;
			case "curriculum":
				curriculum(request, response);	
				break;
		}

//		if(threeUri.equals("couList")) {
//			couList(request,response);
//		}else if(threeUri.equals("curriculum")) {
//			curriculum(request,response);			
//		}
		
	}
	public void doPostAction(String threeUriParam, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String threeUri = threeUriParam.split("\\.")[0];

		if(threeUri.equals("couList")) {
			PostcouList(request,response);
		}else if(threeUri.equals("curriculum")) {
			Postcurriculum(request,response);			
		}
		
	}
	public void couList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pno = (String)request.getSession().getAttribute("no");
		String lyear = request.getParameter("lyear");
		String lsemesterParam = request.getParameter("lsemester");
		int lsemester = 0;
		
		if(lyear == null) {
			response.sendRedirect(request.getContextPath());
			return;
		}
		
		try {
			lsemester = Integer.parseInt(lsemesterParam);
		} catch (NumberFormatException e) {
			response.sendRedirect(request.getContextPath());
			return;
		}
		
		
		CourseDAO courseDAO = new CourseDAO();
		
		List<LectureVO> lecture = courseDAO.pnoFindLecture(pno, lyear, lsemester);
		request.setAttribute("lecture",lecture);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/professor/course/couList.jsp");
		rd.forward(request, response);
		
		
	}
	public void PostcouList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/professor/course/couList.jsp");
		rd.forward(request, response);
		
		
	}
	public void curriculum(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String lno = request.getParameter("lno");
		CourseDAO courseDAO = new CourseDAO();
		
		LectureVO lecture = courseDAO.lnoFindLecture(lno);
		request.setAttribute("lectureVO",lecture);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/professor/course/curriculum.jsp");
		rd.forward(request, response);
		
		
	}
	public void Postcurriculum(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/professor/course/courriculum.jsp");
		rd.forward(request, response);
		
		
	}
	
}
