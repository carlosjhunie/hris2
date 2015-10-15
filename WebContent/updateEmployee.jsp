<%@ page language="java" contentType="text/html; charset=iso-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Update Employee</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/styleTables.css" />
<link rel="stylesheet" type="text/css" href="css/style.css" />
<link rel="stylesheet" type="text/css" href="css/dai.css" />
<link rel="stylesheet" href="css/datePickerStyle.css">

<script type="text/javascript" src="js/common.js"></script>
<!-- Tabs -->
<link rel="stylesheet" type="text/css" href="css/jquery-ui-1.9.2.custom.css" />
<script type="text/javascript" src="js/jquery-ui-1.10.0.min.js"></script>
<script src="js/jquery-1.10.2.js"></script>
<script src="js/jquery-ui-1.11.4/jquery-ui.js"></script>
<script src="js/common.js"></script>
<script src="js/employee.js"></script>
<script type="text/javascript" src="js/moment.min.js"></script>
<%@include file="commonJtables.jsp" %>

<script>
var isSelectedSV1=false;
var isSelectedSV2=false;
var isSelectedSV3=false;

	$(document).ready(function() {
		var empId = document.getElementById("empId").value;		
		initDropDown();
		getEmployeeInfo(empId);		
		getEmployeePayrollInfo(empId);		
		renderEmployeeEduction(empId);
		renderFamilyMember(empId);
		renderWorkHistory(empId);
		renderTrainings(empId);
		renderCBA(empId);
		
		displayEmployeeSavedSVNames(empId);

		
	    $("#upload-button").click(function() {
	        var formData = new FormData($("form#uploadFileForm")[0]);
	        $.ajax({
	            url: '/hris/UploadEmployeeImageFileAction?empId='+empId,
	            type: 'POST',
	            data: formData,
	            async: false,
	            success: function(data) {
	                alert('Image uploaded.\nPlease Save Personal Info to confirm profile picture change.');
	                console.log(data.name);
	                $('#picLoc').attr('value', 'hrisImages/'+data.name);	      
					$('#empImgHolder').attr('src', 'hrisImages/'+data.name+'?'+Math.random()); //need to add to refresh image
					//use prop rather than attr					          
	                
	            },
	    		error : function(data) {
	    			alert('The image upload failed. Specify a valid image file and try again. ');
	    		},
	            cache: false,
	            contentType: false,
	            processData: false
	        });

	        return false;
	    });
	    
		$("input[id^=select-sv-button").click(function() {
			if ($(this).prop("id") == "select-sv-button-1") {
				isSelectedSV1 = true;
			}
			if ($(this).prop("id") == "select-sv-button-2") {
				isSelectedSV2 = true;
			}
			if ($(this).prop("id") == "select-sv-button-3") {
				isSelectedSV3 = true;
			}			
			
			$( "#searchEmployeeDialog" ).dialog({
				show: 'fade',
				hide: 'fade',
				width: 600,
				maxWidth: 600,
				height: 400,
				maxHeight: 400,
			    open: function() {
			        // On open, hide the original submit button
			        $( this ).find( "[type=submit]" ).hide();
			    },
			    buttons: [
			        {
			            text: "Close",
			            click: function() {
			                $( this ).dialog( "close" );
			            }
			        }
			    ]
			});
		});
		
		
		//for payrollinfo deductions section start		
		 $('#containerEmployeeIncomeDeduction').jtable({
			formCreated: function (event, data) {
				alert('Do not add standard govt deductions here (GSIS, PHIC, HDMF).');
			},
            title: 'Deductions',
            actions: {
                listAction:		'/hris/GetAllEmployeeDeductionAction?empId='+empId,
                createAction:	'/hris/SaveEmployeeDeductionAction?empId='+empId,
                updateAction:	'/hris/UpdateEmployeeDeductionAction'
                
            },
            fields: {
            	empDeductionId: {
                    key: true,
                    list: false
                },
                
            	empId: {
                    key: true,
                    list: false
                },
                
            	deductionId: {
            		title: 'DeductionName',
            		create: true,
            		edit: true,
                    list: true,
                    options:  
	                   	function(data) {       //Readme in GetLeaveTypeAction
                    	//alert (data.record.approvedBy);
			                        if (data.source == 'list' || data.source == 'create' || data.source == 'edit') {
			                            return '/hris/GetAllIncomeDeductionAction?displayOption=true&pInfoType=deduction';
			                        }
                    },      
                },
                
            	active: {
            		title: 'Active',
            		create: true,
            		edit: true,
                    list: true,
                    options: [{ Value: 1, DisplayText: 'True' }, { Value: 0, DisplayText: 'False' }]
                }, 
                
                
                remarks: {
                    title: 'Remarks',
                    type: 'textarea',
                    create: true,
                    edit : true 
                }              
            }
        });
        $('#containerEmployeeIncomeDeduction').jtable('load');
        
        //for payrollinfo deductions section end
        
        //for payrollinfo income section start
        
		 $('#containerEmployeeIncomeDeduction2').jtable({
	            title: 'Income',
	            actions: {
	                listAction:		'/hris/GetAllEmployeeIncomeAction?empId='+empId,
	                createAction:	'/hris/SaveEmployeeIncomeAction?empId='+empId,
	                updateAction:	'/hris/UpdateEmployeeIncomeAction'
	                
	            },
	            fields: {
	            	empIncomeId: {
	                    key: true,
	                    list: false
	                },
	                
	            	empId: {
	                    key: true,
	                    list: false
	                },
	                
	            	incomeId: {
	            		title: 'IncomeName',
	            		create: true,
	            		edit: true,
	                    list: true,
	                    options:  
		                   	function(data) {       //Readme in GetLeaveTypeAction
	                    	//alert (data.record.approvedBy);
				                        if (data.source == 'list' || data.source == 'create' || data.source == 'edit') {
				                            return '/hris/GetAllIncomeDeductionAction?displayOption=true&pInfoType=income';
				                        }
	                    },      
	                },
	                
	            	active: {
	            		title: 'Active',
	            		create: true,
	            		edit: true,
	                    list: true,
	                    options: [{ Value: 1, DisplayText: 'True' }, { Value: 0, DisplayText: 'False' }]
	                }, 
	                
	                
	                remarks: {
	                    title: 'Remarks',
	                    type: 'textarea',
	                    create: true,
	                    edit : true 
	                }              
	            }
	        });
	        $('#containerEmployeeIncomeDeduction2').jtable('load');
		
		//for payrollinfo income section end
		

		
	});	
	
	function initDropDown() {		
		populateCityDropDown();
		populateJobTitleDropDown();
		populateCountryDropDown();
		populateDepartmentDropDown();
		populateDivisionDropDown();
		populateEmployeeTypeDropDown();
		populateProvinceDropDown();		
	}
	
	$.fn.serializeObject = function() {
		var o = {};
		var a = this.serializeArray();
		$.each(a, function() {
			if (o[this.name] !== undefined) {
				if (!o[this.name].push) {
					o[this.name] = [ o[this.name] ];
				}
				o[this.name].push(this.value || '');
			} else {
				o[this.name] = this.value || '';
			}
		});
		return o;
	};
	
	function clickSearchResult(empid) {		
		if (isSelectedSV1){
			//alert('You chose this supervisor: '+ empSearchMap[empid].firstname + ' ' + empSearchMap[empid].lastname);
			$("#superVisor1FullName").prop("value",  empSearchMap[empid].firstname + ' ' + empSearchMap[empid].lastname);
			$("#superVisor1Id").prop("value", empid);
			isSelectedSV1=false;
		}
		if (isSelectedSV2){
			//alert('You chose this supervisor: '+ empSearchMap[empid].firstname + ' ' + empSearchMap[empid].lastname);
			$("#superVisor2FullName").prop("value",  empSearchMap[empid].firstname + ' ' + empSearchMap[empid].lastname);
			$("#superVisor2Id").prop("value", empid);
			isSelectedSV2=false;
		}
		if (isSelectedSV3){
			//alert('You chose this supervisor: '+ empSearchMap[empid].firstname + ' ' + empSearchMap[empid].lastname);
			$("#superVisor3FullName").prop("value",  empSearchMap[empid].firstname + ' ' + empSearchMap[empid].lastname);
			$("#superVisor3Id").prop("value", empid);
			isSelectedSV3=false;			
		}
		$( "#searchEmployeeDialog" ).dialog("close");
}
</script>
</head>
<body>
<div><c:import url="header.jsp" /></div>
<div>	
	<input type="hidden" name="empIdLoggedIn" id="empIdLoggedIn" value="${employeeLoggedIn.empId}" />
	<input type="hidden" name="departmentIdLoggedIn" id="departmentIdLoggedIn" value="${employeeLoggedIn.departmentId}" />	
	<div id="content">
		<!-- Left Side of Dashboard -->
		<div id="dashBoardLeftPannel">
			<!-- Employee Information -->
			<div>
				<!-- Picture -->
				<div style="float: left;"><img id="empImgHolder" src="" alt="no image" width="180px" height="220px" style="padding:20px;" /></div>
				<!-- Employee Info -->
				<div class="welcomeHeaderDashboardTable">WELCOME Back!</div>
				<div class="nameDashBoard" id="employeeName"></div>
				<div class="jobTitle" id="jobTitleNameDashboard"></div>
				<div class="departmentDashboard" id="departmentNameDashEmployee"></div>				
				<div class="dashboardTxt">&nbsp;&nbsp;&nbsp;&nbsp;</div>
				<div class="dashboardTxtBig">&nbsp;</div>				
				<div class="dashboardTxt">&nbsp;&nbsp;&nbsp;&nbsp;</div>
				<div class="dashboardTxtBig">&nbsp;</div>
				<div class="cb"></div>
				<div id="dashBoardButtonUploadGroup">
					<form method="POST" id="uploadFileForm" name="uploadFileForm" enctype="multipart/form-data">
						<input type="file" id="upload-file" name="uploadImage" accept="image/*"/>
						<input type="button" id="upload-button" value="Update Image" />
					</form>
				</div>
				<div class="dashBoardButton"><a href="employeeLeaveEntry.jsp?empId=${param.empId}">Request Time Off</a></div>
				<div class="dashBoardButton"><a href="employeeOvertimeEntry.jsp?empId=${param.empId}">File Overtime</a></div>
				<div class="dashBoardButton"><a href="employeeOutOfOfficeEntry.jsp?empId=${param.empId}">File Training &amp; Seminar</a></div>				
			</div>
		</div>
		<!-- Right Side of Dashboard -->
		<div id="dashBoardRightPannel">
			<div id="tabs-nohdr">
			  <ul>
			    <li><a href="#tabs-nohdr-1">Personal Info</a></li>
			    <li class="hiddenField"><a href="#tabs-nohdr-2">Educ Info</a></li>
			    <li class="hiddenField"><a href="#tabs-nohdr-3">Family Info</a></li>
			    <li class="hiddenField"><a href="#tabs-nohdr-4">Payroll Info</a></li>
			    <li class="hiddenField"><a href="#tabs-nohdr-5">Other Income/Deduct.</a></li>
			    <li class="hiddenField"><a href="#tabs-nohdr-6">Work Hist.</a></li>
			    <li class="hiddenField"><a href="#tabs-nohdr-7">Trainings/Seminars</a></li>
			    <li class="hiddenField"><a href="#tabs-nohdr-8">Competency Based Assessment</a></li>
			  </ul>
			  <div id="tabs-nohdr-1">			    
			    <form method="POST" id="addEmployeeForm" name="addEmployeeForm" action="AddEmployeeAction">
			    	<input type="hidden" name="empId" id="empId" value="${param.empId}" />		
			    	<input type="hidden" name="picLoc" id="picLoc" value="NONE" />					
				    <div class="dataEntryText">Employee No</div>
				    <div class="dataEntryInput"><input type="text" name="empNo" id="empNo"  value="${employeeObject.empNo}" placeholder="Employee Number" /></div>
				    <div class="dataEntryText">Firstname</div>			    
				    <div class="dataEntryInput"><input type="text" name="firstname" id="firstname"  value="${employeeObject.firstname}" placeholder="Firstname" /></div>
				    <div class="cb"></div>
				    <div class="dataEntryText">Lastname</div>
				    <div class="dataEntryInput"><input type="text" name="lastname" id="lastname"  value="${employeeObject.lastname}" placeholder="Lastname" /></div>
				    <div class="dataEntryText">Middlename</div>
				    <div class="dataEntryInput"><input type="text" name="middlename" id="middlename"  value="${employeeObject.middlename}" placeholder="Middlename" /></div>
				    <div class="cb"></div>
				    <div class="dataEntryText">Username</div>
				    <div class="dataEntryInput"><input type="text" name="username" id="username"  value="${employeeObject.username}" placeholder="Username" /></div>
				    <%--092815 start remove password in update employee
				    <div class="dataEntryText">Password</div>
				    <div class="dataEntryInput"><input type="password" name="password" id="password"  value="${employeeObject.password}" placeholder="Password" /></div>
				        092815 end remove password in update employee --%>
				    <div class="cb"></div>
				    <div class="dataEntryText">Birthdate</div>
				    <div class="dataEntryInput"><input type="text" name="dateOfBirth" id="dateOfBirth"  value="${employeeObject.dateOfBirth}" class="useDPicker" placeholder="Birthdate" /></div>
				    <div class="dataEntryText">Email</div>
				    <div class="dataEntryInput"><input type="text" name="email" id="email"  value="${employeeObject.email}" placeholder="Email" /></div>
				    <div class="cb"></div>
				    <div class="dataEntryText">Landline</div>
				    <div class="dataEntryInput"><input type="text" name="telNo" id="telNo"  value="${employeeObject.telNo}" placeholder="Landline" /></div>
				    <div class="dataEntryText">Mobile No.</div>
				    <div class="dataEntryInput"><input type="text" name="mobileNo" id="mobileNo"  value="${employeeObject.mobileNo}" placeholder="Mobile Number" /></div>
				    <div class="cb"></div>
				    <div class="dataEntryText">Gender</div>
				    <div class="dataEntryInput">			    	
				    	<select name="gender" id="gender" style="width:214px; height:41px; background:#fff; border: 1px solid #52833b; margin-top: 5px; color: #A9A9A9;" >
							<option selected="selected" value="n">Select Gender</option>									
							<option value="M">Male</option>
							<option value="F">Female</option>
						</select>
				    </div>
				    <div class="dataEntryText">Place of Birth</div>
				    <div class="dataEntryInput"><input type="text" name="birthPlace" id="birthPlace" value="${employeeObject.birthPlace}" placeholder="Place of Birth" /></div>
				    <div class="cb"></div>
				    <div class="dataEntryText">Civil Status</div>
				    <div class="dataEntryInput">			    	
				    	<select name="civilStatus" id="civilStatus" style="width:214px; height:41px; background:#fff; border: 1px solid #52833b; margin-top: 5px; color: #A9A9A9;" >
							<option selected="selected" value="n">Select Status</option>									
							<option value="S">Single</option>
							<option value="M">Married</option>
							<option value="D">Divorced</option>
							<option value="W">Widowed</option>
						</select>
				    </div>
				    <div class="dataEntryText">Nationality</div>
				    <div class="dataEntryInput"><input type="text" name="nationality" id="nationality"  value="${employeeObject.nationality}" placeholder="Nationality" /></div>
				    <div class="cb"></div>
				    <div class="dataEntryText">Street</div>
				    <div class="dataEntryInput"><input type="text" name="street" id="street"  value="${employeeObject.street}" placeholder="Street" /></div>
				    <div class="dataEntryText">City</div>
				    <div class="dataEntryInput">
				    	<select name="cityId" id="cityDropDownID" style="width:214px; height:41px; background:#fff; border: 1px solid #52833b; margin-top: 5px; color: #A9A9A9;" >			    													
							
						</select>			    		    	
				    </div>
				    <div class="cb"></div>
				    <div class="dataEntryText">Province</div>
				    <div class="dataEntryInput">
				    	<select name="provinceId" id="provinceDropDownID" style="width:214px; height:41px; background:#fff; border: 1px solid #52833b; margin-top: 5px; color: #A9A9A9;" >			    													
							
						</select>			    	
				    </div>
				    <div class="dataEntryText">Country</div>
				    <div class="dataEntryInput">
				    	<select name="countryId" id="countryDropDownID" style="width:214px; height:41px; background:#fff; border: 1px solid #52833b; margin-top: 5px; color: #A9A9A9;" >			    													
							
						</select>			    	
				    </div>
				    <div class="cb"></div>
				    <div class="dataEntryText">Zip Code</div>
				    <div class="dataEntryInput"><input type="text" name="zipCode" id="zipCode"  value="${employeeObject.zipCode}" placeholder="Zipcode" /></div>
				    <div class="dataEntryText">Job Title</div>
				    <div class="dataEntryInput">
				    	<select name="jobTitleId" id="jobTitleDropDownID" style="width:214px; height:41px; background:#fff; border: 1px solid #52833b; margin-top: 5px; color: #A9A9A9;" >			    													
							
						</select>			    	
				    </div>	
				    <div class="cb"></div>
				    <div class="dataEntryText">Employee Status</div>
				    <div class="dataEntryInput">
				    	<select name="empStatus" id="empStatus" style="width:214px; height:41px; background:#fff; border: 1px solid #52833b; margin-top: 5px; color: #A9A9A9;" >			    													
							<option value="">Select Status</option>						
							<option value="A">ACTIVE</option>						
							<option value="NA">NOT ACTIVE</option>
						</select>			    	
				    </div>
				    <div class="dataEntryText">Department</div>
				    <div class="dataEntryInput">
				    	<select name="departmentId" id="departmentDropDownID" style="width:214px; height:41px; background:#fff; border: 1px solid #52833b; margin-top: 5px; color: #A9A9A9;" >			    													
							
						</select>			    	
				    </div>	
				    <div class="cb"></div>
				    <div class="dataEntryText">Division</div>
				    <div class="dataEntryInput">
				    	<select name="divisionId" id="divisionDropDownID" style="width:214px; height:41px; background:#fff; border: 1px solid #52833b; margin-top: 5px; color: #A9A9A9;" >			    													
							
						</select>			    	
				    </div>
				    <div class="dataEntryText">Date Employed</div>
				    <div class="dataEntryInput">
				    	<input type="text" name="employmentDate" id="employmentDate"  value="${employeeObject.employmentDate}" class="useDPicker" placeholder="Date Employed" />			    	
				    </div>		
				    <div class="cb"></div>
				    <div class="dataEntryText">Employee Type</div>
				    <div class="dataEntryInput">
				    	<select name="employeeTypeId" id="employeeTypeDropDownID" style="width:214px; height:41px; background:#fff; border: 1px solid #52833b; margin-top: 5px; color: #A9A9A9;" >			    													
							
						</select>			    	
				    </div> 
				    <div class="dataEntryText">End of Contract</div>
				    <div class="dataEntryInput">
				    	<input type="text" name="endOfContract" id="endOfContract" value="${employeeObject.endOfContract}" class="useDPicker" placeholder="Date Employed" />	    	
				    </div>
				    
				    <div class="cb"></div>
				    
				    <div class="dataEntryText">GSIS</div>
				    <div class="dataEntryInput"><input type="text" id="gsis" name="gsis"  value="${param.gsis}" placeholder="GSIS" /></div>
				    <div class="dataEntryText">SSS</div>
				    <div class="dataEntryInput"><input type="text" id="sss" name="sss"  value="${param.sss}" placeholder="SSS" /></div>				    
				    <div class="cb"></div>
				    
				    <div class="dataEntryText">TIN</div>
				    <div class="dataEntryInput"><input type="text" id="tin" name="tin"  value="${param.tin}" placeholder="TIN" /></div>
				    <div class="dataEntryText">TAX STATUS</div>
				    <div class="dataEntryInput">
				    	<select id="taxstatus" name="taxstatus" style="width:208px; height:41px; background:#fff; border: 1px solid #52833b; margin-top: 5px; color: #A9A9A9;" >														
							<option value="Z">Z</option>						
							<option value="S">S</option>
							<option value="S">S1</option>
							<option value="S">S2</option>
							<option value="S">S3</option>
							<option value="S">S4</option>
							<option value="ME">ME</option>
							<option value="ME">ME1</option>
							<option value="ME">ME2</option>
							<option value="ME">ME3</option>
							<option value="ME">ME4</option>
						</select>
				    </div>
				    <div class="cb"></div>
				    
				   
				    <div class="dataEntryText">PHILHEALTH</div>
				    <div class="dataEntryInput"><input type="text" id="phic" name="phic"  value="${param.phic}" placeholder="PHILHEALTH" /></div>
				    <div class="dataEntryText">PAGIBIG</div>
				    <div class="dataEntryInput"><input type="text" id="hdmf" name="hdmf"  value="${param.hdmf}" placeholder="PAGIBIG" /></div>
				    
				    <div class="cb"></div>
				    <div class="dataEntryText">Supervisor 1</div>
				    <div class="dataEntryInput">
				    	<input type="text" name="superVisor1FullName"  id="superVisor1FullName" value="" disabled/>
				    	<input type="hidden" name="superVisor1Id" id="superVisor1Id" value="0" />			
				 		<input type="button" id="select-sv-button-1" value="Choose..">
				    </div>
				    <div class="cb"></div>
				    <!-- 
				    <div class="dataEntryText">Supervisor 1</div>
				    <div class="dataEntryInput">
				    	<input type="text" name="superVisor1FullName"  id="superVisor1FullName" value="" disabled/>
				    	<input type="hidden" name="superVisor1Id" id="superVisor1Id" value="0" />			
				 		<input type="button" id="select-sv-button-1" value="Choose..">
				    </div>

					<div class="cb"></div>
				    <div class="dataEntryText">Supervisor 2</div>
				    <div class="dataEntryInput">
				    	<input type="text" name="superVisor2FullName" id="superVisor2FullName"  value="" disabled/>
				    	<input type="hidden" name="superVisor2Id" id="superVisor2Id" value="0" />	
				    	<input type="button" id="select-sv-button-2" value="Choose.."> 	
				    </div>
				    
				    
				    <div class="cb"></div>
				    <div class="dataEntryText">Supervisor 3</div>
				    <div class="dataEntryInput">
				    	<input type="text" name="superVisor3FullName" id="superVisor3FullName"  value="" disabled/>
				    	<input type="hidden" name="superVisor3Id" id="superVisor3Id" value="0" />	
				    	<input type="button" id="select-sv-button-3" value="Choose..">    	
				    </div>
				    
				     -->
				    			    
				</form>
			    <div class="cb" style="height: 10px;"></div>
			    <div class="employeeButton" onClick="updateEmployee();">Save</div>
			    <div class="employeeButton">Clear</div>
			    <div class="cb"></div>
			    
			  </div>
			  <div id="tabs-nohdr-2" class="hiddenField">			    
			    <div id="educTable"></div>				  
			  </div>
			  <div id="tabs-nohdr-3" class="hiddenField">			    
			    <div id="familyMemberTable"></div>			    
			  </div>
			  <div id="tabs-nohdr-4" class="hiddenField">
			  	<form method="POST" id="savePayrollInfoForm" name="savePayrollInfoForm" action="SaveEmployeePayrollInfoAction">
				  	<input type="hidden" name="empId" id="empId" value="${param.empId}" />	
				    <div class="dataEntryText">Monthly Rate</div>
				    <div class="dataEntryInput"><input type="text" id="monthlyRate" name="monthlyRate"  value="${param.monthlyRate}" placeholder="Monthly Rate" /></div>
				    <div class="dataEntryText">Daily Rate</div>
				    <div class="dataEntryInput"><input type="text" id="dailyRate" name="dailyRate"  value="${param.dailyRate}" placeholder="Daily Rate" /></div>
				    <div class="cb"></div>
				    <div class="dataEntryText">Hourly Rate</div>
				    <div class="dataEntryInput"><input type="text" id="hourlyRate" name="hourlyRate"  value="${param.hourlyRate}" placeholder="Hourly Rate" /></div>
				    <div class="dataEntryText">Food Allowance</div>
				    <div class="dataEntryInput"><input type="text" id="foodAllowance" name="foodAllowance"  value="${param.foodAllowance}" placeholder="Food Allowance" /></div>			    
				    <div class="cb"></div>
				    <div class="dataEntryText">Cola</div>
				    <div class="dataEntryInput"><input type="text" id="cola" name="cola"  value="${param.cola}" placeholder="Cola" /></div>
				    <div class="dataEntryText">Tax Shield</div>
				    <div class="dataEntryInput"><input type="text" id="taxShield" name="taxShield"  value="${param.taxShield}" placeholder="Tax Shield" /></div>
				    <div class="cb"></div>
				    <div class="dataEntryText">Transportation Allow.</div>
				    <div class="dataEntryInput"><input type="text" id="transAllowance" name="transAllowance"  value="${param.transAllowance}" placeholder="Transportation Allowance" /></div>
				    <div class="dataEntryText">Payroll Type</div>
				    <div class="dataEntryInput">			    	
				    	<select name="payrollType" id="payrollType" style="width:214px; height:41px; background:#fff; border: 1px solid #52833b; margin-top: 5px; color: #A9A9A9;" >
								<option selected="selected" value="n">Select Payroll Type</option>									
								<option value="M">Monthly</option>
								<option value="S">Semi-Monthly</option>
								<option value="W">Weekly</option>
						</select>
				    </div>
					<div class="cb" style="height: 10px;"></div>
				    <div class="dataEntryText">Bank Information</div>
				    <div class="cb"></div>	
				    <div class="dataEntryText">Bank Name</div>
				    <div class="dataEntryInput"><input type="text" id="bankNameBan" name="bankNameBan"  value="${param.bankNameBan}" placeholder="Bank Name" /></div>	     
				    <div class="dataEntryText">Bank Account #</div>
				    <div class="dataEntryInput"><input type="text" id="ban" name="ban"  value="${param.ban}" placeholder="Bank Account #" /></div>
					<div class="cb" style="height: 10px;"></div>
				    <%--start: govt deductions --%>
				    
				    <!-- 
					    <div class="dataEntryText">GSIS Contrib.</div>
					    <div class="cb"></div>	
					    <div class="dataEntryText">EE Share</div>
					    <div class="dataEntryInput"><input type="text" id="gsisEmployeeShare" name="gsisEmployeeShare"  value="${param.gsisEmployeeShare}" placeholder="Employee Share" /></div>
					    <div class="dataEntryText">ER Share</div>
					    <div class="dataEntryInput"><input type="text" id="gsisEmployerShare" name="gsisEmployerShare"  value="${param.gsisEmployerShare}" placeholder="Employer Share" /></div>
					    			    
					    <div class="cb"></div>			    
					    <div class="dataEntryText">PAGIBIG Contrib.</div>			    
					    <div class="cb"></div>	
					    <div class="dataEntryText">EE Share</div>
					    <div class="dataEntryInput"><input type="text" id="pagibigEmployeeShare" name="pagibigEmployeeShare"  value="${param.pagibigEmployeeShare}" placeholder="Employee Share" /></div>
					    <div class="dataEntryText">ER Share</div>
					    <div class="dataEntryInput"><input type="text" id="pagibigEmployerShare" name="pagibigEmployerShare"  value="${param.pagibigEmployerShare}" placeholder="Employer Share" /></div>
					    <div class="cb"></div>	
					    
					    
					    <div class="dataEntryText">PHILHEALTH</div>			    
					    <div class="cb"></div>		    
					    <div class="dataEntryText">EE Share</div>
					    <div class="dataEntryInput"><input type="text" id="philhealthEmployeeShare" name="philhealthEmployeeShare"  value="${param.philhealthEmployeeShare}" placeholder="Employee Share" /></div>
					    <div class="dataEntryText">ER Share</div>
					    <div class="dataEntryInput"><input type="text" id="philhealthEmployerShare" name="philhealthEmployerShare"  value="${param.philhealthEmployerShare}" placeholder="Employer Share" /></div>
					 -->   
					    
				    <%--end: govt deductions --%>
				    <div class="cb" style="height: 60px;"></div>
				    <div class="employeeButton" onClick="saveEmployeePayrollInfo();">Save</div>
				    <div class="employeeButton">Clear</div>
				    <div class="cb"></div>
			    </form>
			  </div>
			  <div id="tabs-nohdr-5" class="hiddenField">
			    <div>
				<div class="cb" style="height: 10px;"></div>
					<div id="containerEmployeeIncomeDeduction" class="jTableContainerDaiExtended"></div>		
				</div>	
				<div id="content-payroll-info">
				<div class="cb" style="height: 20px;"></div>
					<div id="containerEmployeeIncomeDeduction2" class="jTableContainerDaiExtended"></div>		
				</div>	
			  			    
			  </div>
			  <div id="tabs-nohdr-6" class="hiddenField">
			  	<div id="workHistoryTable"></div>			    
			  </div>
			  <div id="tabs-nohdr-7" class="hiddenField">
			    <div id="trainingTable"></div>			    
			  </div>
			  <div id="tabs-nohdr-8" class="hiddenField">			    
 				<div id="competencyTable" class="jTableContainerDaiExtended"></div>		
			  </div>
			</div>
		</div>
		<div class="cb" style="height: 50px;"></div>
	</div>
</div>	

<div style=""><c:import url="footer.jsp" /></div>

<div id="searchEmployeeDialog" title="Search Employee" class="ui-front">
<c:import url="searchEmployee_solr.jsp" />
	<div class="cb"></div>
	<div>
		<div id="searchHolderId"></div>	
	</div>
</div>
</body>
</html>
