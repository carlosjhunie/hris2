<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Payroll Period</title>
<link rel="stylesheet" type="text/css" href="css/styleTables.css" />
<link rel="stylesheet" type="text/css" href="css/style.css" />
<link rel="stylesheet" type="text/css" href="css/dai.css" />
<link rel="stylesheet" href="css/datePickerStyle.css">

<script type="text/javascript" src="js/common.js"></script>
<!-- Tabs -->
<link rel="stylesheet" type="text/css" href="css/jquery-ui-1.9.2.custom.css" />
<script type="text/javascript" src="js/jquery-ui-1.10.0.min.js"></script>
<script src="js/jquery-1.10.2.js"></script>
<script src="js/jquery-ui-1.11.4/jquery-ui.js"></script>

<script>
  $(function() {
    $(".useDPicker").datepicker({
      changeMonth: true,
      changeYear: true,
      dateFormat: 'yy-mm-dd',
	  yearRange: '1910:2100'
    });
    
 
  });
</script>
</head>
<body>

    <form id="addPayrollPeriodForm" name="addPayrollPeriodForm" method="POST" action="AddPayrollPeriodAction">
		<table style="width: 638px; height: 102px; "></table>
		<div>
		<input id="payrollPeriodId" name="payrollPeriodId" value="" type="hidden">
		Pay Year: <input id="payYear" name="payYear"><br>
		Pay Month: <select id="payMonth" name="payMonth">
		    <option value="1">Jan</option>
		    <option value="2">Feb</option>
		    <option value="3">Mar</option>
		    <option value="4">Apr</option>
		    <option value="5">May</option>
		    <option value="6">Jun</option>
		    <option value="7">Jul</option>
		    <option value="8">Aug</option>
		    <option value="9">Sep</option>
		    <option value="10">Oct</option>
		    <option value="11">Nov</option>
		    <option value="12">Dec</option>
		</select><br>
		Payroll Type: <select id="payrollType" name="payrollType">
		    <option value="weekly">Weekly</option>
		    <option value="biweekly">BiWeekly</option>
		    <option value="monthly">Monthly</option>
		</select><br>
		Period of service<br>
		From: <input id="fromDate" name="fromDate" class="useDPicker"> <br>
		To:<input id="toDate" name="toDate" class="useDPicker">
		Pay Date: <input id="payDate" name="payDate" class="useDPicker"><br>
		Payroll Code: <input id="payrollCode" name="payrollCode">
		Working Days: <input id="numWorkingDays" name="numWorkingDays"><br>
		Pay Period: <select id="payPeriod" name="payPeriod">
		    <option value="">---</option>
		    <option value="13thMonth">13th month</option>
		</select><br>
		<input id="empId" name="empId" value="1" type="hidden" />
		</div>
		<div>
			<input type="submit" value="Add">
		</div>

    </form>
</body>
</html>