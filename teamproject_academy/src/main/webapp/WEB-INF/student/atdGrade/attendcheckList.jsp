<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>출석확인 목록</title>
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
					<h1 class="mt-4">출석확인</h1>
					<ol class="breadcrumb mb-4">
						<li class="breadcrumb-item">출석/성적관리</li>
						<li class="breadcrumb-item active">출석확인</li>
					</ol>
					<div class="card mb-4 white">
						<div class="card-header disNone">출석확인</div>
						<div class="card-body">
							<div class="card mb-4">
								<div class="card-body">
									<h6 class="bold">출결관리 및 출석점수 관련 규정</h6>
									<p>* 각 교과목을 담당하는 교수는 매 수업시간 학생의 출석 및 결석사실을 확인하여 출석부에 기록해야
										한다.</p>
									<p>* 각 교과목을 담당하는 교수는 수업 시작 후 일정시간이 지난 시점 전후로 지각이나 결석을 판단하는
										기준을 정할 수 있으며 해당 기준은 강의계획서 및 학기 초 학생들에게 공지하여야 한다.</p>
									<p>* 학기 중 실제 수업시간 수의 4분의 1 이상 결석한 과목에 대하여는 해당 학기의 학업성적을
										인정하지 않는다.</p>
									<p>&#10003; 관련규정</p>
									<p>- 학칙 제55조(출석의무), 제55조2(출결관리)</p>
									<p>- 수업운영에 관한 규정 제6조(출결관리), 제8조(출석점수)</p>

								</div>
							</div>
							<div class="datatable-wrapper datatable-loading no-footer sortable searchable fixed-columns">

								<div class="datatable-container">

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
													<td>${course.cno }</td>
													<td><a href="attendcheckView?cno=${course.cno }">${course.lname }</a></td>
													<td>${course.pname }</td>
													<td>${course.lname }</td>
													<td>${course.lroom }</td>
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