<%@ page language="java" contentType="text/html; charset=iso-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Employee Search</title>
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
<script>
  $(function() {
    $( "#tabs-nohdr" ).tabs();
  });
</script>

<script>
  $(function() {
    $(".useDPicker").datepicker({
      changeMonth: true,
      changeYear: true,
      dateFormat: 'yy-mm-dd',
	  yearRange: '1910:2100'
    });
    
 
  });
</script>

<script>
	$(document).ready(function() {	
		//make this readonly
		$('#empNo').attr("readonly", true);
		$('#firstname').attr("readonly", true);
		$('#lastname').attr("readonly", true);
		$('#department').attr("readonly", true);
		
		
	});
	
	function makeVisible(){
		$(".hiddenField").addClass("visible")
		$(".hiddenField").removeClass("invisible")
	}
	
	//override this
	function clickSearchResult(empid) {
		
		$('#empNo').val(empSearchMap[empid].empno);
		$('#firstname').val(empSearchMap[empid].firstname);
		$('#lastname').val(empSearchMap[empid].lastname);
	
	}
	
	
</script>


</head>
<body>
<div><c:import url="header.jsp" /></div>	
<div id="content">
	<div style="width: 590px; margin: 0 auto;">
		<c:import url="searchEmployee_solr_new.jsp" />
		<div class="cb"></div>
		<div>
			<div id="searchHolderId"></div>	
		</div>
	</div>	
</div>
<div style=""><c:import url="footer.jsp" /></div>
</body>
</html>