package dai.hris.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

public class Deduction implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -634787901735295993L;

	private int deductionId;
	private String deductionName;
	private BigDecimal amount;
	/*private boolean isForContractual;
	private boolean isForRegular;
	private boolean isForProbationary;
	private boolean isWeekly;
	private boolean isSemiMonthly;
	private boolean isMonthly;*/
	private String employeeType;
	private String payrollType;
	private String isForSecondHalf;
	private Date createdAt;
	private Date updatedAt;

	public int getDeductionId() {
		return deductionId;
	}
	public void setDeductionId(int deductionId) {
		this.deductionId = deductionId;
	}
	public String getDeductionName() {
		return deductionName;
	}
	public void setDeductionName(String deductionName) {
		this.deductionName = deductionName;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getEmployeeType() {
		return employeeType;
	}
	public void setEmployeeType(String employeeType) {
		this.employeeType = employeeType;
	}
	public String getPayrollType() {
		return payrollType;
	}
	public void setPayrollType(String payrollType) {
		this.payrollType = payrollType;
	}
	public String getIsForSecondHalf() {
		return isForSecondHalf;
	}
	public void setIsForSecondHalf(String isForSecondHalf) {
		this.isForSecondHalf = isForSecondHalf;
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
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result
				+ ((createdAt == null) ? 0 : createdAt.hashCode());
		result = prime * result + deductionId;
		result = prime * result
				+ ((deductionName == null) ? 0 : deductionName.hashCode());
		result = prime * result
				+ ((employeeType == null) ? 0 : employeeType.hashCode());
		result = prime * result
				+ ((isForSecondHalf == null) ? 0 : isForSecondHalf.hashCode());
		result = prime * result
				+ ((payrollType == null) ? 0 : payrollType.hashCode());
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
		Deduction other = (Deduction) obj;
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (!amount.equals(other.amount))
			return false;
		if (createdAt == null) {
			if (other.createdAt != null)
				return false;
		} else if (!createdAt.equals(other.createdAt))
			return false;
		if (deductionId != other.deductionId)
			return false;
		if (deductionName == null) {
			if (other.deductionName != null)
				return false;
		} else if (!deductionName.equals(other.deductionName))
			return false;
		if (employeeType == null) {
			if (other.employeeType != null)
				return false;
		} else if (!employeeType.equals(other.employeeType))
			return false;
		if (isForSecondHalf == null) {
			if (other.isForSecondHalf != null)
				return false;
		} else if (!isForSecondHalf.equals(other.isForSecondHalf))
			return false;
		if (payrollType == null) {
			if (other.payrollType != null)
				return false;
		} else if (!payrollType.equals(other.payrollType))
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
		return "Deduction [deductionId=" + deductionId + ", deductionName="
				+ deductionName + ", amount=" + amount + ", employeeType="
				+ employeeType + ", payrollType=" + payrollType
				+ ", isForSecondHalf=" + isForSecondHalf + ", createdAt="
				+ createdAt + ", updatedAt=" + updatedAt + "]";
	}
	
}
