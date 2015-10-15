<%@ page language="java" contentType="text/html; charset=iso-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Time Entry Dispute</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/styleTables.css" />
<link rel="stylesheet" type="text/css" href="css/style.css" />
<link rel="stylesheet" type="text/css" href="css/dai.css" />

	
<script src="js/jquery-1.10.2.js"></script>

<script src="js/jquery-ui-1.11.4/jquery-ui.js"></script>
<link rel="stylesheet" type="text/css" href="css/jquery.timepicker.css" />
<script type="text/javascript" src="js/jquery.timepicker.js"></script>
<script>
	$(document).ready(function() {	
		
		$('#timeInHrs').timepicker({ 'timeFormat': 'H:i' });
		$('#timeOutHrs').timepicker({ 'timeFormat': 'H:i' });
		
		$('#resolutionButton').click(function() {
			document.resolutionForm.submit();
		});	
		
		
		

	});
	
function resolveTimeEntry(timeEntryId, empId, shiftScheduleId){
		
		
		
		var id = "#dialog";			
		
		//Get the screen height and width
		var maskHeight = $(document).height();
		var maskWidth = $(window).width();

		//Set heigth and width to mask to fill up the whole screen
		$('#mask').css({
			'width' : maskWidth,
			'height' : maskHeight
		});

		//transition effect		
		$('#mask').fadeIn(1000);
		$('#mask').fadeTo("slow", 0.8);

		//Get the window height and width
		var winH = $(window).height();
		var winW = $(window).width();

		//Set the popup window to center
		$(id).css('top', winH / 2 - $(id).height() / 2);
		$(id).css('left', winW / 2 - $(id).width() / 2);

		//transition effect
		$(id).fadeIn(2000);
		
		
		$('#empId').val(empId);
		$('#shiftScheduleId').val(shiftScheduleId);
		$('#timeEntryId').val(timeEntryId);
		
	}
	
	
</script>

</head>
<body>
	<div>
		<c:import url="header.jsp" />
	</div>
	<div id="content">

		<div style="height: 300px; width: 400px; margin: 0px auto; background-color: #E8EDFF;">
				<form method="POST" id="resolutionForm" name="resolutionForm" action="TimeEntryDisputeAction">
				
				<input type="hidden" id="shiftScheduleId" name="shiftScheduleId"  value="${param.shiftScheduleId}" />
				<input type="hidden" id="timeEntryId" name="timeEntryId"  value="${param.timeEntryId}" />
				
				<input type="hidden" name="clockDate"  value="${param.clockDate}" />
				<input type="hidden" id="empId" name="empId"  value="${param.empId}" />
				<input type="hidden" id="resolvedBy" name="resolvedBy"  value="${employeeLoggedIn.empId}" />
				<div id="resolutionHeader">Dispute Missing Time In and Out</div>
				
				<div class="resolutionText">
					Time In &nbsp;&nbsp;&nbsp;<input id="timeInHrs" name="timeInHrs" type="text" size="5" placeholder="HH:MM"
						width="30px;" />
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	
					Time Out &nbsp;&nbsp;&nbsp;<input id="timeOutHrs" name="timeOutHrs" type="text" size="5" placeholder="HH:MM"
						width="30px;" />
				</div>
				<div class="resolutionText">					
					
				</div>
				<div style="margin: 0px 0px 0px 25px;">
					Update Time Entry Reason<br />
					<textarea rows="6" cols="45" name="resolutionRemarks"></textarea>
					<br />
				</div>
				<div style="width: 400px; margin: 0 auto;">
					&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;
					<input id="resolutionButton" value="Submit as Resolved" border="0" > 
					<input id="addEmployeeButton" value="Clear" border="0" type="reset" style="padding: 0px 50px 0px 50px;">
				</div>
				</form>
			</div>

		

	</div>
	<div>
		<c:import url="footer.jsp" />
	</div>
</body>
</html>