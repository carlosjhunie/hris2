<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Module Access</title>
<%@include file="commonHeader.jsp" %>
<%@include file="commonJtables.jsp" %>
<link rel="stylesheet" type="text/css" href="css/styleTables.css" />
<link rel="stylesheet" type="text/css" href="css/style.css" />
<link rel="stylesheet" type="text/css" href="css/dai.css" />

<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/moment.min.js"></script>
<script type="text/javascript">
function clickSearchResult(empid) {
	$('#empNo').html(empSearchMap[empid].empno);
	$('#fullname').html(empSearchMap[empid].lastname + ", " + empSearchMap[empid].firstname);
	$('#empId').val(empid);
	$('#dashBoardRightPannel2').show();
	var oAjaxCall = $.ajax({
		type : "POST",
		url : "/hris/GetModuleAccessAction?empId=" + empid,
		cache : false,
		async : false,
		dataType : "json",
		success : function(data) {
			console.log(data);
			$('.myCheckbox').attr('checked', false);
			if (data.Record) {
				$('#moduleAccessId').val(data.Record.moduleAccessId);
				if (data.Record.fileManagementList){
				$.each(data.Record.fileManagementList, function(i, item) {
					document.getElementById(item).checked = true;				    			
				});
				}
				if (data.Record.employeeList){
				$.each(data.Record.employeeList, function(i, item) {
					document.getElementById(item).checked = true;
				});	
				}
				if (data.Record.timeManagementList){
				$.each(data.Record.timeManagementList, function(i, item) {
					document.getElementById(item).checked = true;
				});	
				}
				if (data.Record.payrollList){
				$.each(data.Record.payrollList, function(i, item) {
					document.getElementById(item).checked = true;
				});	
				}
				if (data.Record.employeesLoanList){
				$.each(data.Record.employeesLoanList, function(i, item) {
					document.getElementById(item).checked = true;
				});	
				}
				if (data.Record.payrollReportsList){
				$.each(data.Record.payrollReportsList, function(i, item) {
					document.getElementById(item).checked = true;
				});	
				}
			}
		},
		error : function(data) {
			alert('error: moduleAccess' + data);
		}
	});
}

