<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 작성페이지</title>
<link href="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/style.min.css" rel="stylesheet" />
<link href="<%=request.getContextPath()%>/resources/admin/css/styles.css" rel="stylesheet" />
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.css" rel="stylesheet">

<script src="<%=request.getContextPath() %>/resources/student/js/jquery-3.7.1.min.js"></script>
<script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
<script src="<%=request.getContextPath() %>/resources/student/js/summernote/summernote-lite.js"></script>
<script src="<%=request.getContextPath() %>/resources/student/js/summernote/lang/summernote-ko-KR.js"></script>
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
					<h1 class="mt-4">공지사항 작성</h1>
					<ol class="breadcrumb mb-4">
						<li class="breadcrumb-item"><a href="noticeList">공지사항</a></li>
						<li class="breadcrumb-item active">공지사항 작성페이지</li>
					</ol>
					<form name="noticeWritefrm" action="noticeWrite" method="post" onsubmit="return false;">
					<div class="card mb-4 white">
						<div class="card-header disNone">공지사항</div>
						<div class="card-body">
							<div
								class="datatable-wrapper datatable-loading no-footer sortable searchable fixed-columns">
								<div class="datatable-container">
									<table class="datatable-table viewtable">
										<tbody>
											<tr>
												<th>제목</th>
												<td colspan="5">
													<input type="text" name="title" class="datatable-input" id="title"
														   placeholder="제목을 입력해주세요.">
												</td>
											</tr>
											<tr>
												<th>작성자</th>
												<td>관리자</td>
												<th>작성일</th>
												<td></td>
												<th>조회수</th>
												<td>0</td>
											</tr>
											<!-- <tr>
												<td colspan="6" style="height: 500px;">본문내용입니다</td>
											</tr>
											<tr>
												<th>첨부파일</th>
												<td colspan="5"></td>
											</tr> -->
										</tbody>
									</table>
									<textarea id="summernote" name="content"></textarea>
								</div>
								<div class="datatable-bottom d-inline-block">
									<button onclick="register()" class="btn btn-primary grey right mright">저장</button>
								</div>
								<div class="datatable-bottom d-inline-block">
									<a href="noticeList" class="btn btn-primary grey right mright">목록</a>
								</div>
							</div>
						</div>
					</div>
					</form>
				</div>
			</main>
			<%@ include file="/resources/admin/include/footer.jsp" %>
		</div>
	</div>
	<script>
		$('#summernote').summernote({
			height: 400,
			lang: "ko-KR"
		});
	</script>
	<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<script src="<%=request.getContextPath()%>/resources/admin/js/notice.js"></script>
</body>
</body>
</html>