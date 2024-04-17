<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>성적조회</title>
<link href="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/style.min.css" rel="stylesheet" />
<link href="<%=request.getContextPath()%>/resources/share/css/styles.css" rel="stylesheet" />
<script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
</head>
<body class="sb-nav-fixed">
	<%@ include file="/resources/student/include/navHead.jsp" %>
	<div id="layoutSidenav">
	<%@ include file="/resources/student/include/navLeft.jsp" %>

		<div id="layoutSidenav_content">
			<main>
				<div class="container-fluid px-4">
					<h1 class="mt-4">성적조회</h1>
					<ol class="breadcrumb mb-4">
						<li class="breadcrumb-item">출석/성적관리</li>
						<li class="breadcrumb-item active">성적조회</li>
					</ol>

					<div class="card mb-4 white">
						<div class="card-header disNone">성적 조회</div>
						<div class="card-body">

							<div class="datatable-wrapper datatable-loading no-footer sortable searchable fixed-columns">
								<div class="datatable-container">
									<!-- 테이블 -->
									<table class="datatable-table viewtable checktable">
										<thead>
											<tr>
												<th>구분</th>
												<th>학과/학부</th>
												<th>학번</th>
												<th>이름</th>
												<th>학년</th>
												<th>학적상태</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td>학부</td>
												<td>${student.smajor }</td>
												<td>${student.sid }</td>
												<td>${student.sname }</td>
												<td>${student.sgrade }</td>
												<td>
													<c:if test="${ student.sstatus == '0'}">재학</c:if>
													<c:if test="${ student.sstatus == '1'}">휴학</c:if>
												</td>
											</tr>
										</tbody>
									</table>

								</div>
								<div class="datatable-top">
									<div class="right">
										<form action="cgradeCheck" method="get" class="datatable-search inline">
											<select class="datatable-selector"  name="lyearType">
												<option value="2024" 
													<c:if test="${param.lyear eq '2024'}">selected</c:if>>
												2024</option>
												<option value="2023" 
													<c:if test="${param.lyear eq '2023'}">selected</c:if>>
												2023</option>
											</select>
											<select class="datatable-selector"  name="lsemesterType">
													<option value="1" 
														<c:if test="${param.lsemester eq '1'}">selected</c:if>>
													1학기</option>
													<option value="2" 
														<c:if test="${param.lsemester eq '2'}">selected</c:if>>
													2학기</option>
											</select>
												<button class="btn btn-primary inline grey">조회</button>
										</form>
									</div>
								</div>

								<div class="datatable-container">

									<table class="datatable-table viewtable checktable">
										<thead>
											<tr>
												<th>번호</th>
												<th>과목명</th>
												<th>학점</th>
												<th>성적</th>
											</tr>
										</thead>
										<tbody>
										<c:forEach var="grade" items="${gradeList}">
											<tr>
												<td>${grade.cno }</td>
												<td>${grade.lname }</td>
												<td>${grade.lcredit }</td>
												<td>${grade.cgrade }</td>
											</tr>
										</c:forEach>
										</tbody>
									</table>

								</div>
							</div>

						</div>
					</div>
				</div>
			</main>
<%@ include file="/resources/student/include/footer.jsp" %>
		</div>
	</div>
</body>
</html>