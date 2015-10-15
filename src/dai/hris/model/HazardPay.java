package dai.hris.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class HazardPay implements Serializable {

	private static final long serialVersionUID = -5366901815394607122L;
	private int hazardPayId;
	private int salaryGrade;
	private BigDecimal basicSalary;
	private BigDecimal hazardPay;
    private BigDecimal withHoldingTax;
    private BigDecimal eamcDeduction;
    private BigDecimal netAmountDue;
    private int year;
    private int month;
    private String remarks;
    private int empId;
    
	public int getHazardPayId() {
		return hazardPayId;
	}
	public void setHazardPayId(int id) {
		this.hazardPayId = id;
	}
	public int getSalaryGrade() {
		return salaryGrade;
	}
	public void setSalaryGrade(int salaryGrade) {
		this.salaryGrade = salaryGrade;
	}
	public BigDecimal getBasicSalary() {
		return basicSalary;
	}
	public void setBasicSalary(BigDecimal basicSalary) {
		this.basicSalary = basicSalary;
	}
	public BigDecimal getHazardPay() {
		return hazardPay;
	}
	public void setHazardPay(BigDecimal hazardPay) {
		this.hazardPay = hazardPay;
	}
	public BigDecimal getWithHoldingTax() {
		return withHoldingTax;
	}
	public void setWithHoldingTax(BigDecimal withHoldingTax) {
		this.withHoldingTax = withHoldingTax;
	}
	public BigDecimal getEamcDeduction() {
		return eamcDeduction;
	}
	public void setEamcDeduction(BigDecimal eamcDeduction) {
		this.eamcDeduction = eamcDeduction;
	}
	public BigDecimal getNetAmountDue() {
		return netAmountDue;
	}
	public void setNetAmountDue(BigDecimal netAmountDue) {
		this.netAmountDue = netAmountDue;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int employeeId) {
		this.empId = employeeId;
	}
}
