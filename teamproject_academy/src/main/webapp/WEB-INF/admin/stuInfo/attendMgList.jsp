<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>출석관리</title>
<link href="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/style.min.css" rel="stylesheet" />
<link href="<%=request.getContextPath()%>/resources/share/css/styles.css" rel="stylesheet" />
<script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
</head>
<body class="sb-nav-fixed">
	<!-- 상단 nav 바 -->
    <%@ include file="/resources/admin/include/navHead.jsp" %>
    <div id="layoutSidenav">
    <!-- 좌측 nav 바 -->
    <%@ include file="/resources/admin/include/navLeft.jsp" %>
		
		<!-- 메인페이지 -->
		<div id="layoutSidenav_content">
			<main>
				<div class="container-fluid px-4">
					<h1 class="mt-4">출석 관리</h1>
					<div class="card mb-4 white">
						<div class="card-header disNone">출석 관리</div>
						<div class="card-body">
							<div
								class="datatable-wrapper datatable-loading no-footer sortable searchable fixed-columns">
								<!-- 테이블 탑 -->
								<div class="datatable-top">
									<!-- 셀렉트 -->

									<!-- 검색 -->
									<div class="right">

										<form action="attendMgList" method="get"
											class="datatable-search inline">
											<select class="datatable-selector" name="lstatus" onchange="this.form.submit()">
												<option value="6" <c:if test="${lstatus eq '6' }">selected</c:if>>모든 강의</option>
												<option value="4" <c:if test="${lstatus eq '4' }">selected</c:if>>진행중인 강의</option>
												<option value="5" <c:if test="${lstatus eq '5' }">selected</c:if>>지난 강의</option>
											</select>
										</form>
									</div>
								</div>
								<!-- 테이블 컨테이너 -->
								<div class="datatable-container">
									<!-- 테이블 -->
									<table class="datatable-table">
										<thead>
											<tr>
												<th>번호</th>
												<th>강의명</th>
												<th>강의년도</th>
												<th>강의학기</th>
												<th>강의실</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${lectureList }" var="lectureVO" varStatus="loop">
												<tr>
													<td>${loop.count }</td>
													<td><a href="attendMgView?lno=${lectureVO.lno }">${lectureVO.lname }</a></td>
													<td>${lectureVO.lyear }</td>
													<td>${lectureVO.lsemester }</td>
													<td>${lectureVO.lroom }</td>
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
			<%@ include file="/resources/admin/include/footer.jsp" %>
		</div>
		
	</div>
	<!-- 좌측 nav 바~푸터까지 END -->
	<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
</body>
</html>