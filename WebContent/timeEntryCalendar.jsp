<%@ page language="java" contentType="text/html; charset=iso-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Time Entry Calendar</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/dai.css" />
<link href='css/timeentry/fullcalendar.css' rel='stylesheet' />
<link href='css/timeentry/fullcalendar.print.css' rel='stylesheet' media='print' />
<link rel='stylesheet' href='css/timeentry/lib/cupertino/jquery-ui.min.css' />
<link rel="stylesheet" type="text/css" href="css/calendarmodal.css" />
<script src='js/timeentry/lib/moment.min.js'></script>
<script src='js/timeentry/lib/jquery.min.js'></script>
<script src='js/timeentry/fullcalendar.min.js'></script>

<script>

	$(document).ready(function() {
		var superVisorId = document.getElementById("empIdLoggedIn").value;

		//alert(superVisorId);
		$('#calendar').fullCalendar({
			theme: true,
			editable: true,
			eventLimit: true, // allow "more" link when too many events
			events: {
				url: '/hris/GetTimeEntryCalendarAction?superVisorId=' + superVisorId,
				error: function() {
					$('#script-warning').show();
				}
			},
			loading: function(bool) {
				$('#loading').toggle(bool);
			}
		});
		
		
		
		
		
	});

</script>

</head>
<body>
<div><c:import url="header.jsp" /></div>
<input type="hidden" name="empIdLoggedIn" id="empIdLoggedIn" value="${employeeLoggedIn.empId}" />
<div id="content">
<div id="legendArea">
	<div>Paid - Rest Day <div style="width: 60px; height: 10px; background-color: #838389; float:left; margin: 5px 10px 0px 0px"></div></div>
	<div class="cb"></div>
	<div>Unpaid - Rest Day <div style="width: 60px; height: 10px; background-color: #000000; float:left; margin: 5px 10px 0px 0px"></div></div>
	<div class="cb"></div>
	<div>Problematic Time Entry <div style="width: 60px; height: 10px; background-color: #FF0000; float:left; margin: 5px 10px 0px 0px"></div></div>
	<div class="cb"></div>
	<div>Perfect Time Entry <div style="width: 60px; height: 10px; background-color: #008000; float:left; margin: 5px 10px 0px 0px"></div></div>
	<div class="cb"></div>
	<div>Incomplete Time Entry <div style="width: 60px; height: 10px; background-color: #9900FF; float:left; margin: 5px 10px 0px 0px"></div></div>
	
	<div class="cb"></div>
</div>
<div id='calendar'></div>
</div>
<div><c:import url="footer.jsp" /></div>
</body>
</html>