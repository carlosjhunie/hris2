<%@ page language="java" contentType="text/html; charset=iso-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Employee Loan Entry</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@include file="commonHeader.jsp" %>
<%@include file="commonJtables.jsp" %>
<link rel="stylesheet" type="text/css" href="css/styleTables.css" />
<link rel="stylesheet" type="text/css" href="css/style.css" />

<link rel="stylesheet" type="text/css" href="css/dai.css" />
<link href="js/validationEngine/validationEngine.jquery.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/validationEngine/jquery.validationEngine.js"></script>
<script type="text/javascript" src="js/validationEngine/jquery.validationEngine-en.js"></script>
<style>
.jtable-input-field-container {
    margin-bottom: 5px;
    display: block;
    float: none;
}
#jtable-create-form {
    display: block;
    width: 100%;
    -moz-column-gap:40px;
    /* Firefox */
    -webkit-column-gap:40px;
    /* Safari and Chrome */
    column-gap:40px;
    -moz-column-count:2;
    /* Firefox */
    -webkit-column-count:2;
    /* Safari and Chrome */
    column-count:2;
}
#jtable-edit-form {
    display: block;
    width: 100%;
    -moz-column-gap:40px;
    /* Firefox */
    -webkit-column-gap:40px;
    /* Safari and Chrome */
    column-gap:40px;
    -moz-column-count:2;
    /* Firefox */
    -webkit-column-count:2;
    /* Safari and Chrome */
    column-count:2;
}
</style>

<script type="text/javascript" src="js/common.js"></script>


