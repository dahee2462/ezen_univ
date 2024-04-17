<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>학생관리 페이지</title>
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
					<h1 class="mt-4">학생 사용자 관리</h1>
					<ol class="breadcrumb mb-4">
						<li class="breadcrumb-item">사용자 관리</li>
						<li class="breadcrumb-item active">학생 관리</li>
					</ol>

					<div class="card mb-4 white">
						
						<div class="card-body">
							<div
								class="datatable-wrapper datatable-loading no-footer sortable searchable fixed-columns">
								<!-- 테이블 탑 -->
								<div class="datatable-top">
									<!-- 검색 -->
									<div class="right">
										<form action="stuUserMgList" method="post"
											class="datatable-search inline">
											<input class="datatable-input inline wauto"
												placeholder="이름을 입력하세요" type="search"
												title="Search within table" aria-controls="datatablesSimple">
											<button class="btn btn-primary inline grey">검색</button>
										</form>
									</div>
								</div>
								<!-- 테이블 컨테이너 -->
								<div class="datatable-container">
									<!-- 테이블 -->
									<table class="datatable-table">
										<thead>
											<tr>
												<th><input type="checkbox" name="" id=""></th>
												<th>학생번호</th>
												<th>이름</th>
												<th>학적상태</th>
												<th>연락처</th>
												<th>이메일</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td style="text-align: center">
													<input type="checkbox" name="" id="">
												</td>
												<td>1</td>
												<td><a href="stuUserMgView">홍길동</a></td>
												<td>재학</td>
												<td>010-1111-1111</td>
												<td>ezen@gmail.com</td>
											</tr>
										</tbody>
									</table>
								</div>
							</div>
							<!-- 테이블 바텀 -->
							<div class="datatable-bottom">
							<button class="btn btn-primary inline grey" type="button">삭제</button>
								<!-- 페이징 -->
								<nav class="datatable-pagination d-inline-block offset-5">
									<ul class="datatable-pagination-list">
										<li class="datatable-pagination-list-item datatable-hidden datatable-disabled">
											<a data-page="1" class="datatable-pagination-list-item-link">‹</a>
										</li>
										<li class="datatable-pagination-list-item datatable-active">
											<a data-page="1" class="datatable-pagination-list-item-link">1</a>
										</li>
										<li class="datatable-pagination-list-item">
											<a data-page="2" class="datatable-pagination-list-item-link">2</a>
										</li>
										<li class="datatable-pagination-list-item">
											<a data-page="2" class="datatable-pagination-list-item-link">›</a>
										</li>
									</ul>
								</nav>
								<div class="datatable-bottom ms-auto">
									<a href="stuUserAdd" class="btn btn-primary grey right mright">사용자 추가</a>
								</div>
							</div>
						</div>
					</div>
				</div>
			</main>
			<%@ include file="/resources/admin/include/footer.jsp" %>
		</div>
        </div>
    </body>
</html>