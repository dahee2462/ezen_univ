<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>출결확인 상세</title>
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
					<h1 class="mt-4">출결확인</h1>
					<ol class="breadcrumb mb-4">
						<li class="breadcrumb-item">출결확인</li>
						<li class="breadcrumb-item active">출결확인 상세</li>
					</ol>

					<div class="card mb-4 white">
						<div class="card-body">
							<div
								class="datatable-wrapper datatable-loading no-footer sortable searchable fixed-columns">
								<div class=""></div>
								<div class="datatable-container">
									
									<table class="datatable-table viewtable sschecktable">
										<tbody>
											<tr>
												<th>교과목명</th>
												<td colspan="3">${courseInfo.lname}</td>
												<th>년도/학기</th>
												<td>${courseInfo.lyear}/${courseInfo.lsemester}</td>
											</tr>
											<tr>
												<th>강의시간</th>
												<td colspan="3">${courseInfo.ltime} </td>
												<th>강의실</th>
												<td>${courseInfo.lroom}</td>
											</tr>
											<tr>
												<th>학점</th>
												<td colspan="3">${courseInfo.lcredit }</td>
												<th>수강인원</th>
												<td>${courseInfo.lmaxpeople }</td>
											</tr>
										</tbody>
									</table>

									<div class="card mb-4">
										<div class="card-body">
											<h6 class="bold blue">&#10003; 온라인과목 출결기준</h6>
											<p>- 출석인정기간 내에 학습을 완료(진도율 100% 달성)한 경우:출석</p>
											<p>- 출석인정기간 종료 후 지간인정 기한 내에 학습을 완료(진도율 100% 달성)한 경우:지각</p>
											<p>- 지각인정기간 종료 후 학습을 완료(진도율 100% 달성)한 경우:결석</p>
										</div>
									</div>
									<table
										class="datatable-table viewtable sschecktable checktable">
										<thead>
											<tr>
												<th style="width: 10%;">날짜</th>
												<th>출결</th>
											</tr>
										</thead>
										<tbody>
										<c:forEach var="attend" items="${attendList}">
											<tr>
												<td>${attend.attendrdate }</td>
												<td>
												    <c:choose>
												        <c:when test="${attend.attendyn eq 1}">출석</c:when>
												        <c:when test="${attend.attendyn eq 2}">결석</c:when>
												        <c:when test="${attend.attendyn eq 3}">지각</c:when>
												    </c:choose>
												</td>
											</tr>
										</c:forEach>
										</tbody>
									</table>
								</div>
								<div class="datatable-bottom">
									<a class="btn btn-primary grey right mright" href="attendcheckList">뒤로가기</a>
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