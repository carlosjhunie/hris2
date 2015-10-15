<%@ page language="java" contentType="text/html; charset=iso-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Time Entry Calendar</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/styleTables.css" />
<link rel="stylesheet" type="text/css" href="css/style.css" />
<link rel="stylesheet" type="text/css" href="css/dai.css" />
<link rel="stylesheet" type="text/css" href="css/jquery-ui-1.9.2.custom.css" />
<link rel="stylesheet" type="text/css" href="css/calendarmodal.css" />

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
		
		
		//if close button is clicked
		$('.window .close').click(function(e) {
			//Cancel the link behavior
			e.preventDefault();

			$('#mask').hide();
			$('.window').hide();
		});
		
		
		

		//if mask is clicked
		$('#mask').click(function() {
			$(this).hide();
			$('.window').hide();
		});

		$(window).resize(function() {

			var box = $('#boxes .window');

			//Get the screen height and width
			var maskHeight = $(document).height();
			var maskWidth = $(window).width();

			//Set height and width to mask to fill up the whole screen
			$('#mask').css({
				'width' : maskWidth,
				'height' : maskHeight
			});

			//Get the window height and width
			var winH = $(window).height();
			var winW = $(window).width();

			//Set the popup window to center
			box.css('top', winH / 2 - box.height() / 2);
			box.css('left', winW / 2 - box.width() / 2);

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
	
	function approveTimeEntry(timeEntryDisputeId) {
		if (confirm('Are you sure you want to approve the time entry?')) {
			$.ajax({
				type:"POST",
				url:"/hris/UpdateTimeEntryDisputeStatusAction?newStatus=SA&timeEntryDisputeId="+timeEntryDisputeId,			
				async: true,
				dataType: 'json',
				success: function (data) {				
					if(data.Record == "YES") {
						alert("Time Entry Dispute has been approved.");
						location.reload();
					}							
				},
				error: function (data) {alert('error: '+data)}
			});
		}		
	}
	
	
</script>


</head>
<body>
	<div>
		<c:import url="header.jsp" />
	</div>
	<div id="content">



		<div id="boxes">

			<div id="dialog" class="window" style="height: 400px;">
				<form method="POST" id="resolutionForm" name="resolutionForm" action="TimeEntryResolutionAction">
				
				<input type="hidden" id="shiftScheduleId" name="shiftScheduleId"  value="" />
				<input type="hidden" id="timeEntryId" name="timeEntryId"  value="" />
				
				<input type="hidden" name="clockDate"  value="${param.clockDate}" />
				<input type="hidden" id="empId" name="empId"  value="" />
				<input type="hidden" id="resolvedBy" name="resolvedBy"  value="${employeeLoggedIn.empId}" />
				<div id="resolutionHeader">Resolve Missing Time In and Out</div>
				
				<div class="resolutionText">
					Time In &nbsp;&nbsp;&nbsp;<input id="timeInHrs" name="timeInHrs" type="text" size="5" placeholder="HH:MM"
						width="30px;" />
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	
					Time Out &nbsp;&nbsp;&nbsp;<input id="timeOutHrs" name="timeOutHrs" type="text" size="5" placeholder="HH:MM"
						width="30px;" />
				</div>
				<div class="resolutionText">					
					
				</div>
				<div class="resolutionRemarks" style="margin-left: 10px;">
					Update Time Entry Reason<br />
					<textarea rows="6" cols="45" name="resolutionRemarks"></textarea>
					<br />
				</div>
				<div style="width: 400px; margin: 0 auto;">
					&nbsp;&nbsp;&nbsp;&nbsp; 
					<input id="resolutionButton" value="Submit as Resolved" border="0" > 
					<input id="addEmployeeButton" value="Cancel" border="0" class="close">
				</div>
				</form>
			</div>



			<!-- Mask to cover the whole screen -->
			<div id="mask"></div>


			<div style="width: 820px; margin: 0 auto;">
				<div id="clockEntryArea">
					<c:if test="${not empty timeEntryListProblematic}">
						<div class="headerDashboardTable">
							Employee Schedule and Time Entry -
							<c:out value="${param.clockDate}"></c:out>
						</div>
						<table id="box-table-a" summary="Employee Pay Sheet"
							width="820px;">
							<thead>
								<tr>
									<th scope="col">Name</th>
									<th scope="col">Schedule</th>
									<th scope="col">Time In</th>
									<th scope="col">Time Out</th>
									<th scope="col">&nbsp;</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="timeEntryProb"
									items="${timeEntryListProblematic}">
									<tr>
										<td>${timeEntryProb.empName}</td>
										<td>${timeEntryProb.shiftScheduleDesc}</td>
										<td>${timeEntryProb.timeIn}</td>
										<td>${timeEntryProb.timeOut}</td>
										<td>
											<c:if test="${timeEntryProb.shiftScheduleId != 2000 && timeEntryProb.shiftScheduleId != 2001}">
												<div id="resolveButton" style="color: red;" onClick="resolveTimeEntry(${timeEntryProb.timeEntryId},${timeEntryProb.empId},${timeEntryProb.shiftScheduleId});">Resolve</div>
												
											</c:if>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</c:if>
					<c:if test="${not empty timeEntryDisputeList}">
						<div class="headerDashboardTable">
							Employee Schedule and Time Entry -
							<c:out value="${param.clockDate}"></c:out>
						</div>
						<table id="box-table-a" summary="Employee Pay Sheet"
							width="820px;">
							<thead>
								<tr>
									<th scope="col">Name</th>
									<th scope="col">Schedule</th>
									<th scope="col">Time In</th>
									<th scope="col">Time Out</th>
									<th scope="col">&nbsp;</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="timeEntryDispute"	items="${timeEntryDisputeList}">
									<tr>
										<td>${timeEntryDispute.empName}</td>
										<td>${timeEntryDispute.shiftScheduleDesc}</td>
										<td>${timeEntryDispute.timeIn}</td>
										<td>${timeEntryDispute.timeOut}</td>
										<td>										
											<c:if test="${timeEntryDispute.approvalStatus == 'P'}">
												<div><div style="color: red;" id="resolveButton" onClick="approveTimeEntry(${timeEntryDispute.timeEntryDisputeId});">APPROVE TIME ENTRY</div></div>												
											</c:if>
											<c:if test="${timeEntryDispute.approvalStatus == 'SA'}">
												<div>-- PENDING HR APPROVAL --</div>												
											</c:if>
											<c:if test="${timeEntryDispute.approvalStatus == 'HA'}">
												<div>-- HR APPROVED --</div>												
											</c:if>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</c:if>
					
					
					
				</div>
			</div>
		</div>

	</div>
	<div>
		<c:import url="footer.jsp" />
	</div>
</body>
</html>