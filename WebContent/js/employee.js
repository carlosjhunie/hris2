function renderTrainings(empId) {
	$('#trainingTable').jtable({
		title : 'Training and Seminars List',
		actions : {
			createAction : '/hris/SaveEmployeeTrainingAction',
			listAction : '/hris/GetEmployeeTrainingAction?empId=' + empId,
			updateAction: '/hris/UpdateEducationalTrainingAction'
		},
		formCreated : function(event, data) {
			$(data.form).parent().parent().css("top", 155);
			$(data.form).parent().parent().css('left',500); 
			$(data.form).addClass("custom_horizontal_form_field");			
			data.form.children(':lt(7)').wrapAll('<div class="col1"/>');
			data.form.children(':gt(0)').wrapAll('<div class="col2"/>');
			
			var $df = data.form.find ('input[name="dateFrom"]');   
        	
            $df.datepicker({
        		changeMonth : true,
        		changeYear : true,
        		dateFormat : 'yy-mm-dd',
        		yearRange : '1910:2100',
        	    beforeShow: function(input, inst){	            	    	
     	           $(".ui-datepicker").css('font-size', 11);
     	    }
        	});
            
            var $dt = data.form.find ('input[name="dateTo"]');   
        	
            $dt.datepicker({
        		changeMonth : true,
        		changeYear : true,
        		dateFormat : 'yy-mm-dd',
        		yearRange : '1910:2100',
        	    beforeShow: function(input, inst){	            	    	
     	           $(".ui-datepicker").css('font-size', 11);
     	    }
        	});
            
            //data.form.find('input[name="dateFrom"]').addClass('validate[required]');
			//data.form.find('input[name="dateTo"]').addClass('validate[required]');
            //data.form.validationEngine();
			
		},
		fields : {
			empOOOId : {
				key : true,
				list : false
			},
			empId : {
				create : true,
				list : false,
				type : 'hidden',
				defaultValue : empId

			},
			dateFrom : {
				title : 'Date From',
				edit : true
			},
			dateTo : {
				title : 'Date To',
				edit : true
			},
			titleActivity : {
				title : 'Title Activity',
				edit : true
			},
			provider : {
				title : 'Provider',
				edit : true
			},
			remarks : {
				title : 'Remarks',
				edit : true
			}

		}
	});
	$('#trainingTable').jtable('load');
}

function renderWorkHistory(empId) {
	$('#workHistoryTable').jtable({
		title : 'Work History List',
		//paging: true, //Enable paging (need to change action class to impl query change or sublist)
		//pageSize: 3,
		actions : {
			createAction : '/hris/SaveEmployeeWorkHistoryAction',
			listAction : '/hris/GetEmployeeWorkHistoryAction?empId=' + empId
		},
		formCreated : function(event, data) {
			$(data.form).parent().parent().css("top", 200);
			$(data.form).parent().parent().css('left',185); 
			$(data.form).addClass("custom_horizontal_form_field");
			data.form.children(':lt(4)').wrapAll('<div class="col1"/>');
			data.form.children(':gt(0)').wrapAll('<div class="col2"/>');
		},
		fields : {
			empWorkHistoryId : {
				key : true,
				list : false
			},
			empId : {
				create : true,
				list : false,
				type : 'hidden',
				defaultValue : empId
			},
			yrsService : {
				title : 'Years of Service',
				edit : true
			},
			employerName : {
				title : 'Employer Name',
				edit : true
			},

			industry : {
				title : 'Industry',
				edit : true
			},
			address : {
				title : 'Address',
				edit : true
			},
			position : {
				title : 'Position',
				edit : true
			},
			remarks : {
				title : 'Remarks',
				edit : true
			},
			salary : {
				title : 'Salary',
				edit : true
			},
			salaryGrade : {
				title : 'Salary Grade',
				edit : true
			},
			stepIncrement : {
				title : 'Step Increment',
				edit : true
			}

		}
	});
	$('#workHistoryTable').jtable('load');
}

