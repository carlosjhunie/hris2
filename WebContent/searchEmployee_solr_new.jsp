<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<link href="jtables/jquery-ui.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="jtables/jquery-ui-1.10.0.min.js"></script>
<script type="text/javascript">
//global variable
var empSearchMap = {};


$(document).ready(function () {
	
		//var URL_PREFIX = "http://<%=request.getServerName()%>:8983/solr/hris_employee/select?q=firstname%3A(SEARCHFIELD)+OR+empno%3A(SEARCHFIELD)+OR+lastname%3A(SEARCHFIELD)&wt=json&indent=true";

		
			
			$( "#searchButton" ).click(function() {
				fnSearchEmployee($("#searchBox").val());
			});			
			
});

function fnSearchEmployee(oSearchText) {
	empSearchMap = {};

	$.ajax({
		  'url': "/hris/SelectEmployeeAction?oSearchText="+oSearchText,
		  //'data': oSearchText,
		  'success': function(data) {
			  //console.log(data);
			  var divs = '';
			  divs +="<table id=\"box-table-a\" width=\"580px;\" style=\"margin: 15px 0px 0px 15px;\"     >";				    
			  divs +="<thead><tr>";
			  divs +="<th scope=\"col\">Emp No</th>";
			  divs +="<th scope=\"col\">Name</th>";
			  divs +="<th scope=\"col\">Department</th>";
			  divs +="<th scope=\"col\">Division</th></tr></thead><tbody>";
			  jQuery.each($.parseJSON(data), function(i, item) { 
				  empSearchMap[item.empId] = { empid: item.empId, firstname:item.firstname, lastname:item.lastname, empno:item.empNo, departmentName:item.departmentName, divisionName:item.divisionName};			  
				  	
				  
				    divs +="<tr onclick=\"editEmployee("+item.empId+")\" style='cursor:pointer;'>";
				  	divs +="<td>"
				  
				  divs += item.empNo;
				  
				  divs +="</td>"
				  divs +="<td>" + item.lastname + ', ' + item.firstname + "</td>";
				  divs +="<td>" + item.departmentName + "</td>";
				  divs +="<td>" + item.divisionName + "</td>";
				  divs +="</tr>"  				  
				
		        });
			  	
			  	divs +="</tbody></table>";
				
				$('#searchHolderId').html(divs);
				
		  },
			error : function(data) {
				
				console.log(data);
				alert('error: ' + data);
			},
		  datatype: 'json'
		});
};

function addEmployee(){
	window.location = "addEmployee.jsp";
}

function editEmployee(empid){
	window.location = "updateEmployee.jsp?empId=" + empid;
}


</script>
</head>
<body>
	<div>
		<div class="searchTxt">Enter First Name or Last Name or Employee number</div>
		<div class="cb"></div>
		<div>			
			<a href=""></a>
			<input id="searchBox" name="searchBox" type="text" size="40" placeholder="Type keyword here" />
			<input  id="searchButton" value="SEARCH" border="0">
			<input	id="addEmployeeButton" value="ADD EMPLOYEE" border="0" onclick="addEmployee();">
		</div>		 
	</div>	
</body>
</html>
