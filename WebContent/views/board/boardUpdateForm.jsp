<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import ="com.uni.board.model.dto.*"%>
<% 
	Board board  = (Board)request.getAttribute("board"); //이미 form은 구현이 되어있음
	Attachment at  = (Attachment)request.getAttribute("at");
	
	String category = board.getCategory();
	String[] selected = new String[7];
	
	switch(category){
	case "공통": selected[0] = "selected";break;
	case "운동": selected[1] = "selected";break;
	case "등산": selected[2] = "selected";break;
	case "게임": selected[3] = "selected";break;
	case "낚시": selected[4] = "selected";break;
	case "요리": selected[5] = "selected";break;
	case "기타": selected[6] = "selected";break;
	}
	%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Insert title here</title>
<style>
	.outer{
		width:900px;
		height:500px;
		background:black;
		color:white;
		margin:auto;
		margin-top:50px;
	}
	
	#updateForm>table{
		border:1px solid white;
	}
	
	#updateForm>table input, #updateForm>table textarea{
		width:100%;
		box-sizing:border-box;
	}
	
	#deleteBtn{color:gray;}
	#deleteBtn:hover{cursor:pointer}
</style>
</head>
<body>
	<%@ include file="../common/menubar.jsp" %>
	
	<div class="outer">
		<br>
		
		<h2 align="center">게시판 수정하기</h2>
		<br>
		
		<form id="updateForm" action="<%= contextPath %>/updateBoard.do" method="post" enctype="multipart/form-data"> <!-- 첨부파일이있어서 멀티파트로 넘김 -->
			<input type="hidden" name="bno" value="<%= board.getBoardNo() %>">
			<table align="center">
				<tr>
					<th width="100">분야</th>
					<td width="500">
						<select name="category">
							<option value="10" <%= selected[0] %>>공통</option>
							<option value="20" <%= selected[1] %>>운동</option>
							<option value="30" <%= selected[2] %>>등산</option>
							<option value="40" <%= selected[3] %>>게임</option>
							<option value="50" <%= selected[4] %>>낚시</option>
							<option value="60" <%= selected[5] %>>요리</option>
							<option value="70" <%= selected[6] %>>기타</option> 
						</select>
					</td>
				</tr>
				<tr>
					<th>제목</th>
					<td><input type="text" name="title" value="<%= board.getBoardTitle() %>"></td>
				</tr>
				<tr>
					<th>내용</th>
					<td>
						<textarea rows="15" name="content" style="resize:none;"><%= board.getBoardContent() %></textarea>
					</td>
				</tr>
				<tr>
					<th>첨부파일</th>
					<td>
						<% if(at != null){ %> <!-- 기존의 첨부파일이 있었을 경우 없었을 경우에 받아주는 서블릿에서 처리를 다르게 해주기 때문에 if를 꼭 해줘야한다. -->
							<%= at.getOriginName() %> <br> <!-- 화면에 보여는 내용 기존 파일 이름 -->
							<input type='hidden' name='originFile' value='<%=at.getChangeName()%>'>  <!-- 기존 파일의 이름을 바꿔주려고 벨류 가져감 -->
							<input type='hidden' name='originFileNo' value='<%=at.getFileNo()%>'> <!-- 기존 파일의 넘버를 바꿔주려고 벨류 가져감 -->
						<% }%>
						<input type="file" name="upFile"> <!-- 새로 첨부되는 파일을 받아줌 -->
					</td>
					
				</tr>
			</table>
			<br>
			
			<div class="btns" align="center">
				<button type="submit">수정하기</button>
			</div>
		</form>
	</div>
</body>
</html>