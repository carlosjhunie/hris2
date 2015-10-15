<%@ page language="java" contentType="text/html; charset=iso-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Time Entry Calendar</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/dai.css" />
<link rel="stylesheet" type="text/css" href="css/styleTables.css" />
<link rel="stylesheet" href="css/datePickerStyle.css">
<link rel="stylesheet" type="text/css" href="css/calendarmodal.css" />
<link href='css/timeentry/fullcalendar.css' rel='stylesheet' />
<link rel="stylesheet" type="text/css" href="css/jquery-ui-1.9.2.custom.css" />
<link href='css/timeentry/fullcalendar.print.css' rel='stylesheet' media='print' />
<script src='js/common.js'></script>

<script src='js/timeentry/lib/moment.min.js'></script>
<script src='js/timeentry/lib/jquery.min.js'></script>
<script src='js/timeentry/fullcalendar.min.js'></script>
<script type="text/javascript" src="js/jquery-ui-1.10.0.min.js"></script>
<script src="js/jquery-ui-1.11.4/jquery-ui.js"></script>

<script src="js/employee.js"></script>



<link rel="stylesheet" type="text/css" href="css/styleTables.css" />
<link rel="stylesheet" type="text/css" href="css/style.css" />
<link rel="stylesheet" type="text/css" href="css/dai.css" />
<link rel="stylesheet" href="css/datePickerStyle.css">

<script type="text/javascript" src="js/common.js"></script>
<!-- Tabs -->
<link rel="stylesheet" type="text/css" href="css/jquery-ui-1.9.2.custom.css" />
<script type="text/javascript" src="js/jquery-ui-1.10.0.min.js"></script>
<script src="js/jquery-1.10.2.js"></script>
<script src="js/common.js"></script>
<script src="js/employee.js"></script>
<script src="js/jquery-ui-1.11.4/jquery-ui.js"></script>

<script>

	$(document).ready(function() {
		var superVisorId = document.getElementById("empIdLoggedIn").value;
		
		var oAjaxCall = $.ajax({
			type : "POST",
			url : "GetEmployeeScheduleAction?scheduleDate=" + '${param.scheduleDate}',
			cache : false,
			async : false,
			dataType : "json",
			success : function(data) {
				var items = "<table id='box-table-a' summary='Employee Pay Sheet' width='820px;'>";
				items += "<thead>";
				items += "<tr>";
				items += "<th scope='col'>Name</th>";
				items += "<th scope='col'>Date</th>";
				items += "<th scope='col'>Schedule</th>";
				items += "<th scope='col'>&nbsp;</th>";
				items += "<th scope='col'>&nbsp;</th>";
				items += "<th scope='col'>&nbsp;</th>";
				items += "</tr>";
				items += "</thead>";
				items += "<tbody>";
				
				if (data.Records.length) {
					$.each(data.Records, function(i, item) {
						items += "<tr>";
						items += "<td>" + item.empName + "</td>";
						items += "<td>" + item.scheduleDate + "</td>";
						items += "<td>" + item.empShift + "</td>";
						items += "<td><img src='images/plus.png' alt='' width='30px' height='30px' onclick='addSchedule(" + item.empId + ");' /></td>";
						items += "<td><img src='images/delete.png' alt='' width='30px' height='30px' onclick='deleteSchedule(" + item.empScheduleId + ");' /></td>";
						items += "<td><img src='images/edit_blue.png' alt='' width='30px' height='30px' onclick='editSchedule(" + item.empScheduleId + ", " + item.scheduleDate.replace(/-/g, "") + ", " + item.shiftingScheduleId + ");' /></td>";
						
						items += "</tr>";
					});				
				}
				;
				
				items += "</tbody>";			
				items += "</table>";
				
				$('#empSchedDetailsTable').html(items);

			},
			error : function(data) {
				alert('error: ' + data);
			}

		});
		
		
		populateShiftingScheduleDropDown();
		

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
	
	function addSchedule(empId) {
		$('#resolutionHeader').html("Add Employee Shift");
		$('#saveMethod').val("ADD");
		$('#empId').val(empId);
		setDialogWindow();
	}
	
	function editSchedule(empScheduleId, scheduleDate, shiftingScheduleId) {
		$('#resolutionHeader').html("Edit Employee Shift");
		$('#saveMethod').val("EDIT");
		$('#empScheduleId').val(empScheduleId);
		
		var newSchedDate = scheduleDate.toString();
		
		newSchedDate = newSchedDate.substring(0, 4) + "-" + newSchedDate.substring(4, 6) + "-" + newSchedDate.substring(6, 8);
		
		
		
		$('#scheduleDate').val(newSchedDate);
		$('#shiftingScheduleDropDownID').val(shiftingScheduleId);
		setDialogWindow();
		
	}
	
	function saveSchedule() {
		document.addEmpShiftForm.submit();
	}
	
	
	
	function deleteSchedule(empScheduleId){
		
		if (confirm('Are you sure you want to delete the record?')) {
			document.location.href="/hris/DeleteEmployeeScheduleAction?empScheduleId=" + empScheduleId;
		}
		
	}
	
	function setDialogWindow(){
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
	}
	
	
	

</script>


</head>
<body>
	<div>
		<c:import url="header.jsp" />
	</div>
	<input type="hidden" name="empIdLoggedIn" id="empIdLoggedIn" value="${employeeLoggedIn.empId}" />
	<div id="boxes">
		<div id="dialog" class="window" style="height: 210px;">
			<div id="resolutionHeader">Add Employee Shift</div>
			<form method="POST" id="addEmpShiftForm" name="addEmpShiftForm" action="SaveEmployeeScheduleNewAction">
			<input type="hidden" name="empIdLoggedIn" id="empIdLoggedIn" value="${employeeLoggedIn.empId}" />
			
			<input type="hidden" name="saveMethod" id="saveMethod" value="">
			<input type="hidden" name="empId" id="empId" value="">
			<input type="hidden" name="empScheduleId" id="empScheduleId" value="">
			<div>Shift Date&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" id="scheduleDate" name="scheduleDate" class="useDPicker" placeholder="Date" size="23" /></div>
			<div class="dataEntryInput">
				Shift Schedule&nbsp;&nbsp; <select name="shiftingScheduleId"
					id="shiftingScheduleDropDownID"
					style="width: 180px; height: 35px; background: #fff; border: 1px solid #52833b; margin-top: 5px; color: #A9A9A9;">

				</select>
			</div>
			
			<div class="cb" style="height: 20px;"></div>
			<div style="width: 400px; margin: 0 auto;">
				&nbsp;&nbsp;&nbsp;&nbsp; <input id="addEmployeeButton"
					value="Submit Schedule" border="0" onclick="javascript:saveSchedule();"> <input
					id="addEmployeeButton" value="Cancel" border="0" class="close">
			</div>
			</form>
		</div>
		
		
		<!-- Mask to cover the whole screen -->
		<div id="mask"></div>
	</div>
	<div id="content">
		<div style="width: 820px; margin: 0 auto;">
				<div id="clockEntryArea">					
					<div class="headerDashboardTable">					
						Employee Schedule
						<input id="addEmployeeButton" value="Add Employee Schedule" border="0" onclick="javascript:window.location='addEmpScheduleNew.jsp';">
						<input id="addEmployeeButton" value="Return To Calendar View" border="0" onclick="javascript:window.location='employeeScheduleCalendar.jsp';">
					</div>
					<div id="empSchedDetailsTable"></div>				
				</div>
			</div>
	</div>
	<div>
		<c:import url="footer.jsp" />
	</div>
</body>
</html>