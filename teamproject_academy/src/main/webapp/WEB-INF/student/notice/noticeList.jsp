<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 목록</title>
<link href="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/style.min.css" rel="stylesheet" />
<link href="<%=request.getContextPath()%>/resources/share/css/styles.css" rel="stylesheet" />
<script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
</head>
<body class="sb-nav-fixed">
		<%@ include file="/resources/student/include/navHead.jsp" %>

	<div id="layoutSidenav">
	<%@ include file="/resources/student/include/navLeft.jsp" %>
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
							<div
								class="datatable-wrapper datatable-loading no-footer sortable searchable fixed-columns">
								<!-- 테이블 탑 -->
								<div class="datatable-top">
									<form action="noticeList" method="get" class="datatable-search right">
										<!-- 셀렉트 -->
										<div class="datatable-dropdown ">
											<label> 
												<select class="datatable-selector" name="searchAlign">
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
<%@ include file="/resources/student/include/footer.jsp" %>
		</div>
	</div>

</body>
</body>
</html>