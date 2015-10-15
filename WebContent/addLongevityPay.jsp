<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Longevity Pay</title>
<%@include file="commonHeader.jsp" %>
<%@include file="commonJtables.jsp" %>
<link rel="stylesheet" type="text/css" href="css/dai.css" />

<script type="text/javascript" src="js/common.js" ></script>
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
<div style=""><c:import url="header.jsp" /></div>
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
			    <li><a href="#tabs-nohdr-1">Longevity Pay</a></li>			    
			  </ul>
			  <div id="tabs-nohdr-1">			    
			    <form method="POST" id="addLongevityPayForm" name="addLongevityPayForm" action="SaveLongevityPayAction">
			    	<input type="hidden" name="createdBy" id="createdBy" value="${employeeLoggedIn.empId}" />						
				    <div class="dataEntryText">Salary Grade</div>
				    <div class="dataEntryInput"><input type="text" name="salaryGrade"  value="${param.salaryGrade}" placeholder="Salary Grade" /></div>
				    <div class="dataEntryText">Basic Salary</div>			    
				    <div class="dataEntryInput"><input type="text" name="basicSalary"  value="${param.basicSalary}" placeholder="Basic Salary" /></div>
				    <div class="cb"></div>
				    <div class="dataEntryText">Net Amount Due</div>
				    <div class="dataEntryInput"><input type="text" name="netAmountDue"  value="${param.netAmountDue}" placeholder="Net Amount Due" /></div>
				    <div class="dataEntryText">Year</div>
				    <div class="dataEntryInput"><input type="text" name="year"  value="${param.year}" placeholder="Year" /></div>
				    <div class="cb"></div>
				    <div class="dataEntryText">Month</div>
				    <div class="dataEntryInput"><input type="text" name="month"  value="${param.month}" placeholder="Month" /></div>
				    <div class="dataEntryText">Remarks</div>
				    <div class="dataEntryInput"><input type="text" name="remarks"  value="${param.remarks}" placeholder="Remarks" /></div>
				    <div class="cb"></div>				    		    
				</form>
			    <div class="cb" style="height: 10px;"></div>
			    <div class="employeeButton" onClick="save();">Save</div>
			    <div class="employeeButton">Clear</div>
			    <div class="cb"></div>
			  </div>
			</div>
		</div>		
</div>
<div style=""><c:import url="footer.jsp" /></div>
</body>
</html>
