//dashboard

function showMemoTable(empId) {	

	var oAjaxCall = $.ajax({
		type : "POST",
		url : "GetEmployeeMemoByToRecipientAction?toRecipientEmpId=" + empId,
		cache : false,
		async : false,
		dataType : "json",
		success : function(data) {
			if (data.Records.length) {
				
				var items = "<div class='headerDashboardTable'>Memo List</div>";
				items += "<table id='box-table-a' summary='Employee Pay Sheet' width='420px;'>";
				items += "<thead>";
				items += "<tr>";
				items += "<th scope='col'>Subject</th>";
				items += "<th scope='col'>From</th>";
				items += "<th scope='col'>Message</th>";
				items += "</tr>";
				items += "</thead>";
				items += "<tbody>";
			
			
				$.each(data.Records, function(i, item) {
					items += "<tr>";
					items += "<td>" + item.subject + "</td>";
					items += "<td>" + item.fromSender + "</td>";
					items += "<td>" + item.message + "</td>";
					items += "</tr>";
				});		
				
				items += "</tbody>";			
				items += "</table>";
				
				$('#memoTable').html(items);
			}
			;
			
			

		},
		error : function(data) {
			alert('error: showMemoTable' + data);
		}

	});
};

function showNotificationTable(empId) {	

	var oAjaxCall = $.ajax({
		type : "POST",
		url : "GetEmpNotificationByToRecipientAction?toRecipientEmpId=" + empId,
		cache : false,
		async : false,
		dataType : "json",
		success : function(data) {
			
			if (data.Records.length) {
				var items = "<div class='headerDashboardTable'>Notification List</div>";
				items += "<table id='newspaper-a' summary='Employee Pay Sheet' width='440px;'>";
				items += "<thead>";
				items += "<tr>";
				items += "<th scope='col'>Subject</th>";
				items += "<th scope='col'>From</th>";
				items += "<th scope='col'>Message</th>";
				items += "</tr>";
				items += "</thead>";
				items += "<tbody>";
			
			
				$.each(data.Records, function(i, item) {
					items += "<tr>";
					items += "<td>" + item.subject + "</td>";
					items += "<td>" + item.fromSender + "</td>";
					items += "<td>" + item.message + "</td>";
					items += "</tr>";
				});			
				
				items += "</tbody>";			
				items += "</table>";
				
				$('#notificationTable').html(items);
			}
			;
			
			

		},
		error : function(data) {
			alert('error: showNotificationTable' + data);
		}

	});
};

function showLeaveTable(empId) {	
	var oAjaxCall = $.ajax({
		type : "POST",
		url : "GetAllLeaveAction?empId=" + empId,
		cache : false,
		async : false,
		dataType : "json",
		success : function(data) {
			if (data.Records.length) {
				var items = "<div class='headerDashboardTable'>Filed Leave List</div>";	
				items += "<table id='hor-minimalist-b' summary='Employee Pay Sheet' width='890px;'>";
				items += "<thead>";
				items += "<tr>";
				items += "<th scope='col'>Leave Type</th>";
				items += "<th scope='col'>Date From</th>";
				items += "<th scope='col'>Date To</th>";
				items += "<th scope='col'># of Days</th>";			
				items += "<th scope='col'>Status</th>";
				items += "</tr>";
				items += "</thead>";
				items += "<tbody>";
			
			
				$.each(data.Records, function(i, item) {
					items += "<tr>";
					items += "<td>" + item.leaveType + "</td>";
					items += "<td>" + item.dateFrom + "</td>";
					items += "<td>" + item.dateTo + "</td>";
					items += "<td>" + item.noDays + "</td>";
					items += "<td>";
					
					if(item.status == 0){
						items += "FOR SUPERVISOR APPROVAL"
					}
					
					if(item.status == 1){
						items += "NOT APPROVED"
					}
					
					if(item.status == 2){
						items += "FOR HR APPROVAL"
					}
					
					if(item.status == 3){
						items += "APPROVED"
					}
					
					items += "</td>";
					
					items += "</tr>";
				});		
				
				items += "</tbody>";			
				items += "</table>";
				
				$('#leaveTable').html(items);
				
			}
			;
			
			

		},
		error : function(data) {
			alert('error: showLeaveTable' + data);
		}

	});
};


