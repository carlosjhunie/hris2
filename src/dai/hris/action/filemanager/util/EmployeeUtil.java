package dai.hris.action.filemanager.util;

import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;

public class EmployeeUtil {
	private static String CHARSET_UTF8 = "utf-8";
	//private SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  //from datepicker: "2015-06-01"
	private SimpleDateFormat sdfMilitaryTime = new SimpleDateFormat("HH:mm");

	public static String encodePassword(String password) {
		String encodedPassword = null;
		try {
			encodedPassword = Base64.getEncoder().encodeToString(password.getBytes(CHARSET_UTF8));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			System.err.println("password encoding failed");
			e.printStackTrace();
			
		}
		return encodedPassword;		
	}
	
	
	public static String decodePassword(String encodedPassword) {
		String decodedPassword = null;
		try {
			decodedPassword = new String(Base64.getDecoder().decode(encodedPassword), CHARSET_UTF8);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			System.err.println("password encoding failed");
			e.printStackTrace();
			
		}
		return decodedPassword;
	}
	
	public Date convertToSqlDate(String stringDate) {
		Date convertedDate = null;
		try {
			convertedDate = new Date(sdf.parse(stringDate).getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			System.err.println("converting to sql date failed");
			e.printStackTrace();
		}
		
		return convertedDate;		
	}
	
	public String sqlDateToString(Date sqlDate) {
		String convertedDate  = "";			
		if(sqlDate != null ) {
			convertedDate = sdf.format(sqlDate);	
		}
		return convertedDate;		
	}
	
	public String sqlTimeToString(Time sqlTime) {
		String convertedTime = "";
		if(sqlTime != null) {
			convertedTime = sdfMilitaryTime.format(sqlTime);
		}
		return convertedTime;
	}
	
	public Time convertToSqlTime(String stringTime) {
		Time convertedTime = null;
		try {
			convertedTime = new Time(sdfMilitaryTime.parse(stringTime).getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			System.err.println("converting to sql time failed");
			e.printStackTrace();
		}
		
		return convertedTime;		
	}
	
	
	public static Date getCurrentSystemDateSqlFormat() {
		return new java.sql.Date((new java.util.Date()).getTime());
	}

	
/*	*//**
	 * Use for add or update employee
	 * @param employee
	 * @param request
	 * @param isCreate
	 * @throws ParseException
	 *//*
	public void populateEmployeeModel(Employee employee, HttpServletRequest request, boolean isCreate) {
		String empId = null;
		
		System.out.println("populateEmployeeModel");
		employee.setEmpNo(request.getParameter("empNo"));
		employee.setFirstname(request.getParameter("firstname"));
		employee.setMiddlename(request.getParameter("middlename"));
		employee.setLastname(request.getParameter("lastname"));
		//employee.setDateOfBirth(!EMPTY.equals(request.getParameter("dateOfBirth")) ? convertToSqlDate(request.getParameter("dateOfBirth")): null);
		employee.setEmail(request.getParameter("email"));
		employee.setTelNo(request.getParameter("telNo"));
		employee.setMobileNo(request.getParameter("mobileNo"));
		employee.setGender(request.getParameter("gender"));		
		employee.setBirthPlace(request.getParameter("birthPlace"));		
		employee.setCivilStatus(request.getParameter("civilStatus"));
		employee.setNationality(request.getParameter("nationality"));
		employee.setStreet(request.getParameter("street"));
		
		if(!EMPTY.equals(request.getParameter("cityId"))){
			employee.setCityId(Integer.parseInt(request.getParameter("cityId")));
		}
		
		if(!EMPTY.equals(request.getParameter("provinceId"))){
			employee.setProvinceId(Integer.parseInt(request.getParameter("provinceId")));
		}
		
		if(!EMPTY.equals(request.getParameter("countryId"))){
			employee.setCountryId(Integer.parseInt(request.getParameter("countryId")));
		}
		
		
		
		
		employee.setZipCode(request.getParameter("zipCode"));		
		
		if(!EMPTY.equals(request.getParameter("jobTitleId"))){
			employee.setJobTitleId(Integer.parseInt(request.getParameter("jobTitleId")));
		}
		
		employee.setEmpStatus(request.getParameter("empStatus"));	
		
//		if(!EMPTY.equals(request.getParameter("deptId"))){
//			employee.setDeptId(Integer.parseInt(request.getParameter("deptId")));
//		}
//		
		if(!EMPTY.equals(request.getParameter("divisionId"))){
			employee.setDivisionId(Integer.parseInt(request.getParameter("divisionId")));
		}
		
		
				
		//employee.setEmploymentDate(!EMPTY.equals(request.getParameter("employmentDate")) ? convertToSqlDate(request.getParameter("employmentDate")): null);	
		
		if(!EMPTY.equals(request.getParameter("employeeTypeId"))){
			employee.setEmployeeTypeId(Integer.parseInt(request.getParameter("employeeTypeId")));
		}		
		
		//employee.setEndOfContract(!EMPTY.equals(request.getParameter("endOfContract")) ? convertToSqlDate(request.getParameter("endOfContract")): null);
		
		 * employee supervisor1 to 3 attributes not set for now (should be dynamic based on department)
		 
		employee.setUsername(request.getParameter("username"));		
		employee.setPassword(EmployeeUtil.encodePassword(request.getParameter("password")));

		
		// get the employee object from session  - currently logged in USER		
		if (null != request.getSession().getAttribute("employee")) {
			empId = String.valueOf(((Employee)request.getSession().getAttribute("employee")).getEmpId());
		}
		
		//createdBy is set only for new employee entries
		if (isCreate) {
			employee.setCreatedBy(empId);
			employee.setCreationDate(new java.sql.Date((new java.util.Date()).getTime()));
		}

	}*/
}
