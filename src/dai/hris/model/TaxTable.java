package dai.hris.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

public class TaxTable implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1007768015002654248L;

	private int taxTableId;
	private String taxStatus;
	private BigDecimal salaryBase;
	private BigDecimal taxExemption;
	private BigDecimal taxAmount;
	private int taxRate;
	private String payrollType;
	private Date createdAt;
	private Date updatedAt;
	
	
	private float taxPercentage;
	
	
	public int getTaxTableId() {
		return taxTableId;
	}
	public void setTaxTableId(int taxTableId) {
		this.taxTableId = taxTableId;
	}
	public String getTaxStatus() {
		return taxStatus;
	}
	public void setTaxStatus(String taxStatus) {
		this.taxStatus = taxStatus;
	}
	public BigDecimal getSalaryBase() {
		return salaryBase;
	}
	public void setSalaryBase(BigDecimal salaryBase) {
		this.salaryBase = salaryBase;
	}
	public BigDecimal getTaxExemption() {
		return taxExemption;
	}
	public void setTaxExemption(BigDecimal taxExemption) {
		this.taxExemption = taxExemption;
	}
	public BigDecimal getTaxAmount() {
		return taxAmount;
	}
	public void setTaxAmount(BigDecimal taxAmount) {
		this.taxAmount = taxAmount;
	}
	public int getTaxRate() {
		return taxRate;
	}
	public void setTaxRate(int taxRate) {
		this.taxRate = taxRate;
	}
	public String getPayrollType() {
		return payrollType;
	}
	public void setPayrollType(String payrollType) {
		this.payrollType = payrollType;
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
		result = prime * result
				+ ((createdAt == null) ? 0 : createdAt.hashCode());
		result = prime * result
				+ ((payrollType == null) ? 0 : payrollType.hashCode());
		result = prime * result
				+ ((salaryBase == null) ? 0 : salaryBase.hashCode());
		result = prime * result
				+ ((taxAmount == null) ? 0 : taxAmount.hashCode());
		result = prime * result
				+ ((taxExemption == null) ? 0 : taxExemption.hashCode());
		result = prime * result + taxRate;
		result = prime * result
				+ ((taxStatus == null) ? 0 : taxStatus.hashCode());
		result = prime * result + taxTableId;
		result = prime * result
				+ ((updatedAt == null) ? 0 : updatedAt.hashCode());
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
		TaxTable other = (TaxTable) obj;
		if (createdAt == null) {
			if (other.createdAt != null)
				return false;
		} else if (!createdAt.equals(other.createdAt))
			return false;
		if (payrollType == null) {
			if (other.payrollType != null)
				return false;
		} else if (!payrollType.equals(other.payrollType))
			return false;
		if (salaryBase == null) {
			if (other.salaryBase != null)
				return false;
		} else if (!salaryBase.equals(other.salaryBase))
			return false;
		if (taxAmount == null) {
			if (other.taxAmount != null)
				return false;
		} else if (!taxAmount.equals(other.taxAmount))
			return false;
		if (taxExemption == null) {
			if (other.taxExemption != null)
				return false;
		} else if (!taxExemption.equals(other.taxExemption))
			return false;
		if (taxRate != other.taxRate)
			return false;
		if (taxStatus == null) {
			if (other.taxStatus != null)
				return false;
		} else if (!taxStatus.equals(other.taxStatus))
			return false;
		if (taxTableId != other.taxTableId)
			return false;
		if (updatedAt == null) {
			if (other.updatedAt != null)
				return false;
		} else if (!updatedAt.equals(other.updatedAt))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "TaxTable [taxTableId=" + taxTableId + ", taxStatus="
				+ taxStatus + ", salaryBase=" + salaryBase + ", taxExemption="
				+ taxExemption + ", taxAmount=" + taxAmount + ", taxRate="
				+ taxRate + ", payrollType=" + payrollType + ", createdAt="
				+ createdAt + ", updatedAt=" + updatedAt + "]";
	}
	public float getTaxPercentage() {
		return (float) taxRate/100;
	}

	
}
