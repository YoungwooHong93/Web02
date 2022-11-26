<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>** BoardDetail Web_MVC2 **</title>
	<link rel="stylesheet" type="text/css" href="/Web02/myLib/myStyle.css">
</head>
<body>
<h2>** BoardDetail Web_MVC2 **</h2>
<hr>
<c:if test="${not empty apple}">
	<table>
		<tr height="40"><td bgcolor="Khaki" style="text-align: center;">Seq</td><td>${apple.seq}</td></tr>
		<tr height="40"><td bgcolor="Khaki" style="text-align: center;">I  D</td><td>${apple.id}</td></tr>
		<tr height="40"><td bgcolor="Khaki" style="text-align: center;">Title</td><td>${apple.title}</td></tr>
		<tr height="40"><td bgcolor="Khaki" style="text-align: center;">Content</td>
						<td><textarea rows="5" cols="50" readonly="readonly">${apple.content}</textarea></td>
		</tr>
		<tr height="40"><td bgcolor="Khaki" style="text-align: center;">RegDate</td><td>${apple.regdate}</td></tr>
		<tr height="40"><td bgcolor="Khaki" style="text-align: center;">조회수</td><td>${apple.cnt}</td></tr>
	</table>
</c:if>
<c:if test="${not empty message}">
	<hr>
	${message} <br>
</c:if>
<hr>
<c:if test="${loginID==apple.id || loginID=='admin'}">
   &nbsp;&nbsp;<a href="/Web02/bdetail?jCode=U&seq=${apple.seq}">[게시물 수정]</a>
   &nbsp;&nbsp;<a href="/Web02/bdelete?seq=${apple.seq}">[게시물 삭제]</a>
</c:if>
<c:if test="${not empty loginID}">
	&nbsp;&nbsp;<a href="/Web02/board/rinsertForm.jsp?root=${apple.root}&step=${apple.step}&indent=${apple.indent}">[댓글 달기]</a>
</c:if>
&nbsp;&nbsp;<a href="javascript:history.go(-1)">이전으로</a> 
&nbsp;&nbsp;<a href="/Web02/index.jsp">[Home]</a>
</body>
</html>