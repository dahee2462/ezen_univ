package admin.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.mindrot.jbcrypt.BCrypt;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import admin.dao.UserManageDAO;
import vo.PagingVO;
import vo.ProfessorVO;

public class UserManageController {
	
	public void doAction(String threeUriParam, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		switch(threeUriParam) {
			case "stuUserAdd":
				stuUserAdd(request,response);
				break;
			case "profUserAdd":
				profUserAdd(request,response);
				break;
			case "stuUserInfoModify":
				stuUserInfoModify(request,response);
				break;
			case "profUserInfoModify":
				profUserInfoModify(request,response);
				break;
			case "stuUserMgList":
				stuUserMgList(request,response);	
				break;
			case "profUserMgList":
				profUserMgList(request,response);	
				break;
			case "stuUserMgView":
				stuUserMgView(request,response);	
				break;
			case "profUserMgView":
				profUserMgView(request,response);	
				break;
			default:
				break;
		}
		
//		String threeUri = threeUriParam.split("\\.")[0];
//
//		if(threeUri.equals("stuUserAdd")) {
//			stuUserAdd(request,response);
//		}else if(threeUri.equals("profUserAdd")) {
//			profUserAdd(request,response);
//		}else if(threeUri.equals("stuUserInfoModify")) {
//			stuUserInfoModify(request,response);			
//		}else if(threeUri.equals("profUserInfoModify")) {
//			profUserInfoModify(request,response);			
//		}else if(threeUri.equals("stuUserMgList")) {
//			stuUserMgList(request,response);			
//		}else if(threeUri.equals("profUserMgList")) {
//			profUserMgList(request,response);			
//		}else if(threeUri.equals("stuUserMgView")) {
//			stuUserMgView(request,response);			
//		}else if(threeUri.equals("profUserMgView")) {
//			profUserMgView(request,response);			
//		}
	}
	
	public void doPostAction(String threeUriParam, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String threeUri = threeUriParam.split("\\.")[0];

		if(threeUri.equals("stuUserAdd")) {
			PoststuUserAdd(request,response);
		}else if(threeUri.equals("profUserAdd")) {
			PostprofUserAdd(request,response);			
		}else if(threeUri.equals("stuUserInfoModify")) {
			PoststuUserInfoModify(request,response);			
		}else if(threeUri.equals("profUserInfoModify")) {
			PostprofUserInfoModify(request,response);			
		}else if(threeUri.equals("stuUserMgList")) {
			PoststuUserMgList(request,response);			
		}else if(threeUri.equals("profUserMgList")) {
			PostprofUserMgList(request,response);			
		}else if(threeUri.equals("stuUserMgView")) {
			PoststuUserMgView(request,response);			
		}else if(threeUri.equals("profUserMgView")) {
			PostprofUserMgView(request,response);			
		}
	}
	
	public void stuUserAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/admin/userManage/stuUserAdd.jsp");
		rd.forward(request, response);
		
		
	}
	
	public void PoststuUserAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/admin/userManage/stuUserAdd.jsp");
		rd.forward(request, response);
		
		
	}
	
	public void profUserAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/admin/userManage/profUserAdd.jsp");
		rd.forward(request, response);
		
		
	}
	
	public void PostprofUserAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		
		String directory = "E:\\98.팀프로젝트\\02. 2차프로젝트\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\teamproject_academy\\upload\\profUpload";
