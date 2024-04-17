<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>휴복학 신청</title>
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
					<h1 class="mt-4">휴복학 신청</h1>
					<ol class="breadcrumb mb-4">
						<li class="breadcrumb-item">학적/수강관리</li>
						<li class="breadcrumb-item active">휴복학 신청</li>
					</ol>

					<div class="card mb-4 white">
						<div class="card-header disNone">휴복학 신청</div>
						<div class="card-body">
							<table class="datatable-table viewtable checktable" style="width: 20%;">
								<tbody>
									<tr>
										<th>학적상태</th>
										<td>${absenseList[0].sstatus }</td>
									</tr>
								</tbody>
							</table>

							<div class="card mb-4">
								<div class="card-body">
									<h6 class="bold">휴복학 신청에 대한 규정 및 설명</h6>
									<p>* 휴학기간</p>
									<p>- 일반휴학은 1년 또는 학기 단위로 실시하며, 통산하여 6학기를 초과할 수 없다.</p>
									<p>- 휴학기간을 연장하고자 할 때에는 휴학기간 만료 7일전까지 휴학 연장원을 제출하여 총장의 허가를
										받아야 함.</p>
									<p>* 신(편)입생의 휴학</p>
									<p>- 신(편)입생에게는 입학이후 1년간 휴학을 허가하지 아니한다. 다만, 법령에 의한 의무이행,
										임신.출산.육아, 질병, 대학원생, 기타 부득이한 사유로 인하여 수학할 수 없는 경우는 예외로 한다.</p>
									<p>* 복학</p>
									<p>- 복학은 매 학기 등록기간 내에 소정의 복학신청서를 제출해야 하며, 수업일수 1/3이상 경과 되었을
										때에는 복학을 허가하지 아니함.</p>
									<p>- 군입대 후 전역예정자인 경우, 수업일수 2/3이상 출석이 가능함을 입증하는 서류(휴가증, 전역예정
										증명서, 부대장의 복학추천서 등)를 첨부하여 제출하면 복학을 허가함.</p>
									<p>- 1학년 재학생(2개학기 이하 이수자)은 역학기 복학을 할 수 없음.</p>
									<p></p>
									<p>&#10003; 관련규정</p>
									<p>- 학칙 제47조, 제48조, 학사운영규정 제18조, 제19조, 제20조, 제21조</p>
								</div>
							</div>
							<h3>신청 내역</h3>
							<table class="datatable-table viewtable checktable">
								<thead>
									<tr>
										<th>휴복학번호</th>
										<th>신청구분</th>
										<th>신청일자</th>
										<th>처리상태</th>
										<th>처리일자</th>

									</tr>
								</thead>
								<tbody>
								<c:forEach var="absense" items="${absenseList}">
									<tr>
										<td>${absense.abseno}</td>
										<td>${absense.abseinfo }</td>
										<td>${absense.abserdate}</td>
										<td>${absense.absestatus}</td>
										<td>${absense.absepdate}</td>
									</tr>
								</c:forEach>
								</tbody>
								
							</table>
							<button class="btn btn-primary inline grey">휴학신청</button>
							<button class="btn btn-primary inline grey">복학신청</button>
						</div>
					</div>

				</div>
			</main>
<%@ include file="/resources/student/include/footer.jsp" %>
		</div>
	</div>


</body>
</html>