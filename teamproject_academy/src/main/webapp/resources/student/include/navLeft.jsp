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
					<div class="sb-sidenav-menu-heading">학적/수강관리</div>
						<a class="nav-link" href="<%=request.getContextPath()%>/student/acdCourse/sscheck">학적사항 조회</a> 
<%-- 						<a class="nav-link"	href="< % =request.getContextPath() % >/student/acdCourse/absenseApp">휴복학 신청</a>  --%>
						<a class="nav-link" href="<%=request.getContextPath()%>/student/acdCourse/subcheck">교과목 조회</a> 
<%-- 						<a class="nav-link" href="< % =request.getContextPath() % >/student/acdCourse/scheduleCheck">수강시간표 조회</a> --%>

					<div class="sb-sidenav-menu-heading">출석/성적관리</div>
						<a class="nav-link" href="<%=request.getContextPath()%>/student/atdGrade/attendcheckList">출결확인</a>
						<a class="nav-link" href="<%=request.getContextPath()%>/student/atdGrade/cgradeCheck">성적조회</a>

					<div class="sb-sidenav-menu-heading">수강신청</div>
						<a class="nav-link" href="<%=request.getContextPath()%>/student/corReg/corReg">수강신청</a>
						<a class="nav-link" href="<%=request.getContextPath()%>/student/corReg/cAppCheck">수강신청 현황조회</a>

					<div class="sb-sidenav-menu-heading">마이페이지</div>
						<a class="nav-link" href="<%=request.getContextPath()%>/student/mypage/myInfoModify">비밀번호 변경</a>
				</div>
			</div>
		</nav>
	</div>
</body>
</html>