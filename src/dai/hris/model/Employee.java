package dai.hris.model;

import java.sql.Date;

/**
From dbo.employee table

	[empId] [int] IDENTITY(1,1) NOT NULL,
	[empNo] [varchar](50) NOT NULL,
	[lastname] [varchar](50) NULL,
	[firstname] [varchar](50) NULL,
	[middlename] [varchar](50) NULL,
	[dateOfBirth] [datetime] NULL,
	[gender] [varchar](1) NULL,
	[civilStatus] [varchar](50) NULL,
	[nationality] [varchar](50) NULL,
	[street] [varchar](150) NULL,
	[cityId] [int] NULL,
	[email] [varchar](150) NULL,
	[mobileNo] [varchar](50) NULL,
	[telNo] [varchar](50) NULL,
	[birthPlace] [varchar](50) NULL,
	[provId] [int] NULL,
	[zipCode] [varchar](50) NULL,
	[jtId] [int] NULL,
	[deptId] [int] NULL,
	[divisionId] [int] NULL,
	[empTypeId] [int] NULL,
	[empStatus] [varchar](50) NULL,
	[employmentDate] [datetime] NULL,
	[endOfContract] [datetime] NULL,
	[sss] [varchar](50) NULL,
	[gsis] [varchar](50) NULL,
	[hdmf] [varchar](50) NULL,
	[tin] [varchar](50) NULL,
	[phic] [varchar](50) NULL,
	[taxstatus] [varchar](50) NULL,
	[picLoc] [varchar](50) NULL,
	[superVisor1Id] [int] NULL,
	[superVisor2Id] [int] NULL,
	[superVisor3Id] [int] NULL,
	[username] [varchar](50) NULL,
	[password] [varchar](50) NULL,
	[createdBy] [varchar](50) NULL,
	[creationDate] [datetime] NULL,

*/


public class Employee {
	private int empId;
	private String empNo; //required field
	private String lastname;
	private String firstname;
	private String middlename;
	private String username;
	private String password;
	private String dateOfBirth;
	private String gender;
	private String civilStatus;
	private String nationality;
	private String street;
	private int cityId;
	private String email;
	private String mobileNo;
	private String telNo;
	private String birthPlace;
	private int provinceId;
	private int countryId;
	private String zipCode;
	private int jobTitleId;
	private int departmentId;
	private int divisionId;
	private int employeeTypeId;
	private String empStatus;
	private String employmentDate;
	private String endOfContract;
	
	
	private String sss;
	private String gsis;
	private String hdmf;
	private String tin;
	private String phic;
	private String taxstatus;
	private String picLoc;
	private int superVisor1Id;
	private int superVisor2Id;
	private int superVisor3Id;
	private Date creationDate;
	private String createdBy;
	
	private String jobTitleName;
	private String departmentName;
	private String divisionName;

	
	
	
	public String getJobTitleName() {
		return jobTitleName;
	}
	public void setJobTitleName(String jobTitleName) {
		this.jobTitleName = jobTitleName;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getDivisionName() {
		return divisionName;
	}
	public void setDivisionName(String divisionName) {
		this.divisionName = divisionName;
	}
	public int getCountryId() {
		return countryId;
	}
	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getEmpNo() {
		return empNo;
	}
	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getMiddlename() {
		return middlename;
	}
	public void setMiddlename(String middlename) {
		this.middlename = middlename;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getCivilStatus() {
		return civilStatus;
	}
	public void setCivilStatus(String civilStatus) {
		this.civilStatus = civilStatus;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public int getCityId() {
		return cityId;
	}
	public void setCityId(int cityId) {
		this.cityId = cityId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getTelNo() {
		return telNo;
	}
	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}
	public String getBirthPlace() {
		return birthPlace;
	}
	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}
	
	public int getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(int provinceId) {
		this.provinceId = provinceId;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public int getJobTitleId() {
		return jobTitleId;
	}
	public void setJobTitleId(int jobTitleId) {
		this.jobTitleId = jobTitleId;
	}
	
	public int getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}
	public int getDivisionId() {
		return divisionId;
	}
	public void setDivisionId(int divisionId) {
		this.divisionId = divisionId;
	}
	
	public int getEmployeeTypeId() {
		return employeeTypeId;
	}
	public void setEmployeeTypeId(int employeeTypeId) {
		this.employeeTypeId = employeeTypeId;
	}
	public String getEmpStatus() {
		return empStatus;
	}
	public void setEmpStatus(String empStatus) {
		this.empStatus = empStatus;
	}
	public String getEmploymentDate() {
		return employmentDate;
	}
	public void setEmploymentDate(String employmentDate) {
		this.employmentDate = employmentDate;
	}
	public String getEndOfContract() {
		return endOfContract;
	}
	public void setEndOfContract(String endOfContract) {
		this.endOfContract = endOfContract;
	}
	public String getSss() {
		return sss;
	}
	public void setSss(String sss) {
		this.sss = sss;
	}
	public String getGsis() {
		return gsis;
	}
	public void setGsis(String gsis) {
		this.gsis = gsis;
	}
	public String getHdmf() {
		return hdmf;
	}
	public void setHdmf(String hdmf) {
		this.hdmf = hdmf;
	}
	public String getTin() {
		return tin;
	}
	public void setTin(String tin) {
		this.tin = tin;
	}
	public String getPhic() {
		return phic;
	}
	public void setPhic(String phic) {
		this.phic = phic;
	}
	public String getTaxstatus() {
		return taxstatus;
	}
	public void setTaxstatus(String taxstatus) {
		this.taxstatus = taxstatus;
	}
	public String getPicLoc() {
		return picLoc;
	}
	public void setPicLoc(String picLoc) {
		this.picLoc = picLoc;
	}
	public int getSuperVisor1Id() {
		return superVisor1Id;
	}
	public void setSuperVisor1Id(int superVisor1Id) {
		this.superVisor1Id = superVisor1Id;
	}
	public int getSuperVisor2Id() {
		return superVisor2Id;
	}
	public void setSuperVisor2Id(int superVisor2Id) {
		this.superVisor2Id = superVisor2Id;
	}
	public int getSuperVisor3Id() {
		return superVisor3Id;
	}
	public void setSuperVisor3Id(int superVisor3Id) {
		this.superVisor3Id = superVisor3Id;
	}

}
