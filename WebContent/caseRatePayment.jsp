<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Case Rate Payment</title>
<%@include file="commonHeader.jsp" %>
<%@include file="commonJtables.jsp" %>
<link rel="stylesheet" type="text/css" href="css/styleTables.css" />
<link rel="stylesheet" type="text/css" href="css/style.css" />
<link rel="stylesheet" type="text/css" href="css/dai.css" />
<link href="js/validationEngine/validationEngine.jquery.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/validationEngine/jquery.validationEngine.js"></script>
<script type="text/javascript" src="js/validationEngine/jquery.validationEngine-en.js"></script>

<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/moment.min.js"></script>
<script type="text/javascript">

    function clickSearchResult(empid) {
    	$('#empNo').html(empSearchMap[empid].empno);
		$('#fullname').html(empSearchMap[empid].lastname + ", " + empSearchMap[empid].firstname);
		
		$('#containerCaseRate').jtable();
		$('#containerCaseRate').jtable('destroy');
		$('#containerCaseRate').jtable({
			title: 'Case Rate',
			actions: {
				listAction: '/hris/GetCaseRatePaymentAction?empId='+empid,
				createAction: '/hris/SaveCaseRatePaymentAction',
				updateAction: '/hris/SaveCaseRatePaymentAction'
			},
			fields: {
				caseRatePaymentId: {
					key: true,
				},
				empId: {
					type: 'hidden'
				},
				grossAmount: {
					title: 'Gross Amount'
				},
				withHoldingTax: {
					title: 'Withholding Tax'
				},
				finalTax: {
					title: 'Final Tax'
				},
				netAmountDue: {
					title: 'Net Amount Due'
				},
				year: {
					title: 'Year',
					options: ['2010','2011','2012','2013','2014','2015', '2016', '2017', '2018', '2019', '2020', '2021'] //make this a function
				},
				month: {
					title: 'Month',
					options: {'1':'January','2':'February','3':'March','4':'April','5':'May','6':'June',
						'7':'July','8':'August','9':'September','10':'October','11':'November','12':'December'},
				},
				batch: {
					title: 'Batch',
					list: false
				},
				patientId: {
					title: 'Patient ID',
					list: false
				},
				patientName: {
					title: 'Patient Name'
				},
				remarks: {
					title: 'Remarks',
					type: 'textarea',
					list: false
				},
				officialReceiptDate: {
					title: 'Date',
					list:false
				}
			},
			formCreated : function(event, data){
            	$('#Edit-empId').val(empid);
            	$(data.form).parent().parent().css('top', 150); //to make the form popup when adding/updating visible
				$(data.form).parent().parent().css('left', 160);
				$(data.form).addClass("custom_horizontal_form_field");	
            	if (data.formType == 'edit') {
            		data.form.children(':lt(6)').wrapAll('<div class="col1"/>'); //to split into 2 columns so that it wouldnt be one long form
            	}
            	else {
            		data.form.children(':lt(6)').wrapAll('<div class="col1"/>'); //to split into 2 columns so that it wouldnt be one long form
            	}
            	data.form.children(':gt(0)').wrapAll('<div class="col2"/>');
            	
            	var $dtOR = data.form.find ('input[name="officialReceiptDate"]');     
                //manually activate the datepicker, and supply the params from useDPicker (you need to do this so that when date is selected, the date format is followed.)
                //and you need to add val in order to display the value saved in the DB in the proper format, if not, it will become Mon dd, yyyy which will fail.
    			if (data.formType == 'edit'){
                    $dtOR.datepicker({
                		changeMonth : true,
                		changeYear : true,
                		dateFormat : 'yy-mm-dd',
                		yearRange : '1910:2100',
                	    beforeShow: function(input, inst){	            	    	
                	           $(".ui-datepicker").css('font-size', 11);
                	    }
                	}).val(moment(data.record.fromDate).format('YYYY-MM-DD'));		
    			}
    			$dtOR.datepicker({
            		changeMonth : true,
            		changeYear : true,
            		dateFormat : 'yy-mm-dd',
            		yearRange : '1910:2100',
            	    beforeShow: function(input, inst){	            	    	
         	           $(".ui-datepicker").css('font-size', 11);
         	        }
            	});
            	
	        	data.form.find('input[name="grossAmount"]').addClass('validate[required]');
	        	data.form.find('input[name="withHoldingTax"]').addClass('validate[required]');
	        	data.form.find('input[name="finalTax"]').addClass('validate[required]');
	        	data.form.find('input[name="netAmountDue"]').addClass('validate[required]');
	        	data.form.find('input[name="batch"]').addClass('validate[required]');
	        	data.form.find('input[name="patientId"]').addClass('validate[required]');
	        	data.form.find('input[name="patientName"]').addClass('validate[required]');
	        	data.form.find('textarea[name="remarks"]').addClass('validate[required]');
	        	
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
		$('#containerCaseRate').jtable('load');
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
	<div id="dashBoardRightPannel2" width="100%">	
		<div class="dataEntryText">Employee No:</div>
	    <div class="dataEntryTextBlue" id="empNo"></div>	    
	    <div class="dataEntryText">Employee Name:</div>			    
	    <div class="dataEntryTextBlue" id="fullname"></div>	    
	    <div class="cb"></div>	    				
	  
	    <!--div class="cb" style="height: 20px;"></div-->
		<div id="containerCaseRate" class="jTableContainerDaiLeft1020"></div>		
	</div>
</div>
<div><c:import url="footer.jsp" /></div>
</body>
</html>