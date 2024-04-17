<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>수강시간표 조회</title>
<link href="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/style.min.css" rel="stylesheet" />
<link href="<%=request.getContextPath()%>/resources/share/css/styles.css" rel="stylesheet" />
<script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
    <style>

        th {
            background-color: #f2f2f2;
        }
        td.subject1 {
            background-color: #ffc0cb; /* 과목1 배경색 */
        }
        td.subject2 {
            background-color: #87ceeb; /* 과목2 배경색 */
        }
    </style>
</head>
<body class="sb-nav-fixed">
<%@ include file="/resources/student/include/navHead.jsp" %>
	<div id="layoutSidenav">
<%@ include file="/resources/student/include/navLeft.jsp" %>
		<div id="layoutSidenav_content">
			<main>
				<div class="container-fluid px-4">
					<h1 class="mt-4">수강시간표</h1>
					<ol class="breadcrumb mb-4">
						<li class="breadcrumb-item">학적/수강관리</li>
						<li class="breadcrumb-item active">수강시간표</li>
					</ol>

					<div class="card mb-4">
						<div class="card-header disNone">수강시간표</div>
						<div class="card-body">

							<div class="datatable-wrapper datatable-loading no-footer sortable searchable fixed-columns">
								<div class="datatable-container">
									<table class="datatable-table viewtable checktable timetable">
										<thead>
											<tr>
												<th style="width: 5%;">교시</th>
												<th>월</th>
												<th>화</th>
												<th>수</th>
												<th>목</th>
												<th>금</th>
											</tr>
										</thead>
										<tbody>

       <c:forEach var="schedule" items="${scheduleList}" >
            <tr>
                <td></td>
                <td>${schedule.json_data.subject_name}</td>
                <td>${schedule.json_data.subject_name}</td>
                <td>${schedule.json_data.subject_name}</td>
                <td>${schedule.json_data.subject_name}</td>
                <td>${schedule.json_data.subject_name}</td>
            </tr>
        </c:forEach>
										</tbody>
									</table>

								</div>
								<div class="card mb-4 white">
									<table class="datatable-table viewtable checktable">
										<tr>
											<td>1교시(09:00 ~ 09:50)</td>
											<td>2교시(10:00 ~ 10:50)</td>
											<td>3교시(11:00 ~ 11:50)</td>
											<td>4교시(12:00 ~ 12:50)</td>
										</tr>
										<tr>
											<td>4교시(12:00 ~ 12:50)</td>
											<td>5교시(13:00 ~ 13:50)</td>
											<td>6교시(14:00 ~ 14:50)</td>
											<td>7교시(15:00 ~ 15:50)</td>
										</tr>
										<tr>
											<td>8교시(16:00 ~ 16:50)</td>
											<td>9교시(17:00 ~ 17:50)</td>
											<td>10교시(18:00 ~ 18:50)</td>	
											<td>11교시(19:00 ~ 19:50)</td>	
										</tr>
									</table>
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
</html>