<script type="text/javascript">

	function clickSearchResult(empid) {
		
		
		$('#empNo').html(empSearchMap[empid].empno);
		$('#fullname').html(empSearchMap[empid].lastname + ", " + empSearchMap[empid].firstname);
		
		
		$('#containerEmployeeLoanEntry').jtable();
		$('#containerEmployeeLoanEntry').jtable('destroy');
		
		
		
		  $('#containerEmployeeLoanEntry').jtable({
	            title: 'Loan Entry',
	            actions: {
	                listAction: '/hris/GetAllLoanEntryByEmpIdAction?empId='+empid,
	                createAction: '/hris/AddLoanEntryAction',
	                updateAction: '/hris/UpdateLoanEntryAction'
	            },
	            fields: {
	            	loanEntryId: {
	                    key: true,
	                    list: false
	                },
	                empId: {
	                	type:'hidden',
	                	defaultValue:empid
	                },
	                //CHILD TABLE DEFINITION FOR "PAYMENTS "
	                Payments: {
	                    title: '',
	                    width: '5%',
	                    sorting: false,
	                    edit: false,
	                    create: false,
	                    display: function (paymentData) {
	                        //Create an image that will be used to open child table
	                        var $img = $('<img src="/hris/images/list_metro.png" title="List of Payments" />');
	                        //Open child table when user clicks the image
	                        $img.click(function () {
	                            $('#containerEmployeeLoanEntry').jtable('openChildTable',
	                                    $img.closest('tr'),
	                                    {
	                                        title: 'Payment for referenceNo: ' + paymentData.record.referenceNo,
	                                        actions: {
	                                           	 listAction: '/hris/GetAllLoanPaymentsByLoanEntryId?loanEntryId='+paymentData.record.loanEntryId,
	                                             createAction: '/hris/AddLoanPaymentAction',
	                                             updateAction: '/hris/UpdateLoanTypeAction'
	                                        },
	                                        fields: {
	                                        		loanPaymentId: {
	                                        			key: true,
	                            	                    list: false
	                                            	},
	                                            	loanEntryId: {
	                                            		type:'hidden',
	                            	                	defaultValue:paymentData.record.loanEntryId
	                                            	},
	                                            	paymentDate: {
	                            	                    title: 'Payment Date',
	                            	                    type: 'date',
	                            	                    displayFormat: 'yy-mm-dd'
	                            	                },
	                            	                paidAmount:  {
	                            	                	title: 'Paid Amount'
	                            	                },
	                            	                remarks: {
	                            	                    title: 'Remarks',
	                            	                    type: 'textarea'
	                            	                },
	                            	                payrollPeriodId: {
	                            	                	type:'hidden',
	                            	                	defaultValue:0
	                            	                }
	                                            	
	                                            },
	                                            
	                                        }
	                                    , function (data) { //opened handler
	                                        data.childTable.jtable('load');
	                                    });
	                        });
	                        //Return image to show on the person row
	                        return $img;
	                    }
	                },
	                dateFile: {
	                    title: 'Date Filed',                
	                    //type: 'date',
	                    displayFormat: 'yy-mm-dd'
	                },
	                loanTypeId: {
	                    title: 'Loan Type',                
	                    options:  
		                   	function(data) {      
                
							return '/hris/GetAllLoanTypeAction?displayOption=true';
				                        
	                    }
	                },
	                referenceNo: {
	                    title: 'Reference No'
	                },
	                loanAmount: {
	                    title: 'Loan Amount'
	                },
	                monthlyAmortization: {
	                    title: 'Monthly Amor.'
	                },
	                startDateToPay: {
	                    title: 'Payment Start',
	                    //type: 'date',
	                    displayFormat: 'yy-mm-dd'
	                },
	                endDateToPay: {
	                    title: 'Payment End',
	                    //type: 'date',
	                    displayFormat: 'yy-mm-dd'
	                },   
	                remarks: {
	                    title: 'Remarks',
	                    type: 'textarea',
	                    list: false
	                },
	                PNNo: {
	                    title: 'PNNo',
	                    list: false
	                },
	                PNDate: {
	                    title: 'PN Date',
	                    list: false
	                },
	                deductionFlagActive: {
	                    title: 'Deduct Loan',
	                    list: false,
	                    options: {'N':'No','Y':'Yes'}
	                }
	                
	                //,
	                //periodFrom: {
	                //    title: 'Period From',
	                //    list: false
	                //},
	                //periodTo: {
	                //    title: 'Period To',
	                //    list: false
	                //},
	                
	            },
	            
	          //Initialize validation logic when a form is created
	          formCreated: function (event, data) {
	        	 
              	$(data.form).parent().parent().css("top","155");   				//to make the form popup when adding/updating visible
            	$(data.form).parent().parent().css("left","180");
	        	$(data.form).addClass("custom_horizontal_form_field");
	        	
	        	data.form.find('input[name="dateFile"]').addClass('validate[required]');
	        	data.form.find('input[name="referenceNo"]').addClass('validate[required]');
	        	data.form.find('input[name="loanAmount"]').addClass('validate[required]');
	        	data.form.find('input[name="monthlyAmortization"]').addClass('validate[required]');
	        	data.form.find('input[name="startDateToPay"]').addClass('validate[required]');
	        	data.form.find('input[name="endDateToPay"]').addClass('validate[required]');
	        	data.form.find('input[name="remarks"]').addClass('validate[required]');
	        	data.form.find('input[name="PNNo"]').addClass('validate[required]');
	        	data.form.find('input[name="PNDate"]').addClass('validate[required]');
	        	
	           if ((data.formType == 'edit')|| (data.formType == 'create')){
	        	   var $startDateToPay = data.form.find ('input[name="startDateToPay"]');     
	               var $endDateToPay = data.form.find ('input[name="endDateToPay"]');
	               
	               var $dateFile = data.form.find ('input[name="dateFile"]');
	               var $PNDate = data.form.find ('input[name="PNDate"]');
	               
	               
	               $dateFile.datepicker({
	            		changeMonth : true,
	            		changeYear : true,
	            		dateFormat : 'yy-mm-dd',
	            		yearRange : '1910:2100',
	            	    beforeShow: function(input, inst){	            	    	
	            	           $(".ui-datepicker").css('font-size', 11);
	            	    }
	            	});	
	               
	               $startDateToPay.datepicker({
	            		changeMonth : true,
	            		changeYear : true,
	            		dateFormat : 'yy-mm-dd',
	            		yearRange : '1910:2100',
	            	    beforeShow: function(input, inst){	            	    	
	            	           $(".ui-datepicker").css('font-size', 11);
	            	    }
	            	});	
	               
	               $endDateToPay.datepicker({
	            		changeMonth : true,
	            		changeYear : true,
	            		dateFormat : 'yy-mm-dd',
	            		yearRange : '1910:2100',
	            	    beforeShow: function(input, inst){	            	    	
	            	           $(".ui-datepicker").css('font-size', 11);
	            	    }
	            	});	
	                
	               
	               $PNDate.datepicker({
	            		changeMonth : true,
	            		changeYear : true,
	            		dateFormat : 'yy-mm-dd',
	            		yearRange : '1910:2100',
	            	    beforeShow: function(input, inst){	            	    	
	            	           $(".ui-datepicker").css('font-size', 11);
	            	    }
	            	});	
	        	   
	        	   
	            	
	            }
	           
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
	        $('#containerEmployeeLoanEntry').jtable('load');
	
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
	  <div id="containerEmployeeLoanEntry"  class="jTableContainerDaiLeft1020"></div>
	</div>	
</div>
<div style=""><c:import url="footer.jsp" /></div>
</body>
</body>
</html>