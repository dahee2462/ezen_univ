package admin.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CourseController {
	
	public void doAction(String threeUriParam, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		switch(threeUriParam) {
			case "courMgList":
				courMgList(request,response);
				break;
			case "courseRegAdd":
				courseRegAdd(request,response);
				break;
			case "courseRegList":
				courseRegList(request,response);
				break;
			case "curriculum":
				curriculum(request,response);
				break;
			case "curriculumAdd":
				curriculumAdd(request,response);
				break;
			case "curriculumModify":
				curriculumModify(request,response);	
				break;
			default:
				break;
		}
		
//		String threeUri = threeUriParam.split("\\.")[0];
//		if(threeUri.equals("courMgList")) {
//			courMgList(request,response);
//		}else if(threeUri.equals("courseRegAdd")) {
//			courseRegAdd(request,response);			
//		}else if(threeUri.equals("courseRegList")) {
//			courseRegList(request,response);			
//		}else if(threeUri.equals("curriculum")) {
//			curriculum(request,response);			
//		}else if(threeUri.equals("curriculumAdd")) {
//			curriculumAdd(request,response);			
//		}else if(threeUri.equals("curriculumModify")) {
//			curriculumModify(request,response);			
//		}
	}
	
	public void doPostAction(String threeUriParam, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String threeUri = threeUriParam.split("\\.")[0];
		if(threeUri.equals("courMgList")) {
			PostcourMgList(request,response);
		}else if(threeUri.equals("courseRegAdd")) {
			PostcourseRegAdd(request,response);			
		}else if(threeUri.equals("courseRegList")) {
			PostcourseRegList(request,response);			
		}else if(threeUri.equals("curriculum")) {
			Postcurriculum(request,response);			
		}else if(threeUri.equals("curriculumAdd")) {
			PostcurriculumAdd(request,response);			
		}else if(threeUri.equals("curriculumModify")) {
			PostcurriculumModify(request,response);			
		}
	}
	
	public void courMgList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/admin/course/courMgList.jsp");
		rd.forward(request, response);
		
		
	}
	
	public void PostcourMgList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/admin/course/courMgList.jsp");
		rd.forward(request, response);
		
		
	}
	
	
	public void courseRegAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/admin/course/courseRegAdd.jsp");
		rd.forward(request, response);
		
		
	}
	
	public void PostcourseRegAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/admin/course/courseRegAdd.jsp");
		rd.forward(request, response);
		
		
	}
	
	public void courseRegList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/admin/course/courseRegList.jsp");
		rd.forward(request, response);
		
		
	}
	public void PostcourseRegList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/admin/course/courseRegList.jsp");
		rd.forward(request, response);
		
		
	}
	
	
	public void curriculum(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/admin/course/curriculum.jsp");
		rd.forward(request, response);
		
		
	}
	public void Postcurriculum(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/admin/course/curriculum.jsp");
		rd.forward(request, response);
		
		
	}
	
	public void curriculumAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/admin/course/curriculumAdd.jsp");
		rd.forward(request, response);
	}
		
	public void PostcurriculumAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/admin/course/curriculumAdd.jsp");
		rd.forward(request, response);
			
	}
	
	public void curriculumModify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/admin/course/curriculumModify.jsp");
		rd.forward(request, response);
		
	
	}
	public void PostcurriculumModify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/admin/course/curriculumModify.jsp");
		rd.forward(request, response);
		
	
	}

}
