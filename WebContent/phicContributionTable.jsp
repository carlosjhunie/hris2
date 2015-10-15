<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>PHIC Contribution Table</title>
<%@include file="commonHeader.jsp" %>
<%@include file="commonJtables.jsp" %>
<link rel="stylesheet" type="text/css" href="css/dai.css" />

<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/moment.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$('#containerPhicContributionTable').jtable({
		title: 'PHIC Contribution Table',
		actions: {
			listAction: '/hris/GetPhicContributionTableAction',
			//createAction: '/hris/SavePhicContributionTableAction',
			//updateAction: '/hris/SavePhicContributionTableAction'
		},
		fields:{
			phicContributionTableId: {
			    key: true,
			    list: true
			},
			salaryBase: {
				title: 'Salary Base',
			    list: true
			},
			employeeShare: {
				title: 'Employee Share',
			    list: true
			},
			employerShare: {
				title: 'Employer Share',
			    list: true
			},
			totalMonthlyPremium: {
				title: 'Total Monthly Premium',
				list: true
			}
		}
    });
    $('#containerPhicContributionTable').jtable('load');
});
</script>
</head>
<body>
<div><c:import url="header.jsp" /></div>
<div id="content">
<div class="cb" style="height: 20px;"></div>
	<div id="containerPhicContributionTable" class="jTableContainerDaiExtended"></div>		
</div>	
<div><c:import url="footer.jsp" /></div>
</body>
</html>