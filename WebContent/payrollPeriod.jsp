<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Payroll Period</title>
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
	$('#containerPayrollPeriod').jtable({
		title: 'Payroll Period',
		actions: {
			createAction: '/hris/SavePayrollPeriodAction',
			updateAction: '/hris/SavePayrollPeriodAction',
			listAction: '/hris/GetPayrollPeriodAction'
		},
		fields: {
			payrollPeriodId: {
				key: true,
				list: false
			},
			payYear: {
				title: 'Pay Year',
				options: ['2015','2016','2017','2018','2019','2020'],
				edit : true 
			},
			payMonth: {
				title: 'Pay Month',
				options: {'1':'January','2':'February','3':'March','4':'April','5':'May','6':'June',
					'7':'July','8':'August','9':'September','10':'October','11':'November','12':'December'},
				edit : true 
			},
			payrollType: {
				title: 'Payroll Type',
				options: [ { Value: 'S', DisplayText: 'Semi-Monthly' }, { Value: 'M', DisplayText: 'Monthly' }],
				edit : true 
			},
			fromDate: {
				title: 'From',
				edit : true 
			},
			toDate: {
				title: 'To',
				edit : true 
			},
			payDate: {
			    title: 'Pay Date',
			    edit : true 
			},
			payrollCode: {
				title: 'Payroll Code',
				edit : true 
			},
			numWorkDays: {
				title: '# of Work Days',
				edit : true 
			},
			payPeriod: {
				title: 'Pay Period',
				edit : false,	 
				list: false,
				create: false
				//options: ['13th Month','']
			},
			
			lockedAt: {
				title: 'Locked Date',
				edit : false,
				create: false,
				list: true
                //display:
                //	function (data) {
                		//alert (data.record.numWorkDays);
                //			if (data.record.lockedAt != undefined){      //need to have a condition for undefined since moment will display default date.
                //    			return moment(data.record.lockedAt).format('YYYY-MM-DD');
                //			}
                //			return 'N/A';
               	//	 }

			}
		},
		formCreated : function(event, data){
			$(data.form).parent().parent().css('top', 155);  //to make the form popup when adding/updating visible
        	$(data.form).parent().parent().css('left',170);
			$(data.form).addClass("custom_horizontal_form_field");	
        	if (data.formType == 'edit') {
        		data.form.children(':lt(6)').wrapAll('<div class="col1"/>');	//to split into 2 columns so that it wouldnt be one long form
        	}
        	else {
        		data.form.children(':lt(5)').wrapAll('<div class="col1"/>');    //to split into 2 columns so that it wouldnt be one long form
        	}
        	data.form.children(':gt(0)').wrapAll('<div class="col2nw"/>');
        	
        	//for dates: we need to find the from/to manually
            var $dtfrom = data.form.find ('input[name="fromDate"]');     
            var $dtTo = data.form.find ('input[name="toDate"]');
            var $dtPay = data.form.find ('input[name=payDate]');
            
            //manually activate the datepicker, and supply the params from useDPicker (you need to do this so that when date is selected, the date format is followed.)
            //and you need to add val in order to display the value saved in the DB in the proper format, if not, it will become Mon dd, yyyy which will fail.
			if (data.formType == 'edit'){
                $dtfrom.datepicker({
            		changeMonth : true,
            		changeYear : true,
            		dateFormat : 'yy-mm-dd',
            		yearRange : '1910:2100',
            	    beforeShow: function(input, inst){	            	    	
            	           $(".ui-datepicker").css('font-size', 11);
            	    }
            	}).val(moment(data.record.fromDate).format('YYYY-MM-DD'));		

                $dtTo.datepicker({
            		changeMonth : true,
            		changeYear : true,
            		dateFormat : 'yy-mm-dd',
            		yearRange : '1910:2100',
            	    beforeShow: function(input, inst){	            	    	
            	           $(".ui-datepicker").css('font-size', 11);
            	    }
            	}).val(moment(data.record.toDate).format('YYYY-MM-DD'));
                
                $dtPay.datepicker({
            		changeMonth : true,
            		changeYear : true,
            		dateFormat : 'yy-mm-dd',
            		yearRange : '1910:2100',
            	    beforeShow: function(input, inst){	            	    	
            	           $(".ui-datepicker").css('font-size', 11);
            	    }
            	}).val(moment(data.record.toDate).format('YYYY-MM-DD'));
			}
            
			//call these datepickers below when doing create entry, since we do not need to display any dateFrom or dateTo
            $dtfrom.datepicker({
        		changeMonth : true,
        		changeYear : true,
        		dateFormat : 'yy-mm-dd',
        		yearRange : '1910:2100',
        	    beforeShow: function(input, inst){	            	    	
     	           $(".ui-datepicker").css('font-size', 11);
     	    }
        	});		

            $dtTo.datepicker({
        		changeMonth : true,
        		changeYear : true,
        		dateFormat : 'yy-mm-dd',
        		yearRange : '1910:2100',
        	    beforeShow: function(input, inst){	            	    	
     	           $(".ui-datepicker").css('font-size', 11);
     	    }
        	});				
            
            $dtPay.datepicker({
        		changeMonth : true,
        		changeYear : true,
        		dateFormat : 'yy-mm-dd',
        		yearRange : '1910:2100',
        	    beforeShow: function(input, inst){	            	    	
     	           $(".ui-datepicker").css('font-size', 11);
     	    }
        	});	
            
        	data.form.find('input[name="fromDate"]').addClass('validate[required]');
        	data.form.find('input[name="toDate"]').addClass('validate[required]');
        	data.form.find('input[name="paydate"]').addClass('validate[required]');
        	data.form.find('input[name="payrollCode"]').addClass('validate[required]');
        	data.form.find('input[name="numWorkDays"]').addClass('validate[required]');
        	data.form.find('input[name="payPeriod"]').addClass('validate[required]');
        	data.form.find('input[name="payDate"]').addClass('validate[required]');
        	
        	
            
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
	$('#containerPayrollPeriod').jtable('load');
});
</script>
</head>
<body>
<div><c:import url="header.jsp" /></div>
<div id="content">
<div class="cb" style="height: 20px;"></div>
	<div id="containerPayrollPeriod" class="jTableContainerDaiExtendedLong"></div>		
</div>	
<div><c:import url="footer.jsp" /></div>
</body>
</html>