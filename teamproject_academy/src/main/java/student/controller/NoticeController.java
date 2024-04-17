package student.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import student.dao.NoticeDAO;
import vo.BoardVO;
import vo.PagingVO;
import vo.StudentVO;

public class NoticeController {
	public void doAction(String threeUriParam, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		switch(threeUriParam) {
			case "noticeList":
				noticeList(request, response);
				break;
			case "noticeView":
				noticeView(request, response);
				break;
				
		}
	}
	public void doPostAction(String threeUriParam, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String threeUri = threeUriParam.split("\\.")[0];

		if(threeUri.equals("noticeList")) {
			noticeList(request,response);
		}else if(threeUri.equals("noticeView")) {
			noticeView(request,response);			
		}
	}
	//공지사항 목록
	public void noticeList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String searchAlign = request.getParameter("searchAlign");
		String searchType = request.getParameter("searchType");
		String searchValue = request.getParameter("searchValue");
		
		String nowPageParam = request.getParameter("nowPage");
		int nowPage = 1;
		if(nowPageParam != null && !nowPageParam.isEmpty()) {
			nowPage = Integer.parseInt(nowPageParam);
		}
		
		if(searchAlign == null || searchType == null) {
			response.sendRedirect(request.getContextPath());
			return;
		}
		if(searchValue == null) {
			searchValue = "";
		}
		
		NoticeDAO noticeDAO = new NoticeDAO();
		int totalCnt = noticeDAO.FindTotalCnt(searchAlign, searchType, searchValue);
		
		PagingVO pagingVO = new PagingVO(nowPage,totalCnt,5);
		int start = pagingVO.getStart();
		int perPage = pagingVO.getPerPage();
		
		request.setAttribute("pagingVO", pagingVO);
		
		List<BoardVO> board = noticeDAO.FindBoard(searchAlign, searchType, searchValue, start, perPage);
		
		request.setAttribute("board",board);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/student/notice/noticeList.jsp");
		rd.forward(request, response);
		
		
	}
	public void PostnoticeList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/student/notice/noticeList.jsp");
		rd.forward(request, response);
		
		
	}
	public void noticeView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bnoParam = request.getParameter("bno");
		int bno = 0;
		if(bnoParam != null && !bnoParam.isEmpty()) {
			bno = Integer.parseInt(bnoParam);
		}
		
		NoticeDAO noticeDAO = new NoticeDAO();
		BoardVO boardVO = noticeDAO.bnoFindBoard(bno);
		
		request.setAttribute("boardVO", boardVO);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/student/notice/noticeView.jsp");
		rd.forward(request, response);
		
		
	}
	public void PostnoticeView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/student/notice/noticeView.jsp");
		rd.forward(request, response);
		
		
	}
	
}
