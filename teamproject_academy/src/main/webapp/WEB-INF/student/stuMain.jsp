<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="vo.*" %>
<%@ page import="student.dao.MainDAO" %>
<%@ page import="java.sql.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>학생 메인페이지</title>
<link href="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/style.min.css" rel="stylesheet" />
<link href="<%=request.getContextPath()%>/resources/share/css/styles.css" rel="stylesheet" />
</head>
<body class="sb-nav-fixed">
	<%@ include file="/resources/student/include/navHead.jsp" %>

	<div id="layoutSidenav">
	<%@ include file="/resources/student/include/navLeft.jsp" %>

		<div id="layoutSidenav_content">
			<main>
				<div class="container-fluid px-4">
					<h1 class="mt-4">메인 페이지</h1>
					<ol class="breadcrumb mb-4">
						<li class="breadcrumb-item active">학생 메인페이지</li>
					</ol>

					<div class="card mb-4">
						<div class="card-header">
							<b>수강과목</b> 
							<a href="acdCourse/subcheck" class="nav-link right bold"><b>&#43;</b></a>
						</div>
						<div class="card-body">
							<table class="datatable-table checktable">
								<thead>
									<tr>
										<th>번호</th>
										<th>과목명</th>
										<th>교수명</th>
										<th>시간표</th>
										<th>강의실</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="course" items="${courseList}">
										<tr>
											<td>${course.cno}</td>
											<td><a href="acdCourse/curriculum?lno=${course.lno }">${course.lname }</a></td>
											<td>${course.pname }</td>
											<td>${course.ltime }</td>
											<td>${course.lroom }</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
					<div class="card mb-4">
						<div class="card-header">
							<b>공지사항</b>
							<a href="notice/noticeList?nowPage=1&searchAlign=late&searchType=title" class="nav-link right bold"><b>&#43;</b></a>
						</div>
						<div class="card-body">
							<table class="datatable-table checktable">
								<thead>
									<tr>
										<th>번호</th>
										<th>제목</th>
										<th>작성자</th>
										<th>작성일</th>
										<th>조회수</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="cntVar" begin="0" end="4" step="1">
										<tr>
											<td>${cntVar+1}</td>
											<td><a href="notice/noticeView?bno=${board[cntVar].getBno() }">${board[cntVar].getBtitle() }</a></td>
											<td>관리자</td>
											<td>${board[cntVar].getBrdate() }</td>
											<td>${board[cntVar].getBhit() }</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</main>
		<%@ include file="/resources/student/include/footer.jsp" %>
		</div>
	</div>
</body>
</html>
