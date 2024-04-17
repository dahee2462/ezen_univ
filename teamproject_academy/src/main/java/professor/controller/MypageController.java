package professor.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import professor.dao.MypageDAO;

public class MypageController {
	public void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/professor/mypage/myInfoModify.jsp");
		rd.forward(request, response);
		
	}
	
	
	public void doPostAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pno = (String)request.getSession().getAttribute("no");
		String oldpw = request.getParameter("oldpw");
		String newpw = request.getParameter("newpw");		
		String checknewpw = request.getParameter("checknewpw");
		
		if(oldpw == null || newpw == null || checknewpw == null) {
			response.sendRedirect(request.getContextPath());
			return;
		}
		
		MypageDAO mypageDAO = new MypageDAO();
		
		int result = mypageDAO.pwUpdate(pno, oldpw, newpw, checknewpw);
		
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		if(result <= 0) {
			response.getWriter().append("<script>alert('비밀번호가 변경에 실패했습니다.'); location.href='"+request.getContextPath()+"/professor/mypage/myInfoModify';</script>");
		}else{			
			response.getWriter().append("<script>alert('비밀번호가 변경되었습니다.'); location.href='"+request.getContextPath()+"';</script>");
		}
		response.getWriter().flush();

		
	}
		
		
	
	
	
	
}
