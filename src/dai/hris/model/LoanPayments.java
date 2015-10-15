package dai.hris.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class LoanPayments implements Serializable {

	private static final long serialVersionUID = 1L; 
	
	public LoanPayments() {
		// TODO Auto-generated constructor stub
	}
	
	private int loanPaymentId;
	private int loanEntryId;
	private String remarks;
	private BigDecimal paidAmount;
	private String paymentDate;
	private int payrollPeriodId;

	
	public int getPayrollPeriodId() {
		return payrollPeriodId;
	}
	public void setPayrollPeriodId(int payrollPeriodId) {
		this.payrollPeriodId = payrollPeriodId;
	}
	public int getLoanPaymentId() {
		return loanPaymentId;
	}
	public void setLoanPaymentId(int loanPaymentId) {
		this.loanPaymentId = loanPaymentId;
	}
	public int getLoanEntryId() {
		return loanEntryId;
	}
	public void setLoanEntryId(int loanEntryId) {
		this.loanEntryId = loanEntryId;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public BigDecimal getPaidAmount() {
		return paidAmount;
	}
	public void setPaidAmount(BigDecimal paidAmount) {
		this.paidAmount = paidAmount;
	}
	public String getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}
	

}