function renderFamilyMember(empId) {
	$('#familyMemberTable').jtable({
		title : 'Family Members List',
		actions : {
			createAction : '/hris/SaveEmployeeFamilyMemberAction',
			listAction : '/hris/GetEmployeeFamilyMemberAction?empId=' + empId,
			updateAction: '/hris/UpdateEmployeeFamilyMemberAction'
		},
		formCreated : function(event, data) {
			$(data.form).parent().parent().css("top", 155);
			$(data.form).parent().parent().css("left", 500);
			$(data.form).addClass("custom_horizontal_form_field");
			if ($data.form == create) {
				data.form.children(':lt(4)').wrapAll('<div class="col1"/>');
			}
			if ($data.form == create) {
				data.form.children(':lt(7)').wrapAll('<div class="col1"/>');
			}
			data.form.children(':gt(0)').wrapAll('<div class="col2"/>');
		},
		fields : {
			empFamilyMemberId : {
				key : true,
				list : false
			},
			empId : {
				create : true,
				list : false,
				type : 'hidden',
				defaultValue : empId

			},
			name : {
				title : 'Family Member Name',
				edit : true
			},
			gender : {
				title : 'Gender',
				edit : true,
				options : {
					'M' : 'Male',
					'F' : 'Female'
				}
			},
			relation : {
				title : 'Relationship',
				edit : true
			},
			age : {
				title : 'Age',
				edit : true
			},
			remarks : {
				title : 'Remarks',
				edit : true
			},
			contactNum : {
				title : 'Contact Number',
				edit : true
			}

		}
	});
	$('#familyMemberTable').jtable('load');
}

function renderEmployeeEduction(empId) {
	$('#educTable').jtable({
		title : 'Educational Background List',
		actions : {
			createAction : '/hris/SaveEducationalBackgroundAction',
			listAction : '/hris/GetEducationalBackgroundAction?empId=' + empId,
			updateAction: '/hris/UpdateEducationalBackgroundAction'
			
		},
		formCreated : function(event, data) {
			$(data.form).parent().parent().css("top", 155);
			$(data.form).parent().parent().css('left', 500);
			$(data.form).addClass("custom_horizontal_form_field");	
			if (data.formType == 'create'){
			data.form.children(':lt(6)').wrapAll('<div class="col1"/>');
			
			}			
			if (data.formType == 'edit'){
				data.form.children(':lt(7)').wrapAll('<div class="col1"/>');
				
			}
			data.form.children(':gt(0)').wrapAll('<div class="col2"/>');
		},
		fields : {
			educBkgrndId : {
				key : true,
				list : false
			},
			empId : {
				key : true,
				create : true,
				list : false,
				type : 'hidden',
				defaultValue : empId

			},
			yearAttended : {
				title : 'Year Attended',
				edit : true
			},
			course : {
				title : 'Course/Degree',
				edit : true
			},
			school : {
				title : 'School',
				edit : true
			},
			yearGraduated : {
				title : 'Year Graduated',
				edit : true
			},
			remarks : {
				title : 'Remarks',
				edit : true
			}
		}
	});
	$('#educTable').jtable('load');

}

function getEmployeeInfoDashboard(empId) {
	var oAjaxCall = $.ajax({
		type : "GET",
		url : "/hris/GetEmployeeAction",
		data : "empId=" + empId,
		cache : false,
		async : false,
		dataType : "json",
		success : function(data) {
			
			$('#employeeName').html(data.Employee.firstname + " " + data.Employee.lastname);			
			$('#departmentNameDashEmployee').html(data.Employee.departmentName);			
			$('#jobTitleNameDashboard').html(data.Employee.jobTitleName);			
			$('#empImgHolder').attr('src', data.Employee.picLoc);
			

		},
		error : function(data) {
			alert('error: ' + data);
			isSuccessful = false;
		}
	});

}


