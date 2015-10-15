package dai.hris.model;

import java.math.BigDecimal;
import java.sql.Date;

public class EmployeePayrollRunExt extends EmployeePayrollRun {
	//Employee
	private String empNo;
	private String lastname;
	private String firstname;
	private String middlename;
	private String dateOfBirth;
	private String gender;
	private String civilStatus;
	private String email;
	private String mobileNo;
	private String telNo;
	private int jobTitleId;
	private String jobTitle;
	private int departmentId;
	private String departmentName;
	private int divisionId;
	private String divisionName;
	private int employeeTypeId;
	private String employeeTypeName;
	private String empStatus;
	private String employmentDate;
	private String endOfContract;
	
	//EmployeePayrollInfo
	private BigDecimal monthlyRate;
	private BigDecimal dailyRate;
	private BigDecimal hourlyRate;	
	private BigDecimal foodAllowance;	
	private BigDecimal cola;
	private BigDecimal taxShield;
	private BigDecimal transAllowance;	
	private String payrollType;
	private String ban;
	private String bankNameBan;
	private int shiftingScheduleId;

	//PayrollPeriod
	private int payYear;
	private int payMonth;
	private Date fromDate;
	private Date toDate;
	private Date payDate;
	private int numWorkDays;
	private String payPeriod;
	private String deductGovtFlag;
	private String isForSecondHalf;
	
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
	public BigDecimal getMonthlyRate() {
		return monthlyRate;
	}
	public void setMonthlyRate(BigDecimal monthlyRate) {
		this.monthlyRate = checkNull(monthlyRate);
	}
	public BigDecimal getDailyRate() {
		return dailyRate;
	}
	public void setDailyRate(BigDecimal dailyRate) {
		this.dailyRate = checkNull(dailyRate);
	}
	public BigDecimal getHourlyRate() {
		return hourlyRate;
	}
	public void setHourlyRate(BigDecimal hourlyRate) {
		this.hourlyRate = checkNull(hourlyRate);
	}
	public BigDecimal getFoodAllowance() {
		return foodAllowance;
	}
	public void setFoodAllowance(BigDecimal foodAllowance) {
		this.foodAllowance = checkNull(foodAllowance);
	}
	public BigDecimal getCola() {
		return cola;
	}
	public void setCola(BigDecimal cola) {
		this.cola = checkNull(cola);
	}
	public BigDecimal getTaxShield() {
		return taxShield;
	}
	public void setTaxShield(BigDecimal taxShield) {
		this.taxShield = checkNull(taxShield);
	}
	public BigDecimal getTransAllowance() {
		return transAllowance;
	}
	public void setTransAllowance(BigDecimal transAllowance) {
		this.transAllowance = checkNull(transAllowance);
	}
	public String getPayrollType() {
		return payrollType;
	}
	public void setPayrollType(String payrollType) {
		this.payrollType = payrollType;
	}
	public String getBan() {
		return ban;
	}
	public void setBan(String ban) {
		this.ban = ban;
	}
	public int getShiftingScheduleId() {
		return shiftingScheduleId;
	}
	public void setShiftingScheduleId(int shiftingScheduleId) {
		this.shiftingScheduleId = shiftingScheduleId;
	}
	public int getPayYear() {
		return payYear;
	}
	public void setPayYear(int payYear) {
		this.payYear = payYear;
	}
	public int getPayMonth() {
		return payMonth;
	}
	public void setPayMonth(int payMonth) {
		this.payMonth = payMonth;
	}
	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	public Date getToDate() {
		return toDate;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	public Date getPayDate() {
		return payDate;
	}
	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}
	public int getNumWorkDays() {
		return numWorkDays;
	}
	public void setNumWorkDays(int numWorkDays) {
		this.numWorkDays = numWorkDays;
	}
	public String getPayPeriod() {
		return payPeriod;
	}
	public void setPayPeriod(String payPeriod) {
		this.payPeriod = payPeriod;
	}
	public String getJobTitle() {
		return jobTitle;
	}
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
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
	public String getEmployeeTypeName() {
		return employeeTypeName;
	}
	public void setEmployeeTypeName(String employeeTypeName) {
		this.employeeTypeName = employeeTypeName;
	}
	public String getBankNameBan() {
		return bankNameBan;
	}
	public void setBankNameBan(String bankNameBan) {
		this.bankNameBan = bankNameBan;
	}
	public String getDeductGovtFlag() {
		return deductGovtFlag;
	}
	public void setDeductGovtFlag(String deductGovtFlag) {
		this.deductGovtFlag = deductGovtFlag;
	}
	public String getIsForSecondHalf() {
		return isForSecondHalf;
	}
	public void setIsForSecondHalf(String isForSecondHalf) {
		this.isForSecondHalf = isForSecondHalf;
	}

	
 
}
