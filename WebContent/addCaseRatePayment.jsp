
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>Case Rate Payment PHIC</title>
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

function saveCaseRatePayment() {
	$.ajax({
		type:"POST",
		url:"/hris/SaveCaseRatePaymentAction",
		data: JSON.stringify($('#addCaseRatePaymentForm').serializeObject()),
		cache: false,
		async: true,
		dataType: 'json',
		success: function (data) {alert("Saved! "+data);},
		error: function (data) {alert('error: '+data)}
	});
}

function clickSearchResult(empid) {
	$('#empId').val(empSearchMap[empid].empid);
	$('#empNo').val(empSearchMap[empid].empno);
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
		<div id="dashBoardLeftPannel2">
			<c:import url="searchEmployee_solr.jsp" />
			<div class="cb"></div>
			<div>
				<div id="searchHolderId"></div>	
			</div>
	    </div>
		</div>
		<div></div>
	</div>
	<div id="dashBoardRightPannel">
			<div id="tabs-nohdr">
			  <ul>
			    <li><a href="#tabs-nohdr-1">Case Rate Payment PHIC</a></li>			    
			  </ul>
			  <div id="tabs-nohdr-1">			    
			    <form method="POST" id="addCaseRatePaymentForm" name="addCaseRatePaymentForm" action="SaveCaseRatePaymentAction">
			    	<input type="hidden" name="empId"  value="1" placeholder="Employee" />
			    	<input type="hidden" name="createdBy" id="createdBy" value="${employeeLoggedIn.empId}" />						
				    <div class="dataEntryText">Gross Amount</div>
				    <div class="dataEntryInput"><input type="text" name="grossAmount"  value="${param.grossAmount}" placeholder="Gross Amount" /></div>
				    <div class="dataEntryText">Withholding Tax</div>			    
				    <div class="dataEntryInput"><input type="text" name="withHoldingTax"  value="${param.withHoldingTax}" placeholder="Withholding Tax" /></div>
				    <div class="cb"></div>
				    <div class="dataEntryText">Final Tax</div>
				    <div class="dataEntryInput"><input type="text" name="finalTax"  value="${param.finalTax}" placeholder="Final Tax" /></div>
				    <div class="dataEntryText">Net Amount Due</div>
				    <div class="dataEntryInput"><input type="text" name="netAmountDue"  value="${param.netAmountDue}" placeholder="Net Amount Due" /></div>
				    <div class="cb"></div>
				    <div class="dataEntryText">Year</div>
				    <div class="dataEntryInput"><input type="text" name="year"  value="${param.year}" placeholder="Year" /></div>
				    <div class="dataEntryText">Month</div>
				    <div class="dataEntryInput"><input type="text" name="month"  value="${param.month}" placeholder="Month" /></div>
				    <div class="cb"></div>
				    <div class="dataEntryText">Batch</div>
				    <div class="dataEntryInput"><input type="text" name="batch"  value="${param.batch}" placeholder="Batch" /></div>
				    <div class="dataEntryText">Remarks</div>
				    <div class="dataEntryInput"><input type="text" name="remarks"  value="${param.remarks}" placeholder="Remarks" /></div>
				    <div class="cb"></div>				    		    
				</form>
			    <div class="cb" style="height: 10px;"></div>
			    <div class="employeeButton" onClick="saveCaseRatePayment();">Save</div>
			    <div class="employeeButton">Clear</div>
			    <div class="cb"></div>
			    
			  </div>
			  
			  
			</div>
		</div>		
</div>	
<div><c:import url="footer.jsp" /></div>
</body>
</html>
