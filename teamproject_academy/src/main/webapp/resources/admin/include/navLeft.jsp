<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
</head>
<body>
	<div id="layoutSidenav_nav">
		<nav class="sb-sidenav accordion sb-sidenav-dark" id="sidenavAccordion">
			<div class="sb-sidenav-menu">
				<div class="nav">
	                  <div class="sb-sidenav-menu-heading">공지사항</div>
	                   <a class="nav-link" href="<%=request.getContextPath()%>/admin/notice/noticeList">공지사항 관리</a>
	                  <div class="sb-sidenav-menu-heading">사용자 관리</div>
	                   <a class="nav-link" href="<%=request.getContextPath()%>/admin/userManage/stuUserMgList">학생 관리</a>
	                   <a class="nav-link" href="<%=request.getContextPath()%>/admin/userManage/profUserMgList">교수 관리</a>
	                   
	                   <div class="sb-sidenav-menu-heading">학생 정보</div>
	                   <a class="nav-link" href="<%=request.getContextPath()%>/admin/stuInfo/gradeMgList">학생 성적 관리</a>
	                   <a class="nav-link" href="<%=request.getContextPath()%>/admin/stuInfo/attendMgList">학생 출결 관리</a>
               </div>
			</div>
		</nav>
	</div>
</body>
</html>