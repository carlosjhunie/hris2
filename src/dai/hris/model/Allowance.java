package dai.hris.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

public class Allowance implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7474128024052269059L;
	
	private int allowanceId;
	private String allowanceName;
	private String allowanceDescription;
	private BigDecimal amount;
	private boolean isTaxable;
	private Date createdAt;
	private Date updatedAt;

	public int getAllowanceId() {
		return allowanceId;
	}
	public void setAllowanceId(int allowanceId) {
		this.allowanceId = allowanceId;
	}
	public String getAllowanceName() {
		return allowanceName;
	}
	public void setAllowanceName(String allowanceName) {
		this.allowanceName = allowanceName;
	}
	public String getAllowanceDescription() {
		return allowanceDescription;
	}
	public void setAllowanceDescription(String allowanceDescription) {
		this.allowanceDescription = allowanceDescription;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public boolean isTaxable() {
		return isTaxable;
	}
	public void setTaxable(boolean isTaxable) {
		this.isTaxable = isTaxable;
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
		result = prime
				* result
				+ ((allowanceDescription == null) ? 0 : allowanceDescription
						.hashCode());
		result = prime * result + allowanceId;
		result = prime * result
				+ ((allowanceName == null) ? 0 : allowanceName.hashCode());
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result
				+ ((createdAt == null) ? 0 : createdAt.hashCode());
		result = prime * result + (isTaxable ? 1231 : 1237);
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
		Allowance other = (Allowance) obj;
		if (allowanceDescription == null) {
			if (other.allowanceDescription != null)
				return false;
		} else if (!allowanceDescription.equals(other.allowanceDescription))
			return false;
		if (allowanceId != other.allowanceId)
			return false;
		if (allowanceName == null) {
			if (other.allowanceName != null)
				return false;
		} else if (!allowanceName.equals(other.allowanceName))
			return false;
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
		if (isTaxable != other.isTaxable)
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
		return "Allowance [allowanceId=" + allowanceId + ", allowanceName="
				+ allowanceName + ", allowanceDescription="
				+ allowanceDescription + ", amount=" + amount + ", isTaxable="
				+ isTaxable + ", createdAt=" + createdAt + ", updatedAt="
				+ updatedAt + "]";
	}
	
}
