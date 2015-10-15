<%@ page language="java" contentType="text/html; charset=iso-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Shifting Schedule</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@include file="commonHeader.jsp" %>
<%@include file="commonJtables.jsp" %>
<link rel="stylesheet" type="text/css" href="css/dai.css" />
<link href="js/validationEngine/validationEngine.jquery.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/validationEngine/jquery.validationEngine.js"></script>
<script type="text/javascript" src="js/validationEngine/jquery.validationEngine-en.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<link rel="stylesheet" type="text/css" href="css/jquery.timepicker.css" />
<script type="text/javascript" src="js/jquery.timepicker.js"></script>

<script type="text/javascript">

    $(document).ready(function () {
        $('#containerShiftingSchedule').jtable({
            title: 'Shifting Schedule',
            actions: {
                listAction: '/hris/GetAllShiftingSchedule',
                createAction:'/hris/AddShifitingSchedule',
                updateAction:'/hris/UpdateShifitingSchedule'
                
                
            },
            formCreated : function(event, data){
            	$(data.form).parent().parent().css("top","155"); 
            	data.form.children(':lt(3)').wrapAll('<div class="col1"/>');
    			data.form.children(':gt(0)').wrapAll('<div class="col2"/>');
    			
            	//to make the form popup when adding/updating visible
            	  var timeIn = data.form.find ('input[name="timeIn"]');
            	  timeIn.timepicker({ 'timeFormat': 'H:i' });
            	  var timeOut = data.form.find ('input[name="timeOut"]');
            	  timeOut.timepicker({ 'timeFormat': 'H:i' });
            	  
  	        	data.form.find('input[name="timeIn"]').addClass('validate[required]');
	        	data.form.find('input[name="timeOut"]').addClass('validate[required]');
	        	data.form.find('input[name="description"]').addClass('validate[required]');
	        	
	        	data.form.validationEngine();
            },
            fields: {
            	shiftingScheduleId: {
                    key: true,
                    list: false
                },
                shiftType: {
                    title: 'Shift Type',
                    edit : true ,
                    options:  [{ Value: 'F', DisplayText: 'Fixed' }, { Value: 'G', DisplayText: 'Gliding' }]
                },
                timeIn: {
                    title: 'Time In',
                    edit : true 
                },
                timeOut: {
                    title: 'Time Out',
                    edit : true 
                },
                description: {
                    title: 'Description',
                    edit : true 
                },
                noOfHours: {
                    title: '# of Hours',
                    edit : true 
                }
                
                
                
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
        $('#containerShiftingSchedule').jtable('load');
    });
</script>

</head>
<body>
<div><c:import url="header.jsp" /></div>
<div id="content">
<div class="cb" style="height: 20px;"></div>
	<div id="containerShiftingSchedule"  class="jTableContainerDaiExtended"></div>
</div>	
<div><c:import url="footer.jsp" /></div>
</body>
</html>