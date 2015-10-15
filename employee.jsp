<%@ page language="java" contentType="text/html; charset=iso-8859-1"
	pageEncoding="ISO-8859-1"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<html>
<head>
<title>Search Employee</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/styleTables.css" />
<link rel="stylesheet" type="text/css" href="css/style.css" />
<link rel="stylesheet" type="text/css" href="css/dai.css" />
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

<script type="text/javascript">
$(document).ready(function() {
	
	var items = $(".resultItem");

    var numItems = items.length;
    var perPage = 10;
    
    var numPages = numItems;
    var remainingItems = 0;
    
    if(numItems < perPage){
    	numPages = 1;
    } else {    	
    	remainingItems = numItems % perPage;
    	
    	if(remainingItems > 0){
    		numPages = ((numItems - remainingItems) / perPage) + 1;	
    	} else {
    		numPages = (numItems - remainingItems) / perPage;
    	}
    	
    }    
    
    // only show the first 2 (or "first per_page") items initially
    items.slice(perPage).hide();

    // now setup your pagination
    // you need that .pagination-page div before/after your table
   $('.pagination').jqPagination({        
		max_page	: numPages,
		paged		: function(page) {			
			var showFrom = perPage * (page - 1);
            var showTo = showFrom + perPage;

            items.hide() // first hide everything, then show for the new page
            .slice(showFrom, showTo).show();
		}        
    });

});

