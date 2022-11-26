<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>** MemberDetail Web_MVC2 **</title>
	<link rel="stylesheet" type="text/css" href="/Web02/myLib/myStyle.css">
</head>
<body>
<h2>** MemberDetail Web_MVC2 **</h2>
=> Request Member_id : ${param.id}<br>
<hr>

<c:if test="${not empty apple}">
	<table>
		<tr height="40"><td bgcolor="Lavender">Id</td><td>${apple.id}</td></tr>
		<tr height="40"><td bgcolor="Lavender">Password</td><td>${apple.password}</td></tr>
		<tr height="40"><td bgcolor="Lavender">Name</td><td>${apple.name}</td></tr>
		<tr height="40"><td bgcolor="Lavender">Info</td><td>${apple.info}</td></tr>
		<tr height="40"><td bgcolor="Lavender">Birthday</td><td>${apple.birthday}</td></tr>
		<tr height="40"><td bgcolor="Lavender">Jno</td><td>${apple.jno}</td></tr>
		<tr height="40"><td bgcolor="Lavender">Age</td><td>${apple.age}</td></tr>
		<tr height="40"><td bgcolor="Lavender">Point</td><td>${apple.point}</td></tr>
	</table>
</c:if>
<c:if test="${not empty message}">
	<hr>
	${message} <br>
</c:if>
<hr>
&nbsp;&nbsp;<a href="/Web02/mdetail?jCode=U&id=${apple.id}">[내 정보 수정]</a>
<!-- memberList 에서 요청 한 경우에는 session 에 보관된 id로는 수정 할 수 없기때문에 parameter로 처리함 -->
&nbsp;&nbsp;<a href="/Web02/mdelete?id=${apple.id}">[회원탈퇴]</a><br><br>
&nbsp;&nbsp;<a href="javascript:history.go(-1)">이전으로</a> 
&nbsp;&nbsp;<a href="/Web02/index.jsp">[Home]</a>
</body>
</html>