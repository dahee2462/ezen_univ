<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 변경</title>
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
					<h1 class="mt-4">비밀번호 변경</h1>
					<ol class="breadcrumb mb-4">
						<li class="breadcrumb-item">마이페이지</li>
						<li class="breadcrumb-item active">비밀번호 변경</li>
					</ol>

					<div class="card mb-4">
						<div class="card-header ">비밀번호 변경</div>
						<div class="card-body">

							<div
								class="datatable-wrapper datatable-loading no-footer sortable searchable fixed-columns">
								<div class="datatable-container">
									<form method="post" action="myInfoModify">
										<table class="datatable-table viewtable sschecktable">
											<tbody>
												<tr>
													<th>* 현재 비밀번호</th>
													<td colspan="5"><input type="password" name="oldpw"></td>
												</tr>
												<tr>
													<th>* 새 비밀번호</th>
													<td colspan="5"><input type="password" name="newpw"></td>
												</tr>
												<tr>
													<th>* 비밀번호 확인</th>
													<td colspan="5"><input type="password" name="checknewpw"></td>
												</tr>
											</tbody>
										</table>
										<div class="card mb-4">
											<div class="card-body">
												<h6 class="bold">*교육부 정보보안기본지침 제31조(비밀번호 관리)에 의거</h6>
												<p>- 사용자 계정(ID)과 동일하지 않은 값으로 지정</p>
												<p>- 숫자, 문자, 특수문자를 혼합하여 9자리 이상 지정</p>
												<p>- 동일단어 또는 숫자를 반복하여 사용금지</p>
											</div>
										</div>
										<button class="btn btn-primary inline grey">저장</button>
									</form>
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