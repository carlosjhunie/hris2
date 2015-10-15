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

//override
/*
function clickSearchResult(empid) {
		
		
		alert(empSearchMap[empid].empno);
	}
*/
$(document).ready(function () {
	var URL_PREFIX = "http://<%=request.getServerName()%>:8983/solr/hris_employee/select?q=firstname%3A(SEARCHFIELD)+OR+empno%3A(SEARCHFIELD)+OR+lastname%3A(SEARCHFIELD)&wt=json&indent=true";

		$("#searchBox").autocomplete(
				{
					source : function(request, response) {
						var URL = URL_PREFIX.replace(/SEARCHFIELD/g, $(
								"#searchBox").val());

						$.ajax({
							url : URL,
							success : function(data) {
								var docs = JSON.stringify(data.response.docs);
								var jsonData = JSON.parse(docs);
								response($.map(jsonData, function(value, key) {
									return {
										label : value.firstname + ' '
												+ value.lastname
												
									}
								}));
							},
							dataType : 'jsonp',
							jsonp : 'json.wrf'
						});
					},
					minLength : 3,
					select: function( event, ui ) {
						fnSearchEmployee(ui.item.label);
						
					}
			});
		
			$( "#searchButton" ).click(function() {
				fnSearchEmployee($("#searchBox").val());
			});
			
			
			
			
});

function fnSearchEmployee(oSearchTxt) {
	empSearchMap = {};
	var searchString = 'firstname:(*SEARCHFIELD*) OR lastname:(*SEARCHFIELD*) OR empno:(*SEARCHFIELD*) OR divisionName:(*SEARCHFIELD*) OR departmentName:(*SEARCHFIELD*)';
	//var searchString = 'firstname:*SEARCHFIELD*';
	searchString = searchString.replace(/SEARCHFIELD/g,oSearchTxt);
		
	$.ajax({
		 'url': "http://<%=request.getServerName()%>:8983/solr/hris_employee/select",
		  'data': {'wt':'json', 'q':searchString},
		  'success': function(data) {
			  var divs = '';
			  divs +="<table id=\"box-table-a\" summary=\"Employee Pay Sheet\" width=\"520px;\" style=\"margin: 15px 0px 0px 15px;\"     >";				    
			  divs +="<thead><tr>";
			  divs +="<th scope=\"col\">Emp No</th>";
			  divs +="<th scope=\"col\">Name</th>";
			  divs +="<th scope=\"col\">Department</th>";
			  divs +="<th scope=\"col\">Division</th></tr></thead><tbody>";	
			  jQuery.each(data.response.docs, function(i, item) {
				  empSearchMap[item.empid] = { empId: item.empid, firstname:item.firstname, lastname:item.lastname, empno:item.empno, departmentName:item.departmentName, divisionName:item.divisionName};			  	  
				  
				  divs +="<tr onclick=\"editEmployee("+item.empid+")\" style='cursor:pointer;'>";
				  
				  
				 
				  divs +="<td>"
				  
				  divs += item.empno;
				  
				  
				  divs +="</td>"
				  divs +="<td>" + item.lastname + ', ' + item.firstname + "</td>";
				  divs +="<td>" + item.departmentName + "</td>";
				  divs +="<td>" + item.divisionName + "</td>";
				  divs +="</tr>"  				  
				
		        });
			  	
			  	divs +="</tbody></table>";
				
				$('#searchHolderId').html(divs);
				
		  },
		  'dataType': 'jsonp',
		  'jsonp': 'json.wrf',
		 	 error : function(data) {
				alert('error: ' + data);
			}
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
			<input	id="searchButton" value="SEARCH" border="0">
			<input	id="addEmployeeButton" value="ADD EMPLOYEE" border="0" onclick="addEmployee();">
		</div>		 
	</div>	
</body>
</html>
