<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.emp.model.*"%>
<%@ page import="com.Users.model.*"%>
<%
EmpVO empVO = (EmpVO) session.getAttribute("empVO");
%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous"> -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/back-end/emp/css/back.css">
<title>7 Tour | 個人訊息系統 </title>
</head>

<body>
	<div id="Mes"
		style="background-color: black; width: 1489px; height: 753px">
		<div id="MesBorder">
			<h2 id="info">會員訊息系統</h2>
			<h3 id="info">資料查詢:</h3>
			<%-- 錯誤表列 --%>
			<c:if test="${not empty errorMsgs}">
				<font
					style="color: red; font-size: 20px; display: inline-block; margin: 0 0 0px 50px;"></font>
				<ul style="display: inline-block">
					<c:forEach var="message" items="${errorMsgs}">
						<li
							style="color: red; font-size: 20px; list-style-type: none; margin: 0 0 -20px -40px;">${message}</li>
					</c:forEach>
				</ul>
			</c:if>
			<div id="sercher">
				<FORM METHOD="post"
					ACTION="<%=request.getContextPath()%>/empServlet">
					<b id="b" style="font-size: 25px">輸入使用者Email:</b> <input
						id="userEmailButton" type="text" name="userMail"
						style="height: 25px"> <input type="hidden" name="action"
						value="getOne_For_userMail"> <input type="submit"
						value="送出">
				</FORM>
			</div>
			<jsp:useBean id="userSvc" scope="page"
				class="com.Users.model.UsersService" />
			<div id="sercher">
				<FORM METHOD="post"
					ACTION="<%=request.getContextPath()%>/empServlet">
					<b style="font-size: 25px">輸入使用者姓名:</b> <input id="userNameButton"
						type="text" name="userName"> <input type="hidden"
						name="action" value="getOne_For_userName" style="height: 25px">
					<input type="submit" value="送出">
				</FORM>
			</div>
		</div>
	</div>






	<!-- ---------------------aside body區 start--------------------->
	<aside class="aside">

		<img src="<%=request.getContextPath()%>/back-end/emp/images/logo.png"
			width="80%">
		<form id="userct" name="userct" method="post"
			ACTION="<%=request.getContextPath()%>/empServlet">
			<input type="hidden" name="action" value="usercontrol">
			<ul class="aside_list">
				<li><a id="empcontrol" href="#">員工管理</a></li>
				<button id="usercontrol">
					<li><a id="text" href="#">會員管理</a></li>
				</button>
		</form>
		<li><a href="#">客服管理</a></li>
		<li><a href="#">優惠管理</a></li>
		<li><a href="#">論壇管理</a></li>
		<li><a href="#">揪團管理</a></li>
		<li><a href="#">訂房管理</a></li>
		<li><a href="#">景點管理</a></li>
		</ul>
		<h1 style="margin: -10px 0 0 0; color: red; font-size: 20px">
			使用者:<%=empVO.getEmpName()%></h1>
		<h1 style="margin: 0; color: red; font-size: 20px">
			登入身分:<%=empVO.getJobTitle()%></h1>
		<form id="login_out" name="login_out" method="post"
			ACTION="<%=request.getContextPath()%>/empServlet">
			<input type="hidden" name="action" value="loginOut">
			<button id="loginOut" style="margin: 10px 0 10px 0">
				<a href="#">登出</a>
			</button>
		</form>
		<button style="margin: 0">
			<a href="#">修改密碼</a>
		</button>

	</aside>
	<style>
#usercontrol {
	margin: 0 0px 0px 0px;
	line-height: 12px;
	border: none;
	border-radius: 0px;
	width: 220px;
	height: 50px;
}

#usercontrol:hover {
	cursor: pointer;
}

#empcontrol {
	margin: 0px 0px 0px -10px;
	line-height: 12px;
	height: 50px;
}

#usercontrol #text {
	position: absolute;
	font-size: 20px;
	font-weight: bold;
	top: 265px;
	left: 65px;
}

#MesBorder {
	border: 1px solid white;
	width: 500px;
	font-family: "微軟正黑體";
	position: absolute;
	font-size: 20px;
	font-weight: bold;
	top: 100px;
	left: 600px;
	border-radius: 20px;
}

#MesBorder #info {
	margin: 30px 0px 0 50px;
	color: lightgray;
}

#MesBorder #sercher {
	height: 50px;
	color: lightgray;
	margin: 50px 0px 20px 50px;
}
</style>
</body>
<script>
	let userEmailButton = document.getElementById("userEmailButton")
			.addEventListener("click", function() {
				document.getElementById("userNameButton").disabled = true;
			});
</script>
<script>
	let userNameButton = document.getElementById("userNameButton")
			.addEventListener("click", function() {
				document.getElementById("userEmailButton").disabled = true;
			});
</script>
<srcipt let usercontrol=document.getElementById(
	"usercontrol").addEventListener("click", function() {document.getElementById("userct").submit();});></srcipt>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ"
	crossorigin="anonymous"></script>
<srcipt let loginout=document.getElementById(
	"loginOut").addEventListener("click", function() {document.login_out.submit();});></srcipt>


</html>