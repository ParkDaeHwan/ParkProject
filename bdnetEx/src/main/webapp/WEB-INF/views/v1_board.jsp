<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>게시글 목록</title>
		<link rel="stylesheet" href="resources/css/bootstrap.css"/>
		<style>
			.container {margin-top: 10px}
			#search_bar {margin-bottom: 10px}
			table {table-layout: fixed}
			.brd_title, .brd_writer {overflow: hidden}
		</style>
	</head>
	<body>
		<div class="container">
			<h3>글목록</h3>
			<div class="form-inline" align="right" id="search_bar">
				<select class="form-control" id="search_type">
					<option value="brd_title">제목</option>
					<option value="brd_content">내용</option>
					<option value="brd_writer">작성자</option>
				</select>
				<input type="text" class="form-control" id="search_text"/>
				<input type="button" class="btn btn-success" id="search_btn" value="검색"/>
			</div>
			<table class="table">
				<colgroup>
					<col width="5%"/>
					<col width="*"/>
					<col width="6%"/>
					<col width="20%"/>
					<col width="13%"/>
				</colgroup>
				<thead>
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>조회수</th>
						<th>작성자</th>
						<th>날짜</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${empty list}">
					<tr>
						<td colspan="5">자료가 없습니다.</td>
					</tr>
					</c:if>
					<c:if test="${not empty list}">
					<c:forEach var="tmp" items="${list}">
					<form action="boardc.do?no=${tmp.brd_no}&page=${param.page}&type=${param.type}&text=${param.text}" method="post" class="form">
					</form>
					<tr>
						<td>${tmp.brd_no}</td>
						<td class="brd_title"><a href="javascript:void(0)" class="href">${tmp.brd_title}</a></td>
						<td>${tmp.brd_hit}</td>
						<td class="brd_writer">${tmp.brd_writer}</td>
						<td>${tmp.brd_date}</td> <!-- 날짜만 출력되도록 포멧 -->
					</tr>
					</c:forEach>
					</c:if>
				</tbody>
			</table>
			<div align="center">
				<ul id="pagination" class="pagination"></ul>
			</div>
			<div align="right">
				<a href="boardw.do?page=${param.page}&type=${param.type}&text=${param.text}" class="btn btn-primary">글쓰기</a>
				<a href="board.do" class="btn btn-primary">글목록</a>  
			</div>
		</div>
		<script type="text/javascript" src="resources/js/jquery-1.11.1.js"></script>
		<script type="text/javascript" src="resources/js/jquery.twbsPagination-1.3.1.js"></script>
		<script type="text/javascript">
			$.search_func = function (){
				var ty = $("#search_type").val();
				var tx = encodeURIComponent($("#search_text").val());
				
				window.location.href="board.do?page=1&type="+ty+"&text="+tx;
			}
			$(function(){
				$("#pagination").twbsPagination({
					totalPages: "${totPage}",
					visiblePages: 10,
					href: "?page={{number}}&type=${param.type}&text=${param.text}"
				});
				
				$("#search_btn").on("click", function(){
					$.search_func();
				});
				
				$("#search_text").on("keyup", function(event){
					if(event.which == 13){
						$.search_func();
					}
				});
				
				$(".href").on("click", function(){
					var idx = $(this).index(".href");
					$(".form").eq(idx).submit();
				});
			});
		</script>
	</body>
</html>