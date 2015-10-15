<%@ page language="java" contentType="text/html; charset=iso-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Employee Type</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@include file="commonHeader.jsp" %>
<%@include file="commonJtables.jsp" %>
<link rel="stylesheet" type="text/css" href="css/dai.css" />
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript">

    $(document).ready(function () {
        $('#containerEmployeeType').jtable({
            title: 'List of EmployeeType',
            actions: {
                listAction: '/hris/GetAllEmployeeTypeAction',
                createAction:'/hris/AddEmployeeTypeAction',
                updateAction: '/hris/UpdateEmployeeTypeAction'
                
            },
            fields: {
            	employeeTypeId: {
                    key: true,
                    list: false
                },
                employeeTypeName: {
                    title: 'EmployeeType',
                    edit : true 
                }
                
                
            }
        });
        $('#containerEmployeeType').jtable('load');
    });
</script>

</head>
<body>
<div><c:import url="header.jsp" /></div>
<div id="content">
<div class="cb" style="height: 20px;"></div>
	<div id="containerEmployeeType"  class="jTableContainerDai"></div>
</div>	
<div><c:import url="footer.jsp" /></div>
</body>
</html>