function showLeaveBalanceTable(empId) {	
	var oAjaxCall = $.ajax({
		type : "POST",
		url : "GetAllLeaveBalanceAction",
		cache : false,
		async : false,
		dataType : "json",
		success : function(data) {
			var items = "<table id='hor-minimalist-b' summary='' width='890px;'>";
			items += "<thead>";
			items += "<tr>";
			items += "<th scope='col'>Category</th>";
			items += "<th scope='col'>Count</th>";
			items += "<th scope='col'>&nbsp</th>";
			items += "</tr>";
			items += "</thead>";
			items += "<tbody>";
			
			if (data.Records.length) {
				$.each(data.Records, function(i, item) {
					items += "<tr>";
					items += "<td>" + item.category + "</td>";
					items += "<td>" + item.count + "</td>";
					items += "<td>";				

					
					items += "</td>";
					
					items += "</tr>";
				});				
			}
			;
			
			items += "</tbody>";			
			items += "</table>";
			
			$('#leaveBalanceTable').html(items);

		},
		error : function(data) {
			alert('error: showLeaveBalanceTable' + data);
		}

	});
};

function showTrainingTable(empId) {	

	var oAjaxCall = $.ajax({
		type : "POST",
		url : "GetEmployeeTrainingAction?empId=" + empId,
		cache : false,
		async : false,
		dataType : "json",
		success : function(data) {
			if (data.Records.length) {
				var items = "<div class='headerDashboardTable'>Trainings &amp; Seminars</div>";
				items += "<table id='box-table-a' summary='Employee Pay Sheet' width='890px;'>";
				items += "<thead>";
				items += "<tr>";
				items += "<th scope='col'>Name of Event</th>";
				items += "<th scope='col'>Date From</th>";
				items += "<th scope='col'>Date To</th>";						
				items += "<th scope='col'>Provider</th>";
				items += "</tr>";
				items += "</thead>";
				items += "<tbody>";
			
			
				$.each(data.Records, function(i, item) {
					items += "<tr>";
					items += "<td>" + item.titleActivity + "</td>";
					items += "<td>" + item.dateFrom + "</td>";
					items += "<td>" + item.dateTo + "</td>";
					items += "<td>" + item.provider + "</td>";					
					items += "</tr>";
				});		
				
				items += "</tbody>";			
				items += "</table>";
				
				$('#trainingTable').html(items);
			}
			;
			
			

		},
		error : function(data) {
			alert('error: showTrainingTable' + data);
		}

	});
};

function showAttendanceTable(empId) {	

	var oAjaxCall = $.ajax({
		type : "POST",
		url : "GetTimeEntryByEmpIdAction?empId=" + empId,
		cache : false,
		async : false,
		dataType : "json",
		success : function(data) {
			var items = "<table id='box-table-a' summary='Employee Pay Sheet' width='890px;'>";
			items += "<thead>";
			items += "<tr>";
			items += "<th scope='col'>Date</th>";
			items += "<th scope='col'>Schedule</th>";
			items += "<th scope='col'>Time In</th>";
			items += "<th scope='col'>Time Out</th>";			
			items += "<th scope='col'>&nbsp;</th>";
			items += "</tr>";
			items += "</thead>";
			items += "<tbody>";
			
			if (data.Records.length) {
				$.each(data.Records, function(i, item) {
					items += "<tr>";
					items += "<td>" + item.scheduleDate + "</td>";
					items += "<td>" + item.shiftScheduleDesc + "</td>";
					items += "<td>";
					
					if(item.shiftScheduleId == 2000){
						items +=  "-- Paid - Rest Day --";
						items += "</td>";  
						items += "<td>";  		
						items +=  "-- Paid - Rest Day --";
						items += "</td>";  
						items += "<td>"; 
						items += "&nbsp;";
					} else if(item.shiftScheduleId == 2001){
						items +=  "-- Unpaid - Rest Day --";
						items += "</td>";  
						items += "<td>";  		
						items +=  "-- Unpaid - Rest Day --";
						items += "</td>";  
						items += "<td>"; 
						items += "&nbsp;";
					} else {
						if (item.timeIn) {
							items += item.timeIn;						
						} else {
							items +=  "-- No Time In --";
						}
						items += "</td>";  
						items += "<td>";  					
						if (item.timeOut) {
							items += item.timeOut;
						} else {					
							items +=  "-- No Time Out --";
						}
						items += "</td>";  
						items += "<td>";  					
						if (item.timeIn) {
							if (item.timeOut) {		
								items += "&nbsp;";
							} else {
								items += "<a href='/hris/timeEntryDispute.jsp?empId=" + item.empId + "&shiftScheduleId=" + item.shiftScheduleId + "&timeEntryId=" + item.timeEntryId + "&clockDate=" + item.scheduleDate + "'>Dispute</a>";
							}
							
							
						} else {
							items += "<a href='/hris/timeEntryDispute.jsp?empId=" + item.empId + "&shiftScheduleId=" + item.shiftScheduleId + "&timeEntryId=" + item.timeEntryId + "&clockDate=" + item.scheduleDate + "'>Dispute</a>";
							
						}
					}
					
					
					items += "</td>";
					
					
					items += "</tr>";
				});				
			}
			;
			
			items += "</tbody>";			
			items += "</table>";
			
			$('#attendanceTable').html(items);

		},
		error : function(data) {
			alert('error: showAttendanceTable' + data);
		}

	});
};

