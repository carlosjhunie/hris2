package dai.hris.model;

import java.math.BigDecimal;
import java.sql.Date;

public class EmployeePayrollRun {
	private int employeePayrollRunId;
	private int empId;
	private String taxStatus;
	private BigDecimal basicPay;
	private BigDecimal absences;
	private BigDecimal tardiness;
	private BigDecimal undertime;
	private BigDecimal overtime;
	private BigDecimal leaveWOPay;
	private BigDecimal nightDiff;
	private BigDecimal holidayPay;
	private BigDecimal taxableIncome;
	private BigDecimal nontaxableIncome;
	private BigDecimal grossPay;
	private BigDecimal gsisEmployeeShare;
	private BigDecimal gsisEmployerShare;
	private BigDecimal philhealthEmployeeShare;
	private BigDecimal philhealthEmployerShare;
	private BigDecimal pagibigEmployeeShare;
	private BigDecimal pagibigEmployerShare;
	private BigDecimal loans;
	private BigDecimal otherDeductions;
	private BigDecimal otherTaxableIncome;
//	private BigDecimal otherNonTaxableIncome;
	private BigDecimal withholdingTax;
	private BigDecimal totalDeductions;
	private BigDecimal netPay;
	private String payrollCode;
	private int payrollPeriodId;
	private String payrollRunStatus;
	private String createdBy;
	private Date createDate;
	private String updatedBy;
	private Date updatedDate;
	private String lockedBy;
	private Date lockedDate;
	
	
	public BigDecimal getGsisEmployeeShare() {
		return gsisEmployeeShare;
	}
	public void setGsisEmployeeShare(BigDecimal gsisEmployeeShare) {
		this.gsisEmployeeShare = checkNull(gsisEmployeeShare);
	}
	public BigDecimal getGsisEmployerShare() {
		return gsisEmployerShare;
	}
	public void setGsisEmployerShare(BigDecimal gsisEmployerShare) {
		this.gsisEmployerShare = checkNull(gsisEmployerShare);
	}
	public BigDecimal getOtherTaxableIncome() {
		return otherTaxableIncome;
	}
	public void setOtherTaxableIncome(BigDecimal otherTaxableIncome) {
		this.otherTaxableIncome = checkNull(otherTaxableIncome);
	}
	public int getEmployeePayrollRunId() {
		return employeePayrollRunId;
	}
	public void setEmployeePayrollRunId(int employeePayrollRunId) {
		this.employeePayrollRunId = employeePayrollRunId;
	}
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public String getTaxStatus() {
		return taxStatus;
	}
	public void setTaxStatus(String taxStatus) {
		this.taxStatus = taxStatus;
	}
	public BigDecimal getBasicPay() {
		return basicPay;
	}
	public void setBasicPay(BigDecimal basicPay) {
		this.basicPay = checkNull(basicPay);
	}
	public BigDecimal getAbsences() {
		return absences;
	}
	public void setAbsences(BigDecimal absences) {
		this.absences = checkNull(absences);
	}
	public BigDecimal getTardiness() {
		return tardiness;
	}
	public void setTardiness(BigDecimal tardiness) {
		this.tardiness = checkNull(tardiness);
	}
	public BigDecimal getUndertime() {
		return undertime;
	}
	public void setUndertime(BigDecimal undertime) {
		this.undertime = checkNull(undertime);
	}
	public BigDecimal getOvertime() {
		return overtime;
	}
	public void setOvertime(BigDecimal overtime) {
		this.overtime = checkNull(overtime);
	}
	public BigDecimal getLeaveWOPay() {
		return leaveWOPay;
	}
	public void setLeaveWOPay(BigDecimal leaveWOPay) {
		this.leaveWOPay = checkNull(leaveWOPay);
	}
	public BigDecimal getNightDiff() {
		return nightDiff;
	}
	public void setNightDiff(BigDecimal nightDiff) {
		this.nightDiff = checkNull(nightDiff);
	}
	public BigDecimal getHolidayPay() {
		return holidayPay;
	}
	public void setHolidayPay(BigDecimal holidayPay) {
		this.holidayPay = checkNull(holidayPay);
	}
	public BigDecimal getTaxableIncome() {
		return taxableIncome;
	}
	public void setTaxableIncome(BigDecimal taxableIncome) {
		this.taxableIncome = checkNull(taxableIncome);
	}
	public BigDecimal getNontaxableIncome() {
		return nontaxableIncome;
	}
	public void setNontaxableIncome(BigDecimal nontaxableIncome) {
		this.nontaxableIncome = checkNull(nontaxableIncome);
	}
	public BigDecimal getGrossPay() {
		return grossPay;
	}
	public void setGrossPay(BigDecimal grossPay) {
		this.grossPay = checkNull(grossPay);
	}
	
