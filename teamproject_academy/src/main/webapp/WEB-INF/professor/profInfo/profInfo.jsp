<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>교수정보 조회</title>
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
		
		<!-- 메인페이지 START -->
		<div id="layoutSidenav_content">
			<main>
				<div class="container-fluid px-4">
					<h1 class="mt-4">교수정보 조회</h1>
					<ol class="breadcrumb mb-4">
						<li class="breadcrumb-item">교수정보 조회</li>
						<li class="breadcrumb-item active">내정보 조회</li>
					</ol>
					
					
					<div class="card mb-4 white">
						<div class="card-header disNone">교수정보 조회</div>
						<div class="card-body">
							<div class="card mb-4">
								<div class="card-body">
								<img src="<%=request.getContextPath()%>/upload/profUpload/${professorVO.foriginnm}" alt="사진" height="200px">
								</div>
							</div>
							<div
								class="datatable-wrapper datatable-loading no-footer sortable searchable fixed-columns">
								<div class="datatable-container">
									<!-- 테이블 -->
									<table class="datatable-table viewtable sschecktable">
										<tbody>
											<tr>
												<th>교번</th>
												<td>${professorVO.pid }</td>
												<th>성명</th>
												<td>${professorVO.pname }</td>
												<th>주민번호</th>
												<td>${professorVO.pregNo1 }-*******</td>
											</tr>
											<tr>
												<th>생년월일</th>
												<td>${professorVO.pbirth }</td>
												<th>성별</th>
												<td>${professorVO.pgender }</td>
												<th>직급</th>
												<td>${professorVO.pposition }</td>
											</tr>
											<tr>
												<th>대학</th>
												<td>${professorVO.puniv }</td>
												<th>학부</th>
												<td>${professorVO.pfaculty }</td>
												<th>전공</th>
												<td>${professorVO.pmajor }</td>
											</tr>
											<tr>
												<th>학위</th>
												<td>${professorVO.pdegree }</td>
												<th>연구실</th>
												<td>${professorVO.plab }</td>
												<th>임용일자</th>
												<td>${professorVO.pappointDate }</td>
											</tr>
											<tr>
												<th>E-mail</th>
												<td>${professorVO.pemail }</td>
												<th>휴대전화번호</th>
												<td>${professorVO.pphone }</td>
												<th>집전화번호</th>
												<td>${professorVO.pcall }</td>
											</tr>
											<tr>
												<th>주소</th>
												<td colspan="3">${professorVO.paddr }</td>
												<th>우편번호</th>
												<td>${professorVO.pzipCode }</td>
											</tr>

										</tbody>
									</table>
								</div>
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