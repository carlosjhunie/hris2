<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
function indexEmployee() {
	var oAjaxCall = $
			.ajax({
				type : "POST",
				url : "http://<%=request.getServerName()%>:8983/solr/hris_employee/dataimport?command=full-import",
				cache : false,
				async : false,
				dataType : 'jsonp',
				jsonp : 'json.wrf',
				success : function(data) {
					// alert("Indexed");

				},
				error : function(data) {
					// alert('error: ' + data);
				}
			});

}

</script>
<!-- DIV Header -->
<div id="headerContainer">
	<div id="headerLogo">
		<div id="companyLogo"><img src="images/dai-logo2.png" width="250px" height="79px" style="padding:10px 0px 0px 10px; margin-left: auto; margin-right: 10px; float: left;" /></div>
		<div id="clock">HUMAN RESOURCE INFORMATION SYSTEM 1.0</div>				
	</div>
	<div class="cb"></div>
	<div id="navigation2">
		<ul>
			<li>	
				<img src="images/icon1.png" width="30px" height="30px" style="padding:10px 0px 0px 10px; margin-left: auto; margin-right: 10px; float: left;" />			
				<a href="#">					 
					My Account
				</a>
				<ul>
					<li><a href="employeeDashBoard.jsp">Dashboard</a></li>
					<li><a href="changePassword.jsp">Change Password</a></li>
					<li><a href="employeeReports.jsp">Employee Reports</a></li>
				</ul>
			</li>
			<c:if test="${not empty sessionScope.moduleAccess.fileManagementList }">
			<li>
				<img src="images/icon2.png" width="30px" height="30px" style="padding:10px 0px 0px 10px; margin-left: auto; margin-right: 10px; float: left;" />
				<a href="#">File Management</a>
				<ul>
					<c:forEach var="fm" items="${sessionScope.moduleAccess.fileManagementList}">
					<jsp:include page="jspf/${fm}.jspf"></jsp:include>
					</c:forEach>
					<!-- 
					<li><a href="fileMaintenanceJobTitle.jsp">Job Title</a></li>
					<li><a href="fileMaintenanceHoliday.jsp">Holidays</a></li>
					<li><a href="fileMaintenanceLoanType.jsp">Loan Type</a></li>
					<li><a href="fileMaintenanceLeaveType.jsp">Leave Type</a></li>					
					<li><a href="fileMaintenanceDepartment.jsp">Department</a></li>
					<li><a href="fileMaintenanceDivision.jsp">Division</a></li>
					<li><a href="fileMaintenanceCity.jsp">City</a></li>
					<li><a href="fileMaintenanceProvince.jsp">Province</a></li>
					<li><a href="fileMaintenanceCountry.jsp">Country</a></li>					 
					-->
				</ul>
			</li>
			</c:if>
			<c:if test="${not empty sessionScope.moduleAccess.employeeList }">
			<li>
				<img src="images/icon3.png" width="30px" height="30px" style="padding:10px 0px 0px 10px; margin-left: auto; margin-right: 10px; float: left;" />
				<a href="#">Employee</a>
				<ul>
					<c:forEach var="em" items="${sessionScope.moduleAccess.employeeList}">
					<jsp:include page="jspf/${em}.jspf"></jsp:include>
					</c:forEach>
					<!-- //use jsp param to pass empId
					<li><a href="employeeSearch.jsp">Employee</a></li>
					<li><a href="memo.jsp">Memo</a></li>
					<li><a href="notification.jsp">Notification</a></li>
					<li><a href="employeeLeaveEntry.jsp?empId=${sessionScope.employeeLoggedIn.empId}">File Leave</a></li>
					<li><a href="employeeOutOfOfficeEntry.jsp?empId=${sessionScope.employeeLoggedIn.empId}">File Out of Office</a></li>
					<li><a href="employeeOvertimeEntry.jsp?empId=${sessionScope.employeeLoggedIn.empId}">File Overtime</a></li>
					<li><a href="leaveReviewAndApproval.jsp?superVisorId=${sessionScope.employeeLoggedIn.empId}">Leave Approval</a></li>
					<li><a href="leaveReviewAndApprovalHR.jsp?superVisorId=${sessionScope.employeeLoggedIn.empId}">Leave Approval For HR</a></li>
					<li><a href="oooReviewAndApproval.jsp?superVisorId=${sessionScope.employeeLoggedIn.empId}">Out of Office Approval</a></li>
					<li><a href="overtimeReviewAndApproval.jsp?superVisorId=">Overtime Approval</a></li>
					 -->					
				</ul>
			</li>
			</c:if>
			<c:if test="${not empty sessionScope.moduleAccess.timeManagementList }">
			<li>
				<img src="images/icon4.png" width="30px" height="30px" style="padding:10px 0px 0px 10px; margin-left: auto; margin-right: 10px; float: left;" />
				
				<a href="#">					 
					Employee Time Management
				</a>
				<ul>
					<c:forEach var="tm" items="${sessionScope.moduleAccess.timeManagementList}">
					<jsp:include page="jspf/${tm}.jspf"></jsp:include>
					</c:forEach>
					<!-- 
					<li><a href="shiftingSchedule.jsp">Create Employee Shift</a></li>			
					<li><a href="timeEntryCalendar.jsp">Attendance Monitoring</a></li>
					<li><a href="employeeScheduleCalendar.jsp">Employee Schedule</a></li>
                    <li><a href="timeEntryDisputeApproval.jsp">Time Dispute HR Approval</a></li>
					-->
				</ul>
			</li>
			</c:if>
			<c:if test="${not empty sessionScope.moduleAccess.payrollList }">	
			<li>
				<img src="images/icon5.png" width="30px" height="30px" style="padding:10px 0px 0px 10px; margin-left: auto; margin-right: 10px; float: left;" />
				<a href="#">Payroll</a>
				<ul>
					<c:forEach var="py" items="${sessionScope.moduleAccess.payrollList}">
					<jsp:include page="jspf/${py}.jspf"></jsp:include>
					</c:forEach>
					<!-- 
                    <li><a href="generateAndLockPayroll.jsp">Generate/Lock Payroll</a></li>
					<li><a href="payrollPeriod.jsp">Payroll Period</a></li>					
					<li><a href="incomeEntry.jsp">Income and Benefits</a></li>
					<li><a href="deductionEntry.jsp">Deductions</a></li>
					<li><a href="caseRatePayment.jsp">Case Rate</a></li>
					<li><a href="professionalFee.jsp">Professional Fee</a></li>
					<li><a href="../hris/pdf/philhealth.pdf" target="blank">PHIC Contribution Table</a></li>						
					<li><a href="../hris/pdf/birTaxTable.pdf" target="blank">Tax Table</a></li>					 
                    -->
				</ul>
			</li>
			</c:if>
			<c:if test="${not empty sessionScope.moduleAccess.employeesLoanList }">
			<li>
				<img src="images/icon6.png" width="30px" height="30px" style="padding:10px 0px 0px 10px; margin-left: auto; margin-right: 10px; float: left;" />
				<a href="#">Employee's Loan</a>
				<ul>
					<c:forEach var="el" items="${sessionScope.moduleAccess.employeesLoanList}">
					<jsp:include page="jspf/${el}.jspf"></jsp:include>
					</c:forEach>
					<!-- 
					<li><a href="employeeLoanEntry.jsp">Loan and Payments</a></li>
					<li><a href="#">Loan Remittance</a></li>
					-->
                     
				</ul>
			</li>
			</c:if>
			<c:if test="${not empty sessionScope.moduleAccess.fileManagementList }">
			<li>
				<img src="images/icon7.png" width="30px" height="30px" style="padding:10px 0px 0px 10px; margin-left: auto; margin-right: 10px; float: left;" />
				<a href="reports.jsp">Payroll Reports</a>
				<!-- 
				<ul>
					<li><a href="#">Payslip</a></li>
					<li><a href="#">Payroll Register</a></li>
					<li><a href="#">Payroll Register Taxable Income</a></li>
					<li><a href="#">Year-End Bonus and Cash Gift</a></li>
					<li><a href="#">Case Rate</a></li>
					<li><a href="#">Professional Fee</a></li>					
					<li><a href="#">Payroll Register for 13 Month</a></li>
					<li><a href="#">Monthly Attendance and Overtime</a></li>
					<li><a href="#">Monthly Reports</a></li>
					<li><a href="#">Year to Date Reports</a></li>
				</ul>
				 -->
			</li>
			</c:if>			
			<li>
				<img src="images/icon8.png" width="30px" height="30px" style="padding:10px 0px 0px 10px; margin-left: auto; margin-right: 10px; float: left;" />
				<a href="LogoutAction">Logout</a>
			</li>
		</ul>
	</div>
</div>
<!-- DIV Header -->
