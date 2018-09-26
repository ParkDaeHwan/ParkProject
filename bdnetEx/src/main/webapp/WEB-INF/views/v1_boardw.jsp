<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>게시글 쓰기</title>
		<link rel="stylesheet" href="resources/css/bootstrap.css"/>
		<style type="text/css">
			.container {margin-top: 10px}
			.form-horizontal {margin-top: 10px}
			.col-sm-10 textarea {resize: none}
		</style>
	</head>
	<body>
		<div class="container">
			<h3>글쓰기</h3>
			<form:form action="boardw.do" enctype="multipart/form-data" method="post" modelAttribute="vo" class="form-horizontal">
				<div class="form-group">
					<label class="col-sm-2 control-label" style="text-align: center">번호</label>
					<div class="col-sm-10">
						<form:input type="text" class="form-control" path="brd_no" readonly="true"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label" style="text-align: center">제목</label>
					<div class="col-sm-10">
						<form:input type="text" class="form-control" path="brd_title"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label" style="text-align: center">내용</label>
					<div class="col-sm-10">
						<form:textarea rows="6" class="form-control" path="brd_content"></form:textarea>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label" style="text-align: center">작성자</label>
					<div class="col-sm-10">
						<form:input type="text" class="form-control" path="brd_writer"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label" style="text-align: center">파일첨부</label>
					<div class="col-sm-10">
						<c:forEach begin="1" end="3" varStatus="st">
							<input type="file" class="form-control" name="file${st.count}"/><br/>
						</c:forEach>
					</div>
				</div>
				<div align="right">
					<input type="submit" class="btn btn-primary" value="글쓰기"/>
					<a href="board.do?page=${param.page}&type=${param.type}&text=${param.text}" class="btn btn-primary">글목록</a> 
				</div>
			</form:form>
		</div>
	</body>
</html>