<%@ page language="java" contentType="text/html; charset=iso-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Update Password</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/dai.css" />
<script type="text/javascript" src="js/common.js"></script>
<script src="js/jquery-1.10.2.js"></script>

<script>	
	function updatePassword(){
		var oldPassword = document.getElementById("oldPassword").value;
		var empId = document.getElementById("empId").value;
		var newPassword1 = document.getElementById("newPassword1").value;
		var newPassword2 = document.getElementById("newPassword2").value;		
		
		
		if(newPassword1 != newPassword2){
			alert("New Password does not match.");
			return;
		}
		
		var oAjaxCall = $.ajax({
			type : "GET",
			url : "/hris/UpdatePasswordAction",			
			data: "oldPassword=" + oldPassword + "&empId=" + empId,
			cache : false,
			async : false,
			dataType:"json",
			success : function(data) {	
				if(data == 'failed'){
					alert("Your Old Password is incorrect.");		
					return;
				} else {
					updatePasswordSubmit();
				}			
			},
			error : function(data) {
				alert('error: ' + data);
			}
		});
		
	}
	
	function updatePasswordSubmit() {
		var newPassword1 = document.getElementById("newPassword1").value;
		var empId = document.getElementById("empId").value;
		
		var oAjaxCall = $.ajax({
			type : "POST",
			url : "/hris/UpdatePasswordAction",			
			data: "newPassword1=" + newPassword1 + "&empId=" + empId,
			cache : false,
			async : false,
			dataType:"json",
			success : function(data) {				
				alert("Your Password was successfully changed.");				
			},
			error : function(data) {
				alert('error: ' + data);
			}
		});
	}
</script>


</head>
<body>
<div><c:import url="header.jsp" /></div>
<div>	
	<input type="hidden" name="empId" id="empId" value="${employeeLoggedIn.empId}" />	
	<input type="hidden" name="departmentId" id="departmentId" value="${employee.departmentId}" />	
	<div id="content" class="updatePasswordContainer">
		<div class="dataEntryText" style="width: 150px;">Old Password</div>
		<div class="dataEntryInput">
			<input type="text" id="oldPassword" name="oldPassword" placeholder="Old Password" />
		</div>
		<div class="cb"></div>
		<div class="dataEntryText" style="width: 150px;">New Password</div>
		<div class="dataEntryInput">
			<input type="text" id="newPassword1" name="newPassword1" placeholder="New Password" />
		</div>
		<div class="cb"></div>
		<div class="dataEntryText" style="width: 150px;">Retype New Password</div>
		<div class="dataEntryInput">
			<input type="text" id="newPassword2" name="newPassword2" placeholder="Retype New Password" />			
		</div>
		<div class="cb" style="height: 10px;"></div>
		<div class="employeeButton" onClick="updatePassword();">Save</div>
		<div class="employeeButton">Clear</div>
	</div>
</div>
<div style=""><c:import url="footer.jsp" /></div>
</body>
</html>