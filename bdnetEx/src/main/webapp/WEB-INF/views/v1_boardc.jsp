<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<% pageContext.setAttribute("nl", "\n"); %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>게시글 내용</title>
		<link rel="stylesheet" href="resources/css/bootstrap.css"/>
		<style>
			.container {margin-top: 10px}
			table {table-layout: fixed}
			.brd_content {overflow: auto}
		</style>
	</head>
	<body>
		<div class="container">
			<h3>글내용</h3>
			<table class="table">
				<colgroup>
					<col width="10%"/>
					<col width="*"/>
				</colgroup>
				<tr>
					<th>번호</th>
					<td>${vo.brd_no}</td>
				</tr>
				<tr>
					<th>제목</th>
					<td>${vo.brd_title}</td>
				</tr>
				<tr>
					<th>파일</th>
					<td>
					<c:if test="${not empty vo.fi_name1}">
					<a href="selectFile.do?no=${vo.fi_no}&idx=1" id="target1">${vo.fi_name1}</a><br/>
					</c:if>
					<c:if test="${not empty vo.fi_name2}">
					<a href="selectFile.do?no=${vo.fi_no}&idx=2" id="target2">${vo.fi_name2}</a><br/>
					</c:if>
					<c:if test="${not empty vo.fi_name3}">
					<a href="selectFile.do?no=${vo.fi_no}&idx=3" id="target3">${vo.fi_name3}</a><br/>
					</c:if>
					</td>
				</tr>
				<tr>
					<th>내용</th>
					<td id="brd_content">${fn:replace(vo.brd_content, nl, "<br/>")}</td>
				</tr>
				<tr>
					<th>조회수</th>
					<td>${vo.brd_hit}</td>
				</tr>
				<tr>
					<th>작성자</th>
					<td>${vo.brd_writer}</td>
				</tr>
				<tr>
					<th>날짜</th>
					<td>${vo.brd_date}</td>
				</tr>
			</table>
		
			<div align="right">
				<c:if test="${prev != 0}">
					<a href="boardc.do?no=${prev}&page=${param.page}&type=${param.type}&text=${param.text}" class="btn btn-primary">이전글</a>
				</c:if>
				<c:if test="${next != 0}">
					<a href="boardc.do?no=${next}&page=${param.page}&type=${param.type}&text=${param.text}" class="btn btn-primary">다음글</a>
				</c:if>
				<a href="boardw.do?page=${param.page}&type=${param.type}&text=${param.text}" class="btn btn-primary">글쓰기</a>
				<a href="boardm.do?no=${vo.brd_no}&page=${param.page}&type=${param.type}&text=${param.text}" class="btn btn-primary">글수정</a>
				<a href="board.do?page=${param.page}&type=${param.type}&text=${param.text}" class="btn btn-primary">글목록</a>
			</div>
		</div>
		<script type="text/javascript" src="resources/js/jquery-1.11.1.js"></script>
		<script type="text/javascript">
			$(function(){
				for(var i = 0; i < 3; i++){
					var obj = $("#target"+(i+1));
					obj.text($.trim(obj.text()));
				}
			});
		</script>
	</body>
</html>