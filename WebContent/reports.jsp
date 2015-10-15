<%@ page language="java" contentType="text/html; charset=iso-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Expires" CONTENT="-1">
<title>File Maintenance Menu</title>

<script type="text/javascript" src="js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="jtables/jquery-ui-1.10.0.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/dai.css" />
<link rel="stylesheet" href="css/datePickerStyle.css">
<link href="jtables/jquery-ui.css" rel="stylesheet" type="text/css" />


<script type="text/javascript">
	function openApplicationWindow(appId){		
		var swidth = 400;
	    var sheight = 500;
	    var url = "";
	    var departmentId = document.getElementById("departmentId").value;	 
	    var empId = document.getElementById("empId").value;	
	    
	    if(appId == "1"){
	    	var dateFrom = document.getElementById("datepicker1").value;
	    	var dateTo = document.getElementById("datepicker2").value;
	    		    		    	
	    	url = "/hris/ViewCaseRateReport?dateFrom="+dateFrom+"&dateTo="+dateTo;
	    	swidth = screen.availWidth;
	    	sheight = screen.availHeight;	
	    	if ((isInvalid(dateFrom) || isInvalid(dateTo))===false) {
	    		openNewPopUpWindow(url, swidth, sheight)
	    	}
	    } else if(appId == "2"){
	    	var dateFrom = document.getElementById("datepicker3").value;
	    	var dateTo = document.getElementById("datepicker4").value;
	    	
	    	url = "/hris/ViewProfessionalFeeReport?dateFrom="+dateFrom+"&dateTo="+dateTo;
	    	swidth = screen.availWidth;
	    	sheight = screen.availHeight;
	    	if ((isInvalid(dateFrom) || isInvalid(dateTo))===false) {	    	
	    		openNewPopUpWindow(url, swidth, sheight)
	    	}
	    }  else if(appId == "3"){   //payroll register
	    	var ppId = document.getElementById("payrollPeriodId").value;
	    	url = "/hris/ViewPayrollRegisterReport?payrollPeriodId="+ppId;
	    	swidth = screen.availWidth;
	    	sheight = screen.availHeight;
	    	if (isInvalid(ppId)===false) {
	    		openNewPopUpWindow(url, swidth, sheight)
	    	}
	    }   else if(appId == "4"){	 //YTD payroll register	    	
	    	
	    	var dateFrom = document.getElementById("datepickerYTDPRFrom").value;
	    	var dateTo = document.getElementById("datepickerYTDPRTo").value;

	    	url = "/hris/ViewYTDPayrollRegisterReport?&fromLockedDate="+dateFrom+"&toLockedDate="+dateTo;
	    	swidth = screen.availWidth;
	    	sheight = screen.availHeight;

	    	if ((isInvalid(dateFrom) || isInvalid(dateTo))===false) {
	    		openNewPopUpWindow(url, swidth, sheight);
	    	}
	    }  else if(appId == "5"){  //employee attendance summary
	    	var dateFrom = document.getElementById("datepickerEASFrom").value;
	    	var dateTo = document.getElementById("datepickerEASTo").value;
	    	
	    	url = "/hris/ViewEmployeeAttendanceSummaryReport?dateFrom="+dateFrom+"&dateTo="+dateTo;
	    	swidth = screen.availWidth;
	    	sheight = screen.availHeight;
	    	
	    	if ((isInvalid(dateFrom) || isInvalid(dateTo))===false) {
	    		openNewPopUpWindow(url, swidth, sheight);
	    	}
	    }else if(appId == "9"){ //my leave history
	    	var dateFrom = document.getElementById("datepickerMLHFrom").value;
	    	var dateTo = document.getElementById("datepickerMLHTo").value;

	    	url = "/hris/ViewEmployeeLeaveHistoryReport?empId="+empId+"&dateFrom="+dateFrom+"&dateTo="+dateTo;
	    	swidth = screen.availWidth;
	    	sheight = screen.availHeight;

	    	if ((isInvalid(dateFrom) || isInvalid(dateTo))===false) {
	    		openNewPopUpWindow(url, swidth, sheight);
	    	}
	    }else if(appId == "11"){ //employee overtime report
	    	var dateFrom = document.getElementById("datepickerEOSRFrom").value;
	    	var dateTo = document.getElementById("datepickerEOSRTo").value;
	    	
	    	url = "/hris/ViewOvertimeSummaryReport?dateFrom="+dateFrom+"&dateTo="+dateTo;
	    	swidth = screen.availWidth;
	    	sheight = screen.availHeight;

	    	if ((isInvalid(dateFrom) || isInvalid(dateTo))===false) {
	    		openNewPopUpWindow(url, swidth, sheight);
	    	}
	    }
	    
	}
	
	function isInvalid(variabols) {
		if (typeof(variabols) === "undefined" || variabols === null || variabols === "") {
			return true;
		}
		return false;
	}
	
