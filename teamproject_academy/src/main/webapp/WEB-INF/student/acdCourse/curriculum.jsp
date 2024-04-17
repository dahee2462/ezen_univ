<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="vo.*" %>
<%@ page import="java.sql.*"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>강의계획서 조회</title>
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
					<h1 class="mt-4">강의계획서 조회</h1>
					<ol class="breadcrumb mb-4">
						<li class="breadcrumb-item">교과목 조회</li>
						<li class="breadcrumb-item active">강의계획서 조회</li>
					</ol>

					<div class="card mb-4 white">
						<div class="card-header disNone">강의계획서 조회</div>
						<div class="card-body">
							<div class="datatable-wrapper datatable-loading no-footer sortable searchable fixed-columns">
								<div class=""></div>
								<div class="datatable-container">
									<div class="datatable-top">
										<a class="btn btn-primary grey right mright" 
											href="javascript:history.back();">뒤로가기</a>
									</div>
									<table class="datatable-table viewtable sschecktable">
										<tbody>
											<tr>
												<th>교과목명 </th>
												<td colspan="3">${curriMap.lname }</td>
												<th>년도/학기</th>
												<td>${curriMap.lyear }&#47;${curriMap.lsemester }</td>
											</tr>
											<tr>
												<th>강의시간(강의실)</th>
												<td>${curriMap.ltime }&#40; ${curriMap.lroom } &#41;</td>
												<th>학점/시간</th>
												<td>${curriMap.lcredit }&#47;${curriMap.ltime }</td>
												<th>강의상태</th>
												<td>
													<c:if test="${ curriMap.lstatus == '2'}">수강신청 강의</c:if>
													<c:if test="${ curriMap.lstatus == '4'}">진행중인 강의</c:if>
													<c:if test="${ curriMap.lstatus == '5'}">지난 강의</c:if>
												</td>
											</tr>
											<tr>
												<th>담당교수</th>
												<td>${curriMap.pname }</td>
												<th>연락처</th>
												<td>${curriMap.pphone }</td>
												<th>이메일</th>
												<td>${curriMap.pemail }</td>
											</tr>
											<tr>
												<th>장애학생 지원</th>
												<td colspan="5">장애학생 수강 시, 학습 지원(장애학생지원실에 등록된 학생의 요청 시 지정 좌석 제공 등)</td>
											</tr>
											<tr>
												<th>교과목 개요</th>
												<td colspan="5">${curriMap.lintro}</td>
											</tr>
											<tr>
												<th style="vertical-align: middle;">학습목표 및 학습방법</th>
												<td colspan="5">${curriMap.lfocus }</td>
											</tr>
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