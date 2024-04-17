<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 상세페이지</title>
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
					<h1 class="mt-4">공지사항</h1>
					<ol class="breadcrumb mb-4">
						<li class="breadcrumb-item">공지사항</li>
						<li class="breadcrumb-item active">공지사항 상세페이지</li>
					</ol>

					<div class="card mb-4 white">
						<div class="card-header disNone">공지사항</div>
						<div class="card-body">
							<div
								class="datatable-wrapper datatable-loading no-footer sortable searchable fixed-columns">
								<div class="datatable-container">
									<!-- 테이블 -->
									<table class="datatable-table viewtable">
										<tbody>
											<tr>
												<th>제목</th>
												<td colspan="5">${boardVO.btitle }</td>
											</tr>
											<tr>
												<th>작성자</th>
												<td>관리자</td>
												<th>작성일</th>
												<td>${boardVO.brdate }</td>
												<th>조회수</th>
												<td>${boardVO.bhit }</td>
											</tr>
											<tr>
												<td colspan="6" style="height: 500px;">${boardVO.bcontent }</td>
											</tr>
										</tbody>
									</table>
								</div>
								<div class="datatable-bottom">
									<a href="noticeList?nowPage=1&searchAlign=late&searchType=title" class="btn btn-primary grey right mright">목록</a>
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