//		String directory = "C:\\Users\\MYCOM\\git\\second_team_project\\teamproject_academy\\src\\main\\webapp\\upload\\profUpload";
//		String directory = "D:\\JYH\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp1\\wtpwebapps\\teamproject_academy\\upload\\profUpload";
		
		int sizeLimit = 100 * 1024 * 1024;
		if (request.getContentType() != null && request.getContentType().startsWith("multipart/form-data")) {
	        try {
	            MultipartRequest multi = new MultipartRequest(request, directory, sizeLimit, "UTF-8", new DefaultFileRenamePolicy());

//	    		교수유저 추가
	    		ProfessorVO professorVO = new ProfessorVO();
	    		
	    		professorVO.setPid(multi.getParameter("pid")); 
	    		String ppwParam = multi.getParameter("pid");
	    		professorVO.setPpw(BCrypt.hashpw(ppwParam, BCrypt.gensalt()));
	    		professorVO.setPname(multi.getParameter("pname"));
	    		professorVO.setPregNo1(multi.getParameter("pregNo1"));
	    		String pregNo2Param = multi.getParameter("pregNo2");
	    		professorVO.setPregNo2(BCrypt.hashpw(pregNo2Param, BCrypt.gensalt()));
	    		professorVO.setPbirth(multi.getParameter("pbirth"));
	    		professorVO.setPgender(multi.getParameter("pgender"));
	    		professorVO.setPposition(multi.getParameter("pposition"));
	    		professorVO.setPuniv(multi.getParameter("puniv"));
	    		professorVO.setPfaculty(multi.getParameter("pfaculty"));
	    		professorVO.setPmajor(multi.getParameter("pmajor"));
	    		professorVO.setPdegree(multi.getParameter("pdegree"));
	    		professorVO.setPlab(multi.getParameter("plab"));
	    		professorVO.setPappointDate(multi.getParameter("pappointDate"));
	    		professorVO.setPemail(multi.getParameter("pemail"));
	    		professorVO.setPphone(multi.getParameter("pphone"));
	    		professorVO.setPcall(multi.getParameter("pcall"));
	    		professorVO.setPaddr(multi.getParameter("paddr"));
	    		professorVO.setPzipCode(multi.getParameter("pzipCode"));
	    		
	    		UserManageDAO userManageDAO = new UserManageDAO();
	    		List<ProfessorVO> profAdd = userManageDAO.insertProf(professorVO);
	    		
	    		response.setContentType("text/html; charset=utf-8");
	    		response.setCharacterEncoding("UTF-8");
	    		
	    		request.setAttribute("profAdd", profAdd);
	    		
	            // 파일 유효성 검사 (예시: 확장자 제한)
	            String allowedExtension = "jpg|jpeg|png";
	            String originalFileName = multi.getOriginalFileName("profPhoto");
	            
	            if (originalFileName != null && !originalFileName.isEmpty()) {
	                String fileExtension = originalFileName.substring(originalFileName.lastIndexOf(".") + 1).toLowerCase();
	                
	                if (!Arrays.asList(allowedExtension.split("\\|")).contains(fileExtension)) {
	                    throw new FileUploadException("허용되지 않는 파일 형식입니다.");
	                }
	            }

	            professorVO.setFrealnm(originalFileName); // 넘어온 파일명
	            professorVO.setForiginnm(multi.getFilesystemName("profPhoto")); // 원본 파일명

	            ProfessorVO profPhoto = userManageDAO.insertProfPhoto(professorVO);
	            
	            response.setContentType("text/html; charset=utf-8");
	    		response.setCharacterEncoding("UTF-8");

	            request.setAttribute("profPhoto", profPhoto);

	        } catch (FileUploadException e) {
	            // 파일 업로드 실패 시 예외 처리
	            request.setAttribute("uploadError", e.getMessage());
	        }
	    }
		
		UserManageDAO userManageDAO = new UserManageDAO();
		ProfessorVO professorVO = userManageDAO.viewProfPhoto();
		
		request.setAttribute("professorVO", professorVO);
		
		response.sendRedirect(request.getContextPath()+"/admin/userManage/profUserMgList");
		
		
	}
	
	public void stuUserInfoModify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/admin/userManage/stuUserInfoModify.jsp");
		rd.forward(request, response);
		
		
	}
	public void PoststuUserInfoModify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/admin/userManage/stuUserInfoModify.jsp");
		rd.forward(request, response);
		
		
	}
	public void profUserInfoModify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String pnoParam = request.getParameter("pno");
		int pno = 0;
		try {
			pno = Integer.parseInt(pnoParam);
		}catch(NumberFormatException e) {}
		
		UserManageDAO userManageDAO = new UserManageDAO();
		
		ProfessorVO professorVO = userManageDAO.viewProf(pno);
		
		request.setAttribute("professorVO", professorVO);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/admin/userManage/profUserInfoModify.jsp");
		rd.forward(request, response);
		
		
	}
	public void PostprofUserInfoModify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String pnoParam = request.getParameter("pno");
		int pno = 0;
		try {
			pno = Integer.parseInt(pnoParam);
		}catch(NumberFormatException e) {}
		
		UserManageDAO userManageDAO = new UserManageDAO();
				
		String ppositionParam = request.getParameter("pposition");
		String plabParam = request.getParameter("plab");
		String pemailParam = request.getParameter("pemail");
		String pphoneParam = request.getParameter("pphone");
		String pcallParam = request.getParameter("pcall");
		String paddrParam = request.getParameter("paddr");
		String pzipCodeParam = request.getParameter("pzipCode");
		
		
		ProfessorVO updateprof = userManageDAO.updateprof(pno, ppositionParam, plabParam, pemailParam, 
														pphoneParam, pcallParam, paddrParam, pzipCodeParam);
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		
		String encodedPno = URLEncoder.encode(String.valueOf(pno), "UTF-8");
	    response.sendRedirect(request.getContextPath() + "/admin/userManage/profUserMgView?pno=" + encodedPno);
		
		
	}
	public void stuUserMgList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/admin/userManage/stuUserMgList.jsp");
		rd.forward(request, response);
		
		
	}
	public void PoststuUserMgList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/admin/userManage/stuUserMgList.jsp");
		rd.forward(request, response);
		
		
	}