</script>
<script>
  $(function() {


    $( "input[id^='datepicker']").datepicker({
        changeMonth: true,
        changeYear: true,
        beforeShow: function(input, inst){	            	    	
            $(".ui-datepicker").css('font-size', 11);
     	  },
        dateFormat: 'mm/dd/yy',
  	  	yearRange: '1910:2100'
      });   
    

    populatePayrollPeriodDropDown();
    
  });
  
  
	function populatePayrollPeriodDropDown() {
		var oAjaxCall = $.ajax({
			type : "POST",
			url : "/hris/GetAllPayrollPeriodAction?includeGenerated=true",
			cache : false,
			async : false,
			dataType : "json",
			success : function(data) {
				var items = '<option value=""></option>';
				if (data.Records.length) {
					$.each(data.Records, function(i, item) {
						items += "<option value='" + item.payrollPeriodId + "'>"
								+ "PayCode: " + item.payrollCode + ", PayrollType: " + item.payrollType +  ", PayDate: " + item.payDate + "</option>";
					});
					$('#payrollPeriodId').html(items);
				}
				;

			},
			error : function(data) {
				alert('error: populatePayrollPeriodDropDown' + data);
			}

		});
	};
  </script>

</head>
<body>	
<div><c:import url="header.jsp" /></div>	
<%--input type="hidden" name="empId" id="empId" value="${user.empId}" />
<input type="hidden" name="departmentId" id="departmentId" value="${user.departmentId}" /--%>	

