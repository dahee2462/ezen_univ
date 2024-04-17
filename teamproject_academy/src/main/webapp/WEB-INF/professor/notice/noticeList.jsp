<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항</title>
<link href="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/style.min.css" rel="stylesheet" />
<link href="<%=request.getContextPath()%>/resources/share/css/styles.css" rel="stylesheet" />
<script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
</head>
<body class="sb-nav-fixed">
	<!-- 상단 nav 바 -->
	<nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark">
		<!--로고자리-->
		<a class="navbar-brand ps-3" href="<%=request.getContextPath()%>/professor/profMain">이젠대학교</a>
		<!--좌측 nav바 토글(클릭시 없어졌다 있어졌다) -->
		<button class="btn btn-link btn-sm order-1 order-lg-0 me-4 me-lg-0" id="sidebarToggle" href="#!">
			<i class="fas fa-bars"></i>
		</button>
		<!-- 우측상단 사용자 메뉴-->
		<div class="d-none d-md-inline-block form-inline ms-auto me-0 me-md-3 my-2 my-md-0">
			<span class="hello">${name} 교수님 안녕하세요 </span>
		</div>
		<ul class="navbar-nav ms-auto ms-md-0 me-3 me-lg-4">
			<li class="nav-item dropdown">
				<a class="nav-link dropdown-toggle" id="navbarDropdown" href="#"
				role="button" data-bs-toggle="dropdown" aria-expanded="false">
				<i class="fas fa-user fa-fw"></i>
				</a>
				<ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdown">
					<li><a class="dropdown-item" href="<%=request.getContextPath()%>/common/logout">로그아웃</a></li>
				</ul>
			</li>
		</ul>
	</nav>


	<!-- 좌측 nav 바~푸터까지 Start -->
	<div id="layoutSidenav">
	
		<div id="layoutSidenav_nav">
			<nav class="sb-sidenav accordion sb-sidenav-dark" id="sidenavAccordion">
				<div class="sb-sidenav-menu">
					<div class="nav">
						<div class="sb-sidenav-menu-heading">출석관리</div>
						<a class="nav-link" href="<%=request.getContextPath()%>/professor/attend/attendMgList">출석관리</a>

						<div class="sb-sidenav-menu-heading">성적관리</div>
						<a class="nav-link" href="<%=request.getContextPath()%>/professor/grade/gradeMgList">성적관리</a>


						<div class="sb-sidenav-menu-heading">교수정보</div>
						<a class="nav-link" href="<%=request.getContextPath()%>/professor/profInfo/profInfo">교수정보 조회</a>

						<div class="sb-sidenav-menu-heading">마이페이지</div>
						<a class="nav-link" href="<%=request.getContextPath()%>/professor/mypage/myInfoModify">비밀번호 변경</a>
					</div>
				</div>
			</nav>
		</div>
		
		
		<!-- 메인페이지 -->
		<div id="layoutSidenav_content">
			<main>
				<div class="container-fluid px-4">
					<h1 class="mt-4">공지사항</h1>
					<ol class="breadcrumb mb-4">
						<li class="breadcrumb-item">공지사항</li>
						<li class="breadcrumb-item active">공지사항 목록페이지</li>
					</ol>

					<div class="card mb-4 white">
						<div class="card-header disNone">공지사항</div>
						<div class="card-body">
						
							<div class="datatable-wrapper datatable-loading no-footer sortable searchable fixed-columns">
								
								<!-- 테이블 탑 -->
								<div class="datatable-top">
									<form action="noticeList" method="get" class="datatable-search right">
										<!-- 셀렉트 -->
										<div class="datatable-dropdown ">
											<label> <select class="datatable-selector" name="searchAlign">
													<option value="late" <c:if test="${param.searchAlign eq 'late' }"> selected </c:if> >최신순</option>
													<option value="hit" <c:if test="${param.searchAlign eq 'hit' }"> selected </c:if>>인기순</option>
											</select>
											</label>
										
										<!-- 검색 -->
										
											<select class="datatable-selector" name="searchType">
												<option value="title" <c:if test="${param.searchType eq 'title' }"> selected </c:if>>제목</option>
												<option value="content" <c:if test="${param.searchType eq 'content' }"> selected </c:if>>내용</option>
											</select>
											<input class="datatable-input inline wauto" name="searchValue" placeholder="검색어를 입력하세요" type="search"
												title="Search within table" aria-controls="datatablesSimple">
											<button class="btn btn-primary inline grey">검색</button>
										</div>
									</form>
								</div>
							
								<!-- 테이블 컨테이너 -->
								<div class="datatable-container">
						
									<!-- 테이블 -->
									<table class="datatable-table">
										<thead>
											<tr>
												<th>번호</th>
												<th>제목</th>
												<th>작성일</th>
												<th>조회수</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${board }" var="board" varStatus="loop">
												<tr>
													<td>${loop.count+pagingVO.start-1}</td>
													<td><a href="noticeView?bno=${board.getBno() }">${board.getBtitle() }</a></td>
													<td>${board.getBrdate() }</td>
													<td>${board.getBhit() }</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
							</div>
							<!-- 테이블 바텀 -->
							<div class="datatable-bottom">
								<!-- 페이징 -->
								<nav class="datatable-pagination" style="display: inline-block; margin-top: 0.75rem; margin-left: 32rem;">
								    <ul class="datatable-pagination-list">
								        <c:if test="${pagingVO.getStartPage() > pagingVO.getCntPage()}">
								            <li class="datatable-pagination-list-item">
								                <a class="datatable-pagination-list-item-link"
								                   href="noticeList?nowPage=${pagingVO.startPage - 1}&searchAlign=${param.searchAlign}&searchType=${param.searchType}&searchValue=${param.searchValue}">‹</a>
								            </li>
								        </c:if>
								
								        <c:forEach var="page"  begin="${pagingVO.startPage}" end="${pagingVO.endPage}" step="1" >
						                    <li class="datatable-pagination-list-item">
						                        <a href="noticeList?nowPage=${page}&searchAlign=${param.searchAlign}&searchType=${param.searchType}&searchValue=${param.searchValue}"
						                           class="datatable-pagination-list-item-link">${page}</a>
						                    </li>
								        </c:forEach>
								
								        <c:if test="${pagingVO.endPage < pagingVO.lastPage}">
								            <li class="datatable-pagination-list-item">
								                <a class="datatable-pagination-list-item-link"
								                href="noticeList?nowPage=${pagingVO.endPage + 1}&searchAlign=${param.searchAlign}&searchType=${param.searchType}&searchValue=${param.searchValue}">›</a>
								            </li>
								        </c:if>
								    </ul>
								</nav>
							</div>
						</div>
					</div>
				</div>
			</main>
			<footer class="py-4 bg-light mt-auto">
				<div class="container-fluid px-4">
					<div
						class="d-flex align-items-center justify-content-between small">
						<div class="text-muted">Copyright &copy; 이젠대학교 2023</div>
					</div>
				</div>
			</footer>
		</div>
		<!-- 메인페이지 END -->
		
	</div>
	<!-- 좌측 nav 바~푸터까지 END -->
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
	<script src="<%=request.getContextPath()%>/resources/share/js/scripts.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/umd/simple-datatables.min.js" crossorigin="anonymous"></script>
	<script src="<%=request.getContextPath()%>/resources/share/js/datatables-simple-demo.js"></script>
</body>
</html>