<%@ page language="java" contentType="text/html; charset=iso-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>LOGIN SCREEN</title>
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Expires" CONTENT="-1">

<link rel="stylesheet" type="text/css" href="css/dai.css" />
<script type="text/javascript" src="js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="js/login.js"></script>
<script type="text/javascript">	

$(document).ready(function () {	
	$("input").keypress(function(){
		  if ( event.which == 13 ) {
			     event.preventDefault();
			     login();
			  }
	});
	
	
	
});
	function login(){
		
		
		if (document.loginForm.username.value == null ||	document.loginForm.username.value.length == 0 ) {
			alert('Username is a mandatory field.');
			document.loginForm.username.focus();
			return;
		}
		if (document.loginForm.password.value == null || document.loginForm.password.value.length == 0 ) {
			alert('Password is a mandtory field.');
			document.loginForm.password.focus();
			return;
		}
		
		document.loginForm.submit();		
	}
</script>


</head>
<body>	
<form method="POST" name="loginForm" action="LoginAction">
<div id="loginContent">
<div id="loginFormArea">
	<div id="loginHeaderText">HRIS LOGIN</div>
	<div id="loginHeaderCaption">Enter your username and password</div>
	<div>
		<div class="loginText">Username</div>
		<div><input type="text" id="username" name="username" size="25" value="" /></div>
	</div>
	<div class="cb"></div>
	<div>
		<div class="loginText">Password</div>
		<div><input type="password" id="password" name="password" size="25" value="" /></div>
	</div>
	<div id="loginButtonArea">
		<input class="ivsButton" type="button" value="Login" onClick="login();">
		<input class="ivsButton" type="reset" value="Clear">
	</div>
		
	<c:if test="${param.isExist == 0}">
		<div class="errorMsg" style="text-align: center; margin-top: 10px; color: red;">INVALID CREDENTIALS PLEASE TRY AGAIN</div>	
	</c:if>
</div>
<div class="cb"></div>
<div class="loginFooter">Copyright &copy; 2015, Dona Alejandra Inc.</div>
</div>
</form>	
</body>
</html>