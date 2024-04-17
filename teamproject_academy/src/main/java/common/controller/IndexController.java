package common.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mindrot.jbcrypt.BCrypt;

import common.dao.FindIdDAO;
import common.dao.FindPwDAO;
import common.dao.indexDAO;
import util.SendEmail;



public class IndexController {
	
	public void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/common/index.jsp");
		rd.forward(request, response);
		
	}
	
	public void doAction(String twoUriParam, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		switch (twoUriParam) {
			case "index":
				index(request, response);
				break;
			case "findId":
				findId(request, response);
				break;
			case "findPw":
				findPw(request, response);
				break;
			case "logout":
				logout(request, response);
				break;
		}
		
//		if(twoUriParam.equals("index")) {
//			index(request,response);
//		}else if(twoUriParam.equals("findId")) {
//			findId(request,response);			
//		}else if(twoUriParam.equals("findPw")) {
//			findPw(request,response);			
//		}
		
		
	}
	
	

	public void doPostAction(String twoUriParam, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String twoUri = twoUriParam.split("\\.")[0];
		
		if(twoUri.equals("index")) {
			Postindex(request,response);
		}else if(twoUri.equals("findId")) {
			PostfindId(request,response);			
		}else if(twoUri.equals("findPw")) {
			PostfindPw(request,response);			
		}else if(twoUri.equals("sendEmail")) {
			PostsendEmail(request,response);			
		}else if(twoUri.equals("sendCode")) {
			PostsendCode(request,response);			
		}
		
	}

	//로그아웃 클릭시 실행
	public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.getSession().invalidate();
		response.sendRedirect(request.getContextPath()+"/common/index");
	}
	
	
	//	index GET접근
	public void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/common/index.jsp");
		rd.forward(request, response);
	}
//	index POST접근
	public void Postindex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String idParam = request.getParameter("id");
		String pwParam = request.getParameter("pw");
		String rememberParam = request.getParameter("inputRememberId");
		
		//해당 id의 데이터베이스 해시된 pw값, BCrypt로 솔트된 해시 값이 들어있다고 가정
		indexDAO indexdao = new indexDAO();
		String[] result = indexdao.selectPw(idParam);
		String pwData = result[0];
		String typeData = result[1];
		String noData = result[2];
		String nameData = result[3];
		//검증
		boolean isValidPassword =false;
		if(pwData != null) {
			isValidPassword = BCrypt.checkpw(pwParam, pwData);
			
		}
		if(isValidPassword) {
			//세션
			request.getSession().setAttribute("type",typeData);
			request.getSession().setAttribute("no",noData);
			request.getSession().setAttribute("name",nameData);
			//아이디 기억 쿠키
			if(rememberParam != null && rememberParam.equals("check")) {
				Cookie cookie = new Cookie("rememberParam",idParam);
				cookie.setMaxAge(60*60*3);
				response.addCookie(cookie);
			}
			if(typeData.equals("student")) {
				response.sendRedirect(request.getContextPath()+"/student/stuMain");
			}else if(typeData.equals("professor")) {
				response.sendRedirect(request.getContextPath()+"/professor/profMain");
			}else if(typeData.equals("administer")) {
				response.sendRedirect(request.getContextPath()+"/admin/admMain");
			}
			
		}else {
			response.setContentType("text/html; charset=utf-8");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().append("<script>alert('로그인에 실패하였습니다.'); location.href='"+request.getContextPath()+"/';</script>");
			response.getWriter().flush();
		}
		
	}
	
	
	public void findId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/common/findId.jsp");
		rd.forward(request, response);
	}
	public void PostfindId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		String type = request.getParameter("selectType");
		String name = request.getParameter("inputName");
		String birth = request.getParameter("inputBirth");
		String phone = request.getParameter("inputPhone");
		
		FindIdDAO findIdDAO = new FindIdDAO();
		String id =null;
		
		if(type.equals("학생")) {
			id = findIdDAO.searchStudentId(name, birth, phone);
		}else if(type.equals("교수")) {
			id = findIdDAO.searchProfessorId(name, birth, phone);
		}
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().append(id);
		response.getWriter().flush();
		
	}
	
	
	public void findPw(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/common/findPw.jsp");
		rd.forward(request, response);
	}
	public void PostfindPw(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String type = request.getParameter("type");
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String birth = request.getParameter("birth");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String inCode = request.getParameter("code");
		
		String outCode = (String)request.getSession().getAttribute("code");
		
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		if(outCode.equals(inCode)) {
			FindPwDAO findPwDAO = new FindPwDAO();
			String memberNo ="";
			if(type.equals("학생")) {
				memberNo = findPwDAO.searchStudentNo(id, name, birth, phone, email);
			}else if(type.equals("교수")) {
				memberNo = findPwDAO.searchProfessorNo(id, name, birth, phone, email);
			}
			
			if(memberNo == null) {
				response.getWriter().append("null");
			}else {
				String newPw = findPwDAO.insertNewPw(type, memberNo);
				response.getWriter().append(newPw);
			}
		}else {
			response.getWriter().append("null");
		}
		response.getWriter().flush();
		
	}
	
	private void PostsendEmail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		SendEmail sendEmail = new SendEmail();
		String code = sendEmail.sendEmail(email);
		
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		if(code == null) {
			response.getWriter().append("null");
		}else {
			request.getSession().setAttribute("code", code);
			request.getSession().setMaxInactiveInterval(180);
			response.getWriter().append("ok");
		}
		response.getWriter().flush();
	}
	
	private void PostsendCode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String outCode = (String)request.getSession().getAttribute("code");
		String inCode = request.getParameter("code");
		
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		if(outCode.equals(inCode)) {
			response.getWriter().append("ok");
		}else {
			response.getWriter().append("null");
		}
		response.getWriter().flush();
		
	}
}
