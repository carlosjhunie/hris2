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
	
	
	showAttendanceDisputeStaffForHRTable(empId);	
	

});

function approveTimeEntry(timeEntryDisputeId) {
	if (confirm('Are you sure you want to approve the time entry?')) {
		$.ajax({
			type:"POST",
			url:"/hris/UpdateTimeEntryDisputeStatusAction?newStatus=HA&timeEntryDisputeId="+timeEntryDisputeId,			
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
		
		<div style="width: 820px; margin: 0 auto;">
					<div class="headerDashboardTable">Employee List with Pending Time Entry Dispute Approval</div>		
					<div id="attendanceDisputeStaffTable"></div>							
				</div>
		<div class="cb" style="height: 50px;"></div>
	</div>
</div>	

<div style=""><c:import url="footer.jsp" /></div>
</body>
</html>