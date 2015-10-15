package dai.hris.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class YearEndBonusCashGift implements Serializable {

	private static final long serialVersionUID = -1865172701750060747L;
	private int yearEndBonusId;
	private int salaryGrade;
	private String STEP;
	private BigDecimal basicSalary;
	private BigDecimal cashGift;
	private BigDecimal total;
	private BigDecimal basicSalaryOct31;
	private BigDecimal cashGift1;
	private BigDecimal firstHalf13thMonth;
	private BigDecimal firstHalfCashGift;
	private BigDecimal secondHalf13thMonth;
	private BigDecimal secondHalfCashGift;
	private BigDecimal totalYearEndBonusCashGift;
	private BigDecimal eamcCoopDeduction;
	private BigDecimal netAmountDue;
	private int year;
	private int empId;
	
	public int getYearEndBonusId() {
		return yearEndBonusId;
	}
	public void setYearEndBonusId(int id) {
		this.yearEndBonusId = id;
	}
	public int getSalaryGrade() {
		return salaryGrade;
	}
	public void setSalaryGrade(int salaryGrade) {
		this.salaryGrade = salaryGrade;
	}
	public String getSTEP() {
		return STEP;
	}
	public void setSTEP(String sTEP) {
		STEP = sTEP;
	}
	public BigDecimal getBasicSalary() {
		return basicSalary;
	}
	public void setBasicSalary(BigDecimal basicSalary) {
		this.basicSalary = basicSalary;
	}
	public BigDecimal getCashGift() {
		return cashGift;
	}
	public void setCashGift(BigDecimal cashGift) {
		this.cashGift = cashGift;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	public BigDecimal getBasicSalaryOct31() {
		return basicSalaryOct31;
	}
	public void setBasicSalaryOct31(BigDecimal basicSalaryOct31) {
		this.basicSalaryOct31 = basicSalaryOct31;
	}
	public BigDecimal getCashGift1() {
		return cashGift1;
	}
	public void setCashGift1(BigDecimal cashGift1) {
		this.cashGift1 = cashGift1;
	}
	public BigDecimal getFirstHalf13thMonth() {
		return firstHalf13thMonth;
	}
	public void setFirstHalf13thMonth(BigDecimal firstHalf13thMonth) {
		this.firstHalf13thMonth = firstHalf13thMonth;
	}
	public BigDecimal getFirstHalfCashGift() {
		return firstHalfCashGift;
	}
	public void setFirstHalfCashGift(BigDecimal firstHalfCashGift) {
		this.firstHalfCashGift = firstHalfCashGift;
	}
	public BigDecimal getSecondHalf13thMonth() {
		return secondHalf13thMonth;
	}
	public void setSecondHalf13thMonth(BigDecimal secondHalf13thMonth) {
		this.secondHalf13thMonth = secondHalf13thMonth;
	}
	public BigDecimal getSecondHalfCashGift() {
		return secondHalfCashGift;
	}
	public void setSecondHalfCashGift(BigDecimal secondHalfCashGift) {
		this.secondHalfCashGift = secondHalfCashGift;
	}
	public BigDecimal getTotalYearEndBonusCashGift() {
		return totalYearEndBonusCashGift;
	}
	public void setTotalYearEndBonusCashGift(BigDecimal totalYearEndBonusCashGift) {
		this.totalYearEndBonusCashGift = totalYearEndBonusCashGift;
	}
	public BigDecimal getEamcCoopDeduction() {
		return eamcCoopDeduction;
	}
	public void setEamcCoopDeduction(BigDecimal eamcCoopDeduction) {
		this.eamcCoopDeduction = eamcCoopDeduction;
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
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int employeeId) {
		this.empId = employeeId;
	}
}
