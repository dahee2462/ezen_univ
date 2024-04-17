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
					<h1 class="mt-4">학생 사용자 정보</h1>
					<ol class="breadcrumb mb-4">
						<li class="breadcrumb-item">사용자 관리</li>
						<li class="breadcrumb-item active">학생 관리</li>
					</ol>

					<div class="card mb-4 white">
						<div class="card-header disNone">학생 정보 조회</div>
						<div class="card-body">
							<div class="card mb-4">
								<div class="card-body">사진</div>
							</div>
							<div class="datatable-wrapper datatable-loading no-footer sortable searchable fixed-columns">
								<div class="datatable-container">
									<table class="datatable-table viewtable sschecktable">
										<tbody>
											<tr>
												<th>학번</th>
												<td></td>
												<th>성명</th>
												<td></td>
												<th>주민번호</th>
												<td></td>
											</tr>
											<tr>
												<th>생년월일</th>
												<td></td>
												<th>성별</th>
												<td></td>
												<th>학적상태</th>
												<td></td>
											</tr>
											<tr>
												<th>대학</th>
												<td></td>
												<th>학부</th>
												<td></td>
												<th>전공</th>
												<td></td>
											</tr>
											<tr>
												<th>학년</th>
												<td></td>
												<th>학과석차</th>
												<td></td>
												<th>입학일자</th>
												<td></td>
											</tr>
											<tr>
												<th>제적일자</th>
												<td></td>
												<th>수료일자</th>
												<td></td>
												<th>졸업일자</th>
												<td></td>
											</tr>
											<tr>
												<th>E-mail</th>
												<td></td>
												<th>휴대전화번호</th>
												<td></td>
												<th>집전화번호</th>
												<td></td>
											</tr>
											<tr>
												<th>주소</th>
												<td colspan="3"></td>
												<th>우편번호</th>
												<td></td>
											</tr>
										</tbody>
									</table>
								</div>
							</div>
							<!-- 테이블 바텀 -->
							<div class="datatable-bottom ms-auto d-inline-block">
								<a href="stuUserInfoModify" class="btn btn-primary grey right mright">수정</a>
							</div>
							<div class="datatable-bottom ms-auto d-inline-block">
								<a href="stuUserMgList" class="btn btn-primary grey right mright">목록</a>
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