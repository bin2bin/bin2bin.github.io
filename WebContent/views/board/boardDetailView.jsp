<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.uni.board.model.dto.*" %>  
<%
	Board b = (Board)request.getAttribute("b");  //보드디테일서블릿서 셋 해준 b, at를 get해온다
	Attachment at = (Attachment)request.getAttribute("at"); 
%>  
    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
	.outer{
		width:800px;
		height:500px;
		background:black;
		color:white;
		margin:auto;
		margin-top:50px;
	}
	
	.outer>table, .outer>table tr>*{
		border:1px solid white;
	}
	
	.outer>table{
		width:600px;
		height:300px;
	}
	
	.outer>table p{
		height:230px;
		margin:0;
	}
	
	.replyArea{
		width:800px;
		color:white;
		background:black;
		margin:auto;
	}
</style>
</head>
<body>
	<%@ include file="../common/menubar.jsp" %>
	
	<div class="outer">
		<br>
		
		<h2 align="center">게시판 상세보기</h2>
		<br>
		
		<table align="center">
			<tr>
				<th width="100">분야</th>
				<td><%= b.getCategory() %></td>
				<th>제목</th>
				<td colspan="2"><%= b.getBoardTitle() %></td>
			</tr>
			<tr>
				<th>작성자</th>
				<td><%= b.getBoardWriter() %></td><%-- 
				<th>조회수</th>
				<td><%= b.getCount() %></td> --%>
				<th>작성일</th>
				<td><%= b.getCreateDate() %></td>
			</tr>
			<tr>
				<th>내용</th>
				<td colspan="3">
					<p><%= b.getBoardContent() %></p>
				</td>
			</tr>
			<tr>
				<th>첨부파일</th>
				<td colspan="3">
					<% if(at != null){ %>
					<a download="<%= at.getOriginName() %>" href="<%=contextPath%>/resources/board_upfiles/<%=at.getChangeName()%>"><%= at.getOriginName() %></a>
					<% }else{ %>
					첨부파일이 없습니다.
					<% } %>
				</td> 
			</tr>
		</table>
		<br>
		
		<div class="btns" align="center">
			<button type="button" onclick="location.href='<%=contextPath%>/listBoard.do?currentPage=1';">목록으로</button>
			
			<% if(loginUser != null && loginUser.getUserId().equals(b.getBoardWriter())){ %>
				
				<button type="button" onclick="updateForm();">수정하기</button>
				<button type="button" onclick="deleteBoard();">삭제하기</button>
			<% } %>
		</div>
		
		<form action="" id="postForm" method="post">
			<input type="hidden" name="bno" value="<%= b.getBoardNo() %>">
		</form>
		<script>
			function updateForm(){
				$("#postForm").attr("action", "<%=contextPath%>/updateFormBoard.do"); <!-- hidden으로 bno 값을 가지고 간다 액션 속성값을 정의를 해주었고 액션이라는 속성은 이 경로에 값을 넣어주겠다-->
				$("#postForm").submit(); <!--updateForm을 눌렀을때 submit을 수행-->
			}
			
			function deleteBoard(){
				$("#postForm").attr("action", "<%=contextPath%>/deleteBoard.do");
				$("#postForm").submit();
			}
		</script>
	</div>
	
</body>
</html>