	public BigDecimal getPhilhealthEmployeeShare() {
		return philhealthEmployeeShare;
	}
	public void setPhilhealthEmployeeShare(BigDecimal philhealthEmployeeShare) {
		this.philhealthEmployeeShare = checkNull(philhealthEmployeeShare);
	}
	public BigDecimal getPhilhealthEmployerShare() {
		return philhealthEmployerShare;
	}
	public void setPhilhealthEmployerShare(BigDecimal philhealthEmployerShare) {
		this.philhealthEmployerShare = checkNull(philhealthEmployerShare);
	}
	public BigDecimal getPagibigEmployeeShare() {
		return pagibigEmployeeShare;
	}
	public void setPagibigEmployeeShare(BigDecimal pagibigEmployeeShare) {
		this.pagibigEmployeeShare = checkNull(pagibigEmployeeShare);
	}
	public BigDecimal getPagibigEmployerShare() {
		return pagibigEmployerShare;
	}
	public void setPagibigEmployerShare(BigDecimal pagibigEmployerShare) {
		this.pagibigEmployerShare = checkNull(pagibigEmployerShare);
	}
	public BigDecimal getLoans() {
		return loans;
	}
	public void setLoans(BigDecimal loans) {
		this.loans = checkNull(loans);
	}
	public BigDecimal getOtherDeductions() {
		return otherDeductions;
	}
	public void setOtherDeductions(BigDecimal otherDeductions) {
		this.otherDeductions = checkNull(otherDeductions);
	}
	public BigDecimal getWithholdingTax() {
		return withholdingTax;
	}
	public void setWithholdingTax(BigDecimal withholdingTax) {
		this.withholdingTax = checkNull(withholdingTax);
	}
	public BigDecimal getTotalDeductions() {
		return totalDeductions;
	}
	public void setTotalDeductions(BigDecimal totalDeductions) {
		this.totalDeductions = checkNull(totalDeductions);
	}
	public BigDecimal getNetPay() {
		return netPay;
	}
	public void setNetPay(BigDecimal netPay) {
		this.netPay = checkNull(netPay);
	}
	public String getPayrollCode() {
		return payrollCode;
	}
	public void setPayrollCode(String payrollCode) {
		this.payrollCode = payrollCode;
	}
	public int getPayrollPeriodId() {
		return payrollPeriodId;
	}
	public void setPayrollPeriodId(int payrollPeriodId) {
		this.payrollPeriodId = payrollPeriodId;
	}
	public String getPayrollRunStatus() {
		return payrollRunStatus;
	}
	public void setPayrollRunStatus(String payrollRunStatus) {
		this.payrollRunStatus = payrollRunStatus;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	public String getLockedBy() {
		return lockedBy;
	}
	public void setLockedBy(String lockedBy) {
		this.lockedBy = lockedBy;
	}
	public Date getLockedDate() {
		return lockedDate;
	}
	public void setLockedDate(Date lockedDate) {
		this.lockedDate = lockedDate;
	}
 
	
	
	protected BigDecimal checkNull(BigDecimal value) {
		if (value == null) {
			value = BigDecimal.ZERO;
		}
		return value;
	}
//	public BigDecimal getOtherNonTaxableIncome() {
//		return otherNonTaxableIncome;
//	}
//	public void setOtherNonTaxableIncome(BigDecimal otherNonTaxableIncome) {
//		this.otherNonTaxableIncome = checkNull(otherNonTaxableIncome);
//	}
}