function getEmployeeInfo(empId) {
	var oAjaxCall = $.ajax({
		type : "GET",
		url : "/hris/GetEmployeeAction",
		data : "empId=" + empId,
		cache : false,
		async : false,
		dataType : "json",
		success : function(data) {
			
			$('#employeeName').html(data.Employee.firstname + " " + data.Employee.lastname);
			$('#jobTitleName').html(data.Employee.jobTitleId);
			$('#departmentNameEmployee').html(data.Employee.departmentId);
			$('#empNo').val(data.Employee.empNo);
			$('#firstname').val(data.Employee.firstname);
			$('#lastname').val(data.Employee.lastname);
			$('#middlename').val(data.Employee.middlename);
			$('#username').val(data.Employee.username);
			$('#password').val(data.Employee.password);
			$('#dateOfBirth').val(data.Employee.dateOfBirth);
			$('#email').val(data.Employee.email);
			$('#telNo').val(data.Employee.telNo);
			$('#mobileNo').val(data.Employee.mobileNo);
			$('#gender').val(data.Employee.gender);
			$('#birthPlace').val(data.Employee.birthPlace);
			$('#civilStatus').val(data.Employee.civilStatus);
			$('#nationality').val(data.Employee.nationality);
			$('#street').val(data.Employee.street);
			$('#zipCode').val(data.Employee.zipCode);
			$('#empStatus').val(data.Employee.empStatus);
			$('#employmentDate').val(data.Employee.employmentDate);
			$('#endOfContract').val(data.Employee.endOfContract);
			$('#jobTitleDropDownID').val(data.Employee.jobTitleId);
			$('#cityDropDownID').val(data.Employee.cityId);
			$('#countryDropDownID').val(data.Employee.countryId);
			$('#departmentDropDownID').val(data.Employee.departmentId);
			$('#divisionDropDownID').val(data.Employee.divisionId);
			$('#employeeTypeDropDownID').val(data.Employee.employeeTypeId);
			$('#provinceDropDownID').val(data.Employee.provinceId);
			$('#empImgHolder').attr('src', data.Employee.picLoc);
			$('#picLoc').val(data.Employee.picLoc);
			$('#sss').val(data.Employee.sss);
			$('#gsis').val(data.Employee.gsis);
			$('#hdmf').val(data.Employee.hdmf);
			$('#tin').val(data.Employee.tin);
			$('#phic').val(data.Employee.phic);
			$('#taxstatus').val(data.Employee.taxstatus);
			
			$('#employeeName').html(data.Employee.firstname + " " + data.Employee.lastname);			
			$('#departmentNameDashEmployee').html(data.Employee.departmentName);			
			$('#jobTitleNameDashboard').html(data.Employee.jobTitleName);	

		},
		error : function(data) {
			alert('error: ' + data);
			isSuccessful = false;
		}
	});

}

function displayEmployeeSavedSVNames(empId) {
	var oAjaxCall = $.ajax({
		type : "GET",
		url : "/hris/GetEmployeeSupervisorAction",
		data : "empId=" + empId,
		cache : false,
		async : false,
		dataType : "json",
		success : function(data) {
			
			$('#superVisor1FullName').val(data.supervisor1FN);
			$('#superVisor2FullName').val(data.supervisor2FN);
			$('#superVisor3FullName').val(data.supervisor3FN);
			
			$('#superVisor1Id').val(data.supervisor1Id);
			$('#superVisor2Id').val(data.supervisor2Id);
			$('#superVisor3Id').val(data.supervisor3Id);

		},
		error : function(data) {
			alert('error: ' + data);
			isSuccessful = false;
		}
	});

}

function getEmployeePayrollInfo(empId) {
	var oAjaxCall = $.ajax({
		type : "GET",
		url : "/hris/GetEmployeePayrollInfoAction",
		data : "empId=" + empId,
		cache : false,
		async : false,
		dataType : "json",
		success : function(data) {
			
			$('#monthlyRate').val(data.EmployeePayrollInfo.monthlyRate);
			$('#dailyRate').val(data.EmployeePayrollInfo.dailyRate);
			$('#hourlyRate').val(data.EmployeePayrollInfo.hourlyRate);			
			$('#foodAllowance').val(data.EmployeePayrollInfo.foodAllowance);			
			$('#cola').val(data.EmployeePayrollInfo.cola);
			$('#taxShield').val(data.EmployeePayrollInfo.taxShield);
			$('#transAllowance').val(data.EmployeePayrollInfo.transAllowance);			
			$('#payrollType').val(data.EmployeePayrollInfo.payrollType);
			$('#ban').val(data.EmployeePayrollInfo.ban);
			$('#bankNameBan').val(data.EmployeePayrollInfo.bankNameBan);
			$('#shiftingScheduleDropDownID').val(data.EmployeePayrollInfo.shiftingScheduleId);
			
			$('#gsisEmployeeShare').val(data.EmployeePayrollInfo.gsisEmployeeShare);
			$('#gsisEmployerShare').val(data.EmployeePayrollInfo.gsisEmployerShare);
			$('#philhealthEmployeeShare').val(data.EmployeePayrollInfo.philhealthEmployeeShare);
			$('#philhealthEmployerShare').val(data.EmployeePayrollInfo.philhealthEmployerShare);
			$('#pagibigEmployeeShare').val(data.EmployeePayrollInfo.pagibigEmployeeShare);
			$('#pagibigEmployerShare').val(data.EmployeePayrollInfo.pagibigEmployerShare);

			
			

		},
		error : function(data) {
			alert('error: ' + data);
			isSuccessful = false;
		}
	});
}

