<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="vo.*"%>
<%@ page import="java.sql.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<link
	href="<%=request.getContextPath()%>/resources/share/css/styles.css" rel="stylesheet" />
<script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
<script src="<%=request.getContextPath()%>/resources/common/js/common.js"></script>
<style>
.bg-primary {
	background-image:
		url("<%=request.getContextPath()%>/resources/common/img/univ1.jpg");
	background-size: cover;
}

.loginBtn {
	margin-left: 42%;
}

.findId {
	margin-left: 20%;
}

.findPw {
	margin-right: 20%;
}
</style>
</head>
<body class="bg-primary">
	<div id="layoutAuthentication">
		<div id="layoutAuthentication_content">
			<main>
				<div class="container">
					<div class="row justify-content-center">
						<div class="col-lg-5">
							<div class="card shadow-lg border-0 rounded-lg mt-5">
								<div class="card-header">
									<h3 class="text-center font-weight-light my-4">로그인</h3>
								</div>
								<div class="card-body">
									<form name="loginForm" action="index" method="post" onsubmit="return false;">
										<div class="form-floating mb-3">
											<input class="form-control" id="id" name="id" type="text" placeholder="아이디" value="${cookie.rememberParam.value }" /> 
											<label for="id">아이디</label>
										</div>
										<div class="form-floating mb-3">
											<input class="form-control" id="pw" name="pw" type="password" placeholder="비밀번호" /> 
											<label for="pw">비밀번호</label>
										</div>
										<div class="form-check mb-3">
											<input class="form-check-input" id="inputRememberId" name="inputRememberId" type="checkbox" value="check" /> 
											<label class="form-check-label" for="inputRememberId">아이디 기억하기</label>
										</div>
										<div class="d-flex align-items-center justify-content-between mt-4 mb-0">
											<button class="btn btn-primary loginBtn" onclick="loginButtonClick();">로그인</button>
										</div>
									</form>
								</div>
								<div class="card-footer text-center py-3 d-flex align-items-center justify-content-between">
									<div class="small findId">
										<a href="findId">아이디 찾기</a>
									</div>
									<div class="small findPw">
										<a href="findPw">비밀번호 찾기</a>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</main>
		</div>
		<div id="layoutAuthentication_footer">
			<footer class="py-4 bg-light mt-auto">
				<div class="container-fluid px-4">
					<div class="d-flex align-items-center justify-content-between small">
						<div class="text-muted">Copyright &copy; 이젠대학교 2024</div>
					</div>
				</div>
			</footer>
		</div>
	</div>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
	<script src="<%=request.getContextPath()%>/resources/share/js/scripts.js"></script>
</body>
</html>