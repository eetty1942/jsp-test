<%@page import="com.cdg.study.entity.BoardDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<%
	BoardDTO dto = (BoardDTO)request.getAttribute("retrieve");
	int num = dto.getNum();
	String author = dto.getAuthor();
	String title = dto.getTitle();
	String content = dto.getContent();
	String writeday = dto.getWriteday();
	int readcnt = dto.getReadcnt();
%>

<body>

	<form action="update" method="post">
	<input type="hidden" name="num" value="<%=num %>">
		글번호:<%=num %> &nbsp;&nbsp; 작성일:<%=writeday %> &nbsp;&nbsp; 조회수:<%=readcnt %><br>
		제목: <input type="text" name="title" value="<%=title%>"><br> 
		작성자: <input type="text" name="author" value="<%=author%>"><br>내용: <br>
		<textarea rows="10" cols="20" name="content"><%=content%></textarea>
		<br> <input type="submit" value="글수정">
	</form>
	<a href="delete?num=<%=num %>">글삭제</a><br>
	
	<a href="list">목록</a>

	

</body>
</html>