function saveEmployeePayrollInfo() {

	var oAjaxCall = $.ajax({
		type : "POST",
		url : "/hris/SaveEmployeePayrollInfoAction",
		data : JSON.stringify($("#savePayrollInfoForm").serializeObject()),
		cache : false,
		async : true,
		dataType : "json",
		success : function(data) {		
			alert("Employee Payroll Information Successfully Saved.");
		},
		error : function(data) {
			obj = JSON.parse(data);
			alert (obj.count);
			alert('error: ' + data);
			//isSuccessful = false;
		}
	});

	//return isSuccessful;
}

function updateEmployee() {
	//var departmentId = document.getElementById("departmentId").value;
	var empId = document.getElementById("empId").value;
	var oAjaxCall = $.ajax({
		type : "POST",
		url : "/hris/UpdateEmployeeAction",
		data : JSON.stringify($("#addEmployeeForm").serializeObject()),
		cache : false,
		async : true,
		dataType : "json",
		success : function(data) {
			
			if(data == 0) {
				alert("Username already exist. Please use a different username.");
			} else {
				indexEmployee();
				alert("Employee Personal Information Successfully Saved.");				
			}
			
			

			// if(saveEmployeePayrollInfo()){
			
			// } else {
			// alert("Employee Payroll Info has invalid values please check.");
			// }

		},
		error : function(data) {
			//console.log(data);  //use this in order to find out [Object Object]
			alert('error: ' + data.responseText); //please see UpdateEmployeeAction for usage
		}
	});
}

function saveEmployee() {
	//var departmentId = document.getElementById("departmentId").value;
	//alert("saveEmployee");
	//var empId = document.getElementById("empId").value;
	var oAjaxCall = $.ajax({
		type : "POST",
		url : "/hris/SaveEmployeeAction",
		data : JSON.stringify($("#addEmployeeForm").serializeObject()),
		cache : false,
		async : false,
		dataType : "json",
		success : function(data) {
			// alert(JSON.stringify($("#addEmployeeForm").serializeObject()));
			if(data == 0) {
				alert("Username already exist. Please use a different username.");
			} else {
				indexEmployee();
				alert("Employee Information Successfully Saved.");
				$(location).attr('href', '/hris/updateEmployee.jsp?empId=' + data);
			}
			
		},
		error : function(data) {
			alert('Error: ' + data);

		}
	});	


}


function renderCBA(empId) {
	//for cba start
    
	 $('#competencyTable').jtable({
           title: 'CBA',
           actions: {
               listAction:		'/hris/GetAllEmployeeCBAAction?empId='+empId,
               createAction:	'/hris/SaveEmployeeCBAAction?empId='+empId,
               updateAction:	'/hris/UpdateEmployeeCBAAction'
               
           },
           fields: {
           	cbaId: {
                   key: true,
                   list: false
               },		                
           	empId: {
                   key: true,
                   list: false
               },
           	performanceYear: {
           		title: 'Perf. Year',
           		create: true,
           		edit: true,
                   list: true,
                   options:  ['2015','2016','2017','2018','2019','2020','2021','2022','2023'],
               }, 
               myInputs: {
                   title: 'My Inputs',
                   type: 'textarea',
                   create: true,
                   edit : true 
               },
               remarks: {
                   title: 'Remarks',
                   type: 'textarea',
                   create: true,
                   edit : true 
               }              
           }
       });
       $('#competencyTable').jtable('load');
   //for cba end
	
}

