<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
	    
	    if(appId == "1"){
	    	var dateFrom = document.getElementById("datepicker1").value;
	    	var dateTo = document.getElementById("datepicker2").value;
	    		    		    	
	    	url = "/hris/ViewCaseRateReport?dateFrom="+dateFrom+"&dateTo="+dateTo;
	    	swidth = screen.availWidth;
	    	sheight = screen.availHeight;	
	    	
	    	openNewPopUpWindow(url, swidth, sheight)
	    } else if(appId == "2"){
	    	var dateFrom = document.getElementById("datepicker3").value;
	    	var dateTo = document.getElementById("datepicker4").value;
	    	
	    	url = "/QUEUE/ViewEmployeeTimeReport?departmentId="+departmentId+"&dateFrom="+dateFrom+"&dateTo="+dateTo;
	    	swidth = screen.availWidth;
	    	sheight = screen.availHeight;
	    	
	    	openNewPopUpWindow(url, swidth, sheight)
	    }  else if(appId == "3"){
	    	var dateFrom = document.getElementById("datepicker5").value;
	    	var dateTo = document.getElementById("datepicker6").value;
	    	
	    	url = "/QUEUE/QueResetReport?departmentId="+departmentId+"&dateFrom="+dateFrom+"&dateTo="+dateTo;
	    	swidth = screen.availWidth;
	    	sheight = screen.availHeight;
	    	
	    	openNewPopUpWindow(url, swidth, sheight)
	    }   else if(appId == "4"){	    		    	
	    	
	    	url = "/QUEUE/ViewEmployeeListReport?departmentId="+departmentId;
	    	swidth = screen.availWidth;
	    	sheight = screen.availHeight;
	    	
	    	openNewPopUpWindow(url, swidth, sheight)
	    }  else if(appId == "5"){
	    	var dateFrom = document.getElementById("datepicker7").value;
	    	var dateTo = document.getElementById("datepicker8").value;
	    	
	    	url = "/QUEUE/ViewClientTimeReport?departmentId="+departmentId+"&dateFrom="+dateFrom+"&dateTo="+dateTo;
	    	swidth = screen.availWidth;
	    	sheight = screen.availHeight;
	    	
	    	openNewPopUpWindow(url, swidth, sheight)
	    }
	    
	}
	
	
	function exportReport(appId){		
	    var url = "";
	    var departmentId = document.getElementById("departmentId").value;	    
	    
	    if(appId == "1"){
	    	var dateFrom = document.getElementById("datepicker1").value;
	    	var dateTo = document.getElementById("datepicker2").value;
	    	
	    	var oAjaxCall = $.ajax({
				type : "POST",
				url : "/QUEUE/ExportEmployeePerformanceReport",
				
				data: "departmentId=" + departmentId + "&dateFrom=" + dateFrom + "&dateTo=" + dateTo,
				cache : false,
				async : false,
				success : function(data) {
					
					alert("Export of EMPLOYEE PERFORMANCE REPORT was SUCCESSFULLY saved in C:\\queReports\\");			

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
	    	
	    	
	    }  else if(appId == "3"){
	    	var dateFrom = document.getElementById("datepicker5").value;
	    	var dateTo = document.getElementById("datepicker6").value;
	    	
	    	var oAjaxCall = $.ajax({
				type : "POST",
				url : "/QUEUE/ExportQueResetReport",
				
				data: "departmentId=" + departmentId + "&dateFrom=" + dateFrom + "&dateTo=" + dateTo,
				cache : false,
				async : false,
				success : function(data) {
					
					alert("Export of QUE RESET REPORT was SUCCESSFULLY saved in C:\\queReports\\");			

				},
				error : function(data) {
					alert('error: ' + data);
				}

			});
	    	
	    	
	    }   else if(appId == "4"){	    	
	    	
	    	var oAjaxCall = $.ajax({
				type : "POST",
				url : "/QUEUE/ExportEmployeeListReport",
				
				data: "departmentId=" + departmentId,
				cache : false,
				async : false,
				success : function(data) {
					
					alert("Export of EMPLOYEE LIST REPORT was SUCCESSFULLY saved in C:\\queReports\\");			

				},
				error : function(data) {
					alert('error: ' + data);
				}

			});	    	
	    	
		}   else if(ppId == "5"){
			var dateFrom = document.getElementById("datepicker7").value;
	    	var dateTo = document.getElementById("datepicker8").value;
	    	
	    	var oAjaxCall = $.ajax({
				type : "POST",
				url : "/QUEUE/ExportClientTimeReport",
				
				data: "departmentId=" + departmentId + "&dateFrom=" + dateFrom + "&dateTo=" + dateTo,
				cache : false,
				async : false,
				success : function(data) {
					
					alert("Export of CLIENT TIME ENTRY REPORT was  SUCCESSFULLY saved in C:\\queReports\\");			

				},
				error : function(data) {
					alert('error: ' + data);
				}

			});	    	
	    	
	    }
	    
	}
	
	
	function returnToMainMenu(){		
		document.location.href="/QUEUE/mainMenu.jsp";    
	}
	
</script>
<script>
  $(function() {
    $( "#datepicker1" ).datepicker({
      changeMonth: true,
      changeYear: true,
      dateFormat: 'mm/dd/yy',
	  yearRange: '1910:2100'
    });
    
    $( "#datepicker2" ).datepicker({
        changeMonth: true,
        changeYear: true,
        dateFormat: 'mm/dd/yy',
  	    yearRange: '1910:2100'
      });
    
    $( "#datepicker3" ).datepicker({
        changeMonth: true,
        changeYear: true,
        dateFormat: 'mm/dd/yy',
  	  	yearRange: '1910:2100'
      });
    
    $( "#datepicker4" ).datepicker({
        changeMonth: true,
        changeYear: true,
        dateFormat: 'mm/dd/yy',
  	  	yearRange: '1910:2100'
      });
    
    $( "#datepicker5" ).datepicker({
        changeMonth: true,
        changeYear: true,
        dateFormat: 'mm/dd/yy',
  	  	yearRange: '1910:2100'
      });
    
    $( "#datepicker6" ).datepicker({
        changeMonth: true,
        changeYear: true,
        dateFormat: 'mm/dd/yy',
  	  	yearRange: '1910:2100'
      });
    
    $( "#datepicker7" ).datepicker({
        changeMonth: true,
        changeYear: true,
        dateFormat: 'mm/dd/yy',
  	  	yearRange: '1910:2100'
      });
    
    $( "#datepicker8" ).datepicker({
        changeMonth: true,
        changeYear: true,
        dateFormat: 'mm/dd/yy',
  	  	yearRange: '1910:2100'
      });
    
    
  });
  </script>

</head>
<body style="background-color: #001941;">	
	<input type="hidden" name="userId" id="userId" value="${user.userId}" />
	<input type="hidden" name="departmentId" id="departmentId" value="${user.departmentId}" />		
	<div id="reportsArea">							
		<div class="reportHeader">REPORTS MENU</div>
		
		<div class="reportItem">				
			<div class="reportsName">EMPLOYEE PERFORMANCE REPORT</div>	
			<div class="dateArea">
				<div class="datepickerTxt">Date From</div>
				<div class="datepicker"><input type="text" id="datepicker1"></div>
				<div class="datepickerTxt">Date From</div>
				<div class="datepicker"><input type="text" id="datepicker2"></div>
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
			<div class="reportsName">EMPLOYEE TIME ENTRY REPORT</div>
			<div class="dateArea">
				<div class="datepickerTxt">Date From</div>
				<div class="datepicker"><input type="text" id="datepicker3"></div>
				<div class="datepickerTxt">Date From</div>
				<div class="datepicker"><input type="text" id="datepicker4"></div>
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
		<div class="reportItem">				
			<div class="reportsName">QUE RESET REPORT</div>	
			<div class="dateArea">
				<div class="datepickerTxt">Date From</div>
				<div class="datepicker"><input type="text" id="datepicker5"></div>
				<div class="datepickerTxt">Date From</div>
				<div class="datepicker"><input type="text" id="datepicker6"></div>
			</div>		
			<div class="reportsIconButton">
				<div onclick="openApplicationWindow('3');"><img src="images/view.png" alt="alternate text" width="30px" height="30px" /></div>				
			</div>
			<div class="reportsIconButton">
				<div onclick="exportReport('3');"><img src="images/excel.png" alt="alternate text" width="30px" height="30px" /></div>				
			</div>
			<div class="reportsIconButton">
				<div onclick="openApplicationWindow('3');"><img src="images/pdf.png" alt="alternate text" width="30px" height="30px" /></div>				
			</div>
		</div>
		<div style="clear: both;"></div>
		<div class="reportItem">			
			<div class="reportsName">EMPLOYEE LIST REPORT</div>		
			<div style="width: 200px;">				
			</div>	
			<div class="reportsIconButton">
				<div onclick="openApplicationWindow('4');"><img src="images/view.png" alt="alternate text" width="30px" height="30px" /></div>				
			</div>
			<div class="reportsIconButton">
				<div onclick="exportReport('4');"><img src="images/excel.png" alt="alternate text" width="30px" height="30px" /></div>				
			</div>
			<div class="reportsIconButton">
				<div onclick="openApplicationWindow('4');"><img src="images/pdf.png" alt="alternate text" width="30px" height="30px" /></div>				
			</div>
		</div>
		<div style="clear: both;"></div>
		<div class="reportItem">			
			<div class="reportsName">CLIENT TIME ENTRY REPORT</div>
			<div class="dateArea">
				<div class="datepickerTxt">Date From</div>
				<div class="datepicker"><input type="text" id="datepicker7"></div>
				<div class="datepickerTxt">Date From</div>
				<div class="datepicker"><input type="text" id="datepicker8"></div>
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
		<div style="clear: both;"></div>		
		<div onclick="returnToMainMenu();" class="reportReturnButton">
			<div><img src="images/return.png" alt="alternate text" width="30px" height="30px" style="padding-bottom:10px; margin-left: auto; margin-right: auto" /></div>
			<div>RETURN TO MAIN MENU</div>
		</div>				
	</div>	
</body>
</html>