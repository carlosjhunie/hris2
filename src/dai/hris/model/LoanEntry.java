package dai.hris.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

public class LoanEntry   implements Serializable {

	private static final long serialVersionUID = 1L; 
	

	
	private int empId;
	private String dateFile;
	private int loanTypeId;
	private String referenceNo;
	private BigDecimal loanAmount;
	private BigDecimal monthlyAmortization;
	private int noOfMonthToPay;
	private String startDateToPay;
	private String endDateToPay;
	private String PNNo;
	private String periodFrom;
	private String PNDate;
	private String periodTo;
	private String remarks;
	private int loanEntryId;
	private String deductionFlagActive;
	
	
	public String getDeductionFlagActive() {
		return deductionFlagActive;
	}
	public void setDeductionFlagActive(String deductionFlagActive) {
		this.deductionFlagActive = deductionFlagActive;
	}
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public String getDateFile() {
		return dateFile;
	}
	public void setDateFile(String dateFile) {
		this.dateFile = dateFile;
	}
	public int getLoanTypeId() {
		return loanTypeId;
	}
	public void setLoanTypeId(int loanTypeId) {
		this.loanTypeId = loanTypeId;
	}
	public String getReferenceNo() {
		return referenceNo;
	}
	public void setReferenceNo(String referenceNo) {
		this.referenceNo = referenceNo;
	}
	public BigDecimal getLoanAmount() {
		return loanAmount;
	}
	public void setLoanAmount(BigDecimal loanAmount) {
		this.loanAmount = loanAmount;
	}
	public BigDecimal getMonthlyAmortization() {
		return monthlyAmortization;
	}
	public void setMonthlyAmortization(BigDecimal monthlyAmortization) {
		this.monthlyAmortization = monthlyAmortization;
	}
	public int getNoOfMonthToPay() {
		return noOfMonthToPay;
	}
	public void setNoOfMonthToPay(int noOfMonthToPay) {
		this.noOfMonthToPay = noOfMonthToPay;
	}
	public String getStartDateToPay() {
		return startDateToPay;
	}
	public void setStartDateToPay(String startDateToPay) {
		this.startDateToPay = startDateToPay;
	}
	public String getEndDateToPay() {
		return endDateToPay;
	}
	public void setEndDateToPay(String endDateToPay) {
		this.endDateToPay = endDateToPay;
	}
	public String getPNNo() {
		return PNNo;
	}
	public void setPNNo(String pNNo) {
		PNNo = pNNo;
	}
	public String getPeriodFrom() {
		return periodFrom;
	}
	public void setPeriodFrom(String periodFrom) {
		this.periodFrom = periodFrom;
	}
	public String getPeriodTo() {
		return periodTo;
	}
	public void setPeriodTo(String periodTo) {
		this.periodTo = periodTo;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public int getLoanEntryId() {
		return loanEntryId;
	}
	public void setLoanEntryId(int loanEntryId) {
		this.loanEntryId = loanEntryId;
	}
	public String getPNDate() {
		return PNDate;
	}
	public void setPNDate(String pNDate) {
		PNDate = pNDate;
	}
	
	

}
