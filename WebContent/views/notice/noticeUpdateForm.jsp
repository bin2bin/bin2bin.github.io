<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.uni.notice.model.dto.Notice" %>
<%
	Notice n = (Notice)request.getAttribute("notice"); 
	//화면로직을 보여줄 수 있는 것들만 자바코드를 작성 해주고 비즈니스 로직(줄바꿈 등등은) 사용하지 않는게 좋음
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
	#updateForm{width:60%; margin:auto;}
	#updateForm>table{border:1px solid white;}
	#updateForm>table input{
		width:100%;
		box-sizing:border-box;
	}
	
</style>
</head>
<body>

	<%@ include file="../common/menubar.jsp" %>
	
	<div class="outer">
		<br>
		<h2 align="center">공지사항 수정하기</h2>
		
		<form id="updateForm" action="<%= contextPath %>/updateNotice.do" method="post" >
			<input type="hidden" name="nno" value="<%= n.getNoticeNo() %>">
			<table align="center">
				<tr>
					<td>제목</td>
					<td colspan="3"><input type="text" name="title" value="<%= n.getNoticeTitle() %>"></td>
				</tr>
				
				<tr>
					<td>내용</td>
					<td colspan="3"></td>
				</tr>
				<tr>
					<td colspan="4">
						<textarea name="content" cols="60" rows="15" style="resize:none;"><%= n.getNoticeContent() %></textarea>
					</td>
				</tr>	
			</table>
			<br>
			
			<div class="btns" align="center">
				<button type="submit">수정하기</button>
			</div>
		</form>
	</div>
	<%@ include file="../common/footer.jsp" %>
	
</body>
</html>