function showAttendanceDisputeTable(empId) {	

	var oAjaxCall = $.ajax({
		type : "POST",
		url : "GetTimeEntryDisputeByEmpIdAction?empId=" + empId,
		cache : false,
		async : false,
		dataType : "json",
		success : function(data) {
			if (data.Records.length) {
				var items = "<div class='headerDashboardTable'>Attendance Dispute List</div>";
				items += "<table id='hor-minimalist-b' summary='Employee Pay Sheet' width='890px;'>";
				items += "<thead>";
				items += "<tr>";
				items += "<th scope='col'>Date</th>";
				items += "<th scope='col'>Schedule</th>";
				items += "<th scope='col'>Time In</th>";
				items += "<th scope='col'>Time Out</th>";			
				items += "<th scope='col'>&nbsp;</th>";
				items += "</tr>";
				items += "</thead>";
				items += "<tbody>";
			
			
				$.each(data.Records, function(i, item) {
					items += "<tr>";
					items += "<td>" + item.scheduleDate + "</td>";
					items += "<td>" + item.shiftScheduleDesc + "</td>";
					items += "<td>";
					
					if(item.shiftScheduleId == 2000){
						items +=  "-- Paid - Rest Day --";
						items += "</td>";  
						items += "<td>";  		
						items +=  "-- Paid - Rest Day --";
						items += "</td>";  
						items += "<td>"; 
						items += "&nbsp;";
					} else if(item.shiftScheduleId == 2001){
						items +=  "-- Unpaid - Rest Day --";
						items += "</td>";  
						items += "<td>";  		
						items +=  "-- Unpaid - Rest Day --";
						items += "</td>";  
						items += "<td>"; 
						items += "&nbsp;";
					} else {
						if (item.timeIn) {
							items += item.timeIn;						
						} else {
							items +=  "-- No Time In --";
						}
						items += "</td>";  
						items += "<td>";  					
						if (item.timeOut) {
							items += item.timeOut;
						} else {					
							items +=  "-- No Time Out --";
						}
						items += "</td>";  
						items += "<td>";  					
						if (item.approvalStatus == "P") {
							items +=  "-- PENDING SUPERVISOR APPROVAL --";							
						} else if (item.approvalStatus == "SA") {
							items +=  "-- PENDING HR APPROVAL --";							
						} else {
							items +=  "-- APPROVED --";	
						}
					}
					
					
					items += "</td>";
					
					
					items += "</tr>";
				});		
				
				items += "</tbody>";			
				items += "</table>";
				
				$('#attendanceDisputeTable').html(items);
				
			}
			;
			
			

		},
		error : function(data) {
			alert('error: showAttendanceDisputeTable' + data);
		}

	});
};

