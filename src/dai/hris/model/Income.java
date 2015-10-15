package dai.hris.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

public class Income implements Serializable {

	private static final long serialVersionUID = 8776520063984896827L;

	private int incomeId;
	private String incomeName;
	private BigDecimal amount;
	private String isTaxable;
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

	public int getIncomeId() {
		return incomeId;
	}
	public void setIncomeId(int incomeId) {
		this.incomeId = incomeId;
	}
	public String getIncomeName() {
		return incomeName;
	}
	public void setIncomeName(String incomeName) {
		this.incomeName = incomeName;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getIsTaxable() {
		return isTaxable;
	}
	public void setIsTaxable(String isTaxable) {
		this.isTaxable = isTaxable;
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
		result = prime * result
				+ ((employeeType == null) ? 0 : employeeType.hashCode());
		result = prime * result + incomeId;
		result = prime * result
				+ ((incomeName == null) ? 0 : incomeName.hashCode());
		result = prime * result
				+ ((isForSecondHalf == null) ? 0 : isForSecondHalf.hashCode());
		result = prime * result
				+ ((isTaxable == null) ? 0 : isTaxable.hashCode());
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
		Income other = (Income) obj;
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
		if (employeeType == null) {
			if (other.employeeType != null)
				return false;
		} else if (!employeeType.equals(other.employeeType))
			return false;
		if (incomeId != other.incomeId)
			return false;
		if (incomeName == null) {
			if (other.incomeName != null)
				return false;
		} else if (!incomeName.equals(other.incomeName))
			return false;
		if (isForSecondHalf == null) {
			if (other.isForSecondHalf != null)
				return false;
		} else if (!isForSecondHalf.equals(other.isForSecondHalf))
			return false;
		if (isTaxable == null) {
			if (other.isTaxable != null)
				return false;
		} else if (!isTaxable.equals(other.isTaxable))
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
		return "Income [incomeId=" + incomeId + ", incomeName=" + incomeName
				+ ", amount=" + amount + ", isTaxable=" + isTaxable
				+ ", employeeType=" + employeeType + ", payrollType="
				+ payrollType + ", isForSecondHalf=" + isForSecondHalf
				+ ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}
	
}
