<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Medicare Share Allowance</title>
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
			    <li><a href="#tabs-nohdr-1">Medicare Share Allowance</a></li>			    
			  </ul>
			  <div id="tabs-nohdr-1">			    
			    <form method="POST" id="addMedicareShareAllowanceForm" name="addMedicareShareAllowanceForm" action="SaveMedicareShareAllowanceAction">
			    	<input type="hidden" name="createdBy" id="createdBy" value="${employeeLoggedIn.empId}" />						
				    <div class="dataEntryText">No. of Days</div>
				    <div class="dataEntryInput"><input type="text" name="numDays"  value="${param.numDays}" placeholder="No. of Days" /></div>
				    <div class="dataEntryText">Rate per Day</div>			    
				    <div class="dataEntryInput"><input type="text" name="ratePerDay"  value="${param.ratePerDay}" placeholder="Rate per Day" /></div>
				    <div class="cb"></div>
				    <div class="dataEntryText">Net Amount Due</div>
				    <div class="dataEntryInput"><input type="text" name="netAmountDue"  value="${param.netAmountDue}" placeholder="Net Amount Due" /></div>
				    <div class="dataEntryText">Date</div>
				    <div class="dataEntryInput"><input type="text" name="date"  value="${param.date}" placeholder="Date" /></div>
				    <div class="cb"></div>
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
