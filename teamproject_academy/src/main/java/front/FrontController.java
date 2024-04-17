package front;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.controller.CourseController;
import admin.controller.MainController;
import admin.controller.NoticeController;
import admin.controller.StuInfoController;
import admin.controller.UserManageController;
import common.controller.IndexController;
import professor.controller.AttendController;
import professor.controller.GradeController;
import professor.controller.MypageController;
import professor.controller.ProfInfoController;
import student.controller.AcdCourseController;
import student.controller.AtdGradeController;
import student.controller.CorRegController;

/**
 * Servlet implementation class FrontController
 */
//@WebServlet("*.do")
@WebServlet(urlPatterns={"/common/*", "/admin/*", "/student/*", "/professor/*"})
public class FrontController extends HttpServlet {
	
	//.do로 들어왔을때 => 이 서블릿 페이지 노출
	//.do 외 다른 페이지로 들어왔을 때 => 404 노출 
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FrontController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String command = request.getRequestURI().substring(request.getContextPath().length()+1);
		
//		command 예시 => admin/course/courMgList.do - admin/admMain.do - asdf.do - adsfasd/asdfasdf.do
		
		String[] uris = command.split("/");
		
		if(uris.length >= 1) {
			
			String oneUri = uris[0];
			
			
			if(uris.length == 2) {
				
			String twoUri = uris[1];
			
			
				switch (oneUri) {
					case "common":
						common.controller.IndexController cic = new common.controller.IndexController();
						cic.doAction(twoUri, request, response);
						break;
					case "student":
						student.controller.MainController smc = new student.controller.MainController();
						smc.doAction(request, response);
						break;
						
					case "professor":
						String type = (String)request.getSession().getAttribute("type");
						//세션- 타입이 일치하지 않으면 화면 초기화
						if(type == null || (type != null && !type.equals("professor"))) {
							response.sendRedirect(request.getContextPath());
							return;
						}
						String pno = (String)request.getSession().getAttribute("no");
						//세션이 없을경우 화면 초기화
						if(pno == null || (pno != null && pno.equals(""))) {
							response.sendRedirect(request.getContextPath());
							return;
						}
						professor.controller.MainController pmc = new professor.controller.MainController();
						pmc.doAction(request, response);
						break;
						
					case "admin":
						admin.controller.MainController amc = new admin.controller.MainController();
						amc.doAction(request, response);
						break;
					default:
						break;
				}
				
			}else if (uris.length == 3) {
				String twoUri = uris[1];
	            String threeUri = uris[2];
	            //System.out.println(twoUri);

	            switch (oneUri) {
	                case "common":
	                    common.controller.IndexController cic = new common.controller.IndexController();
	                    cic.doAction(twoUri, request, response);
	                    break;

	                case "student":
	                    switch(twoUri) {
		                    case "acdCourse":
		                    	student.controller.AcdCourseController sac = new student.controller.AcdCourseController();
		                    	sac.doAction(threeUri, request, response);
		                    	break;
		                    case "atdGrade":
		                    	student.controller.AtdGradeController sgc = new student.controller.AtdGradeController();
		                    	sgc.doAction(threeUri, request, response);
		                    	break;
		                    case "corReg":
		                    	student.controller.CorRegController scc = new student.controller.CorRegController();
		                    	scc.doAction(threeUri, request, response);
		                    	break;
		                    case "mypage":
		                    	student.controller.MypageController smc = new student.controller.MypageController();
		                    	smc.doAction(request, response);
		                    	break;
		                    case "notice":
		                    	student.controller.NoticeController snc = new student.controller.NoticeController();
		    					snc.doAction(threeUri, request, response);
		                    	break;
	                    	}
	                    break;

	                case "professor":
	                	String type = (String)request.getSession().getAttribute("type");
	            		//세션- 타입이 일치하지 않으면 화면 초기화
	            		if(type == null || (type != null && !type.equals("professor"))) {
	            			response.sendRedirect(request.getContextPath());
	            			return;
	            		}
	            		String pno = (String)request.getSession().getAttribute("no");
	            		//세션이 없을경우 화면 초기화
	            		if(pno == null || (pno != null && pno.equals(""))) {
	            			response.sendRedirect(request.getContextPath());
	            			return;
	            		}
	                	switch(twoUri) {
		                    case "attend":
		                    	professor.controller.AttendController pac = new professor.controller.AttendController();
		    					pac.doAction(threeUri, request, response);
		                    	break;
		                    case "course":
		                    	professor.controller.CourseController pcc = new professor.controller.CourseController();
		    					pcc.doAction(threeUri, request, response);
		                    	break;
		                    case "grade":
		                    	professor.controller.GradeController pgc = new professor.controller.GradeController();
		    					pgc.doAction(threeUri, request, response);
		                    	break;
		                    case "mypage":
		                    	professor.controller.MypageController pmc = new professor.controller.MypageController();
		                    	pmc.doAction(request, response);
		                    	break;
		                    case "notice":
		                    	professor.controller.NoticeController pnc = new professor.controller.NoticeController();
		    					pnc.doAction(threeUri, request, response);
		                    	break;
		                    case "profInfo":
		                    	professor.controller.ProfInfoController ppc = new professor.controller.ProfInfoController();
		    					ppc.doAction(request, response);
		                    	break;
                    	}
	                    break;

	                case "admin":
	                	switch(twoUri) {
	                    case "course":
	                    	admin.controller.CourseController acc = new admin.controller.CourseController();
	                    	acc.doAction(threeUri, request, response);
	                    	break;
	                    case "notice":
	                    	admin.controller.NoticeController anc = new admin.controller.NoticeController();
	                    	anc.doAction(threeUri, request, response);
	                    	break;
	                    case "stuInfo":
	                    	admin.controller.StuInfoController asc = new admin.controller.StuInfoController();
	                    	asc.doAction(threeUri, request, response);
	                    	break;
	                    case "userManage":
	                    	admin.controller.UserManageController auc = new admin.controller.UserManageController();
	                    	auc.doAction(threeUri, request, response);
	                    	break;
	                	}
	                	break;
	                default:
	                    break;
	            }
	        }
		}
		
		
		
		
		
		
		
//		String oneUri = uris[0];
//		String twoUri = uris[1];
//		String threeUri = uris[2].split("\\.")[0];
//		rd = request.getRequestDispatcher("/WEB-INF/"+oneUri+"/"+twoUri+"/"+threeUri+".jsp");
		
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String command = request.getRequestURI().substring(request.getContextPath().length()+1);
//		command 예시 => admin/course/courMgList.do - admin/admMain.do - asdf.do - adsfasd/asdfasdf.do
		
