package admin.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.dao.NoticeDAO;
import vo.BoardVO;
import vo.PagingVO;

public class NoticeController {
	
	
	public void doAction(String threeUriParam, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		switch(threeUriParam) {
			case "noticeList":
				noticeList(request,response);
				break;
			case "noticeModify":
				noticeModify(request,response);
				break;
			case "noticeView":
				noticeView(request,response);
				break;
			case "noticeWrite":
				noticeWrite(request,response);
				break;
			default:
				break;
		}
		
//		String threeUri = threeUriParam.split("\\.")[0];
//
//		if(threeUri.equals("noticeList")) {
//			noticeList(request,response);
//		}else if(threeUri.equals("noticeModify")) {
//			noticeModify(request,response);			
//		}else if(threeUri.equals("noticeView")) {
//			noticeView(request,response);			
//		}else if(threeUri.equals("noticeWrite")) {
//			noticeWrite(request,response);			
//		}
	}
	
	public void doPostAction(String threeUriParam, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String threeUri = threeUriParam.split("\\.")[0];

		if(threeUri.equals("noticeList")) {
			PostnoticeList(request,response);
		}else if(threeUri.equals("noticeModify")) {
			PostnoticeModify(request,response);			
		}else if(threeUri.equals("noticeView")) {
			PostnoticeView(request,response);			
		}else if(threeUri.equals("noticeWrite")) {
			PostnoticeWrite(request,response);			
		}
	}
	
	public void noticeList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String searchAlign = request.getParameter("searchAlign");
		String searchType = request.getParameter("searchType");
		String searchValue = request.getParameter("searchValue");
		String nowPageParam = request.getParameter("nowPage");
		if(nowPageParam == null) {
			nowPageParam =  "1";
		}
		
		int nowPage = 1;
		try {
			nowPage = Integer.parseInt(nowPageParam);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		
		
		if(searchAlign == null || searchType == null) {
			searchAlign = "";
			searchType = "";
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
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/admin/notice/noticeList.jsp");
		rd.forward(request, response);
	}
	
	public void PostnoticeList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/admin/notice/noticeList.jsp");
		rd.forward(request, response);
		
		
	}
	
	public void noticeModify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String bnoParam = request.getParameter("bno");
		int bno = 0;
		try {
			bno = Integer.parseInt(bnoParam);
		} catch (NumberFormatException e) {
		}
		
		NoticeDAO noticeDAO = new NoticeDAO();
		BoardVO boardVO = noticeDAO.bnoFindBoard(bno);
		
		request.setAttribute("boardVO", boardVO);
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/admin/notice/noticeModify.jsp");
		rd.forward(request, response);
		
		
	}
	public void PostnoticeModify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String bnoParam = request.getParameter("bno");
		int bno = 0;
		try {
			bno = Integer.parseInt(bnoParam);
		} catch (NumberFormatException e) {
		}
		
		NoticeDAO noticeDAO = new NoticeDAO();
		
		String titleParam = request.getParameter("title");
		String contentParam = request.getParameter("content");
		
		BoardVO updateBoard = noticeDAO.updateBoard(bno, titleParam, contentParam);
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		
		String encodedBno = URLEncoder.encode(String.valueOf(bno), "UTF-8");
	    response.sendRedirect(request.getContextPath() + "/admin/notice/noticeView?bno=" + encodedBno);
	}
	
	public void noticeView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String bnoParam = request.getParameter("bno");
		int bno = 0;
		try {
			bno = Integer.parseInt(bnoParam);
		} catch (NumberFormatException e) {
		}
		
		NoticeDAO noticeDAO = new NoticeDAO();
		BoardVO boardVO = noticeDAO.bnoFindBoard(bno);
				
		request.setAttribute("boardVO", boardVO);
		
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/admin/notice/noticeView.jsp");
		rd.forward(request, response);
		
		
	}
	public void PostnoticeView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/admin/notice/noticeView.jsp");
		rd.forward(request, response);
		
		
	}
	
	public void noticeWrite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/admin/notice/noticeWrite.jsp");
		rd.forward(request, response);
		
		
	}
	public void PostnoticeWrite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String titleParam = request.getParameter("title");
		String contentParam = request.getParameter("content");
		
		String ano = (String)request.getSession().getAttribute("no");
		
		NoticeDAO noticeDAO = new NoticeDAO();
		int boardVO = noticeDAO.insertNotice(titleParam, contentParam, ano);
		
		response.sendRedirect(request.getContextPath()+"/admin/notice/noticeList");
		
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("UTF-8");
	}

	public void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/admin/notice/noticeList.jsp");
		rd.forward(request, response);
		
	}

	 

	
	
}
