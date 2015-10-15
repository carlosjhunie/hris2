<%@ page language="java" contentType="text/html; charset=iso-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Add Employee</title>
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
<script src="js/common.js"></script>
<script src="js/employee.js"></script>
<script src="js/jquery-ui-1.11.4/jquery-ui.js"></script>




<script>
var isSelectedSV1=false;
var isSelectedSV2=false;
var isSelectedSV3=false;

	$(document).ready(function() {	
		populateCityDropDown();
		populateJobTitleDropDown();
		populateCountryDropDown();
		populateDepartmentDropDown();
		populateDivisionDropDown();
		populateEmployeeTypeDropDown();
		populateProvinceDropDown();
		
		/*
	    $("#upload-button").click(function() {
	        var formData = new FormData($("form#uploadFileForm")[0]);

	        $.ajax({
	            url: '/hris/UploadEmployeeImageFileAction',
	            type: 'POST',
	            data: formData,
	            async: false,
	            success: function(data) {
	                alert('The image upload is successful! ');
	                $('#picLoc').prop('value', 'hrisImages/'+data.name);	      
					$('#empImgHolder').prop('src', 'hrisImages/'+data.name);
					//use prop rather than attr
					          
	                
	            },
	    		error : function(data) {
	    			alert('The image upload failed! ');
	    		},
	            cache: false,
	            contentType: false,
	            processData: false
	        });

	        return false;
	    });
		*/
	    
	    
	    
	    
		//$("#select-sv-button-1").click(function() {
		$("input[id^=select-sv-button").click(function() {
			if ($(this).prop("id") == "select-sv-button-1") {
				isSelectedSV1 = true;
			}
			if ($(this).prop("id") == "select-sv-button-2") {
				isSelectedSV2 = true;
			}
			if ($(this).prop("id") == "select-sv-button-3") {
				isSelectedSV3 = true;
			}			
			
			$( "#searchEmployeeDialog" ).dialog({
				show: 'fade',
				hide: 'fade',
				width: 600,
				maxWidth: 600,
				height: 400,
				maxHeight: 400,
			    open: function() {
			        // On open, hide the original submit button
			        $( this ).find( "[type=submit]" ).hide();
			    },
			    buttons: [
			        {
			            text: "Close",
			            click: function() {
			                $( this ).dialog( "close" );
			            }
			        }
			    ]
			});
		});
		
	});	
	
	$.fn.serializeObject = function() {
		var o = {};
		var a = this.serializeArray();
		$.each(a, function() {
			if (o[this.name] !== undefined) {
				if (!o[this.name].push) {
					o[this.name] = [ o[this.name] ];
				}
				o[this.name].push(this.value || '');
			} else {
				o[this.name] = this.value || '';
			}
		});
		return o;
	};
	


function clickSearchResult(empid) {		
		if (isSelectedSV1){
			//alert('You chose this supervisor: '+ empSearchMap[empid].firstname + ' ' + empSearchMap[empid].lastname);
			$("#superVisor1FullName").prop("value",  empSearchMap[empid].firstname + ' ' + empSearchMap[empid].lastname);
			$("#superVisor1Id").prop("value", empid);
			isSelectedSV1=false;
		}
		if (isSelectedSV2){
			//alert('You chose this supervisor: '+ empSearchMap[empid].firstname + ' ' + empSearchMap[empid].lastname);
			$("#superVisor2FullName").prop("value",  empSearchMap[empid].firstname + ' ' + empSearchMap[empid].lastname);
			$("#superVisor2Id").prop("value", empid);
			isSelectedSV2=false;
		}
		if (isSelectedSV3){
			//alert('You chose this supervisor: '+ empSearchMap[empid].firstname + ' ' + empSearchMap[empid].lastname);
			$("#superVisor3FullName").prop("value",  empSearchMap[empid].firstname + ' ' + empSearchMap[empid].lastname);
			$("#superVisor3Id").prop("value", empid);
			isSelectedSV3=false;			
		}
		$( "#searchEmployeeDialog" ).dialog("close");
}
	  
</script>

