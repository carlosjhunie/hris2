<%@ page language="java" contentType="text/html; charset=iso-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Add Employee Schedule</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@include file="commonHeader.jsp" %>
<%@include file="commonJtables.jsp" %>
<link rel="stylesheet" type="text/css" href="css/styleTables.css" />
<link rel="stylesheet" type="text/css" href="css/style.css" />

<link rel="stylesheet" type="text/css" href="css/dai.css" />
<link href="js/validationEngine/validationEngine.jquery.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/validationEngine/jquery.validationEngine.js"></script>
<script type="text/javascript" src="js/validationEngine/jquery.validationEngine-en.js"></script>
<script type="text/javascript" src="js/common.js"></script>


<script type="text/javascript">
	$(document).ready(function() {
		populateShiftingScheduleDropDown();
		
		
		
	});

	function clickSearchResult(empid) {		
		alert(empid);
		
		$('#empNo').html(empSearchMap[empid].empno);
		$('#fullname').html(empSearchMap[empid].lastname + ", " + empSearchMap[empid].firstname);		
		$('#empId').val(empid);
		$('#saveMethod').val("ADD");
	}
	
	function saveSchedule() {
		document.addEmpShiftForm.submit();
	}
	
</script>

</head>
<body>
<div><c:import url="header.jsp" /></div>	
<div id="content">
 
	<div id="dashBoardLeftPannel2">
		<c:import url="searchEmployee_solr.jsp" />
		<div class="cb"></div>
		<div>
			<div id="searchHolderId"></div>	
		</div>
	</div>
	
	<!-- Right Side of Dashboard -->
	<div  id="dashBoardRightPannel2" width="100%">	
		<div class="dataEntryText">Employee No:</div>
	    <div class="dataEntryTextBlue" id="empNo"></div>	    
	    <div class="dataEntryText">Employee Name:</div>			    
	    <div class="dataEntryTextBlue" id="fullname"></div>	    
	    <div class="cb"></div>	    				
	  	<div>
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
			<div style="float: left;">
				<input id="addEmployeeButton" value="Submit Schedule" border="0" onclick="javascript:saveSchedule();"> 
				<input id="addEmployeeButton" value="Clear" border="0" type="reset" style="padding: 0px 30px 0px 30px;">
			</div>
			</form>
		</div>
	</div>	
</div>
<div style=""><c:import url="footer.jsp" /></div>
</body>
</body>
</html>