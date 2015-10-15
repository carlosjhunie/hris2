package dai.hris.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

public class CaseRatePayment implements Serializable {

	private static final long serialVersionUID = 8653434825156632681L;
	private int caseRatePaymentId;
	private Date officialReceiptDate;
	private BigDecimal grossAmount;
	private BigDecimal withHoldingTax;
	private BigDecimal finalTax;
	private BigDecimal netAmountDue;
	private int year;
	private int month;
	private String batch;
	private String patientId;
	private String patientName;
	private String remarks;
	private int empId;
	
	public int getCaseRatePaymentId() {
		return caseRatePaymentId;
	}
	public void setCaseRatePaymentId(int id) {
		this.caseRatePaymentId = id;
	}
	public Date getOfficialReceiptDate() {
		return officialReceiptDate;
	}
	public void setOfficialReceiptDate(Date officialReceiptDate) {
		this.officialReceiptDate = officialReceiptDate;
	}
	public BigDecimal getGrossAmount() {
		return grossAmount;
	}
	public void setGrossAmount(BigDecimal grossAmount) {
		this.grossAmount = grossAmount;
	}
	public BigDecimal getWithHoldingTax() {
		return withHoldingTax;
	}
	public void setWithHoldingTax(BigDecimal withHoldingTax) {
		this.withHoldingTax = withHoldingTax;
	}
	public BigDecimal getFinalTax() {
		return finalTax;
	}
	public void setFinalTax(BigDecimal finalTax) {
		this.finalTax = finalTax;
	}
	public BigDecimal getNetAmountDue() {
		return netAmountDue;
	}
	public void setNetAmountDue(BigDecimal netAmount) {
		this.netAmountDue = netAmount;
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
	public String getBatch() {
		return batch;
	}
	public void setBatch(String batch) {
		this.batch = batch;
	}
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
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
	@Override
	public String toString() {
		return "CaseRatePayment [caseRatePaymentId=" + caseRatePaymentId
				+ ", officialReceiptDate=" + officialReceiptDate
				+ ", grossAmount=" + grossAmount + ", withHoldingTax="
				+ withHoldingTax + ", finalTax=" + finalTax + ", netAmountDue="
				+ netAmountDue + ", year=" + year + ", month=" + month
				+ ", batch=" + batch + ", patientId=" + patientId
				+ ", patientName=" + patientName + ", remarks=" + remarks
				+ ", empId=" + empId + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((batch == null) ? 0 : batch.hashCode());
		result = prime * result + caseRatePaymentId;
		result = prime * result + empId;
		result = prime * result + ((finalTax == null) ? 0 : finalTax.hashCode());
		result = prime * result + ((grossAmount == null) ? 0 : grossAmount.hashCode());
		result = prime * result + month;
		result = prime * result + ((netAmountDue == null) ? 0 : netAmountDue.hashCode());
		result = prime * result + ((officialReceiptDate == null) ? 0 : officialReceiptDate.hashCode());
		result = prime * result + ((patientId == null) ? 0 : patientId.hashCode());
		result = prime * result + ((patientName == null) ? 0 : patientName.hashCode());
		result = prime * result + ((remarks == null) ? 0 : remarks.hashCode());
		result = prime * result + ((withHoldingTax == null) ? 0 : withHoldingTax.hashCode());
		result = prime * result + year;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CaseRatePayment other = (CaseRatePayment) obj;
		if (batch == null) {
			if (other.batch != null)
				return false;
		} else if (!batch.equals(other.batch))
			return false;
		if (caseRatePaymentId != other.caseRatePaymentId)
			return false;
		if (empId != other.empId)
			return false;
		if (finalTax == null) {
			if (other.finalTax != null)
				return false;
		} else if (!finalTax.equals(other.finalTax))
			return false;
		if (grossAmount == null) {
			if (other.grossAmount != null)
				return false;
		} else if (!grossAmount.equals(other.grossAmount))
			return false;
		if (month != other.month)
			return false;
		if (netAmountDue == null) {
			if (other.netAmountDue != null)
				return false;
		} else if (!netAmountDue.equals(other.netAmountDue))
			return false;
		if (officialReceiptDate == null) {
			if (other.officialReceiptDate != null)
				return false;
		} else if (!officialReceiptDate.equals(other.officialReceiptDate))
			return false;
		if (patientId == null) {
			if (other.patientId != null)
				return false;
		} else if (!patientId.equals(other.patientId))
			return false;
		if (patientName == null) {
			if (other.patientName != null)
				return false;
		} else if (!patientName.equals(other.patientName))
			return false;
		if (remarks == null) {
			if (other.remarks != null)
				return false;
		} else if (!remarks.equals(other.remarks))
			return false;
		if (withHoldingTax == null) {
			if (other.withHoldingTax != null)
				return false;
		} else if (!withHoldingTax.equals(other.withHoldingTax))
			return false;
		if (year != other.year)
			return false;
		return true;
	}
}