function saveModuleAccess() {
	
	document.moduleAccessForm.submit();
	//alert("Module Access for employee saved.");
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
	<div id="dashBoardRightPannel2" style="display:none">	
		<div class="dataEntryText">Employee No:</div>
	    <div class="dataEntryTextBlue" id="empNo"></div>	    
	    <div class="dataEntryText">Employee Name:</div>			    
	    <div class="dataEntryTextBlue" id="fullname"></div>	    
	    <div class="cb"></div>	    				
	  
	    <div>
			<div style="height: 1800px;">			    
			  <form method="POST" id="moduleAccessForm" name="moduleAccessForm" action="SaveModuleAccessAction">
				  	<input type="hidden" name="createdBy" id="createdBy" value="${employeeLoggedIn.empId}" />
				   	<input type="hidden" name="empId" id="empId" value="" />
				   	<input type="hidden" name="moduleAccessId" id="moduleAccessId" value="" />						
				    <div class="moduleAccessHeader" style="margin-top: 20px;">File Management</div>			    
				    <div class="myCheckboxTxt">
				    <input type="checkbox" name="fileManagementModule" class="myCheckbox" id="fm_job_title" value="fm_job_title" />Job Title
				    <input type="checkbox" name="fileManagementModule" class="myCheckbox" id="fm_holidays" value="fm_holidays" />Holidays
				    <input type="checkbox" name="fileManagementModule" class="myCheckbox" id="fm_loan_type" value="fm_loan_type" />Loan Type
				    <input type="checkbox" name="fileManagementModule" class="myCheckbox" id="fm_leave_type" value="fm_leave_type" />Leave Type
				    <input type="checkbox" name="fileManagementModule" class="myCheckbox" id="fm_department" value="fm_department" />Department
				    <input type="checkbox" name="fileManagementModule" class="myCheckbox" id="fm_division" value="fm_division" />Division
				    <input type="checkbox" name="fileManagementModule" class="myCheckbox" id="fm_city" value="fm_city" />City
				    <input type="checkbox" name="fileManagementModule" class="myCheckbox" id="fm_province" value="fm_province" />Province
				    <input type="checkbox" name="fileManagementModule" class="myCheckbox" id="fm_country" value="fm_country" />Country
				    <input type="checkbox" name="fileManagementModule" class="myCheckbox" id="fm_module_access" value="fm_module_access" />Module Access
				    </div>
				    <div class="cb"></div>
				    <div class="moduleAccessHeader">Employee</div>
				    <div class="myCheckboxTxt">
				    <input type="checkbox" name="employeeModule" class="myCheckbox" id="em_employee" value="em_employee" />Employee
				    <input type="checkbox" name="employeeModule" class="myCheckbox" id="em_memo" value="em_memo" />Memo
				    <input type="checkbox" name="employeeModule" class="myCheckbox" id="em_notification" value="em_notification" />Notification
				    <input type="checkbox" name="employeeModule" class="myCheckbox" id="em_file_leave" value="em_file_leave" />File Leave
				    <input type="checkbox" name="employeeModule" class="myCheckbox" id="em_file_ooo" value="em_file_ooo" />File Out of Office
				    <input type="checkbox" name="employeeModule" class="myCheckbox" id="em_file_ot" value="em_file_ot" />File Overtime
				    <input type="checkbox" name="employeeModule" class="myCheckbox" id="em_leave_approval" value="em_leave_approval" />Leave Approval<br/>
				    <input type="checkbox" name="employeeModule" class="myCheckbox" id="em_leave_approval_hr" value="em_leave_approval_hr" />Leave Approval for HR
				    <input type="checkbox" name="employeeModule" class="myCheckbox" id="em_ooo_approval" value="em_ooo_approval" />Out of Office Approval
				    <input type="checkbox" name="employeeModule" class="myCheckbox" id="em_ot_approval" value="em_ot_approval" />Overtime Approval
				    </div>
				    <div class="cb"></div>
				    <div class="moduleAccessHeader">Employee Time Management</div>
				    <div class="myCheckboxTxt">
				    <input type="checkbox" name="timeManagementModule" class="myCheckbox" id="tm_create_employee_shift" value="tm_create_employee_shift" />Create Employee Shift
				    <input type="checkbox" name="timeManagementModule" class="myCheckbox" id="tm_attendance_monitoring" value="tm_attendance_monitoring" />Attendance Monitoring
				    <input type="checkbox" name="timeManagementModule" class="myCheckbox" id="tm_employee_schedule" value="tm_employee_schedule" />Employee Schedule
				    <input type="checkbox" name="timeManagementModule" class="myCheckbox" id="tm_time_dispute_hr_approval" value="tm_time_dispute_hr_approval" />Time Dispute HR Approval
				    </div>
				    <div class="cb"></div>
				    <div class="moduleAccessHeader">Payroll</div>
				    <div class="myCheckboxTxt">
				    <input type="checkbox" name="payrollModule" class="myCheckbox" id="py_generate_lock_payroll" value="py_generate_lock_payroll" />Generate/Lock Payroll
				    <input type="checkbox" name="payrollModule" class="myCheckbox" id="py_payroll_period" value="py_payroll_period" />Payroll Period
				    <input type="checkbox" name="payrollModule" class="myCheckbox" id="py_income_benefits" value="py_income_benefits" />Income and Benefits
				    <input type="checkbox" name="payrollModule" class="myCheckbox" id="py_deductions" value="py_deductions" />Deductions
				    <input type="checkbox" name="payrollModule" class="myCheckbox" id="py_case_rate" value="py_case_rate" />Case Rate
				    <input type="checkbox" name="payrollModule" class="myCheckbox" id="py_professional_fee" value="py_professional_fee" />Professional Fee<br/>
				    <input type="checkbox" name="payrollModule" class="myCheckbox" id="py_phic_contribution" value="py_phic_contribution" />PHIC Contribution Table
				    <input type="checkbox" name="payrollModule" class="myCheckbox" id="py_tax_table" value="py_tax_table" />Tax Table
				    </div>
				    <div class="cb"></div>
				    <div class="moduleAccessHeader">Employee's Loan</div>
				    <div class="myCheckboxTxt">
				    <input type="checkbox" name="employeesLoanModule" class="myCheckbox" id="el_loan_payments" value="el_loan_payments" />Loan and Payments
				    </div>
				    <div class="cb"></div>
				    <div class="moduleAccessHeader">Payroll Reports</div>
				    <div class="myCheckboxTxt">
				    <input type="checkbox" name="payrollReportsModule" class="myCheckbox" id="pr_payroll_reports" value="pr_payroll_reports" />Payroll Reports
				    </div>
				    <div class="cb"></div>				    		    
			   <div class="cb" style="height: 10px;"></div>
			  <div style="margin-top: 30px;">
			  <input id="addEmployeeButton" style="padding: 0px 20px 0px 20px;" value="Save Module Access" border="0" onclick="javascript:saveModuleAccess();"> 
			  </div>
			  <div class="cb"></div>
			</form>
			</div>
		</div>		
	</div>
</div>
<div><c:import url="footer.jsp" /></div>
</body>
</html>