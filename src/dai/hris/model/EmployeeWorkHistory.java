package dai.hris.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class EmployeeWorkHistory implements Serializable{

	public EmployeeWorkHistory() {
		// TODO Auto-generated constructor stub
	}
	private static final long serialVersionUID = 1L; 
	
	
	private int empWorkHistoryId;
	private int empId;
	private int yrsService;
	private String employerName;
	private String address;
	private int countryId;
	private String industry;
	private String position;
	private String remarks;
	private BigDecimal salary;
	private String salaryGrade;
	private String stepIncrement;
	
	
	public BigDecimal getSalary() {
		return salary;
	}
	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}
	public String getSalaryGrade() {
		return salaryGrade;
	}
	public void setSalaryGrade(String salaryGrade) {
		this.salaryGrade = salaryGrade;
	}
	public String getStepIncrement() {
		return stepIncrement;
	}
	public void setStepIncrement(String stepIncrement) {
		this.stepIncrement = stepIncrement;
	}
	public int getEmpWorkHistoryId() {
		return empWorkHistoryId;
	}
	public void setEmpWorkHistoryId(int empWorkHistoryId) {
		this.empWorkHistoryId = empWorkHistoryId;
	}
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public int getYrsService() {
		return yrsService;
	}
	public void setYrsService(int yrsService) {
		this.yrsService = yrsService;
	}
	public String getEmployerName() {
		return employerName;
	}
	public void setEmployerName(String employerName) {
		this.employerName = employerName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getCountryId() {
		return countryId;
	}
	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}
	public String getIndustry() {
		return industry;
	}
	public void setIndustry(String industry) {
		this.industry = industry;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	
	
	
	
}
