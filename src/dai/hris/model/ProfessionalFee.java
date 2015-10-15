package dai.hris.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

public class ProfessionalFee implements Serializable {

	private static final long serialVersionUID = 4141104579139186627L;
    private int professionalFeeId;
    private String officialReceiptNumber;
    private Date officialReceiptDate;
    private BigDecimal grossAmount;
    private BigDecimal withHoldingTax;
    private BigDecimal finalTax;
    private BigDecimal netAmountDue;
    private String patientId;
    private String patientName;
    private String remarks;
    private int empId;
    private Date createdAt;
    private Date updatedAt;
	public int getProfessionalFeeId() {
		return professionalFeeId;
	}
	public void setProfessionalFeeId(int professionalFeeId) {
		this.professionalFeeId = professionalFeeId;
	}
	public String getOfficialReceiptNumber() {
		return officialReceiptNumber;
	}
	public void setOfficialReceiptNumber(String officialReceiptNumber) {
		this.officialReceiptNumber = officialReceiptNumber;
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
	public void setNetAmountDue(BigDecimal netAmountDue) {
		this.netAmountDue = netAmountDue;
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
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((createdAt == null) ? 0 : createdAt.hashCode());
		result = prime * result + empId;
		result = prime * result + ((finalTax == null) ? 0 : finalTax.hashCode());
		result = prime * result + ((grossAmount == null) ? 0 : grossAmount.hashCode());
		result = prime * result + ((netAmountDue == null) ? 0 : netAmountDue.hashCode());
		result = prime * result + ((officialReceiptDate == null) ? 0 : officialReceiptDate.hashCode());
		result = prime * result + ((officialReceiptNumber == null) ? 0 : officialReceiptNumber.hashCode());
		result = prime * result + ((patientId == null) ? 0 : patientId.hashCode());
		result = prime * result + ((patientName == null) ? 0 : patientName.hashCode());
		result = prime * result + professionalFeeId;
		result = prime * result + ((remarks == null) ? 0 : remarks.hashCode());
		result = prime * result + ((updatedAt == null) ? 0 : updatedAt.hashCode());
		result = prime * result + ((withHoldingTax == null) ? 0 : withHoldingTax.hashCode());
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
		ProfessionalFee other = (ProfessionalFee) obj;
		if (createdAt == null) {
			if (other.createdAt != null)
				return false;
		} else if (!createdAt.equals(other.createdAt))
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
		if (officialReceiptNumber == null) {
			if (other.officialReceiptNumber != null)
				return false;
		} else if (!officialReceiptNumber.equals(other.officialReceiptNumber))
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
		if (professionalFeeId != other.professionalFeeId)
			return false;
		if (remarks == null) {
			if (other.remarks != null)
				return false;
		} else if (!remarks.equals(other.remarks))
			return false;
		if (updatedAt == null) {
			if (other.updatedAt != null)
				return false;
		} else if (!updatedAt.equals(other.updatedAt))
			return false;
		if (withHoldingTax == null) {
			if (other.withHoldingTax != null)
				return false;
		} else if (!withHoldingTax.equals(other.withHoldingTax))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "ProfessionalFee [professionalFeeId=" + professionalFeeId
				+ ", officialReceiptNumber=" + officialReceiptNumber
				+ ", officialReceiptDate=" + officialReceiptDate
				+ ", grossAmount=" + grossAmount + ", withHoldingTax="
				+ withHoldingTax + ", finalTax=" + finalTax + ", netAmountDue="
				+ netAmountDue + ", patientId=" + patientId + ", patientName="
				+ patientName + ", remarks=" + remarks + ", empId=" + empId
				+ ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}
    
}