<input type="hidden" name="empId" id="empId" value="${employeeLoggedIn.empId}" />
<input type="hidden" name="departmentId" id="departmentId" value="${employeeLoggedIn.departmentId}" />	
	
		
<div id="content"> 
	<div id="reportsArea">							
				
		<div class="reportItem">				
			<div class="reportsName">CASE RATE PAYMENT SUMMARY REPORT</div>	
			<div class="dateArea">
				<div class="datepickerTxt">Date From</div>
				<div class="datepicker"><input type="text" id="datepicker1"></div>
				<div class="datepickerTxt">Date To</div>
				<div class="datepicker"><input type="text" id="datepicker2"></div>
			</div>		
			<div class="reportsIconButton">
				<div onclick="openApplicationWindow('1');"><img src="images/view.png" alt="alternate text" width="30px" height="30px" /></div>				
			</div>
			<%--div class="reportsIconButton">
				<div onclick="exportReport('1');"><img src="images/excel.png" alt="alternate text" width="30px" height="30px" /></div>				
			</div--%>
			<div class="reportsIconButton">
				<div onclick="openApplicationWindow('1');"><img src="images/pdf.png" alt="alternate text" width="30px" height="30px" /></div>				
			</div>
		</div>
		<div style="clear: both;"></div>
		<div class="reportItem">			
			<div class="reportsName">PROFESSIONAL FEE SUMMARY REPORT</div>
			<div class="dateArea">
				<div class="datepickerTxt">Date From</div>
				<div class="datepicker"><input type="text" id="datepicker3"></div>
				<div class="datepickerTxt">Date To</div>
				<div class="datepicker"><input type="text" id="datepicker4"></div>
			</div>			
			<div class="reportsIconButton">
				<div onclick="openApplicationWindow('2');"><img src="images/view.png" alt="alternate text" width="30px" height="30px" /></div>				
			</div>
			<%--div class="reportsIconButton">
				<div onclick="exportReport('2');"><img src="images/excel.png" alt="alternate text" width="30px" height="30px" /></div>				
			</div--%>
			<div class="reportsIconButton">
				<div onclick="openApplicationWindow('2');"><img src="images/pdf.png" alt="alternate text" width="30px" height="30px" /></div>				
			</div>
		</div>		
		<div style="clear: both;"></div>
		
		<%--Payroll Register #3--%>
		<div class="reportItem">				
			<div class="reportsName">PAYROLL REGISTER</div>	
			<div class="dateArea">
				<div class="datepickerTxt">PayPeriod</div>
				<div class="datepicker">				    	
					<select name="payrollPeriod" id="payrollPeriodId" style="width:420px; height:32px; background:#fff; border: 1px solid #52833b; margin-top: 5px; color: #A9A9A9;" ></select>	
				</div>
				
			</div>		
			<div class="reportsIconButton">
				<div onclick="openApplicationWindow('3');"><img src="images/view.png" alt="alternate text" width="30px" height="30px" /></div>				
			</div>
			<%--div class="reportsIconButton">
				<div onclick="exportReport('3');"><img src="images/excel.png" alt="alternate text" width="30px" height="30px" /></div>				
			</div--%>
			<div class="reportsIconButton">
				<div onclick="openApplicationWindow('3');"><img src="images/pdf.png" alt="alternate text" width="30px" height="30px" /></div>				
			</div>
		</div>
		<div style="clear: both;"></div>
		
		<%--YTD Payroll Register #4--%>
		<div class="reportItem">			
			<div class="reportsName">YTD PAYROLL REGISTER (FOR LOCKED)</div>
			<div class="dateArea">
				<div class="datepickerTxt">Date From</div>
				<div class="datepicker"><input type="text" id="datepickerYTDPRFrom"></div>
				<div class="datepickerTxt">Date To</div>
				<div class="datepicker"><input type="text" id="datepickerYTDPRTo"></div>
			</div>			
			<div class="reportsIconButton">
				<div onclick="openApplicationWindow('4');"><img src="images/view.png" alt="alternate text" width="30px" height="30px" /></div>				
			</div>
			<%--div class="reportsIconButton">
				<div onclick="exportReport('4');"><img src="images/excel.png" alt="alternate text" width="30px" height="30px" /></div>				
			</div--%>
			<div class="reportsIconButton">
				<div onclick="openApplicationWindow('4');"><img src="images/pdf.png" alt="alternate text" width="30px" height="30px" /></div>				
			</div>
		</div>
		
		
		<%--Employee Attendance Summary #5--%>
		<div class="reportItem">			
			<div class="reportsName">EMPLOYEE ATTENDANCE SUMMARY</div>
			<div class="dateArea">
				<div class="datepickerTxt">Date From</div>
				<div class="datepicker"><input type="text" id="datepickerEASFrom"></div>
				<div class="datepickerTxt">Date To</div>
				<div class="datepicker"><input type="text" id="datepickerEASTo"></div>
			</div>			
			<div class="reportsIconButton">
				<div onclick="openApplicationWindow('5');"><img src="images/view.png" alt="alternate text" width="30px" height="30px" /></div>				
			</div>
			<%--div class="reportsIconButton">
				<div onclick="exportReport('5');"><img src="images/excel.png" alt="alternate text" width="30px" height="30px" /></div>				
			</div--%>
			<div class="reportsIconButton">
				<div onclick="openApplicationWindow('5');"><img src="images/pdf.png" alt="alternate text" width="30px" height="30px" /></div>				
			</div>
		</div>
		<%--
		<div class="reportItem">			
			<div class="reportsName">PAYROLL REGISTER WITH TAXBLE INCOME</div>
			<div class="dateArea">
				<div class="datepickerTxt">Date From</div>
				<div class="datepicker"><input type="text" id="datepicker4443"></div>
				<div class="datepickerTxt">Date From</div>
				<div class="datepicker"><input type="text" id="datepicker4444"></div>
			</div>			
			<div class="reportsIconButton">
				<div onclick="openApplicationWindow('5');"><img src="images/view.png" alt="alternate text" width="30px" height="30px" /></div>				
			</div>
			<div class="reportsIconButton">
				<div onclick="exportReport('5');"><img src="images/excel.png" alt="alternate text" width="30px" height="30px" /></div>				
			</div>
			<div class="reportsIconButton">
				<div onclick="openApplicationWindow('5');"><img src="images/pdf.png" alt="alternate text" width="30px" height="30px" /></div>				
			</div>
		</div>
		--%>
		
		<%--My leave history #9--%>
		<div class="reportItem">			
			<div class="reportsName">MY LEAVE HISTORY</div>
			<div class="dateArea">
				<div class="datepickerTxt">Date From</div>
				<div class="datepicker"><input type="text" id="datepickerMLHFrom"></div>
				<div class="datepickerTxt">Date To</div>
				<div class="datepicker"><input type="text" id="datepickerMLHTo"></div>
			</div>			
			<div class="reportsIconButton">
				<div onclick="openApplicationWindow('9');"><img src="images/view.png" alt="alternate text" width="30px" height="30px" /></div>				
			</div>
			<%--div class="reportsIconButton">
				<div onclick="exportReport('9');"><img src="images/excel.png" alt="alternate text" width="30px" height="30px" /></div>				
			</div--%>
			<div class="reportsIconButton">
				<div onclick="openApplicationWindow('9');"><img src="images/pdf.png" alt="alternate text" width="30px" height="30px" /></div>				
			</div>
		</div>
		<div style="clear: both;"></div>
		
		<%--Employee Overtime #11--%>
		<div class="reportItem">
		<div class="reportsName">EMPLOYEE OVERTIME SUMM. REPORT</div>
			<div class="dateArea">
				<div class="datepickerTxt">Date From</div>
				<div class="datepicker"><input type="text" id="datepickerEOSRFrom"></div>
				<div class="datepickerTxt">Date To</div>
				<div class="datepicker"><input type="text" id="datepickerEOSRTo"></div>
			</div>			
			<div class="reportsIconButton">
				<div onclick="openApplicationWindow('11');"><img src="images/view.png" alt="alternate text" width="30px" height="30px" /></div>				
			</div>
			<%--div class="reportsIconButton">
				<div onclick="exportReport('11');"><img src="images/excel.png" alt="alternate text" width="30px" height="30px" /></div>				
			</div--%>
			<div class="reportsIconButton">
				<div onclick="openApplicationWindow('11');"><img src="images/pdf.png" alt="alternate text" width="30px" height="30px" /></div>				
			</div>
		</div>
		<div style="clear: both;"></div>						
	</div>
</div>
<div style=""><c:import url="footer.jsp" /></div>




	
		
</body>
</html>
