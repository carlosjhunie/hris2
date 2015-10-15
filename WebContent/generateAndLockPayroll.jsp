<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Generate and Lock Payroll</title>
<%@include file="commonHeader.jsp" %>
<%@include file="commonJtables.jsp" %>
<link rel="stylesheet" type="text/css" href="css/styleTables.css" />
<link rel="stylesheet" type="text/css" href="css/style.css" />
<link rel="stylesheet" type="text/css" href="css/dai.css" />

<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/moment.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	var oAjaxCall = $.ajax({
		type : "POST",
		url : "/hris/GetAllPayrollPeriodAction?includeLocked=true",
		cache : false,
		async : false,
		dataType : "json",
		success : function(data) {
			var divs = '';
			  divs +="<table id=\"box-table-a\" summary=\"Payroll Periods\" width=\"1050px;\" style=\"margin: 0px auto 0px auto	;\"     >";				    
			  divs +="<thead><tr>";
			  divs +="<th scope=\"col\">Pay Year</th>";
			  divs +="<th scope=\"col\">Pay Month</th>";
			  divs +="<th scope=\"col\">Payroll Type</th>";
			  divs +="<th scope=\"col\">Date From</th>";
			  divs +="<th scope=\"col\">Date To</th>";
			  divs +="<th scope=\"col\">Pay Date</th>";
			  divs +="<th scope=\"col\">Payroll Code</th>";
			  divs +="<th scope=\"col\">Goverment Deduct</th>";
			  divs +="<th scope=\"col\">Status</th>";			  
			  divs +="<th scope=\"col\">&nbsp;</th><th scope=\"col\">&nbsp;</th></tr></thead><tbody>";	
			  jQuery.each(data.Records, function(i, item) {				
			  		var payrollType = "";
			  		var status = "";
			  		var details = "No Details";
			  		
			  		if(item.payrollType == 'M'){
			  			payrollType = "Monthly"
			  		}
			  		
			  		if(item.payrollType == 'S'){
			  			payrollType = "Semi-Monthly"
			  		}
			  		
			  		if(item.payrollType == 'W'){
			  			payrollType = "Weekly"
			  		}
			  		
			  		if(item.payrollType == 'D'){
			  			payrollType = "Daily"
			  		}
			  		
			  		if(item.status == 'N'){
			  			status = "New"
			  		}
			  		
			  		if(item.status == 'G'){
			  			status = "Generated"
			  			details = "View Details"
			  		}
			  		
			  		if(item.status == 'L'){
			  			status = "Locked"
			  			details = "View Details"
			  		}
			  		
			  			  	  
				  	
				  	divs +="<tr>"
					divs +="<td>" + item.payYear + "</td>";
					divs +="<td>" + item.payMonth + "</td>";
					divs +="<td>" + payrollType + "</td>";
					divs +="<td>" + item.fromDate + "</td>";
					divs +="<td>" + item.toDate + "</td>";
					divs +="<td>" + item.payDate + "</td>";
					divs +="<td>" + item.payrollCode + "</td>";
					divs +="<td>" + item.deductGovtFlag + "</td>";
					divs +="<td>" + status + "</td>";
					
					if(item.status == 'N'){
			  			divs +="<td>"
						divs +="<a href=\"GeneratePayrollByPayrollPeriodAction?payrollPeriodId=";
						divs += item.payrollPeriodId;
						divs +="&payrollType=";	
						divs += item.payrollType
						divs +="\">";				  
						divs += "Generate";
						divs +="</a>"
						divs +="</td>"
						divs +="<td>" + details + "</td>";
			  		}
			  		
			  		if(item.status == 'G'){
			  			divs +="<td>"
						divs +="<a href=\"LockPayrollPeriodAction?payrollPeriodId=";
						divs += item.payrollPeriodId;
						divs +="\">";				  
						divs += "Lock";
						divs +="</a><br>"
						divs +="<a href=\"GeneratePayrollByPayrollPeriodAction?payrollPeriodId=";
						divs += item.payrollPeriodId;
						divs +="&payrollType=";	
						divs += item.payrollType
						divs +="\">";				  
						divs += "Re-Generate";
						divs +="</a>"
						divs +="</td>"
						
						divs +="<td>"
						divs +="<a href=\"ViewPayrollRegisterReport?payrollPeriodId=";
						divs += item.payrollPeriodId;
						divs +="\">";				  
						divs += details;
						divs +="</a>"
						divs +="</td>"
			  		}
			  		
			  		if(item.status == 'L'){
			  			divs +="<td>Locked</td>";
			  			divs +="<td>"
						divs +="<a href=\"ViewPayrollRegisterReport?payrollPeriodId=";
						divs += item.payrollPeriodId;
						divs +="\">";				  
						divs += details;
						divs +="</a>"
						divs +="</td>"
			  		}
					
					
				  	divs +="</tr>"  				  
				
		        });
			  	
			  	divs +="</tbody></table>";
				
				$('#payrollPeriodContainer').html(divs);
			
			
			

		},
		error : function(data) {
			alert('error: ' + data);
		}

	});
	
	
	//for generate payroll
	$("#generate-button").click(function() {		
		alert($('#payrollPeriod').val());
		
		
		var oAjaxCall = $.ajax({
			type : "POST",
			url : "/hris/GeneratePayrollByPayrollPeriodAction?includeLocked=false",
			cache : false,
			async : false,
			dataType : "json",
			success : function(data) {
				var items = '<option value=""></option>';
				if (data.Records.length) {
					$.each(data.Records, function(i, item) {
						items += "<option value='" + item.payrollPeriodId + "-" + item.payrollType + "'>"
								+ item.fromDate + " - " + item.toDate + " (" + item.payrollType +")" + ": " 
								+ item.payrollCode + "</option>";
					});
					$('#payrollPeriod').html(items);
				}
				;

			},
			error : function(data) {
				alert('error: ' + data);
			}

		});
		
		
		
	});
	
	
	
	
});


</script>
</head>
<body>
<div><c:import url="header.jsp" /></div>
<div id="content">	
	    <div id="payrollPeriodContainer"></div>			
</div>	

<div><c:import url="footer.jsp" /></div>
</body>
</html>