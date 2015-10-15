<%@ page language="java" contentType="text/html; charset=iso-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta http-equiv="cache-control" content="no-store, no-cache, must-revalidate" />
<meta http-equiv="Pragma" content="no-store, no-cache" />
<meta http-equiv="Expires" content="0" />
<title>TIME ENTRY SCREEN</title>



<link rel="stylesheet" type="text/css" href="css/dai.css" />
<script type="text/javascript" src="js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="js/jquery-ui-1.11.4/jquery-ui.js"></script>


<link rel="stylesheet" href="js/jquery-ui-1.11.4/jquery-ui.css">
	
<script type="text/javascript">

$(document).ready(function() {
   var serialNo = '';
	$( "#dialog" ).dialog();
	
	
	 $( "input[type=submit], a, button" )
     .button()
     .click(function( event ) {
       event.preventDefault();
      	// setInterval(function() { fnGetCheckinCheckoutBySN('6523144700057'); },2000);
      	serialNo = $( "#bioSerialNo" ).val();
      
       setInterval(function() { fnGetCheckinCheckoutBySN(serialNo); },2000);
       $( "#dialog" ).dialog('close');
     });
	 
	
	
	


});

function clearMessages() {
	
	$('#errorMessageId').html("");	
	$('#nameId').html("");	
	$('#empNoId').html("");	
	$('#timeInId').html("");	
	$('#timeOutId').html("");	
	 $('#imgId').attr('src',"");
}
function fnGetCheckinCheckoutBySN(oSN) {
	
	
	var oAjaxCall = $.ajax({
		type : "POST",
		url : "/hris/DisplayKioskTimeEntryAction",
		
		data: "sn=" + oSN,
		cache : false,
		async : false,
		success : function(data) {

			
			 
			if(data.errorMessage == 'NOLATESTBIOENTRY') {
				// DO NOTHING
			}			
			else if(data.errorMessage == 'NOMATCHEMP'){
				
				clearMessages();
				
				$('#errorMessageId').html('No matching employee id in DB, please check with ADMIN');	
				$('#nameId').html("");	
				$('#empNoId').html("");	
				$('#timeInId').html("");	
				$('#timeOutId').html("");	
				 $('#imgId').attr('src',"");

			}
			else if(data.errorMessage == 'NOSHIFT'){
				clearMessages();
				
				$('#errorMessageId').html('NO SHIFT in DB, please check with ADMIN');	
				$('#nameId').html(data.firstname + " " + data.lastname);	
				$('#empNoId').html(data.empNo);	
				$('#imgId').attr('src',data.picLoc);

			}
			else if(data.errorMessage == 'NOTINSHIFT'){
				clearMessages();
				
				$('#errorMessageId').html('You are not in shift, time entry not allowed, please check with supervisor');	
				$('#nameId').html(data.firstname + " " + data.lastname);	
				$('#empNoId').html(data.empNo);	
				$('#imgId').attr('src',data.picLoc);

			}

			else if (data.firstname.length > 0 )  {
				clearMessages();
				$('#nameId').html(data.firstname + " " + data.lastname);	
				$('#empNoId').html("Employee Number: " + data.empNo);	
				$('#timeInId').html("Time In: " + data.timeIn);	
				$('#timeOutId').html("Time Out: " +data.timeOut);	
				 $('#imgId').attr('src',data.picLoc);
				
			}

			
			
		},
		error : function(data) {
			//alert('error: ' + data);
		}

	});
};



</script>

<script type="text/javascript">
	function login() {

		if (document.loginForm.username.value == null
				|| document.loginForm.username.value.length == 0) {
			alert('Username is a mandatory field.');
			document.loginForm.username.focus();
			return;
		}
		if (document.loginForm.password.value == null
				|| document.loginForm.password.value.length == 0) {
			alert('Password is a mandtory field.');
			document.loginForm.password.focus();
			return;
		}

		document.loginForm.submit();
	}
</script>


</head>
<body>
<div style="width: 100%; margin: 0px auto;">
	<div id="timeEnryContent">
		<div id="timeEntryLeftPannel">
			<!-- Employee Information -->
			<div>
				<!-- Picture -->
				<div style="float: left;">
					<img src="" id="imgId" width="330px" height="430px" style="padding: 20px;" />
				</div>
				
				
			</div>
		</div>
		<!-- Right Side of Dashboard -->
		<div id="timeEntryRightPannel">
			<form method="POST" name="loginForm" action="LoginAction">
				
				<!-- Employee Info -->
					
					<div id="errorMessageId"></div>
					
					<div class="employeeNameDetailsTimeArea" id="nameId"></div>
					<div class="employeeDetailsTimeArea" id="empNoId"></div>
					<div class="employeeDetailsTimeArea" id="timeInId"></div>
					<div class="employeeDetailsTimeArea" id="timeOutId"></div>
					<!--   TODO 
					<div class="employeeDetailsTimeArea">Developer Analyst III</div>
					<div class="employeeDetailsTimeArea">Application Management</div>
					<div class="pendingMessagesTimeArea">You have (2 Memo Messages)!</div>
					<div class="pendingMessagesTimeArea">You have (2 Notification Messages)!</div>
					-->
			</form>
		</div>
		<div class="cb"></div>		
	</div>
</div>

<div id="dialog" title="">
	<label for="name">Biometrics serial number</label>
	<input type="text"  id="bioSerialNo" class="text ui-widget-content ui-corner-all">
	<input type="submit" value="OK">
			
  
</div>

</body>
</html>