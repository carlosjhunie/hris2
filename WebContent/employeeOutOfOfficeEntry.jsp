<%@ page language="java" contentType="text/html; charset=iso-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Employee Out of Office Entry</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@include file="commonHeader.jsp" %>
<%@include file="commonJtables.jsp" %>
<link rel="stylesheet" type="text/css" href="css/dai.css" />
<link href="js/validationEngine/validationEngine.jquery.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/validationEngine/jquery.validationEngine.js"></script>
<script type="text/javascript" src="js/validationEngine/jquery.validationEngine-en.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/moment.min.js"></script>
<script type="text/javascript">

    $(document).ready(function () {
    	var empId = '${sessionScope.employeeLoggedIn.empId}'; //this is the employeeid filing the leave
    	
        $('#containerEmployeeOutOfOfficeEntry').jtable({
            title: 'My Out of Office Entries',
            actions: {
                listAction:		'/hris/GetAllOutOfOfficeAction?empId='+empId,
                createAction:	'/hris/SaveOutOfOfficeAction?empId='+empId,
                updateAction:	'/hris/UpdateOutOfOfficeAction'
                
            },
            formCreated : function(event, data){            	
            	$(data.form).parent().parent().css("top","155");   				//to make the form popup when adding/updating visible            	
            	if (data.formType == 'edit') {
            		data.form.children(':lt(5)').wrapAll('<div class="col1"/>');	//to split into 2 columns so that it wouldnt be one long form
            	}
            	else {
            		data.form.children(':lt(3)').wrapAll('<div class="col1"/>');    //to split into 2 columns so that it wouldnt be one long form
            	}
            	data.form.children(':gt(0)').wrapAll('<div class="col2"/>');
            	
            	//for dates: we need to find the from/to manually
                var $dtfrom = data.form.find ('input[name="dateFrom"]');     
                var $dtTo = data.form.find ('input[name="dateTo"]');
                
            	data.form.find('input[name="dateFrom"]').addClass('validate[required]');
            	data.form.find('input[name="dateTo"]').addClass('validate[required]');
            	
            	data.form.find('input[name="titleActivity"]').addClass('validate[required]');
            	data.form.find('textarea[name="remarks"]').addClass('validate[required]');
            	data.form.find('input[name="provider"]').addClass('validate[required]');

                
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
	            	}).val(moment(data.record.dateFrom).format('YYYY-MM-DD'));		
					// 

	                $dtTo.datepicker({	                	
	            		changeMonth : true,
	            		changeYear : true,
	            		dateFormat : 'yy-mm-dd',
	            		yearRange : '1910:2100',
	            	    beforeShow: function(input, inst){	            	    	
	            	           $(".ui-datepicker").css('font-size', 11);
	            	    }
	            	}).val(moment(data.record.dateTo).format('YYYY-MM-DD'));
					
					//need to add the 2 below for the hidden attibutes so we can pass back to the servlet the values during update
					var $dtCreatedDate = data.form.find ('input[name="createdDate"]');
					var $dtDateFiled = data.form.find ('input[name="dateFiled"]');    
	                $dtCreatedDate.val(moment(data.record.createdDate).format('YYYY-MM-DD'));	                
	                $dtDateFiled.val(moment(data.record.dateFiled).format('YYYY-MM-DD'));
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

            	data.form.validationEngine();
            },
            fields: {
            	empOOOId: {
                    key: true,
                    list: false
                },
                
            	empId: {
                    key: true,
                    list: false
                },
                
         
                
                dateFrom: {
                    title: 'From',
                    //type: 'date',  //comment-out the type date
                    edit: true,
                    create: true,
                    display:
                      function (data) {
                    	if (data.record.dateTo != undefined){				//need to have a condition for undefined since moment will display default date.
                          return moment(data.record.dateFrom).format('YYYY-MM-DD');
                    	}
                    	return 'N/A';
                      }
                },
                
                dateTo: {
                    title: 'To',
                    //type: 'date',   //comment-out the type date
                    edit: true,
                    create:true,
                    display:
                    	function (data) {
                    			if (data.record.dateTo != undefined){      //need to have a condition for undefined since moment will display default date.
                        			return moment(data.record.dateTo).format('YYYY-MM-DD');
                    			}
                    			return 'N/A';
                   		 }
                },
                
                
                titleActivity: {
                    title: 'Title Activity',
                    //width: '30%',
                    create: true,
                    edit : true 
                },    
                
                remarks: {
                    title: 'Remarks',
                    type: 'textarea',
                    //width: '30%',
                    create: true,
                    edit : true 
                },
                
                provider: {
                    title: 'Provider',
                    //width: '30%',
                    create: true,
                    edit : true 
                },   
                
                status: {
                    title: 'Status',
                    create: false,
                    edit : false,
                    list: true,
                    options: [{ Value: '0', DisplayText: 'FOR APPROVAL' }, { Value: '1', DisplayText: 'NOT APPROVED' }, { Value: '2', DisplayText: 'APPROVED' }]
                    
                },    
            
                approvedBy: {
                    title: 'Supervisor',
                    edit: false,
                    list: true,
                    options:  
	                   	function(data) {       //Readme in GetLeaveTypeAction
                    	//alert (data.record.approvedBy);
			                        if (data.source == 'list') {
			                            return '/hris/GetAllEmployeeAction?displayOption=true';
			                        }
			                        if (data.source == 'create') {
			                            return '/hris/GetAllEmployeeAction?displayOption=true';
			                        }
			                        if (data.source == 'edit') {
			                        return '/hris/GetEmployeeAction?displayOption=true&empId='+data.record.approvedBy;
			                    	}
                    },                    	
                    create:false                    
                }, secondaryApprover: {
                    title: 'Approver 2',
                    edit: false,
                    list: true,
                    options:  
	                   	function(data) {       //Readme in GetLeaveTypeAction
                    	//alert (data.record.approvedBy);
			                        if (data.source == 'list') {
			                            return '/hris/GetAllEmployeeAction?displayOption=true';
			                        }
			                        if (data.source == 'create') {
			                            return '/hris/GetAllEmployeeAction?displayOption=true';
			                        }
			                        if (data.source == 'edit') {
			                        return '/hris/GetEmployeeAction?displayOption=true&empId='+data.record.secondaryApprover;
			                    	}
                    },                    	
                    create:false
                    
                    
                },createdBy: {
                    key: true,
                    list: false 
                
                //need to pass for update and for beanutils
                },createdDate: {
                	type:'hidden',
                    list: false
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
        $('#containerEmployeeOutOfOfficeEntry').jtable('load');
        
    });

</script>

</head>
<body>
<div><c:import url="header.jsp" /></div>
<div id="content">
<div class="cb" style="height: 20px;"></div>
	<div id="containerEmployeeOutOfOfficeEntry" class="jTableContainerDaiExtendedLong"></div>		
</div>	
<div><c:import url="footer.jsp" /></div>
</body>
</html>