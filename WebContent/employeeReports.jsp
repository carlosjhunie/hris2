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
<script src="js/common.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		populatePayrollPeriod();		
	});	

	function openApplicationWindow(appId){		
		var swidth = 400;
	    var sheight = 500;
	    var url = "";
	    var empId = document.getElementById("empIdLoggedIn").value;	    
	    
	    if(appId == "1"){
	    	var payrollPeriodId = document.getElementById("payrollPeriodDropDownID").value;
	    	
	    		    		    	
	    	url = "/hris/ViewPayslipReport?payrollPeriodId="+payrollPeriodId+"&empId="+empId;
	    	swidth = screen.availWidth;
	    	sheight = screen.availHeight;	
	    	
	    	openNewPopUpWindow(url, swidth, sheight)
	    } else if(appId == "2"){
	    	var dateFrom = document.getElementById("datepicker1").value;
	    	var dateTo = document.getElementById("datepicker2").value;
	    	
	    	url = "/hris/ViewEmployeeLeaveHistoryReport?empId="+empId+"&dateFrom="+dateFrom+"&dateTo="+dateTo;
	    	swidth = screen.availWidth;
	    	sheight = screen.availHeight;

	    	if ((isInvalid(dateFrom) && isInvalid(dateTo))===false) {
	    		openNewPopUpWindow(url, swidth, sheight);
	    	}
	    	
	    	openNewPopUpWindow(url, swidth, sheight)
	    }
	    
	}
	
	
	function exportReport(appId){		
	    var url = "";
	    var empId = document.getElementById("empIdLoggedIn").value;	    
	    
	    if(appId == "1"){
	    	var payrollPeriodId = document.getElementById("payrollPeriodDropDownID").value;
	    	
	    	
	    	var oAjaxCall = $.ajax({
				type : "POST",
				url : "/hris/ExportPayslipReport",
				
				data: "departmentId=" + departmentId + "&dateFrom=" + dateFrom + "&dateTo=" + dateTo,
				cache : false,
				async : false,
				success : function(data) {
					
					alert("Export of PAYSLIP was SUCCESSFULLY saved in C:\\reports\\");			

				},
				error : function(data) {
					alert('error: ' + data);
				}

			});
	    		    		    	
	    	
	    } else if(appId == "2"){
	    	var dateFrom = document.getElementById("datepicker3").value;
	    	var dateTo = document.getElementById("datepicker4").value;
	    	
	    	var oAjaxCall = $.ajax({
				type : "POST",
				url : "/QUEUE/ExportEmployeeTimeReport",
				
				data: "departmentId=" + departmentId + "&dateFrom=" + dateFrom + "&dateTo=" + dateTo,
				cache : false,
				async : false,
				success : function(data) {
					
					alert("Export of EMPLOYEE TIME ENTRY REPORT was  SUCCESSFULLY saved in C:\\queReports\\");			

				},
				error : function(data) {
					alert('error: ' + data);
				}

			});
	    	
	    	
	    }
	    
	}
	
	
	
	
