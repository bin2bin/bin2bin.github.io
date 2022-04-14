<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	String msg = (String)request.getAttribute("msg");
	String sTag = (String)request.getAttribute("sTag");
	String originPwd = (String)session.getAttribute("originPwd");

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>
	var msg="<%= msg %>";
	var sTag="<%= sTag %>";
	var originPwd = "<%= originPwd %>";
	$(function(){
		if(msg != "null"){
			
			alert(msg) /*메세지가 있으면 띄어줌*/
		}
		
		if(sTag == "Y"){
			
			opener.document.getElementById("originPwd").value = originPwd; /*열려있는 창 마이페이지에 비밀번호 떠있는거에 값을 넣어줄거임 그래서 변경 후 마이페이지 안에 비밀번호가 바뀌어있어야함*/
			window.close()
		}
		
	})
	
</script>
</head>
<body>
	<b>비밀번호 변경</b>
	<br>
	
	<form id="updatePwdForm" action="<%= request.getContextPath() %>/updatePwdMember.do" method="post">
		<table>
			<tr>
				<td><label>현재 비밀번호</label>
				<td><input type="password" name="userPwd" id="userPwd"></td>
			</tr>
			<tr>
				<td><label>변경할 비밀번호</label></td>
				<td><input type="password" name="newPwd"></td>
			</tr>
			<tr>
				<td><label>변경할 비밀번호 확인</label></td>
				<td><input type="password" name="checkPwd"></td>
			</tr>
		</table>
		
		<br>
		<br>
		
		<div class="btns" align="center">
			<button  type="button" onclick="fnCheckPwd()">변경하기</button>
		</div>
	</form>
	<script>
		function fnCheckPwd(){
			var userPwd = $("#userPwd"); /* 각 변수에 담음*/
			var newPwd = $("input[name='newPwd']");
			var checkPwd = $("input[name='checkPwd']");
			
			if(userPwd.val().trim() === "" || newPwd.val().trim() === "" || checkPwd.val().trim() === ""){ /*해당하는 값들이 null이면*/
				alert("비밀번호를 입력하세요") /*null이면 입쳥 창 띄움*/
				return false;
			}
			
			if(newPwd.val() != checkPwd.val()){ /*비밀번호를 제대로 적었는지 확인*/
				alert("비밀번호가 다릅니다.")
				checkPwd.val('');
				checkPwd.focus();
				return false;
				
			}
			
			$("#updatePwdForm").submit(); //제대로 다 됐으면 submit(전송) 되면 /updatePwdMember.do 서블릿 호출
		}
	</script>
</body>
</html>