<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	tbody{
		font-size: 20px;
	}
</style>

</head>
<body>
	<jsp:include page="../include/header.jsp"/>
	
	<c:if test="${sessionScope.user_id == null}">
		<script>
			alert("회원만 이용 가능한 게시판입니다. 로그인 해 주세요.");
			location.href="../user/user_login.jsp";
		</script>
	</c:if>
	
	

	<div class="container">
		<h2>My Web게시판</h2>

		<table class="table table-secondary table-hover table-bordered">
			<thead style="font-size: 25px">
				<tr>
					<th>글 번호</th>
					<th>작성자</th>
					<th>제목</th>
					<th>날짜</th>
					<th>조회수</th>
				</tr>
			</thead>

			<c:forEach var="article" items="${boardList}">
			<tbody>
				<tr>
					<td>${article.boardId}</td>
					<td>${article.writer}</td>
					<td>
						<a href="content.board?boardId=${article.boardId}">${article.title}</a>
					</td>
					<td>${article.date}</td>
					<td>${article.hit}</td>
				</tr>
			</tbody>
			</c:forEach>
			
			
			<tbody>
				<tr>
					<td colspan="5" align="right">
						<form action="search.board" class="form-inline" >
						  <div class="form-group">
						    <input type="text" name="search" placeholder="제목검색" class="form-control" >
						  	<input type="submit" value="검색" class="btn btn-default">
							<input type="button" value="글 작성" class="btn btn-default" onclick="location.href='write.board'">
						  </div>
						</form> 
					</td>
				</tr>
			</tbody>
		
		</table>
	</div>

	<jsp:include page="../include/footer.jsp"/>

</body>
</html>







