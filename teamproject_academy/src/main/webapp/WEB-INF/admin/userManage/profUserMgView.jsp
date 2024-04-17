<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>학생관리 페이지</title>
<link href="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/style.min.css" rel="stylesheet" />
<link href="<%=request.getContextPath()%>/resources/admin/css/styles.css" rel="stylesheet" />
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
					<h1 class="mt-4">교수 사용자 정보</h1>
					<ol class="breadcrumb mb-4">
						<li class="breadcrumb-item">사용자 관리</li>
						<li class="breadcrumb-item active">교수 관리</li>
					</ol>
					<div class="card mb-4 white">
						<div class="card-header disNone">교수 정보 조회</div>
						<!-- 사진 첨부파일 -->
						<div class="card-body">
							<div class="card mb-4" style="width: 247px; height: 292px;" id="photoPreview">
								<img src="<%=request.getContextPath()%>/upload/profUpload/${professorVO.foriginnm}">
							</div>
							<div
								class="datatable-wrapper datatable-loading no-footer sortable searchable fixed-columns">
								<div class="datatable-container">
								<input type="hidden" name="pno" value="${professorVO.pno}">
									<!-- 테이블 -->
									<table class="datatable-table viewtable sschecktable">
										<tbody>
											<tr>
												<th>교번</th>
												<td>${professorVO.pid }</td>
												<th>성명</th>
												<td>${professorVO.pname }</td>
												<th>주민번호</th>
												<td>${professorVO.pregNo1 } + *******</td>
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
												<th>연구실 전화번호</th>
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
							<!-- 테이블 바텀 -->
							<div class="datatable-bottom ms-auto d-inline-block">
								<a href="profUserInfoModify?pno=${professorVO.pno }" class="btn btn-primary grey right mright">수정</a>
							</div>
							<div class="datatable-bottom ms-auto d-inline-block">
								<a href="profUserMgList" class="btn btn-primary grey right mright">목록</a>
							</div>
						</div>
					</div>
				</div>
			</main>
			<%@ include file="/resources/admin/include/footer.jsp" %>
		</div>
        </div>
        <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
    </body>
</html>