function showAttendanceDisputeStaffTable(empId) {	

	var oAjaxCall = $.ajax({
		type : "POST",
		url : "GetTimeEntryDisputeStaffByEmpIdAction?empId=" + empId,
		cache : false,
		async : false,
		dataType : "json",
		success : function(data) {
			if (data.Records.length) {
				var items = "<div class='headerDashboardTable'>My Staff Time Entry Dispute List</div>";
				items += "<table id='hor-minimalist-b' summary='Employee Pay Sheet' width='890px;'>";
				items += "<thead>";
				items += "<tr>";
				items += "<th scope='col'>Name</th>";
				items += "<th scope='col'>Date</th>";
				items += "<th scope='col'>Schedule</th>";
				items += "<th scope='col'>Time In</th>";
				items += "<th scope='col'>Time Out</th>";			
				items += "<th scope='col'>&nbsp;</th>";
				items += "</tr>";
				items += "</thead>";
				items += "<tbody>";
			
			
				$.each(data.Records, function(i, item) {
					items += "<tr>";
					items += "<td>" + item.empName + "</td>";
					items += "<td>" + item.scheduleDate + "</td>";
					items += "<td>" + item.shiftScheduleDesc + "</td>";
					items += "<td>";
					
					if(item.shiftScheduleId == 2000){
						items +=  "-- Paid - Rest Day --";
						items += "</td>";  
						items += "<td>";  		
						items +=  "-- Paid - Rest Day --";
						items += "</td>";  
						items += "<td>"; 
						items += "&nbsp;";
					} else if(item.shiftScheduleId == 2001){
						items +=  "-- Unpaid - Rest Day --";
						items += "</td>";  
						items += "<td>";  		
						items +=  "-- Unpaid - Rest Day --";
						items += "</td>";  
						items += "<td>"; 
						items += "&nbsp;";
					} else {
						if (item.timeIn) {
							items += item.timeIn;						
						} else {
							items +=  "-- No Time In --";
						}
						items += "</td>";  
						items += "<td>";  					
						if (item.timeOut) {
							items += item.timeOut;
						} else {					
							items +=  "-- No Time Out --";
						}
						items += "</td>";  
						items += "<td>";  					
						if (item.approvalStatus == "P") {
							items +=  "<div style='color: red; cursor: pointer;' id='resolveButton' onClick='approveTimeEntry(" + item.timeEntryDisputeId + ");'>APPROVE TIME ENTRY</div>";							
						} else if (item.approvalStatus == "SA") {
							items +=  "-- PENDING HR APPROVAL --";							
						} else {
							items +=  "-- APPROVED --";	
						}
					}
					
					
					items += "</td>";
					
					
					items += "</tr>";
				});			
				
				items += "</tbody>";			
				items += "</table>";
				
				$('#attendanceDisputeStaffTable').html(items);
				
			}
			;
			
			

		},
		error : function(data) {
			alert('error showAttendanceDisputeStaffTable: ' + data);
		}

	});
};

function showAttendanceDisputeStaffForHRTable(empId) {	

	var oAjaxCall = $.ajax({
		type : "POST",
		url : "GetTimeEntryDisputeHRAction",
		cache : false,
		async : false,
		dataType : "json",
		success : function(data) {
			var items = "<table id='hor-minimalist-b' summary='Employee Pay Sheet' width='890px;'>";
			items += "<thead>";
			items += "<tr>";
			items += "<th scope='col'>Name</th>";
			items += "<th scope='col'>Date</th>";
			items += "<th scope='col'>Schedule</th>";
			items += "<th scope='col'>Time In</th>";
			items += "<th scope='col'>Time Out</th>";			
			items += "<th scope='col'>&nbsp;</th>";
			items += "</tr>";
			items += "</thead>";
			items += "<tbody>";
			
			if (data.Records.length) {
				$.each(data.Records, function(i, item) {
					items += "<tr>";
					items += "<td>" + item.empName + "</td>";
					items += "<td>" + item.scheduleDate + "</td>";
					items += "<td>" + item.shiftScheduleDesc + "</td>";
					items += "<td>";
					
					if(item.shiftScheduleId == 2000){
						items +=  "-- Paid - Rest Day --";
						items += "</td>";  
						items += "<td>";  		
						items +=  "-- Paid - Rest Day --";
						items += "</td>";  
						items += "<td>"; 
						items += "&nbsp;";
					} else if(item.shiftScheduleId == 2001){
						items +=  "-- Unpaid - Rest Day --";
						items += "</td>";  
						items += "<td>";  		
						items +=  "-- Unpaid - Rest Day --";
						items += "</td>";  
						items += "<td>"; 
						items += "&nbsp;";
					} else {
						if (item.timeIn) {
							items += item.timeIn;						
						} else {
							items +=  "-- No Time In --";
						}
						items += "</td>";  
						items += "<td>";  					
						if (item.timeOut) {
							items += item.timeOut;
						} else {					
							items +=  "-- No Time Out --";
						}
						items += "</td>";  
						items += "<td>";  					
						if (item.approvalStatus == "P") {
							items +=  "<div style='color: red; cursor: pointer;' id='resolveButton' onClick='approveTimeEntry(" + item.timeEntryDisputeId + ");'>APPROVE TIME ENTRY</div>";							
						} else if (item.approvalStatus == "SA") {
							items +=  "<div style='color: red; cursor: pointer;' id='resolveButton' onClick='approveTimeEntry(" + item.timeEntryDisputeId + ");'>APPROVE TIME ENTRY</div>";						
						} else {
							items +=  "-- APPROVED --";	
						}
					}
					
					
					items += "</td>";
					
					
					items += "</tr>";
				});				
			}
			;
			
			items += "</tbody>";			
			items += "</table>";
			
			$('#attendanceDisputeStaffTable').html(items);

		},
		error : function(data) {
			alert('error showAttendanceDisputeStaffForHRTable: ' + data);
		}

	});
};


