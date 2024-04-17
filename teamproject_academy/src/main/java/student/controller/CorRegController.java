package student.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import student.dao.CorRegDAO;
import vo.CourseVO;
import vo.LectureVO;
import vo.PagingVO;

public class CorRegController {
	public void doAction(String threeUriParam, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		switch (threeUriParam) {
			case "cAppCheck":
				cAppCheck(request,response);
				break;
			case "corReg":
				corReg(request,response);
				break;
		}
	}
	public void doPostAction(String threeUriParam, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String threeUri = threeUriParam.split("\\.")[0];

		if(threeUri.equals("cAppCheck")) {
			PostcAppCheck(request,response);
		}else if(threeUri.equals("corReg")) {
			PostcorReg(request,response);			
		}
	}
	//수강신청 현황조회
	public void cAppCheck(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String sno = (String)request.getSession().getAttribute("no");
		if(sno == null || (sno != null && sno.equals(""))) {
			response.sendRedirect(request.getContextPath());
			return;
		}
		CorRegDAO corRegDAO = new CorRegDAO();

		List<LectureVO> regList = corRegDAO.selectRegAll(sno);
		request.setAttribute("regList", regList);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/student/corReg/cAppCheck.jsp");
		rd.forward(request, response);
		
	}
	//수강신청 ajax
	public void PostcAppCheck(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String sno = (String)request.getSession().getAttribute("no");
		if(sno == null || (sno != null && sno.equals(""))) {
			response.sendRedirect(request.getContextPath());
			return;
		}
		
		/*
		 * String cdelynParam = request.getParameter("cdelyn"); int cdelyn=4;
		 * if(cdelynParam != null && !cdelynParam.isEmpty()) { cdelyn =
		 * Integer.parseInt("cdelynParam"); }
		 */

		CorRegDAO corRegDAO = new CorRegDAO();
		//System.out.println(cdelyn);
		
		
		//if(cdelyn != 1) {//일반 신청, 삭제시
			
			//신청 insert
			String lnoParam = request.getParameter("lno");
			if(lnoParam != null && !lnoParam.equals("")) {
				int lno = Integer.parseInt(lnoParam);
				//[ajax: 신청]
				int insertRs = corRegDAO.insertReg(lno, sno);
				request.setAttribute("insertRs", insertRs);
		
				if(insertRs >0) {
					response.setContentType("text/html; charset=utf-8");
					response.setCharacterEncoding("UTF-8");
					response.getWriter().append("SUCCESS");
					response.getWriter().flush();
				} else {
					response.setContentType("text/html; charset=utf-8");
					response.setCharacterEncoding("UTF-8");
					response.getWriter().append("FAIL");
					response.getWriter().flush();
				}
			}
			//삭제 delete
			String cnoParam = request.getParameter("cno");
			if(cnoParam != null && !cnoParam.equals("")) {
				int cno = Integer.parseInt(cnoParam);
				//[ajax: 삭제]
				int delRs = corRegDAO.deleteReg(cno);
				request.setAttribute("delRs", delRs);
		
				if(delRs >0) {
					response.setContentType("text/html; charset=utf-8");
					response.setCharacterEncoding("UTF-8");
					response.getWriter().append("SUCCESS2");
					response.getWriter().flush();
				} else {
					response.setContentType("text/html; charset=utf-8");
					response.setCharacterEncoding("UTF-8");
					response.getWriter().append("FAIL2");
					response.getWriter().flush();
				}
			}
		//}else if(cdelyn ==1) { //삭제이력이 있었던 강의 재신청시 -> 변경

		/*
		 * String cnoParam = request.getParameter("cno"); int cno = 0; if(cnoParam !=
		 * null && !cnoParam.isEmpty()) { cno = Integer.parseInt(cnoParam); } int
		 * updateRs = corRegDAO.updateReg(cno); request.setAttribute("updateRs",
		 * updateRs);
		 * 
		 * if(updateRs >0) { response.setContentType("text/html; charset=utf-8");
		 * response.setCharacterEncoding("UTF-8");
		 * response.getWriter().append("SUCCESS"); response.getWriter().flush(); } else
		 * { response.setContentType("text/html; charset=utf-8");
		 * response.setCharacterEncoding("UTF-8"); response.getWriter().append("FAIL");
		 * response.getWriter().flush(); }
		 */
		//}
	}

		
	//수강신청조회
	public void corReg(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sno = (String)request.getSession().getAttribute("no");
		if(sno == null || (sno != null && sno.equals(""))) {
			response.sendRedirect(request.getContextPath());
			return;
		}
		//[검색]
		String searchType = request.getParameter("searchType");
		String searchValue = request.getParameter("searchValue");
		String nowPageParam = request.getParameter("nowPage");
		
		int nowPage = 1;
		if(nowPageParam != null && !nowPageParam.isEmpty()) {
			nowPage = Integer.parseInt(nowPageParam);
		}
		if(searchType == null) { 
			 searchType = "";
		 }
		if(searchValue == null) {
			searchValue = "";
		}
		CorRegDAO corRegDAO = new CorRegDAO();
		int totalCnt = corRegDAO.FindTotalCnt(searchType, searchValue);
		PagingVO pagingVO = new PagingVO(nowPage,totalCnt,5);
		int start = pagingVO.getStart();
		int perPage = pagingVO.getPerPage();
		request.setAttribute("pagingVO", pagingVO);
		//수강신청 lecture list 조회
		List<LectureVO> corRegList = corRegDAO.selectCorRegAll(searchType,searchValue, start, perPage);
		request.setAttribute("corRegList",corRegList);
		//수강신청 course list 조회
		List<CourseVO> courseList = corRegDAO.selectCourseAll(sno);
		request.setAttribute("courseList",courseList);

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/student/corReg/corReg.jsp");
		rd.forward(request, response);
		
	}
	public void PostcorReg(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/student/corReg/corReg.jsp");
		rd.forward(request, response);
		
		
	}
	
}
