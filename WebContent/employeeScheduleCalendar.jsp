<%@ page language="java" contentType="text/html; charset=iso-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Time Entry Calendar</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/dai.css" />
<link rel="stylesheet" type="text/css" href="css/calendarmodal.css" />
<link href='css/timeentry/fullcalendar.css' rel='stylesheet' />
<link href='css/timeentry/fullcalendar.print.css' rel='stylesheet'
	media='print' />
<script src='js/common.js'></script>

<script src='js/timeentry/lib/moment.min.js'></script>
<script src='js/timeentry/lib/jquery.min.js'></script>
<script src='js/timeentry/fullcalendar.min.js'></script>
<script type="text/javascript" src="js/jquery-ui-1.10.0.min.js"></script>

<script src="js/employee.js"></script>

<script>

	$(document).ready(function() {
		var superVisorId = document.getElementById("empIdLoggedIn").value;
		
		populateShiftingScheduleDropDown();
		populateEmployeeCheckBoxes(superVisorId);
		
		$(document).ready(function() {
		    $('#selectAllCheckBox').click(function(event) {  //on click 
		        if(this.checked) { // check select status
		            $('.checkbox1').each(function() { //loop through each checkbox
		                this.checked = true;  //select all checkboxes with class "checkbox1"               
		            });
		        }else{
		            $('.checkbox1').each(function() { //loop through each checkbox
		                this.checked = false; //deselect all checkboxes with class "checkbox1"                       
		            });         
		        }
		    });
		    
		});

		//alert(superVisorId);
		$('#calendar').fullCalendar({
			header: {
				left: 'prev,next today',
				center: 'title',
				right: 'month'
			},
			selectable: true,
			selectHelper: true,
			select: function(start, end) {
				var dateSelected = moment(start).format('YYYY-MM-DD');
				$.ajax({
					type:"POST",
					url:"/hris/CheckEmployeeScheduleAction?scheduleDate="+ dateSelected,
					
					async: true,
					dataType: 'json',
					success: function (data) {
						
						
						
						if(data.Record == "YES") {
							 window.location="employeeScheduleDetails.jsp?scheduleDate=" + dateSelected;
						} else {
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
							
							document.getElementById("scheduleDate").value = dateSelected;	
						}
						
									
					},
					error: function (data) {alert('error: '+data)}
				});
				
				
			},
			editable: true,
			eventLimit: true, // allow "more" link when too many events
			events: {
				
				url: '/hris/GetEmployeeScheduleCalendarAction?superVisorId=' + superVisorId,
				error: function() {
					$('#script-warning').show();
				}
			},
			loading: function(bool) {
				$('#loading').toggle(bool);
			}
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
	
	function saveSchedule() {
		$.ajax({
			type:"POST",
			url:"/hris/SaveEmployeeScheduleAction",
			data: JSON.stringify($('#addEmpShiftForm').serializeObject()),
			cache: false,
			async: true,
			dataType: 'json',
			success: function (data) {
				alert('Employee schedule has been successfully saved.');

				$('#mask').hide();
				$('.window').hide();				
				
				$('#calendar').fullCalendar( 'refetchEvents' );
			
			},
			error: function (data) {alert('error: '+data)}
		});
	}
	
	$.fn.serializeObject = function() {
		var o = {};
		var a = this.serializeArray();
		$.each(a, function() {
			if (o[this.name] !== undefined) {
				if (!o[this.name].push) {
					o[this.name] = [ o[this.name] ];
				}
				o[this.name].push(this.value || '');
			} else {
				o[this.name] = this.value || '';
			}
		});
		return o;
	};
	
	

</script>


</head>
<body>
	<div>
		<c:import url="header.jsp" />
	</div>
	<input type="hidden" name="empIdLoggedIn" id="empIdLoggedIn"
		value="${employeeLoggedIn.empId}" />
	<div id="boxes">
		<div id="dialog" class="window" style="height: 500px;">
			<div id="resolutionHeader">Add Custom Shift</div>
			<form method="POST" id="addEmpShiftForm" name="addEmpShiftForm" action="SaveEmployeeScheduleAction">
			<input type="hidden" name="empIdLoggedIn" id="empIdLoggedIn" value="${employeeLoggedIn.empId}" />
			
			<div class="dataEntryInput">
				Shift Schedule&nbsp;&nbsp; <select name="shiftingScheduleId"
					id="shiftingScheduleDropDownID"
					style="width: 180px; height: 35px; background: #fff; border: 1px solid #52833b; margin-top: 5px; color: #A9A9A9;">

				</select>
			</div>
			<input type="hidden" name="scheduleDate" id="scheduleDate" value="">
			<div class="cb" style="margin: 80px 0px 20px 0px;">			
				Select employees to apply shift&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" id="selectAllCheckBox" value="">Select All				
			</div>
			
			<div id="sidebar">
				<div id="scroller"></div>
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
	<div id="legendArea" style="margin-top: 250px;">
		
		<div>Paid - Rest Day <div style="width: 60px; height: 10px; background-color: #9900FF; float:left; margin: 5px 10px 0px 0px"></div></div>
		<div class="cb"></div>
		<div>Unpaid - Rest Day <div style="width: 60px; height: 10px; background-color: #666699; float:left; margin: 5px 10px 0px 0px"></div></div>
		<div class="cb"></div>
		<div>Unchanged Schedule <div style="width: 60px; height: 10px; background-color: #008000; float:left; margin: 5px 10px 0px 0px"></div></div>
		<div class="cb"></div>
		<div>Updated Schedule <div style="width: 60px; height: 10px; background-color: #0000FF; float:left; margin: 5px 10px 0px 0px"></div></div>
		<div class="cb"></div>
	</div>
	<div id="content">		
		<div style="margin: 0px 0px 10px 0px;"><input id="addEmployeeButton" value="Switch to Details View" border="0" onclick="javascript:window.location='employeeScheduleDetails.jsp?';" style="padding-left: 15px; padding-right: 15px;"></div>
		<div id='calendar'></div>
	</div>
	<div>
		<c:import url="footer.jsp" />
	</div>
</body>
</html>