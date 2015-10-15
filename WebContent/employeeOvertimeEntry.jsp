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

<link rel="stylesheet" type="text/css" href="css/datetimepicker/bootstrap-combined.min.css">
<link rel="stylesheet" type="text/css" href="css/datetimepicker/bootstrap-datetimepicker.min.css">



<script type="text/javascript" src="js/datetimepicker/bootstrap.min.js"></script>
<script type="text/javascript" src="js/datetimepicker/bootstrap-datetimepicker.min.js"></script>

	
	
<script type="text/javascript">

    $(document).ready(function () {
    	var empId = '${sessionScope.employeeLoggedIn.empId}'; //this is the employeeid filing the OT
    	
        $('#containerEmployeeOvertimeEntry').jtable({
            title: 'My Overtime Entries',
            actions: {
                listAction:		'/hris/GetAllOvertimeAction?empId='+empId,
                createAction:	'/hris/SaveOvertimeAction?empId='+empId,
                updateAction:	'/hris/UpdateOvertimeAction'
                
            },
            formCreated : function(event, data){
            	data.form.find('input[name="dateRendered"]').addClass('validate[required]');
            	data.form.find('input[name="noOfHours"]').addClass('validate[required]');
            	data.form.find('textarea[name="remarks"]').addClass('validate[required]');
            	
            	$(data.form).parent().parent().css("top","155");   				//to make the form popup when adding/updating visible
            	
            	
            	if (data.formType == 'edit') {
            		data.form.children(':lt(5)').wrapAll('<div class="col1"/>');	//to split into 2 columns so that it wouldnt be one long form
            	}
            	else {
            		data.form.children(':lt(4)').wrapAll('<div class="col1"/>');	//to split into 2 columns so that it wouldnt be one long form
            	}
            	
            	data.form.children(':gt(0)').wrapAll('<div class="col2"/>');
            	//for dates: we need to find the from/to manually
                var $dtfrom = data.form.find ('input[name="dateRendered"]');
            	
            	//hack since twitter bootstrap conflicts with jtable
            	//but still have some issues with the size of the save and cancel buttons
                var $dtButton = $(data.form).parent().parent().find ('button[type="button"]');
                $dtButton.each(function(index) {
                	if (index==0){
                		$(this).addClass("ui-button ui-widget ui-state-default ui-corner-all ui-button-icon-only ui-dialog-titlebar-close");
                	} else {
                		$(this).addClass("ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only");
                	}
                })
				//$dtButton.first().addClass("ui-button ui-widget ui-state-default ui-corner-all ui-button-icon-only ui-dialog-titlebar-close");
				//$dtButton.next().addClass("ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only");
              //manually activate the datepicker, and supply the params from useDPicker (you need to do this so that when date is selected, the date format is followed.)
              //and you need to add val in order to display the value saved in the DB in the proper format, if not, it will become Mon dd, yyyy which will fail.
				if (data.formType == 'edit'){
	                $dtfrom.val(moment(data.record.dateFrom).format('YYYY-MM-DD HH:MM'));		
										
					//need to add the 2 below for the hidden attibutes so we can pass back to the servlet the values during update
					var $dtCreatedDate = data.form.find ('input[name="createdDate"]');
	                $dtCreatedDate.val(moment(data.record.createdDate).format('YYYY-MM-DD HH:MM'));	                

				}
				
				//call these datepickers below when doing create entry, since we do not need to display any dateFrom or dateTo
                // $dtfrom.datepicker({
            	//	changeMonth : true,
            	//	changeYear : true,
            	//	dateFormat : 'yy-mm-dd',
            	//	yearRange : '1910:2100',
            	//    beforeShow: function(input, inst){	            	    	
            	//           $(".ui-datepicker").css('font-size', 11);
            	//    }
            	//});	

				
				$dtfrom.parent().addClass("input-append date");
				$dtfrom.parent().append("<span class='add-on'><i data-time-icon='icon-time' data-date-icon='icon-calendar'></i></span>");
				$dtfrom.parent().datetimepicker({
			        format: 'yyyy-MM-dd hh:mm',
					pick12HourFormat: false,
			        language: 'en'
				});
				
				data.form.validationEngine();
            },
            fields: {
            	empOvertimeId: {
                    key: true,
                    list: false
                },
                
            	empId: {
                    key: true,
                    list: false
                },
                
         
                
                dateRendered: {
                    title: 'Rendered Date',
                    //type: 'date',  //comment-out the type date
                    edit: true,
                    create: true,
                    //can comment display if data type for date used is varchar
                    display:
                      function (data) {
                    	if (data.record.dateRendered != undefined){				//need to have a condition for undefined since moment will display default date.
                          return moment(data.record.dateRendered).format('YYYY-MM-DD HH:mm');
                    	}
                    	return 'N/A';
                      }
                },
                
               
                noOfHours: {
                    title: '# of Hours',
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
        $('#containerEmployeeOvertimeEntry').jtable('load');
        
    });

</script>

</head>
<body>
<div><c:import url="header.jsp" /></div>
<div id="content">
<div class="cb" style="height: 20px;"></div>
	<div id="containerEmployeeOvertimeEntry" class="jTableContainerDaiExtended"></div>		
</div>	
<div><c:import url="footer.jsp" /></div>
</body>
</html>