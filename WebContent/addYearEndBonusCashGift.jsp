<%@ page language="java" contentType="text/html; charset=iso-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Year End Bonus and Cash Gift</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
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

<script type="text/javascript">
$(document).ready(function() {	
	initDropDown();	
});	

function initDropDown() {
	populateJobTitleDropDown();	
}

</script>

</head>
<body>
<div><c:import url="header.jsp" /></div>
<div id="content">
	<div id="dashBoardLeftPannel">
		<div style="margin: 0px 0px 5px 15px;">Select Update Options</div>
		<div style="float: left;margin-left: 15px; margin-top: 7px;"><input type="radio" name="updateOption" value="jobTitle"></div>
		<div style="float: left;margin: 5px 0px 0px 15px; width: 100px;">By Job Title</div>
		<div style="float: left; margin-left: 15px; margin-top: 0px;">
			<select name="jobTitleId" id="jobTitleDropDownID" style="width:214px; height:30px; background:#fff; border: 1px solid #52833b; margin-top: 5px; color: #A9A9A9; font-size: 16px;" >			    													
							
			</select>
		</div>
		<div class="cb"></div>
		<div style="float: left;margin-left: 15px; margin-top: 7px;"><input type="radio" name="updateOption" value="employee"></div>
		<div style="float: left;margin: 5px 0px 0px 15px; width: 100px;">By Employee</div>
		<div style="float: left; margin-left: 15px;">
			<select name="jobTitleId" id="jobTitleDropDownID" style="width:214px; height:30px; background:#fff; border: 1px solid #52833b; margin-top: 5px; color: #A9A9A9; font-size: 16px;" >			    													
							
			</select>
		</div>
		<div></div>
	</div>
	<div id="dashBoardRightPannel">
			<div id="tabs-nohdr">
			  <ul>
			    <li><a href="#tabs-nohdr-1">Year End Bonus and Cash Gift</a></li>			    
			  </ul>
			  <div id="tabs-nohdr-1">			    
			    <form method="POST" id="addEmployeeForm" name="addEmployeeForm" action="AddEmployeeAction">
			    	<input type="hidden" name="createdBy" id="createdBy" value="${employeeLoggedIn.empId}" />						
				    <div class="dataEntryTextLong">Salary Grade</div>
				    <div class="dataEntryInput"><input type="text" name="empNo"  value="${param.empNo}" placeholder="Salary Grade" /></div>
				    <div class="dataEntryTextLong">STEP</div>			    
				    <div class="dataEntryInput"><input type="text" name="firstname"  value="${param.firstname}" placeholder="STEP" /></div>
				    <div class="cb"></div>
				    <div class="dataEntryTextLong">Basic Salary</div>
				    <div class="dataEntryInput"><input type="text" name="lastname"  value="${param.lastname}" placeholder="Basic Salary" /></div>
				    <div class="dataEntryTextLong">Cash Gift</div>
				    <div class="dataEntryInput"><input type="text" name="middlename"  value="${param.middlename}" placeholder="Cash Gift" /></div>
				    <div class="cb"></div>
				    <div class="dataEntryTextLong">Total</div>
				    <div class="dataEntryInput"><input type="text" name="username"  value="${param.username}" placeholder="Total" /></div>
				    <div class="dataEntryTextLong">Basic Salary as of Oct 31</div>
				    <div class="dataEntryInput"><input type="text" name="password"  value="${param.password}" placeholder="Basic Salary as of Oct 31" /></div>
				    <div class="cb"></div>
				    <div class="dataEntryTextLong">Cash Gift 1</div>
				    <div class="dataEntryInput"><input type="text" name="dateOfBirth"  value="${param.dateOfBirth}" placeholder="Cash Gift 1" /></div>
				    <div class="dataEntryTextLong">1st Half 13th Month</div>
				    <div class="dataEntryInput"><input type="text" name="email"  value="${param.email}" placeholder="1st Half 13th Month" /></div>
				    <div class="cb"></div>
				    <div class="dataEntryTextLong">1st Half Cash Gift</div>
				    <div class="dataEntryInput"><input type="text" name="telNo"  value="${param.telNo}" placeholder="1st Half Cash Gift" /></div>
				    <div class="dataEntryTextLong">2nd Half 13th Month</div>
				    <div class="dataEntryInput"><input type="text" name="mobileNo"  value="${param.mobileNo}" placeholder="2nd Half 13th Month" /></div>
				    <div class="cb"></div>
				    <div class="dataEntryTextLong">2nd Half Cash Gift</div>
				    <div class="dataEntryInput"><input type="text" name="telNo"  value="${param.telNo}" placeholder="2nd Half Cash Gift" /></div>
				    <div class="dataEntryTextLong">Total Year-End Bonus and Cash Gift</div>
				    <div class="dataEntryInput"><input type="text" name="mobileNo"  value="${param.mobileNo}" placeholder="Total Year-End Bonus and Cash Gift" /></div>
				    <div class="cb"></div>
				    <div class="dataEntryTextLong">EAMC Coop Deduction</div>
				    <div class="dataEntryInput"><input type="text" name="telNo"  value="${param.telNo}" placeholder="EAMC Coop Deduction" /></div>
				    <div class="dataEntryTextLong">Net Amount Due</div>
				    <div class="dataEntryInput"><input type="text" name="mobileNo"  value="${param.mobileNo}" placeholder="Net Amount Due" /></div>
				    <div class="cb"></div>
				    <div class="dataEntryTextLong">Year</div>
				    <div class="dataEntryInput"><input type="text" name="telNo"  value="${param.telNo}" placeholder="Year" /></div>				    
				    <div class="cb"></div>
				    		    
				</form>
			    <div class="cb" style="height: 10px;"></div>
			    <div class="employeeButton" onClick="saveEmployee();">Save</div>
			    <div class="employeeButton">Clear</div>
			    <div class="cb"></div>
			    
			  </div>
			  
			  
			</div>
		</div>		
</div>	
<div><c:import url="footer.jsp" /></div>
</body>
</html>