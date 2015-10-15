<%@ page language="java" contentType="text/html; charset=iso-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Employee Dashboard</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/styleTables.css" />
<link rel="stylesheet" type="text/css" href="css/style.css" />
<link rel="stylesheet" type="text/css" href="css/dai.css" />
<script type="text/javascript" src="js/common.js"></script>
<script src="js/employee.js"></script>
<script type="text/javascript" src="js/jquery-ui-1.10.0.min.js"></script>
<script src="js/jquery-1.10.2.js"></script>
<!-- Pagination -->

<script type="text/javascript">
$(document).ready(function() {
	
	var empId = '${employeeLoggedIn.empId}'; 
	
	showMemoTable(empId);
	showLeaveTable(empId);
	showLeaveBalanceTable(empId);
	showTrainingTable(empId);
	showNotificationTable(empId);
	showAttendanceTable(empId);
	showAttendanceDisputeTable(empId);
	showAttendanceDisputeStaffTable(empId);
	getEmployeeInfoDashboard(empId);	
	
	
	var items = $(".resultItem");

    var numItems = items.length;
    var perPage = 10;
    
    var numPages = numItems;
    var remainingItems = 0;
    
    if(numItems < perPage){
    	numPages = 1;
    } else {    	
    	remainingItems = numItems % perPage;
    	
    	if(remainingItems > 0){
    		numPages = ((numItems - remainingItems) / perPage) + 1;	
    	} else {
    		numPages = (numItems - remainingItems) / perPage;
    	}
    	
    }    
    
    // only show the first 2 (or "first per_page") items initially
    items.slice(perPage).hide();

    // now setup your pagination
    // you need that .pagination-page div before/after your table
   $('.pagination').jqPagination({        
		max_page	: numPages,
		paged		: function(page) {			
			var showFrom = perPage * (page - 1);
            var showTo = showFrom + perPage;

            items.hide() // first hide everything, then show for the new page
            .slice(showFrom, showTo).show();
		}        
    });
    
    function showMessage(message){
    	alert(message);
    }

});

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
<div><c:import url="header.jsp" /></div>
<div>		
	<div id="content">
		<!-- Left Side of Dashboard -->
		<div id="dashBoardLeftPannel">
			<!-- Employee Information -->
			<div>
				<!-- Picture -->
				<div style="float: left;"><img id="empImgHolder" src="" alt="no image" alt="alternate text" width="180px" height="220px" style="padding:20px;" /></div>
				<!-- Employee Info -->
				<div class="welcomeHeaderDashboardTable">WELCOME Back!</div>
				<div class="nameDashBoard" id="employeeName"></div>
				<div class="jobTitle" id="jobTitleNameDashboard"></div>
				<div class="departmentDashboard" id="departmentNameDashEmployee"></div>				
				<div class="dashboardTxt">&nbsp;&nbsp;&nbsp;&nbsp;</div>
				<div class="dashboardTxtBig">&nbsp;</div>				
				<div class="dashboardTxt">&nbsp;&nbsp;&nbsp;&nbsp;</div>
				<div class="dashboardTxtBig">&nbsp;</div>
				<div class="cb"></div>
				<div class="dashBoardButton"><a href="employeeLeaveEntry.jsp?empId=${sessionScope.employeeLoggedIn.empId}">Request Time Off</a></div>
				<div class="dashBoardButton"><a href="employeeOvertimeEntry.jsp?empId=${sessionScope.employeeLoggedIn.empId}">File Overtime</a></div>
				<div class="dashBoardButton"><a href="employeeOutOfOfficeEntry.jsp?empId=${sessionScope.employeeLoggedIn.empId}">File Training &amp; Seminar</a></div>				
			</div>
		</div>
		<!-- Right Side of Dashboard -->
		<div id="dashBoardRightPannel">
			<!-- Notifications -->
			<div>
				
				<div style="float: left;">						
					<div id="leaveTable"></div>							
				</div>
				
				<div class="cb"></div>
				<div style="float: left;">
					<div class="headerDashboardTable">Employee Attendance List</div>		
					<div id="attendanceTable"></div>							
				</div>
				
				<div class="cb"></div>
				<div style="float: left;">
					<div class="headerDashboardTable">Leave Balance</div>		
					<div id="leaveBalanceTable"></div>							
				</div>
				
				<div class="cb"></div>
				<div style="float: left;">							
					<div id="attendanceDisputeTable"></div>							
				</div>
				<div class="cb"></div>
				<div style="float: left;">						
					<div id="attendanceDisputeStaffTable"></div>							
				</div>
				<div class="cb"></div>
				<div style="float: left;">
					
					<div id="trainingTable"></div>						
				</div>
				<div class="cb"></div>
				<div style="float: left;">						
					<div id="memoTable"></div>					
				</div>
				<div style="float: left;">					
					<div id="notificationTable"></div>					
				</div>
				<div class="cb"></div>
				
				
			</div>
		</div>
		<div class="cb" style="height: 50px;"></div>
	</div>
</div>	

<div style=""><c:import url="footer.jsp" /></div>
</body>
</html>