//	교수 사용자 관리 페이지 
//	GET	
	public void profUserMgList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		교수 목록		
		UserManageDAO userManageDAO = new UserManageDAO();
		List<ProfessorVO> professorList = userManageDAO.selectProf();
		
		request.setAttribute("professorList", professorList);
		
		int totalCnt = professorList.size();
		
//		검색
		String searchValue = request.getParameter("searchValue");
		
// 		검색 결과가 있을 경우 검색 결과를 전달
		if (searchValue != null && !searchValue.isEmpty()) {
	        List<ProfessorVO> searchResults = userManageDAO.searchProf(searchValue);
	        request.setAttribute("professorList", searchResults);
	        
	        totalCnt = searchResults.size();
	    }
		
//		페이징
		int nowPage = 1;
	    int perPage = 5;

	    PagingVO pagingVO = new PagingVO(nowPage, totalCnt, perPage);

	    List<ProfessorVO> pagingList = userManageDAO.selectProfPaging(pagingVO.getStart(), perPage);

	    request.setAttribute("pagingVO", pagingVO);
	    request.setAttribute("pagingList", pagingList);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/admin/userManage/profUserMgList.jsp");
		rd.forward(request, response);
	}
	
//	POST
	public void PostprofUserMgList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/admin/userManage/profUserMgList.jsp");
		rd.forward(request, response);
		
		
	}
	public void stuUserMgView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/admin/userManage/stuUserMgView.jsp");
		rd.forward(request, response);
		
		
	}
	public void PoststuUserMgView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/admin/userManage/stuUserMgView.jsp");
		rd.forward(request, response);
		
		
	}
	
	public void profUserMgView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String pnoParam = request.getParameter("pno");
		int pno = 0;
		try {
			pno = Integer.parseInt(pnoParam);
		}catch(NumberFormatException e) {
			
		}
            
        UserManageDAO userManageDAO = new UserManageDAO();
        ProfessorVO professorVO = userManageDAO.viewProf(pno);
        
        request.setAttribute("professorVO", professorVO);
        
        response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("UTF-8");
        
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/admin/userManage/profUserMgView.jsp");
		rd.forward(request, response);
		
		
	}
	
	public void PostprofUserMgView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/admin/userManage/profUserMgView.jsp");
		rd.forward(request, response);
	}

	
	
}