</head>
<body>
<div><c:import url="header.jsp" /></div>
<div>	
	<input type="hidden" name="empIdLoggedIn" id="empIdLoggedIn" value="${employeeLoggedIn.empId}" />
	<input type="hidden" name="departmentIdLoggedIn" id="departmentIdLoggedIn" value="${employeeLoggedIn.departmentId}" />	
	<div id="content">
		<!-- Left Side of Dashboard -->
		<div id="dashBoardLeftPannel">
			<!-- Employee Information -->
			<div>
				<!-- Picture -->
				<div style="float: left;"><img id="empImgHolder" src="images/sampleId2.jpg" alt="no image" width="180px" height="220px" style="padding:20px;" /></div>
				<!-- Employee Info -->
				<div class="welcomeHeaderDashboardTable">WELCOME Back!</div>
				<div class="nameDashBoard" id="employeeName"></div>
				<div class="jobTitle" id="jobTitleNameDashboard"></div>
				<div class="departmentDashboard" id="departmentNameDashEmployee"></div>				
				<div class="dashboardTxt">&nbsp;&nbsp;&nbsp;&nbsp;</div>
				<div class="dashboardTxtBig">&nbsp;</div>				
				<div class="dashboardTxt">&nbsp;&nbsp;&nbsp;&nbsp;</div>
				<div class="dashboardTxtBig">&nbsp;</div>
				<div class="cb"></div>
				<!-- div id="dashBoardButtonUploadGroup">
					<form method="POST" id="uploadFileForm" name="uploadFileForm" enctype="multipart/form-data">
						<input type="file" id="upload-file" name="uploadImage" accept="image/*"/>
						<input type="button" id="upload-button" value="Upload Image" />
					</form>
				</div-->
					
			</div>
		</div>
		<!-- Right Side of Dashboard -->
		<div id="dashBoardRightPannel">
			<div id="tabs-nohdr">
			  <ul>
			    <li><a href="#tabs-nohdr-1">Personal Info</a></li>			    
			  </ul>
			  <div id="tabs-nohdr-1">			    
			    <form method="POST" id="addEmployeeForm" name="addEmployeeForm" action="AddEmployeeAction">
			    	<input type="hidden" name="createdBy" id="createdBy" value="${employeeLoggedIn.empId}" />
			    	<input type="hidden" name="picLoc" id="picLoc" value="NONE" />				
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
				    <div class="dataEntryText">Username</div>
				    <div class="dataEntryInput"><input type="text" name="username"  value="${param.username}" placeholder="Username" /></div>
				    <div class="dataEntryText">Password</div>
				    <div class="dataEntryInput"><input type="password" name="password"  value="${param.password}" placeholder="Password" /></div>
				    <div class="cb"></div>
				    <div class="dataEntryText">Birthdate</div>
				    <div class="dataEntryInput"><input type="text" name="dateOfBirth"  value="${param.dateOfBirth}" class="useDPicker" placeholder="Birthdate" /></div>
				    <div class="dataEntryText">Email</div>
				    <div class="dataEntryInput"><input type="text" name="email"  value="${param.email}" placeholder="Email" /></div>
				    <div class="cb"></div>
				    <div class="dataEntryText">Landline</div>
				    <div class="dataEntryInput"><input type="text" name="telNo"  value="${param.telNo}" placeholder="Landline" /></div>
				    <div class="dataEntryText">Mobile No.</div>
				    <div class="dataEntryInput"><input type="text" name="mobileNo"  value="${param.mobileNo}" placeholder="Mobile Number" /></div>
				    <div class="cb"></div>
				    <div class="dataEntryText">Gender</div>
				    <div class="dataEntryInput">			    	
				    	<select name="gender" style="width:208px; height:41px; background:#fff; border: 1px solid #52833b; margin-top: 5px; color: #A9A9A9;" >
							<option selected="selected" value="n">Select Gender</option>									
							<option value="M">Male</option>
							<option value="F">Female</option>
						</select>
				    </div>
				    <div class="dataEntryText">Place of Birth</div>
				    <div class="dataEntryInput"><input type="text" name="birthPlace"  value="${param.birthPlace}" placeholder="Place of Birth" /></div>
				    <div class="cb"></div>
				    <div class="dataEntryText">Civil Status</div>
				    <div class="dataEntryInput">			    	
				    	<select name="civilStatus" style="width:208px; height:41px; background:#fff; border: 1px solid #52833b; margin-top: 5px; color: #A9A9A9;" >
							<option selected="selected" value="n">Select Status</option>									
							<option value="S">Single</option>
							<option value="M">Married</option>
							<option value="D">Divorced</option>
							<option value="W">Widowed</option>
						</select>
				    </div>
				    <div class="dataEntryText">Nationality</div>
				    <div class="dataEntryInput"><input type="text" name="nationality"  value="${param.nationality}" placeholder="Nationality" /></div>
				    <div class="cb"></div>
				    <div class="dataEntryText">Street</div>
				    <div class="dataEntryInput"><input type="text" name="street"  value="${param.street}" placeholder="Street" /></div>
				    <div class="dataEntryText">City</div>
				    <div class="dataEntryInput">
				    	<select name="cityId" id="cityDropDownID" style="width:208px; height:41px; background:#fff; border: 1px solid #52833b; margin-top: 5px; color: #A9A9A9;" >			    													
							
							
						</select>			    		    	
				    </div>
				    <div class="cb"></div>
				    <div class="dataEntryText">Province</div>
				    <div class="dataEntryInput">
				    	<select name="provinceId" id="provinceDropDownID" style="width:208px; height:41px; background:#fff; border: 1px solid #52833b; margin-top: 5px; color: #A9A9A9;" >			    													
							
						</select>			    	
				    </div>
				    <div class="dataEntryText">Country</div>
				    <div class="dataEntryInput">
				    	<select name="countryId" id="countryDropDownID" style="width:208px; height:41px; background:#fff; border: 1px solid #52833b; margin-top: 5px; color: #A9A9A9;" >			    													
							
						</select>			    	
				    </div>
				    <div class="cb"></div>
				    <div class="dataEntryText">Zip Code</div>
				    <div class="dataEntryInput"><input type="text" name="zipCode"  value="${param.zipCode}" placeholder="Zipcode" /></div>
				    <div class="dataEntryText">Job Title</div>
				    <div class="dataEntryInput">
				    	<select name="jobTitleId" id="jobTitleDropDownID" style="width:208px; height:41px; background:#fff; border: 1px solid #52833b; margin-top: 5px; color: #A9A9A9;" >			    													
							
						</select>			    	
				    </div>	
				    <div class="cb"></div>
				    <div class="dataEntryText">Employee Status</div>
				    <div class="dataEntryInput">
				    	<select name="empStatus" style="width:208px; height:41px; background:#fff; border: 1px solid #52833b; margin-top: 5px; color: #A9A9A9;" >			    													
							<option value="A">ACTIVE</option>						
							<option value="NA">NOT ACTIVE</option>
						</select>			    	
				    </div>
				    <div class="dataEntryText">Department</div>
				    <div class="dataEntryInput">
				    	<select name="departmentId" id="departmentDropDownID" style="width:208px; height:41px; background:#fff; border: 1px solid #52833b; margin-top: 5px; color: #A9A9A9;" >			    													
							
						</select>			    	
				    </div>	
				    <div class="cb"></div>
				    <div class="dataEntryText">Division</div>
				    <div class="dataEntryInput">
				    	<select name="divisionId" id="divisionDropDownID" style="width:208px; height:41px; background:#fff; border: 1px solid #52833b; margin-top: 5px; color: #A9A9A9;" >			    													
							
						</select>			    	
				    </div>
				    <div class="dataEntryText">Date Employed</div>
				    <div class="dataEntryInput">
				    	<input type="text" name="employmentDate"  value="${param.employmentDate}" class="useDPicker" placeholder="Date Employed" />			    	
				    </div>		
				    <div class="cb"></div>
				    <div class="dataEntryText">Employee Type</div>
				    <div class="dataEntryInput">
				    	<select name="employeeTypeId" id="employeeTypeDropDownID" style="width:208px; height:41px; background:#fff; border: 1px solid #52833b; margin-top: 5px; color: #A9A9A9;" >			    													
							
						</select>			    	
				    </div> 
				    <div class="dataEntryText">End of Contract</div>
				    <div class="dataEntryInput">
				    	<input type="text" name="endOfContract"  value="${param.endOfContract}" class="useDPicker" placeholder="End of Contract" />	    	
				    </div>
				    <div class="cb"></div>
				    
				    <div class="dataEntryText">GSIS</div>
				    <div class="dataEntryInput"><input type="text" name="gsis"  value="${param.gsis}" placeholder="GSIS" /></div>
				    <div class="dataEntryText">SSS</div>
				    <div class="dataEntryInput"><input type="text" name="sss"  value="${param.sss}" placeholder="SSS" /></div>
				    <div class="cb"></div>
				    
				    <div class="dataEntryText">TIN</div>
				    <div class="dataEntryInput"><input type="text" name="tin"  value="${param.tin}" placeholder="TIN" /></div>
				    <div class="dataEntryText">TAX STATUS</div>
				    <div class="dataEntryInput">
				    	<select name="taxstatus" style="width:208px; height:41px; background:#fff; border: 1px solid #52833b; margin-top: 5px; color: #A9A9A9;" >														
							<option value="Z">Z</option>						
							<option value="S">S</option>
							<option value="S">S1</option>
							<option value="S">S2</option>
							<option value="S">S3</option>
							<option value="S">S4</option>
							<option value="ME">ME</option>
							<option value="ME">ME1</option>
							<option value="ME">ME2</option>
							<option value="ME">ME3</option>
							<option value="ME">ME4</option>
						</select>
				    </div>
				    <div class="cb"></div>
				    
				    <div class="dataEntryText">PHILHEALTH</div>
				    <div class="dataEntryInput"><input type="text" name="phic"  value="${param.phic}" placeholder="PHILHEALTH" /></div>
				    <div class="dataEntryText">PAGIBIG</div>
				    <div class="dataEntryInput"><input type="text" name="hdmf"  value="${param.hdmf}" placeholder="PAGIBIG" /></div>
				    <div class="cb"></div>
				    <div class="dataEntryText">Supervisor 1</div>
				    <div class="dataEntryInput">
				    	<input type="text" name="superVisor1FullName"  id="superVisor1FullName" value="" disabled/>
				    	<input type="hidden" name="superVisor1Id" id="superVisor1Id" value="0" />			
				 		<input type="button" id="select-sv-button-1" value="Choose..">
				    </div>
				    <div class="cb"></div>
				    <!-- 
				    <div class="dataEntryText">Supervisor 1</div>
				    <div class="dataEntryInput">
				    	<input type="text" name="superVisor1FullName"  id="superVisor1FullName" value="" disabled/>
				    	<input type="hidden" name="superVisor1Id" id="superVisor1Id" value="0" />			
				 		<input type="button" id="select-sv-button-1" value="Choose..">
				    </div>

					<div class="cb"></div>
				    <div class="dataEntryText">Supervisor 2</div>
				    <div class="dataEntryInput">
				    	<input type="text" name="superVisor2FullName" id="superVisor2FullName"  value="" disabled/>
				    	<input type="hidden" name="superVisor2Id" id="superVisor2Id" value="0" />	
				    	<input type="button" id="select-sv-button-2" value="Choose.."> 	
				    </div>
				    
				    
				    <div class="cb"></div>
				    <div class="dataEntryText">Supervisor 3</div>
				    <div class="dataEntryInput">
				    	<input type="text" name="superVisor3FullName" id="superVisor3FullName"  value="" disabled/>
				    	<input type="hidden" name="superVisor3Id" id="superVisor3Id" value="0" />	
				    	<input type="button" id="select-sv-button-3" value="Choose..">    	
				    </div>
					 -->
					
				</form>
			    <div class="cb" style="height: 10px;"></div>
			    <div class="employeeButton" onClick="saveEmployee();">Save</div>
			    <div class="employeeButton">Clear</div>
			    <div class="cb"></div>
			    
		    
			  </div>
			  
			  
			</div>
		</div>
		<div class="cb" style="height: 50px;"></div>
	</div>
</div>	

<div style=""><c:import url="footer.jsp" /></div>

<div id="searchEmployeeDialog" title="Search Employee" class="ui-front">
<c:import url="searchEmployee_solr.jsp" />
	<div class="cb"></div>
	<div>
		<div id="searchHolderId"></div>	
	</div>
</div>
</body>
</html>