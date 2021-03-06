<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Deductions List</title>
<%@include file="commonHeader.jsp" %>
<%@include file="commonJtables.jsp" %>
<link rel="stylesheet" type="text/css" href="css/dai.css" />
<link href="js/validationEngine/validationEngine.jquery.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/validationEngine/jquery.validationEngine.js"></script>
<script type="text/javascript" src="js/validationEngine/jquery.validationEngine-en.js"></script>

<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/moment.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$('#containerDeductionEntry').jtable({
			title: 'Deductions',
			actions: {
				listAction: '/hris/GetDeductionAction',
				createAction: '/hris/SaveDeductionAction',
				updateAction: '/hris/SaveDeductionAction'
			},
			fields: {
				deductionId: {
					key: true,
					list: false
				},
				deductionName: {
					title: 'Name',
				},
				amount: {
					title: 'Amount'
				},
				employeeType: {
					title: 'Employee Type',
					options: ['Probationary','Regular','Contractual']
				},
				payrollType: {
					title: 'Payroll Type',
					options: ['Semi-monthly','Monthly','Weekly']
				},
				isForSecondHalf: {
					title: 'For 2nd Half',
					options: {'N':'No','Y':'Yes'}
				}
			},
			formCreated : function(event, data){
            	$(data.form).parent().parent().css('top',180);   				//to make the form popup when adding/updating visible
				$(data.form).parent().parent().css('left', 160);
				$(data.form).addClass("custom_horizontal_form_field");	
            	if (data.formType == 'edit') {
            		data.form.children(':lt(6)').wrapAll('<div class="col1"/>');	//to split into 2 columns so that it wouldnt be one long form
            	}
            	else {
            		data.form.children(':lt(4)').wrapAll('<div class="col1"/>');    //to split into 2 columns so that it wouldnt be one long form
            	}
            	data.form.children(':gt(0)').wrapAll('<div class="col2nw"/>');
            	
	        	data.form.find('input[name="deductionName"]').addClass('validate[required]');
	        	data.form.find('input[name="amount"]').addClass('validate[required]');
            	data.form.validationEngine();
            },
            //Validate form when it is being submitted
            formSubmitting: function (event, data) {
                return data.form.validationEngine('validate');
            },
            //Dispose validation logic when form is closed
            formClosed: function (event, data) {
                data.form.validationEngine('hide');
                data.form.validationEngine('detach');
            }
		});
		$('#containerDeductionEntry').jtable('load');
	});
</script>
</head>
<body>
<div><c:import url="header.jsp" /></div>
<div id="content">
<div class="cb" style="height: 20px;"></div>
	<div id="containerDeductionEntry" class="jTableContainerDaiExtended"></div>		
</div>	
<div><c:import url="footer.jsp" /></div>
</body>
</html>