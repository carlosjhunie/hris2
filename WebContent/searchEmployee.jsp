<%@ page language="java" contentType="text/html; charset=iso-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Search Employee</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@include file="commonHeader.jsp" %>
<%@include file="commonJtables.jsp" %>
<link rel="stylesheet" type="text/css" href="css/dai.css" />
<script type="text/javascript" src="js/common.js"></script>
</head>
<body>
<div><c:import url="header.jsp" /></div>
<div style="height: 450px;">	
	<div>Employee List</div>
	<div><input type="password" id="password" name="password" size="24" value="" /></div>
	<div class="loginButton" onclick="login();">
		<div><img src="images/key.png" alt="alternate text" width="35px" height="35px" /></div>
		<div>Search</div>
	</div>
	<!-- Result Set Table -->
	<div>
	</div>
</div>	
<div><c:import url="footer.jsp" /></div>
</body>
</html>