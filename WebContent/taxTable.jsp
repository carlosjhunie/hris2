<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Tax Table</title>
<%@include file="commonHeader.jsp" %>
<%@include file="commonJtables.jsp" %>
<link rel="stylesheet" type="text/css" href="css/dai.css" />

<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/moment.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$('#containerTaxTable').jtable({
		title: 'Tax Table',
		actions: {
			listAction: '/hris/GetTaxTableAction'			
		},
		fields:{
			taxTableId: {
			    key: true,
			    list: true
			},
			taxStatus: {
				title: 'Status',
			    list: true
			},
			salaryBase: {
				title: 'Salary Base',
			    list: true
			},
			taxExemption: {
				title: 'Exemption',
			    list: true
			},
			taxAmount: {
				title: 'Tax Amount',
				list: true
			},
			taxRate: {
				title: 'Tax Rate',
			    list: true
			},
			payrollType: {
				title: 'Payroll Type',
				list: true,
				options: {'M':'Monthly','S':'Semi-Monthly','W':'Weekly','D':'Daily'}
			}
		}
    });
    $('#containerTaxTable').jtable('load');
});
</script>
</head>
<body>
<div><c:import url="header.jsp" /></div>
<div id="content">
<div class="cb" style="height: 20px;"></div>
	<div id="containerTaxTable" class="jTableContainerDaiExtended"></div>		
</div>	
<div><c:import url="footer.jsp" /></div>
</body>
</html>