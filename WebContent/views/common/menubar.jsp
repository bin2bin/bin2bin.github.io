<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.uni.member.model.dto.Member"%>
    
    <%
    
    	Member loginUser = (Member)session.getAttribute("loginUser"); //오브젝트로 넘어와서 멤버로 형변환 해주고 임포트 작성
    	String msg = (String)session.getAttribute("msg"); //여기서 session으로 받아주기에 성고일시 getsession으로 받아줘야한다 
    	String contextPath = request.getContextPath(); //하나하나 명시를 안해주어도 사용이 가능해짐
    
    %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<style>
	body{
		background:url('<%=request.getContextPath() %>/resources/images/i.jpg') no-repeat; 
		background-size:cover;
	}
	
	/* 로그인 폼 관련 스타일*/
	#loginForm, #userInfo{float:right;}
	.btns button{border-radius:5px;}
	#enrollBtn, #mypageBtn{background-color:yellowgreen;}
	#loginBtn, #logoutBtn{background-color:orangered;}
	#userInfo a{text-decoration:none;color:white;}
		
	/* 메뉴영역 관련 스타일*/
	.navWrap{background-color:black; width:100%; height:50px}
	.navWrap>.nav{width:600px;margin:auto;}
	.menu{text-align:center;color:white;font-weight:bold;width:150px;height:50px;display:table-cell;font-size:20px;vertical-align:middle;}
	.menu:hover{background-color:darkgray;}
	
</style>

<script type="text/javascript">
	$(function(){
		let msg = "<%=msg%>";
		if(msg != "null"){ //위에서 스트링으로 받고 있기때문에 "null" 을 적어줌
			alert(msg); //메세지 창 띄어주고
			<% session.removeAttribute("msg");%> //속성지우기
		}
	})



	function loginValidate(){
		if($("#userId").val().trim().length ===0 ){ //trim 양쪽 공백을 제거
			alert("아이디를 입력하세요");
			$("#userId").focus();
			return false;
		}
		if($("#userPwd").val().trim().length ===0 ){
			alert("비밀번호를 입력하세요");
			$("#userPwd").focus();
			return false;
		}
		return true;
	}

</script>
</head>
<body>
	<h1 align = "center" style="color:white;">Welcome JSP World!</h1>
	<div class= "loginArea">
	
	<%if(loginUser == null){ %> <!-- 세션의 값이 담겨있지 않다면 -->
	<form id = "loginForm" action="<%=request.getContextPath()%>/loginMember.do" method="post" onsubmit="return loginValidate();">
			<table>
				<tr>
					<th><label for = "userId" style="color:white;">아이디</label></th>
					<td><input id="userId" type="text" name="userId"></td>
				</tr>
				<tr>
					<th><label for = "userPwd" style="color:white;">비밀번호</label></th>
					<td><input id="userPwd" type="text" name="userPwd"></td>
				</tr>
			</table>
			<div class ="btns" align="center">
				
				<button id = "loginBtn" type="submit">로그인</button>
			    <button id = "enrollBtn" type="button" onclick="enrollPage();">회원가입</button>
			</div>
	
		</form>
		<% }else{ %> <!--세션의 값이 담겨있다면 -->
			<div id = "userInfo">
				<b style = "color:white;"><%=loginUser.getUserName() %> 님 </b> 의 방문을 환영합니다.
				<br><br>
				<div class ="btns" align="center">
					<a href = "<%=request.getContextPath() %>/mypageMember.do">마이페이지</a>
					<a href = "<%=request.getContextPath() %>/logoutMember.do">로그아웃</a>
				</div>
			
			
			</div>
			
		<%} %>
	</div>
	
	<script>
		function enrollPage(){
			location.href="<%=request.getContextPath()%>/enrollFormMember.do";
		}
	</script>
	
	
	<br clear="both">
	
	
	
	<div class="navWrap">
      <div class="nav">
         <div class="menu" onclick="goMain();">HOME</div>
         <div class="menu" onclick="goNotice();">공지사항</div>
         <div class="menu" onclick="goBoard();">게시판</div>
         <div class="menu" onclick="goThumbnail();">사진게시판</div>
         
      </div>
   </div>
	
	<script>
		function goMain(){
			location.href="<%= request.getContextPath()%>";
		}	
		function goNotice(){
			location.href="<%= request.getContextPath()%>/listNotice.do";
		}	
		function goBoard(){
			location.href="<%= request.getContextPath()%>/listBoard.do";
		}
	</script>
	
</body>
</html>