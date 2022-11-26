<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>** Web02 **</title>
	<link rel="stylesheet" type="text/css" href="/Web02/myLib/myStyle.css">
</head>
<body>
<h2>** WebProgram MVC2 **</h2>
<!-- login 전, 후 구별 기능 추가하기 : 인삿말, 하단의 메뉴 구분 -->
<c:if test="${not empty loginID}">
=> ${loginName} 님 안녕하세요 !!! <br>
</c:if>
<c:if test="${not empty message}">
=> ${message}<br>
</c:if>
<hr>
<img src="/Web02/images/white01.gif" width = "400" height = "300">
<hr>
<!-- Login 전 -->
<c:if test="${empty loginID}">
	&nbsp;&nbsp;<a href="/Web02/member/loginForm.jsp">LoginForm</a>
	&nbsp;&nbsp;<a href="/Web02/member/joinForm.jsp">JoinForm</a>
</c:if>
<!-- Login 후 -->
<c:if test="${not empty loginID}">
	&nbsp;&nbsp;<a href="/Web02/mlogout">Logout</a>
	&nbsp;&nbsp;<a href="/Web02/mdetail">내 정보 보기</a>
	&nbsp;&nbsp;<a href="/Web02/mdetail?jCode=U">내 정보 수정</a>
	&nbsp;&nbsp;<a href="/Web02/mdelete">회원탈퇴</a>
</c:if>
<br>
&nbsp;&nbsp;<a href="/Web02/mlist">memberList</a>
&nbsp;&nbsp;<a href="/Web02/blist">BoardList</a><br>
</body>
</html>