//common dropdown



function populatePayrollPeriod() {

	var oAjaxCall = $.ajax({
		type : "POST",
		url : "/hris/GetPayrollPeriodAction",
		cache : false,
		async : false,
		dataType : "json",
		success : function(data) {
			var items = '<option value=""></option>';
			if (data.Records.length) {
				$.each(data.Records, function(i, item) {
					items += "<option value='" + item.payrollPeriodId + "'>"
							+ item.fromDate + " to " + item.toDate + "</option>";
				});
				$('#payrollPeriodDropDownID').html(items);
			}
			;

		},
		error : function(data) {
			alert('error: populatePayrollPeriod' + data);
		}

	});
};

function populateEmployeeCheckBoxes(superVisorId) {

	var oAjaxCall = $.ajax({
		type : "POST",
		url : "/hris/GetEmployeeListBySupervisorIdAction?superVisorId=" + superVisorId,
		cache : false,
		async : false,
		dataType : "json",
		success : function(data) {
			var items = "";
			if (data.Records.length) {
				$.each(data.Records, function(i, item) {
					items += "<input type='checkbox' class='checkbox1' name='empId' value='" + item.empId + "'>"	+ item.firstname + ", " + item.lastname + "<br>"					
				});
				$('#scroller').html(items);
			}
			;

		},
		error : function(data) {
			alert('error: populateEmployeeCheckBoxes' + data);
		}

	});
};

function populateJobTitleDropDown() {

	var oAjaxCall = $.ajax({
		type : "POST",
		url : "/hris/GetAllJobTitleAction",
		cache : false,
		async : false,
		dataType : "json",
		success : function(data) {
			var items = '<option value=""></option>';
			if (data.Records.length) {
				$.each(data.Records, function(i, item) {
					items += "<option value='" + item.jobTitleId + "'>"
							+ item.jobTitle + "</option>";
				});
				$('#jobTitleDropDownID').html(items);
			}
			;

		},
		error : function(data) {
			alert('error: populateJobTitleDropDown' + data);
		}

	});
};

function populateCityDropDown() {

	var oAjaxCall = $.ajax({
		type : "POST",
		url : "/hris/GetAllCityAction",
		cache : false,
		async : false,
		dataType : "json",
		success : function(data) {
			var items = '<option value=""></option>';
			if (data.Records.length) {
				$.each(data.Records, function(i, item) {
					items += "<option value='" + item.cityId + "'>"
							+ item.cityName + "</option>";
				});
				$('#cityDropDownID').html(items);
			}
			;

		},
		error : function(data) {
			alert('error: populateCityDropDown' + data);
		}

	});
};

