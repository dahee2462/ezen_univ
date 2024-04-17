<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>수강신청 현황조회</title>
<link href="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/style.min.css" rel="stylesheet" />
<link href="<%=request.getContextPath()%>/resources/share/css/styles.css" rel="stylesheet" />
<script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
<script src="<%=request.getContextPath()%>/resources/share/js/jquery-3.7.1.min.js"></script>
<script src="<%=request.getContextPath()%>/resources/student/js/common.js"></script>
</head>
<body class="sb-nav-fixed">
	<%@ include file="/resources/student/include/navHead.jsp" %>
	<div id="layoutSidenav">
	<%@ include file="/resources/student/include/navLeft.jsp" %>

		<div id="layoutSidenav_content">
			<main>
				<div class="container-fluid px-4">
					<h1 class="mt-4">수강신청 현황조회</h1>
					<ol class="breadcrumb mb-4">
						<li class="breadcrumb-item">수강신청</li>
						<li class="breadcrumb-item active">수강신청 현황조회</li>
					</ol>

					<div class="card mb-4 white">
						<div class="card-header disNone">수강신청 현황조회</div>
						<div class="card-body">
							<div
								class="datatable-wrapper datatable-loading no-footer sortable searchable fixed-columns">

								<div class="datatable-container">

									<table class="datatable-table viewtable checktable">
										<thead>
											<tr>
												<th>번호</th>
												<th>교과목명</th>
												<th>학점</th>
												<th>요일 및 교시(강의실)</th>
												<th>교강사</th>
												<th>신청</th>
											</tr>
										</thead>
										<tbody>
										<c:forEach var="reg" items="${regList}">
											<tr>
												<td>${reg.cno }</td>
												<td>${reg.lname }</td>
												<td>${reg.lcredit }</td>
												<td>${reg.ltime } ( ${reg.lroom } )</td>
												<td>${reg.pname }</td>
												<td>
													<button class="btn btn-primary inline grey"
													onclick="canFn(this,${reg.cno })">취소</button>
												</td>
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
</body>
</html>