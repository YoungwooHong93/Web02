<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>** MemberList Web_MVC2 **</title>
	<link rel="stylesheet" type="text/css" href="/Web02/myLib/myStyle.css">
</head>
<body>
<h2>** MemberList Web_MVC2 **</h2>
<br>
<c:if test="${not empty message}">
	${message}<br>
</c:if>
<hr>
<table width=100%>
	<tr bgcolor="orange" height="30">
		<th>Seq</th>
		<th>Title</th>
		<th>I  D</th>
		<th>RegDate</th>
		<th>조회수</th>
	</tr>
	<c:if test="${not empty banana}">
		<c:forEach var="board" items="${banana}">
			<tr height="30">
				<td style ="text-align:center">${board.seq}</td>	
				<td>
					<!-- 답글 등록후 indent 에 따른 들여쓰기 
                  			=> 답글인 경우에만 적용  -->
                  	<c:if test="${board.indent > 0}">
                  		<c:forEach begin="1" end="${board.indent}">
                  			<span>&nbsp;&nbsp;</span>
                  		</c:forEach>
                  		<span style = "color:blue">re →</span>
                  	</c:if>
                  	
					<!-- 로그인 한 경우에만 title을 클릭하면 content를 볼 수 있도록 함 
						=> bdetail 을 실행한다는 뜻 -->
					<c:if test="${not empty loginID}">
						<a href="/Web02/bdetail?seq=${board.seq}">${board.title}</a>
					</c:if>
					<c:if test="${empty loginID}">
						${board.title}
					</c:if>
					</td>
					
				<td style ="text-align:center">
					<c:if test="${loginID == 'admin'}">
						<a href="/Web02/mdetail?id=${board.id}">${board.id}</a>
					</c:if>
					<c:if test="${loginID != 'admin'}">
						${board.id}
					</c:if>
				</td>	
				<td style ="text-align:center">${board.regdate}</td>
				<td style ="text-align:center">${board.cnt}</td>
			</tr>
		</c:forEach>
	</c:if>
</table>
<hr>
<hr>
<c:if test="${not empty loginID}">
&nbsp;&nbsp;<a href="/Web02/board/bInsertForm.jsp">새 게시물 작성</a> 
</c:if>
&nbsp;&nbsp;<a href="javascript:history.go(-1)">이전으로</a> 
&nbsp;&nbsp;<a href="/Web02/index.jsp">[Home]</a>
</body>
</html>