function populateShiftingScheduleDropDown() {

	var oAjaxCall = $.ajax({
		type : "POST",
		url : "/hris/GetAllShiftingSchedule",
		cache : false,
		async : false,
		dataType : "json",
		success : function(data) {
			var items = '<option value=""></option>';
			items += '<option value="2000">Paid - Rest Day</option>';
			items += '<option value="2001">Unpaid - Rest Day</option>';
			if (data.Records.length) {
				$.each(data.Records, function(i, item) {
					items += "<option value='" + item.shiftingScheduleId + "'>"
							+ item.description + "</option>";
				});
				
				$('#shiftingScheduleDropDownID').html(items);
			}
			;

		},
		error : function(data) {
			alert('error: populateShiftingScheduleDropDown' + data);
		}

	});
};



function populateCountryDropDown() {

	var oAjaxCall = $.ajax({
		type : "POST",
		url : "/hris/GetAllCountryAction",
		cache : false,
		async : false,
		dataType : "json",
		success : function(data) {
			var items = '<option value=""></option>';
			if (data.Records.length) {
				$.each(data.Records, function(i, item) {
					items += "<option value='" + item.countryId + "'>"
							+ item.countryName + "</option>";
				});
				$('#countryDropDownID').html(items);
			}
			;

		},
		error : function(data) {
			alert('error: populateCountryDropDown' + data);
		}

	});
};

function populateDepartmentDropDown() {

	var oAjaxCall = $.ajax({
		type : "POST",
		url : "/hris/GetAllDepartmentAction",
		cache : false,
		async : false,
		dataType : "json",
		success : function(data) {
			var items = '<option value=""></option>';
			if (data.Records.length) {
				$.each(data.Records, function(i, item) {
					items += "<option value='" + item.departmentId + "'>"
							+ item.departmentName + "</option>";
				});
				$('#departmentDropDownID').html(items);
			}
			;

		},
		error : function(data) {
			alert('error: populateDepartmentDropDown' + data);
		}

	});
};

function populateDivisionDropDown() {

	var oAjaxCall = $.ajax({
		type : "POST",
		url : "/hris/GetAllDivisionAction",
		cache : false,
		async : false,
		dataType : "json",
		success : function(data) {
			var items = '<option value=""></option>';
			if (data.Records.length) {
				$.each(data.Records, function(i, item) {
					items += "<option value='" + item.divisionId + "'>"
							+ item.divisionName + "</option>";
				});
				$('#divisionDropDownID').html(items);
			}
			;

		},
		error : function(data) {
			alert('error: populateDivisionDropDown' + data);
		}

	});
};

function populateEmployeeTypeDropDown() {

	var oAjaxCall = $.ajax({
		type : "POST",
		url : "/hris/GetAllEmployeeTypeAction",
		cache : false,
		async : false,
		dataType : "json",
		success : function(data) {
			var items = '<option value=""></option>';
			if (data.Records.length) {
				$.each(data.Records, function(i, item) {
					items += "<option value='" + item.employeeTypeId + "'>"
							+ item.employeeTypeName + "</option>";
				});
				$('#employeeTypeDropDownID').html(items);
			}
			;

		},
		error : function(data) {
			alert('error: populateEmployeeTypeDropDown' + data);
		}

	});
};

function populateLeaveTypeDropDown() {

	var oAjaxCall = $.ajax({
		type : "POST",
		url : "/hris/GetAllLeaveTypeAction",
		cache : false,
		async : false,
		dataType : "json",
		success : function(data) {
			var items = '<option value=""></option>';
			if (data.Records.length) {
				$.each(data.Records, function(i, item) {
					items += "<option value='" + item.leaveTypeId + "'>"
							+ item.leaveTypeId + "</option>";
				});
				$('#leaveTypeDropDownID').html(items);
			}
			;

		},
		error : function(data) {
			alert('error: populateLeaveTypeDropDown' + data);
		}

	});
};

function populateLoanTypeDropDown() {

	var oAjaxCall = $.ajax({
		type : "POST",
		url : "/hris/GetAllLoanTypeAction",
		cache : false,
		async : false,
		dataType : "json",
		success : function(data) {
			var items = '<option value=""></option>';
			if (data.Records.length) {
				$.each(data.Records, function(i, item) {
					items += "<option value='" + item.loanTypeId + "'>"
							+ item.loanTypeName + "</option>";
				});
				$('#loanTypeDropDownID').html(items);
			}
			;

		},
		error : function(data) {
			alert('error: populateLoanTypeDropDown' + data);
		}

	});
};