</script>
<script>
  $(function() {
    $( "#datepicker1" ).datepicker({
      changeMonth: true,
      changeYear: true,
      beforeShow: function(input, inst){	            	    	
          $(".ui-datepicker").css('font-size', 11);
   	  },
      dateFormat: 'mm/dd/yy',
	  yearRange: '1910:2100'
    });
    
    $( "#datepicker2" ).datepicker({
        changeMonth: true,
        changeYear: true,
        beforeShow: function(input, inst){	            	    	
            $(".ui-datepicker").css('font-size', 11);
     	  },
        dateFormat: 'mm/dd/yy',
  	    yearRange: '1910:2100'
      });
    
    $( "#datepicker3" ).datepicker({
        changeMonth: true,
        changeYear: true,
        beforeShow: function(input, inst){	            	    	
            $(".ui-datepicker").css('font-size', 11);
     	  },
        dateFormat: 'mm/dd/yy',
  	  	yearRange: '1910:2100'
      });
    
    $( "#datepicker4" ).datepicker({
        changeMonth: true,
        changeYear: true,
        beforeShow: function(input, inst){	            	    	
            $(".ui-datepicker").css('font-size', 11);
     	  },
        dateFormat: 'mm/dd/yy',
  	  	yearRange: '1910:2100'
      });
    
    $( "#datepicker5" ).datepicker({
        changeMonth: true,
        changeYear: true,
        beforeShow: function(input, inst){	            	    	
            $(".ui-datepicker").css('font-size', 11);
     	  },
        dateFormat: 'mm/dd/yy',
  	  	yearRange: '1910:2100'
      });
    
    $( "#datepicker6" ).datepicker({
        changeMonth: true,
        changeYear: true,
        beforeShow: function(input, inst){	            	    	
            $(".ui-datepicker").css('font-size', 11);
     	  },
        dateFormat: 'mm/dd/yy',
  	  	yearRange: '1910:2100'
      });
    
    $( "#datepicker7" ).datepicker({
        changeMonth: true,
        changeYear: true,
        beforeShow: function(input, inst){	            	    	
            $(".ui-datepicker").css('font-size', 11);
     	  },
        dateFormat: 'mm/dd/yy',
  	  	yearRange: '1910:2100'
      });
    
    $( "#datepicker8" ).datepicker({
        changeMonth: true,
        changeYear: true,
        beforeShow: function(input, inst){	            	    	
            $(".ui-datepicker").css('font-size', 11);
     	  },
        dateFormat: 'mm/dd/yy',
  	  	yearRange: '1910:2100'
      });
    
    
  });
  </script>

</head>
<body>	
<div><c:import url="header.jsp" /></div>	
<input type="hidden" name="userId" id="userId" value="${user.userId}" />
<input type="hidden" name="departmentId" id="departmentId" value="${user.departmentId}" />		
<div id="content"> 
	<div id="reportsArea">							
		<input type="hidden" name="empIdLoggedIn" id="empIdLoggedIn" value="${employeeLoggedIn.empId}" />
		<div class="reportItem">				
			<div class="reportsName">PAYSLIP</div>	
			<div class="dateArea">
				<div class="datepickerTxt">Payroll Period</div>
				<div style="float: left; margin-right: 30px;">
					<select name="payrollPeriodId" id="payrollPeriodDropDownID" style="width:214px; height:31px; background:#fff; border: 1px solid #52833b; margin-top: 5px; color: #A9A9A9;" >			    													
							
					</select>
				</div>				
			</div>		
			<div class="reportsIconButton">
				<div onclick="openApplicationWindow('1');"><img src="images/view.png" alt="alternate text" width="30px" height="30px" /></div>				
			</div>
			<div class="reportsIconButton">
				<div onclick="exportReport('1');"><img src="images/excel.png" alt="alternate text" width="30px" height="30px" /></div>				
			</div>
			<div class="reportsIconButton">
				<div onclick="openApplicationWindow('1');"><img src="images/pdf.png" alt="alternate text" width="30px" height="30px" /></div>				
			</div>
		</div>
		<div style="clear: both;"></div>
		<div class="reportItem">			
			<div class="reportsName">LEAVE REPORT</div>
			<div class="dateArea">
				<div class="datepickerTxt">Date From</div>
				<div class="datepicker" style="margin-left: 25px;"><input type="text" id="datepicker1"></div>
				<div class="datepickerTxt">Date From</div>
				<div class="datepicker"><input type="text" id="datepicker2"></div>
			</div>			
			<div class="reportsIconButton">
				<div onclick="openApplicationWindow('2');"><img src="images/view.png" alt="alternate text" width="30px" height="30px" /></div>				
			</div>
			<div class="reportsIconButton">
				<div onclick="exportReport('2');"><img src="images/excel.png" alt="alternate text" width="30px" height="30px" /></div>				
			</div>
			<div class="reportsIconButton">
				<div onclick="openApplicationWindow('2');"><img src="images/pdf.png" alt="alternate text" width="30px" height="30px" /></div>				
			</div>
		</div>		
		
		<div style="clear: both;"></div>						
	</div>
</div>
<div style=""><c:import url="footer.jsp" /></div>




	
		
</body>
</html>