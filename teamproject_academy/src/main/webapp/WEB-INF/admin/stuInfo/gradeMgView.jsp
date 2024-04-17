<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>성적관리</title>
<link href="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/style.min.css" rel="stylesheet" />
<link href="<%=request.getContextPath()%>/resources/share/css/styles.css" rel="stylesheet" />
<script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>

<script type="text/javascript" src="https://cdn.jsdelivr.net/jquery/latest/jquery.min.js"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.min.js"></script>
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
					<h1 class="mt-4">성적 관리</h1>
					<div class="card mb-4 white">
						<div class="card-header disNone">성적 관리</div>
						<div class="card-body">
							<div class="datatable-wrapper datatable-loading no-footer sortable searchable fixed-columns">
							
								<!-- 테이블 탑 -->
								<div class="datatable-top">
									
								</div>
								
								<!-- 테이블 컨테이너 -->
								<div class="datatable-container">
									<!-- 테이블 -->
									<form>
										<table class="datatable-table">
											<thead>
												<tr>
													<th>성명</th>
													<th>학번</th>
													<th>전화번호</th>
													<th>성적</th>
													<th>관리</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${courseList }" var="courseVO" varStatus="loop">
													<tr>
														<td>${courseVO.sname }</td>
														<td>${courseVO.sid }</td>
														<td>${courseVO.sphone }</td>
														<td>${courseVO.cgrade}/${courseVO.cgradeupdater}</td>
														<td>
															<select class="datatable-selector right" onchange="gradeChange(${courseVO.cno },this);">
																<option <c:if test="${courseVO.cgrade eq 'A' }">selected</c:if>>A</option>
																<option <c:if test="${courseVO.cgrade eq 'B' }">selected</c:if>>B</option>
																<option <c:if test="${courseVO.cgrade eq 'C' }">selected</c:if>>C</option>
																<option <c:if test="${courseVO.cgrade eq 'D' }">selected</c:if>>D</option>
																<option <c:if test="${courseVO.cgrade eq 'F' }">selected</c:if>>F</option>
															</select>	
														</td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
										
									</form>
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
	<script src="<%=request.getContextPath()%>/resources/admin/js/admin.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
	<script src="<%=request.getContextPath()%>/resources/share/js/scripts.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/umd/simple-datatables.min.js" crossorigin="anonymous"></script>
	<script src="<%=request.getContextPath()%>/resources/share/js/datatables-simple-demo.js"></script>
	<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
</body>
</html>