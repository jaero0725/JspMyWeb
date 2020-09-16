<%@page import="kr.co.jsp.user.model.UserDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <%
    	/*
    		1. 폼 데이터 처리 (기존 비번, 변경 비번)
    		2. dao주소값 받아오시고 userCheck()메서드를 활용하여 기존 비번과 
    		 아이디 정보를 바탕으로 해당 비밀번호가 일치하는지를 검사.
    		 (id는 세션에서 얻어오면 된다.)
    		
    		3. 일치한다면 비밀번호를 바꾸는 메서드 changePassword()를 호출.
    		4. "비밀번호가 정상적으로 변경되었습니다." 경고창 출력 후 mypage로 이동.
    		5. 현재 비밀번호 불일치 -> "현재 비밀번호가 다릅니다." 출력 후 뒤로가기
    		6. 비밀번호 변경에 실패하면 "비밀번호 변경에 실패했습니다." 출력 후 mypage.
    	*/
    	
    	request.setCharacterEncoding("utf-8");
    	String id = (String) session.getAttribute("user_id");
    	String oldPw = request.getParameter("old_pw");
    	String newPw = request.getParameter("new_pw");
    	
    	UserDAO dao = UserDAO.getInstance();
    	
    	int result = dao.userCheck(id, oldPw);
    	
    	if(result == 1) { //로그인 유효성 검증 성공.
    		if(dao.changePassword(id, newPw)) { %>
    			<script>
    				alert("비밀번호가 정상적으로 변경되었습니다.");
    				location.href="user_mypage.jsp";
    			</script>
    		<% } else { //비밀번호 변경 실패%> 
    			<script>
    				alert("비밀번호 변경에 실패했습니다.");
    				location.href="user_mypage.jsp";
    			</script>
    		<% } 
    	} else { //로그인 유효성 검증 실패 %>
    		<script>
    			alert("현재 비밀번호를 확인하세요.");
    			history.back();
    		</script>
    	<% } %>	
    	
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    