</script>
</head>
<body>
<div><c:import url="header.jsp" /></div>
<div>		
	<div>
		<!-- Left Side of Dashboard -->
		<div id="dashBoardLeftPannel">
			<!-- Employee Information -->
			<div>
				<!-- Picture -->
				<div style="float: left;"><img src="images/sampleId.jpg" alt="alternate text" width="180px" height="220px" style="padding:20px;" /></div>
				<!-- Employee Info -->
				<div class="welcomeHeaderDashboardTable">WELCOME Back!</div>
				<div class="nameDashBoard">Ian Alfred Orozco</div>
				<div class="jobTitle">Developer Analyst III</div>
				<div class="departmentDashboard">Application Management</div>				
				<div class="dashboardTxt">Remaining SL&nbsp;&nbsp;:&nbsp;&nbsp;</div>
				<div class="dashboardTxtBig">30</div>				
				<div class="dashboardTxt">Remaining VL&nbsp;&nbsp;:&nbsp;&nbsp;</div>
				<div class="dashboardTxtBig">40</div>
				<div class="cb"></div>
				<div class="dashBoardButton">Request Time Off</div>
				<div class="dashBoardButton">File Overtime</div>
				<div class="dashBoardButton">File Training &amp; Seminar</div>				
			</div>
		</div>
		<!-- Right Side of Dashboard -->
		<div id="dashBoardRightPannel">
			<div id="tabs-nohdr">
			  <ul>
			    <li><a href="#tabs-nohdr-1">Personal Info</a></li>
			    <li><a href="#tabs-nohdr-2">Educational Background</a></li>
			    <li><a href="#tabs-nohdr-3">Family Members</a></li>
			    <li><a href="#tabs-nohdr-4">Payroll Info</a></li>
			    <li><a href="#tabs-nohdr-5">Work History</a></li>
			    <li><a href="#tabs-nohdr-6">Trainings &amp; Seminars</a></li>
			  </ul>
			  <div id="tabs-nohdr-1">			    
			    
			    <div class="dataEntryText">Employee No</div>
			    <div class="dataEntryInput"><input type="text" name="empNo"  value="${param.empNo}" placeholder="Employee Number" /></div>
			    <div class="dataEntryText">Firstname</div>			    
			    <div class="dataEntryInput"><input type="text" name="firstname"  value="${param.firstname}" placeholder="Firstname" /></div>
			    <div class="cb"></div>
			    <div class="dataEntryText">Lastname</div>
			    <div class="dataEntryInput"><input type="text" name="lastname"  value="${param.lastname}" placeholder="Lastname" /></div>
			    <div class="dataEntryText">Middlename</div>
			    <div class="dataEntryInput"><input type="text" name="middlename"  value="${param.middlename}" placeholder="Middlename" /></div>
			    <div class="cb"></div>
			    <div class="dataEntryText">Birthdate</div>
			    <div class="dataEntryInput"><input type="text" name="lastname"  value="${param.lastname}" placeholder="Birthdate" /></div>
			    <div class="dataEntryText">Email</div>
			    <div class="dataEntryInput"><input type="text" name="middlename"  value="${param.middlename}" placeholder="Email" /></div>
			    <div class="cb"></div>
			    <div class="dataEntryText">Landline</div>
			    <div class="dataEntryInput"><input type="text" name="lastname"  value="${param.lastname}" placeholder="Landline" /></div>
			    <div class="dataEntryText">Mobile No.</div>
			    <div class="dataEntryInput"><input type="text" name="middlename"  value="${param.middlename}" placeholder="Mobile Number" /></div>
			    <div class="cb"></div>
			    <div class="dataEntryText">Gender</div>
			    <div class="dataEntryInput"><input type="text" name="lastname"  value="${param.lastname}" placeholder="Gender" /></div>
			    <div class="dataEntryText">Place of Birth</div>
			    <div class="dataEntryInput"><input type="text" name="middlename"  value="${param.middlename}" placeholder="Place of Birth" /></div>
			    <div class="cb"></div>
			    <div class="dataEntryText">Civil Status</div>
			    <div class="dataEntryInput"><input type="text" name="lastname"  value="${param.lastname}" placeholder="Civil Status" /></div>
			    <div class="dataEntryText">Nationality</div>
			    <div class="dataEntryInput"><input type="text" name="middlename"  value="${param.middlename}" placeholder="Nationality" /></div>
			    <div class="cb"></div>
			    <div class="dataEntryText">Street</div>
			    <div class="dataEntryInput"><input type="text" name="lastname"  value="${param.lastname}" placeholder="Street" /></div>
			    <div class="dataEntryText">City</div>
			    <div class="dataEntryInput"><input type="text" name="middlename"  value="${param.middlename}" placeholder="City" /></div>
			    <div class="cb"></div>
			    <div class="dataEntryText">Province</div>
			    <div class="dataEntryInput"><input type="text" name="lastname"  value="${param.lastname}" placeholder="Province" /></div>
			    <div class="dataEntryText">Country</div>
			    <div class="dataEntryInput"><input type="text" name="middlename"  value="${param.middlename}" placeholder="Country" /></div>
			    <div class="cb"></div>
			    <div class="dataEntryText">Zip Code</div>
			    <div class="dataEntryInput"><input type="text" name="lastname"  value="${param.lastname}" placeholder="Zipcode" /></div>			    			    
			    <div class="cb" style="height: 10px;"></div>
			    <div class="employeeButton">Save</div>
			    <div class="employeeButton">Clear</div>
			    <div class="cb"></div>
			  </div>
			  <div id="tabs-nohdr-2">
			    <div class="dataEntryText">Year Attended</div>
			    <div class="dataEntryInput"><input type="text" name="lastname"  value="${param.lastname}" placeholder="Year Attended" /></div>
			    <div class="dataEntryText">Course</div>
			    <div class="dataEntryInput"><input type="text" name="middlename"  value="${param.middlename}" placeholder="Course" /></div>
			    <div class="cb"></div>
			    <div class="dataEntryText">School</div>
			    <div class="dataEntryInput"><input type="text" name="lastname"  value="${param.lastname}" placeholder="School" /></div>
			    <div class="dataEntryText">Year Graduated</div>
			    <div class="dataEntryInput"><input type="text" name="middlename"  value="${param.middlename}" placeholder="Year Graduated" /></div>
			    <div class="cb"></div>
			    <div class="dataEntryText">Remarks</div>
			    <div class="dataEntryInput"><input type="text" name="lastname"  value="${param.lastname}" placeholder="Remarks" /></div>			    
			    <div class="cb" style="height: 10px;"></div>
			    <div class="employeeButton">Add</div>
			    <div class="employeeButton">Clear</div>
			    <div>
			    	<div class="headerDashboardTable">Education Background List</div>					
					<table id="hor-minimalist-b" summary="Employee Pay Sheet" width="890px;">					    
					    <thead>
					    	<tr>
					        	<th scope="col">Year Attended</th>
					            <th scope="col">Course/Degree</th>
					            <th scope="col">School</th>
					            <th scope="col">Year Graduated</th>					            
					            <th scope="col">&nbsp;</th>
					        </tr>
					    </thead>
					    <tbody>
					    	<tr>					        	
					        	<td>1999-2004</td>
					        	<td>Engineering</td>
					        	<td>UST</td>
					            <td>2004</td>	
					            <td><img src="images/edit.png" alt="Edit Record" width="16px" height="16px" border="0"/></td>				            					            
					        </tr>
					        <tr>					        	
					        	<td>2005-2007</td>
					        	<td>Master in Mathematics</td>
					        	<td>UP</td>
					            <td>2007</td>
					            <td><img src="images/edit.png" alt="Edit Record" width="16px" height="16px" border="0"/></td>					            					            
					        </tr>					        
					    </tbody>
					</table>
			    </div>
			  
			  </div>
			  <div id="tabs-nohdr-3">			    
			    <div class="dataEntryText">Name</div>
			    <div class="dataEntryInput"><input type="text" name="lastname"  value="${param.lastname}" placeholder="Name" /></div>
			    <div class="dataEntryText">Birthdate</div>
			    <div class="dataEntryInput"><input type="text" name="middlename"  value="${param.middlename}" placeholder="Birthdate" /></div>
			    <div class="cb"></div>
			    <div class="dataEntryText">Gender</div>
			    <div class="dataEntryInput"><input type="text" name="lastname"  value="${param.lastname}" placeholder="Gender" /></div>
			    <div class="dataEntryText">Age</div>
			    <div class="dataEntryInput"><input type="text" name="middlename"  value="${param.middlename}" placeholder="Age" /></div>
			    <div class="cb"></div>
			    <div class="dataEntryText">Relation</div>
			    <div class="dataEntryInput"><input type="text" name="lastname"  value="${param.lastname}" placeholder="Relation" /></div>
			    <div class="dataEntryText">Remarks</div>
			    <div class="dataEntryInput"><input type="text" name="middlename"  value="${param.middlename}" placeholder="Remarks" /></div>
			    <div class="cb" style="height: 10px;"></div>
			    <div class="employeeButton">Add</div>
			    <div class="employeeButton">Clear</div>
			    <div>
			    	<div class="headerDashboardTable">Family Members List</div>					
					<table id="hor-minimalist-b" summary="Employee Pay Sheet" width="890px;">					    
					    <thead>
					    	<tr>
					        	<th scope="col">Name</th>
					            <th scope="col">Birthdate</th>
					            <th scope="col">Gender</th>
					            <th scope="col">Age</th>	
					            <th scope="col">Relation</th>					            
					            <th scope="col">&nbsp;</th>
					        </tr>
					    </thead>
					    <tbody>
					    	<tr>					
					    		<td>James Yap</td>        	
					        	<td>Jan 12, 1981</td>
					        	<td>Male</td>
					        	<td>33</td>
					            <td>Son</td>	
					            <td><img src="images/edit.png" alt="Edit Record" width="16px" height="16px" border="0"/></td>				            					            
					        </tr>
					        <tr>					
					    		<td>James Yap</td>        	
					        	<td>Jan 12, 1981</td>
					        	<td>Male</td>
					        	<td>33</td>
					            <td>Son</td>	
					            <td><img src="images/edit.png" alt="Edit Record" width="16px" height="16px" border="0"/></td>				            					            
					        </tr>					        
					    </tbody>
					</table>
			    </div>
			  </div>
			  <div id="tabs-nohdr-4">
			    <div class="dataEntryText">Monthly Rate</div>
			    <div class="dataEntryInput"><input type="text" name="lastname"  value="${param.lastname}" placeholder="Monthly Rate" /></div>
			    <div class="dataEntryText">Date Rate</div>
			    <div class="dataEntryInput"><input type="text" name="middlename"  value="${param.middlename}" placeholder="Date Rate" /></div>
			    <div class="cb"></div>
			    <div class="dataEntryText">Hourly Rate</div>
			    <div class="dataEntryInput"><input type="text" name="lastname"  value="${param.lastname}" placeholder="Hourly Rate" /></div>
			    <div class="dataEntryText">Allowance</div>
			    <div class="dataEntryInput"><input type="text" name="middlename"  value="${param.middlename}" placeholder="Allowance" /></div>
			    <div class="cb"></div>
			    <div class="dataEntryText">Food Allowance</div>
			    <div class="dataEntryInput"><input type="text" name="lastname"  value="${param.lastname}" placeholder="Food Allowance" /></div>
			    <div class="dataEntryText">Payroll Days</div>
			    <div class="dataEntryInput"><input type="text" name="middlename"  value="${param.middlename}" placeholder="Payroll Days" /></div>
			    <div class="cb"></div>
			    <div class="dataEntryText">Cola</div>
			    <div class="dataEntryInput"><input type="text" name="lastname"  value="${param.lastname}" placeholder="Cola" /></div>
			    <div class="dataEntryText">Tax Shield</div>
			    <div class="dataEntryInput"><input type="text" name="middlename"  value="${param.middlename}" placeholder="Tax Shield" /></div>
			    <div class="cb"></div>
			    <div class="dataEntryText">Transportation Allowance</div>
			    <div class="dataEntryInput"><input type="text" name="lastname"  value="${param.lastname}" placeholder="Transportation Allowance" /></div>
			    <div class="dataEntryText">Other Allowance</div>
			    <div class="dataEntryInput"><input type="text" name="middlename"  value="${param.middlename}" placeholder="Other Allowance" /></div>
			    <div class="cb"></div>
			    <div class="dataEntryText">Payroll Type</div>
			    <div class="dataEntryInput"><input type="text" name="lastname"  value="${param.lastname}" placeholder="Payroll Type" /></div>
			    <div class="dataEntryText">Bank Account #</div>
			    <div class="dataEntryInput"><input type="text" name="middlename"  value="${param.middlename}" placeholder="Bank Account #" /></div>
			    <div class="cb"></div>
			    <div class="dataEntryText">Shift From</div>
			    <div class="dataEntryInput"><input type="text" name="lastname"  value="${param.lastname}" placeholder="Shift From" /></div>
			    <div class="dataEntryText">Shift To</div>
			    <div class="dataEntryInput"><input type="text" name="middlename"  value="${param.middlename}" placeholder="Shift To" /></div>
			    <div class="cb" style="height: 10px;"></div>
			    <div class="employeeButton">Save</div>
			    <div class="employeeButton">Clear</div>
			    <div class="cb"></div>
			  </div>
			  <div id="tabs-nohdr-5">
			    <div class="dataEntryText">Years of Service</div>
			    <div class="dataEntryInput"><input type="text" name="lastname"  value="${param.lastname}" placeholder="Years of Service" /></div>
			    <div class="dataEntryText">Employer Name</div>
			    <div class="dataEntryInput"><input type="text" name="middlename"  value="${param.middlename}" placeholder="Employer Name" /></div>
			    <div class="cb"></div>
			    <div class="dataEntryText">Address</div>
			    <div class="dataEntryInput"><input type="text" name="lastname"  value="${param.lastname}" placeholder="Address" /></div>
			    <div class="dataEntryText">Country</div>
			    <div class="dataEntryInput"><input type="text" name="middlename"  value="${param.middlename}" placeholder="Country" /></div>
			    <div class="cb"></div>
			    <div class="dataEntryText">Industry</div>
			    <div class="dataEntryInput"><input type="text" name="lastname"  value="${param.lastname}" placeholder="Industry" /></div>
			    <div class="dataEntryText">Position</div>
			    <div class="dataEntryInput"><input type="text" name="middlename"  value="${param.middlename}" placeholder="Position" /></div>
			     <div class="cb"></div>
			    <div class="dataEntryText">Remarks</div>
			    <div class="dataEntryInput"><input type="text" name="lastname"  value="${param.lastname}" placeholder="Remarks" /></div>			    
			    <div class="cb" style="height: 10px;"></div>
			    <div class="employeeButton">Add</div>
			    <div class="employeeButton">Clear</div>
			    <div>
			    	<div class="headerDashboardTable">Work History List</div>					
					<table id="hor-minimalist-b" summary="Employee Pay Sheet" width="890px;">					    
					    <thead>
					    	<tr>
					        	<th scope="col">Employer Name</th>
					            <th scope="col">Yrs of Service</th>
					            <th scope="col">Address</th>
					            <th scope="col">Country</th>	
					            <th scope="col">Industry</th>					            
					            <th scope="col">Position</th>
					            <th scope="col">Remarks</th>
					            <th scope="col">&nbsp;</th>
					        </tr>
					    </thead>
					    <tbody>
					    	<tr>					
					    		<td>James Yap</td>        	
					        	<td>3</td>
					        	<td>Makati</td>
					        	<td>Engineering</td>
					            <td>Supervisor</td>	
					            <td>&nbsp;</td>	
					            <td><img src="images/edit.png" alt="Edit Record" width="16px" height="16px" border="0"/></td>				            					            
					        </tr>
					        <tr>					
					    		<td>James Yap</td>        	
					        	<td>4</td>
					        	<td>Makati</td>
					        	<td>Engineering</td>
					            <td>Supervisor</td>	
					            <td>&nbsp;</td>	
					            <td><img src="images/edit.png" alt="Edit Record" width="16px" height="16px" border="0"/></td>				            					            
					        </tr>					        
					    </tbody>
					</table>
			    </div>
			  </div>
			  <div id="tabs-nohdr-6">
			    <div class="dataEntryText">Date From</div>
			    <div class="dataEntryInput"><input type="text" name="lastname"  value="${param.lastname}" placeholder="Date From" /></div>
			    <div class="dataEntryText">Date To</div>
			    <div class="dataEntryInput"><input type="text" name="middlename"  value="${param.middlename}" placeholder="Date To" /></div>
			    <div class="cb"></div>
			    <div class="dataEntryText">Title of Activity</div>
			    <div class="dataEntryInput"><input type="text" name="lastname"  value="${param.lastname}" placeholder="Title of Activity" /></div>
			    <div class="dataEntryText">Provider</div>
			    <div class="dataEntryInput"><input type="text" name="middlename"  value="${param.middlename}" placeholder="Provider" /></div>
			    <div class="cb"></div>
			    <div class="dataEntryText">Remarks</div>
			    <div class="dataEntryInput"><input type="text" name="lastname"  value="${param.lastname}" placeholder="Remarks" /></div>			    			    
			    <div class="cb" style="height: 10px;"></div>
			    <div class="employeeButton">Add</div>
			    <div class="employeeButton">Clear</div>
			    <div>
			    	<div class="headerDashboardTable">Work History List</div>					
					<table id="hor-minimalist-b" summary="Employee Pay Sheet" width="890px;">					    
					    <thead>
					    	<tr>
					        	<th scope="col">Period</th>
					            <th scope="col">Title of Activity</th>
					            <th scope="col">Provider</th>
					            <th scope="col">Remarks</th>				            
					            <th scope="col">&nbsp;</th>
					        </tr>
					    </thead>
					    <tbody>
					    	<tr>					
					    		<td>Jan 1 to 15, 2015</td>        	
					        	<td>First Aid Training</td>
					        	<td>DOH</td>
					        	<td>Test Remarks</td>					            
					            <td><img src="images/edit.png" alt="Edit Record" width="16px" height="16px" border="0"/></td>				            					            
					        </tr>
					        <tr>					
					    		<td>Jan 1 to 15, 2015</td>        	
					        	<td>First Aid Training</td>
					        	<td>DOH</td>
					        	<td>Test Remarks</td>					            
					            <td><img src="images/edit.png" alt="Edit Record" width="16px" height="16px" border="0"/></td>				            					            
					        </tr>					        
					    </tbody>
					</table>
			    </div>
			  </div>
			</div>
		</div>
		<div class="cb" style="height: 50px;"></div>
	</div>
</div>	

<div style=""><c:import url="footer.jsp" /></div>
</body>
</html>