function populateProvinceDropDown() {

	var oAjaxCall = $.ajax({
		type : "POST",
		url : "/hris/GetAllProvinceAction",
		cache : false,
		async : false,
		dataType : "json",
		success : function(data) {
			var items = '<option value=""></option>';
			if (data.Records.length) {
				$.each(data.Records, function(i, item) {
					items += "<option value='" + item.provinceId + "'>"
							+ item.provinceName + "</option>";
				});
				$('#provinceDropDownID').html(items);
			}
			;

		},
		error : function(data) {
			alert('error: populateProvinceDropDown' + data);
		}

	});
};



$(function() {
	$(".useDPicker").datepicker({
		changeMonth : true,
		changeYear : true,
		dateFormat : 'yy-mm-dd',
		yearRange : '1910:2100',
		beforeShow: function(input, inst){	            	    	
		   $(".ui-datepicker").css('font-size', 11);
		}
	});

});

$(function() {
	$("#tabs-nohdr").tabs();
});



/*
Copyright 2011, Assist Plus
JavaScript Name: common.js
Develop By: Ian Orozco
Date: May 31, 2011
*/

// ----------------------------------------------------------------- 
// Function Name      : initLoginPage 
// Function Purpose   : Removes the menus, toolbar, address bar,  and status bar from 
//						the login page and fits it to specified size
// Passed Parameters  : <none> 
// Retuned Parameters : <none> 
// Author             : Ian Orozco
// ----------------------------------------------------------------- 
function initLoginPage(x, y) {
	
	if(!window.legallyOpened)
	{
		var pageURL = "../lps/login.jsp";
		var w = x;
		var h = y;
		var left = (screen.width/2)-(w/2);
		var top = (screen.height/2)-(h/2);
		var winParameters = "toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=yes,copyhistory=no,width="+w+",height="+h+",top="+top+",left="+left;
		
		// open new login window as popup
		var o = window.open(pageURL, "_blank", winParameters);
		o.legallyOpened = true;
		//dock(o);

		var ver = getInternetExplorerVersion();	
		
		// Close old login window
		if ( ver >= 7.0 )    {			
			window.open('', '_self', '');
			window.close();
		} else {			
			window.opener = top;
			window.close();
		}		
		
	}
}

// ----------------------------------------------------------------- 
// Function Name      : openNewPopUpWindowCloseParentWithScrollBar 
// Function Purpose   : Removes the menus, toolbar, address bar,  and status bar from 
//						the login page and fits it to specified size
// Passed Parameters  : URL, SCREEN WIDTH, SCREEN HEIGHT 
// Retuned Parameters : <none> 
// Author             : Ian Orozco
// ----------------------------------------------------------------
function openNewPopUpWindowCloseParentWithScrollBar(url, swidth, sheight){		
		
	var w = swidth;
	var h = sheight;
	var left = (screen.width/2)-(w/2);
	var top = (screen.height/2)-(h/2);		
		
	var winParameters = "toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=no,copyhistory=no,width="+w+",height="+h+",top="+top+",left="+left;
	var o = window.open(url, "_blank", winParameters);
	o.legallyOpened = true;
	
	var ver = getInternetExplorerVersion();	
	if ( ver >= 7.0 )    {			
		window.open('', '_self', '');
		window.close();
	} else {			
		window.opener = top;
		window.close();			
	}
}

// ----------------------------------------------------------------- 
// Function Name      : openNewPopUpWindowWithScrollBar 
// Function Purpose   : Removes the menus, toolbar, address bar,  and status bar from 
//						the login page and fits it to specified size
// Passed Parameters  : URL, SCREEN WIDTH, SCREEN HEIGHT 
// Retuned Parameters : <none> 
// Author             : Ian Orozco
// ----------------------------------------------------------------
function openNewPopUpWindowWithScrollBar(url, swidth, sheight){		
		
	var w = swidth;
	var h = sheight;
	var left = (screen.width/2)-(w/2);
	var top = (screen.height/2)-(h/2);		
		
	var winParameters = "toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=no,copyhistory=no,width="+w+",height="+h+",top="+top+",left="+left;
	var o = window.open(url, "_blank", winParameters);
	o.legallyOpened = true;
	
	var ver = getInternetExplorerVersion();	
	if ( ver >= 7.0 )    {			
		window.open('', '_self', '');
	} else {			
		window.opener = top;
	}
}

