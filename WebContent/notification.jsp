<%@ page language="java" contentType="text/html; charset=iso-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Notification</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@include file="commonHeader.jsp" %>
<%@include file="commonJtables.jsp" %>
<link rel="stylesheet" type="text/css" href="css/styleTables.css" />
<link rel="stylesheet" type="text/css" href="css/style.css" />

<link rel="stylesheet" type="text/css" href="css/dai.css" />
<link href="js/validationEngine/validationEngine.jquery.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/validationEngine/jquery.validationEngine.js"></script>
<script type="text/javascript" src="js/validationEngine/jquery.validationEngine-en.js"></script>

<script type="text/javascript" src="js/common.js"></script>


<script type="text/javascript">

	function clickSearchResult(empid) {
		
		
		$('#empNo').html(empSearchMap[empid].empno);
		$('#fullname').html(empSearchMap[empid].lastname + ", " + empSearchMap[empid].firstname);
		
		$('#containerNotification').jtable();
		$('#containerNotification').jtable('destroy');
		
			  $('#containerNotification').jtable({
	            title: 'Memo',
	            actions: {
	                listAction: '/hris/GetEmpNotificationByToRecipientAction?toRecipientEmpId='+empid,
	                createAction: '/hris/AddNotificationAction',
	                updateAction: '/hris/UpdateNotificationAction'
	            },
	            formCreated : function(event, data){
	            	data.form.find('input[name="filedDate"]').addClass('validate[required]');
	            	data.form.find('input[name="subject"]').addClass('validate[required]');
	            	data.form.find('textarea[name="message"]').addClass('validate[required]');
	            	data.form.find('input[name="remarks"]').addClass('validate[required]');
	            	data.form.find('input[name="fromSender"]').addClass('validate[required]');
	            	
	            	$(data.form).parent().parent().css("top",155);   				//to make the form popup when adding/updating visible
	            	$(data.form).parent().parent().css("left",155);  
	            	$(data.form).addClass("custom_horizontal_form_field");
	            	data.form.find('input[name="jobTitle"]').addClass('validate[required]');
	            	
	            	
	            	if (data.formType == 'edit') {
	            		data.form.children(':lt(6)').wrapAll('<div class="col1"/>');	//to split into 2 columns so that it wouldnt be one long form
	            	}
	            	else {
	            		data.form.children(':lt(4)').wrapAll('<div class="col1"/>');    //to split into 2 columns so that it wouldnt be one long form
	            	}
	            	data.form.children(':gt(0)').wrapAll('<div class="col2"/>');
	            	
	            	var $mfd = data.form.find ('input[name="filedDate"]');   
	            	
                    $mfd.datepicker({
                		changeMonth : true,
                		changeYear : true,
                		dateFormat : 'yy-mm-dd',
                		yearRange : '1910:2100',
                	    beforeShow: function(input, inst){	            	    	
             	           $(".ui-datepicker").css('font-size', 11);
             	    }
                	});	

	            	data.form.validationEngine();

	            },
	            fields: {
	            	empNotificationId: {
	                    key: true,
	                    list: false
	                },
	                toRecipientEmpId: {
	                	type:'hidden',
	                	defaultValue:empid
	                },	               
	                filedDate: {
	                    title: 'Date File',                
	                    //type: 'date',
	                    displayFormat: 'yy-mm-dd'
	                },	                
	                ccRecipient: {
	                    title: 'CC'
	                },
	                fromSender: {
	                    title: 'From'
	                },
	                subject: {
	                    title: 'Subject'
	                },
	                message: {
	                    title: 'Message',
	                    type: 'textarea'
	                },

	                remarks: {
	                    title: 'Remarks'
	                }
	            },
	          //Initialize validation logic when a form is created
	          //  formCreated: function (event, data) {

	            //    if (data.formType == "create")
	             //   {

	                   

	                	//$( '#Edit-dateFile' ).datepicker();
	                	//$( '#Edit-startDateToPay' ).datepicker();

	               // }
	           // }
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
	        $('#containerNotification').jtable('load');
	
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
	
	<!-- Right Side of Dashboard -->
	<div  id="dashBoardRightPannel2" width="100%">	
		<div class="dataEntryText">Employee No:</div>
	    <div class="dataEntryTextBlue" id="empNo"></div>	    
	    <div class="dataEntryText">Employee Name:</div>			    
	    <div class="dataEntryTextBlue" id="fullname"></div>	    
	    <div class="cb"></div>	    				
	  <div id="containerNotification"  class="jTableContainerDaiLeftLong"></div>
	</div>	
</div>
<div style=""><c:import url="footer.jsp" /></div>
</body>
</body>
</html>