		String[] uris = command.split("/");
		
		if(uris.length == 2) {
			
			String uriOne = uris[0];
			String uriTwo = uris[1];
			if(uriOne.equals("admin")) {
				MainController amc = new MainController();
				amc.doPostAction(request,response);
			}else if(uriOne.equals("common")){
				IndexController ic = new IndexController();
				ic.doPostAction(uriTwo, request, response);
			}else if(uriOne.equals("professor")){
				String type = (String)request.getSession().getAttribute("type");
        		//세션- 타입이 일치하지 않으면 화면 초기화
        		if(type == null || (type != null && !type.equals("professor"))) {
        			response.sendRedirect(request.getContextPath());
        			return;
        		}
        		String pno = (String)request.getSession().getAttribute("no");
        		//세션이 없을경우 화면 초기화
        		if(pno == null || (pno != null && pno.equals(""))) {
        			response.sendRedirect(request.getContextPath());
        			return;
        		}
				professor.controller.MainController pmc = new professor.controller.MainController();
				pmc.doPostAction(request, response);
			}else if(uriOne.equals("student")){
				student.controller.MainController smc = new student.controller.MainController();
				smc.doPostAction(request, response);
			}
			
		}else if(uris.length == 3) {
			
			String uriOne = uris[0];
			String uriTwo = uris[1];
			String uriThree = uris[2];
			if(uriOne.equals("admin")) {
				if(uriTwo.equals("course")) {
					CourseController acc = new CourseController();
					acc.doPostAction(uriThree, request, response);
				}else if(uriTwo.equals("notice")) {
					NoticeController anc = new NoticeController();
					anc.doPostAction(uriThree, request, response);
				}else if(uriTwo.equals("stuInfo")) {
					StuInfoController asc = new StuInfoController();
					asc.doPostAction(uriThree, request, response);
				}else if(uriTwo.equals("userManage")) {
					UserManageController auc = new UserManageController();
					auc.doPostAction(uriThree, request, response);
				}	
			}else if(uriOne.equals("professor")) {
				String type = (String)request.getSession().getAttribute("type");
        		//세션- 타입이 일치하지 않으면 화면 초기화
        		if(type == null || (type != null && !type.equals("professor"))) {
        			response.sendRedirect(request.getContextPath());
        			return;
        		}
        		String pno = (String)request.getSession().getAttribute("no");
        		//세션이 없을경우 화면 초기화
        		if(pno == null || (pno != null && pno.equals(""))) {
        			response.sendRedirect(request.getContextPath());
        			return;
        		}
				if(uriTwo.equals("attend")) {
					AttendController pac = new AttendController();
					pac.doPostAction(uriThree, request, response);
				}else if(uriTwo.equals("course")) {
					professor.controller.CourseController pcc = new professor.controller.CourseController();
					pcc.doPostAction(uriThree, request, response);
				}else if(uriTwo.equals("grade")) {
					GradeController pgc = new GradeController();
					pgc.doPostAction(uriThree, request, response);
				}else if(uriTwo.equals("mypage")) {
					MypageController pmc = new MypageController();
					pmc.doPostAction(request, response);
				}else if(uriTwo.equals("notice")) {
					professor.controller.NoticeController pnc = new professor.controller.NoticeController();
					pnc.doPostAction(uriThree, request, response);
				}else if(uriTwo.equals("profInfo")) {
					ProfInfoController ppc = new ProfInfoController();
					ppc.doPostAction(request, response);
				}
			}else if(uriOne.equals("student")) {
				if(uriTwo.equals("acdCourse")) {
					AcdCourseController sac = new AcdCourseController();
					sac.doPostAction(uriThree, request, response);
				}else if(uriTwo.equals("atdGrade")) {
					AtdGradeController sac = new AtdGradeController();
					sac.doPostAction(uriThree, request, response);
				}else if(uriTwo.equals("corReg")) {
					CorRegController scc = new CorRegController();
					scc.doPostAction(uriThree, request, response);
				}else if(uriTwo.equals("mypage")) {
					student.controller.MypageController smc = new student.controller.MypageController();
					smc.doPostAction(request, response);
				}else if(uriTwo.equals("notice")) {
					student.controller.NoticeController snc = new student.controller.NoticeController();
					snc.doPostAction(uriThree, request, response);
				}
			}
			
		}
	}

}