// ----------------------------------------------------------------- 
// Function Name      : openNewPopUpWindowCloseParent 
// Function Purpose   : Removes the menus, toolbar, address bar,  and status bar from 
//						the login page and fits it to specified size
// Passed Parameters  : URL, SCREEN WIDTH, SCREEN HEIGHT 
// Retuned Parameters : <none> 
// Author             : Ian Orozco
// ----------------------------------------------------------------	
function openNewPopUpWindowCloseParent(url, swidth, sheight){		
		
	var w = swidth;
	var h = sheight;
	var left = (screen.width/2)-(w/2);
	var top = (screen.height/2)-(h/2);		
		
	var winParameters = "toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=no,resizable=no,copyhistory=no,width="+w+",height="+h+",top="+top+",left="+left;
	var o = window.open(url, "_blank", winParameters);
	o.legallyOpened = true;
	
	var ver = getInternetExplorerVersion();	
	if ( ver >= 7.0 )    {			
		window.open('', '_self', '');
		window.close();		
	} else {			
		window.opener = top;
		window.close();					
	}

}

// ----------------------------------------------------------------- 
// Function Name      : openNewPopUpWindow 
// Function Purpose   : Removes the menus, toolbar, address bar,  and status bar from 
//						the login page and fits it to specified size
// Passed Parameters  : URL, SCREEN WIDTH, SCREEN HEIGHT 
// Retuned Parameters : <none> 
// Author             : Ian Orozco
// ----------------------------------------------------------------	
function openNewPopUpWindow(url, swidth, sheight){		
		
	var w = swidth;
	var h = sheight;
	var left = (screen.width/2)-(w/2);
	var top = (screen.height/2)-(h/2);		
		
	var winParameters = "toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=no,resizable=no,copyhistory=no,fullscreen=true,width="+w+",height="+h+",top="+top+",left="+left;
	var o = window.open(url, "_blank", winParameters);
	o.legallyOpened = true;
	
	
	var ver = getInternetExplorerVersion();	
	if ( ver >= 7.0 )    {			
		window.open('', '_self', '');
	} else {			
		window.opener = top;			
	}

}

//----------------------------------------------------------------- 
//Function Name      : openNewPopUpWindow 
//Function Purpose   : Removes the menus, toolbar, address bar,  and status bar from 
//						the login page and fits it to specified size
//Passed Parameters  : URL, SCREEN WIDTH, SCREEN HEIGHT 
//Retuned Parameters : <none> 
//Author             : Ian Orozco
//----------------------------------------------------------------	
function openNewPopUpWindowSelf(url, swidth, sheight){		
		
	var w = swidth;
	var h = sheight;
	var left = (screen.width/2)-(w/2);
	var top = (screen.height/2)-(h/2);		
		
	var winParameters = "toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=no,resizable=no,copyhistory=no,fullscreen=true,width="+w+",height="+h+",top="+top+",left="+left;
	var o = window.open(url, "_self", winParameters);
	o.legallyOpened = true;
	
	
	var ver = getInternetExplorerVersion();	
	if ( ver >= 7.0 )    {			
		window.open('', '_self', '');
	} else {			
		window.opener = top;			
	}

}

//-----------------------------------------------------------------
//Function Name      : getInternetExplorerVersion
//Function Purpose   : Returns the version of Internet Explorer or a -1
//                   (indicating the use of another browser).
//Passed Parameters  : The Select element id, the option id, and the input id
//Retuned Parameters : <none> 
//Author             : Ian Orozco
//-----------------------------------------------------------------
function getInternetExplorerVersion()

{
	var rv = -1; // Return value assumes failure.
	if (navigator.appName == 'Microsoft Internet Explorer')
	{
		var ua = navigator.userAgent;
		var re  = new RegExp("MSIE ([0-9]{1,}[\.0-9]{0,})");
		if (re.exec(ua) != null)
			rv = parseFloat( RegExp.$1 );
	}
	return rv;
}
