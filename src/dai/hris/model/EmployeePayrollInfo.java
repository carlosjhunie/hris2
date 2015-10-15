package dai.hris.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class EmployeePayrollInfo implements Serializable{

	public EmployeePayrollInfo() {
		// TODO Auto-generated constructor stub
	}
	private static final long serialVersionUID = 1L; 
	
	
	private int empPayrollInfoId;
	private int empId;
	private BigDecimal monthlyRate;
	private BigDecimal dailyRate;
	private BigDecimal hourlyRate;	
	private BigDecimal foodAllowance;	
	private BigDecimal cola;
	private BigDecimal taxShield;
	private BigDecimal transAllowance;
	
	
	private BigDecimal gsisEmployeeShare;
	private BigDecimal gsisEmployerShare;
	private BigDecimal philhealthEmployeeShare;
	private BigDecimal philhealthEmployerShare;
	private BigDecimal pagibigEmployeeShare;
	private BigDecimal pagibigEmployerShare;
	
	private String payrollType;
	private String ban;
	private String bankNameBan;
	private int shiftingScheduleId;
	
	
	public int getShiftingScheduleId() {
		return shiftingScheduleId;
	}
	public void setShiftingScheduleId(int shiftingScheduleId) {
		this.shiftingScheduleId = shiftingScheduleId;
	}
	public int getEmpPayrollInfoId() {
		return empPayrollInfoId;
	}
	public void setEmpPayrollInfoId(int empPayrollInfoId) {
		this.empPayrollInfoId = empPayrollInfoId;
	}
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public BigDecimal getMonthlyRate() {
		return monthlyRate;
	}
	public void setMonthlyRate(BigDecimal monthlyRate) {
		this.monthlyRate = monthlyRate;
	}
	public BigDecimal getDailyRate() {
		return dailyRate;
	}
	public void setDailyRate(BigDecimal dailyRate) {
		this.dailyRate = dailyRate;
	}
	public BigDecimal getHourlyRate() {
		return hourlyRate;
	}
	public void setHourlyRate(BigDecimal hourlyRate) {
		this.hourlyRate = hourlyRate;
	}
	
	public BigDecimal getFoodAllowance() {
		return foodAllowance;
	}
	public void setFoodAllowance(BigDecimal foodAllowance) {
		this.foodAllowance = foodAllowance;
	}
	
	public BigDecimal getCola() {
		return cola;
	}
	public void setCola(BigDecimal cola) {
		this.cola = cola;
	}
	public BigDecimal getTaxShield() {
		return taxShield;
	}
	public void setTaxShield(BigDecimal taxShield) {
		this.taxShield = taxShield;
	}
	public BigDecimal getTransAllowance() {
		return transAllowance;
	}
	public void setTransAllowance(BigDecimal transAllowance) {
		this.transAllowance = transAllowance;
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
	public BigDecimal getGsisEmployeeShare() {
		return gsisEmployeeShare;
	}
	public void setGsisEmployeeShare(BigDecimal gsisEmployeeShare) {
		this.gsisEmployeeShare = gsisEmployeeShare;
	}
	public BigDecimal getGsisEmployerShare() {
		return gsisEmployerShare;
	}
	public void setGsisEmployerShare(BigDecimal gsisEmployerShare) {
		this.gsisEmployerShare = gsisEmployerShare;
	}
	public BigDecimal getPhilhealthEmployeeShare() {
		return philhealthEmployeeShare;
	}
	public void setPhilhealthEmployeeShare(BigDecimal philhealthEmployeeShare) {
		this.philhealthEmployeeShare = philhealthEmployeeShare;
	}
	public BigDecimal getPhilhealthEmployerShare() {
		return philhealthEmployerShare;
	}
	public void setPhilhealthEmployerShare(BigDecimal philhealthEmployerShare) {
		this.philhealthEmployerShare = philhealthEmployerShare;
	}
	public BigDecimal getPagibigEmployeeShare() {
		return pagibigEmployeeShare;
	}
	public void setPagibigEmployeeShare(BigDecimal pagibigEmployeeShare) {
		this.pagibigEmployeeShare = pagibigEmployeeShare;
	}
	public BigDecimal getPagibigEmployerShare() {
		return pagibigEmployerShare;
	}
	public void setPagibigEmployerShare(BigDecimal pagibigEmployerShare) {
		this.pagibigEmployerShare = pagibigEmployerShare;
	}
	public String getBankNameBan() {
		return bankNameBan;
	}
	public void setBankNameBan(String bankNameBan) {
		this.bankNameBan = bankNameBan;
	}
	
	
	
	
	
}
