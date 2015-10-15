package dai.hris.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

public class PhicContributionTable implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4495733046955267817L;

	private int phicContributionTableId;
	private BigDecimal salaryBase;
	private BigDecimal employeeShare;
	private BigDecimal employerShare;
	private BigDecimal totalMonthlyPremium;
	private Date createdAt;
	private Date updatedAt;
	public int getPhicContributionTableId() {
		return phicContributionTableId;
	}
	public void setPhicContributionTableId(int phicContributionTableId) {
		this.phicContributionTableId = phicContributionTableId;
	}
	public BigDecimal getSalaryBase() {
		return salaryBase;
	}
	public void setSalaryBase(BigDecimal salaryBase) {
		this.salaryBase = salaryBase;
	}
	public BigDecimal getEmployeeShare() {
		return employeeShare;
	}
	public void setEmployeeShare(BigDecimal employeeShare) {
		this.employeeShare = employeeShare;
	}
	public BigDecimal getEmployerShare() {
		return employerShare;
	}
	public void setEmployerShare(BigDecimal employerShare) {
		this.employerShare = employerShare;
	}
	public BigDecimal getTotalMonthlyPremium() {
		return totalMonthlyPremium;
	}
	public void setTotalMonthlyPremium(BigDecimal totalMonthlyPremium) {
		this.totalMonthlyPremium = totalMonthlyPremium;
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
				+ ((employeeShare == null) ? 0 : employeeShare.hashCode());
		result = prime * result
				+ ((employerShare == null) ? 0 : employerShare.hashCode());
		result = prime * result + phicContributionTableId;
		result = prime * result
				+ ((salaryBase == null) ? 0 : salaryBase.hashCode());
		result = prime
				* result
				+ ((totalMonthlyPremium == null) ? 0 : totalMonthlyPremium
						.hashCode());
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
		PhicContributionTable other = (PhicContributionTable) obj;
		if (createdAt == null) {
			if (other.createdAt != null)
				return false;
		} else if (!createdAt.equals(other.createdAt))
			return false;
		if (employeeShare == null) {
			if (other.employeeShare != null)
				return false;
		} else if (!employeeShare.equals(other.employeeShare))
			return false;
		if (employerShare == null) {
			if (other.employerShare != null)
				return false;
		} else if (!employerShare.equals(other.employerShare))
			return false;
		if (phicContributionTableId != other.phicContributionTableId)
			return false;
		if (salaryBase == null) {
			if (other.salaryBase != null)
				return false;
		} else if (!salaryBase.equals(other.salaryBase))
			return false;
		if (totalMonthlyPremium == null) {
			if (other.totalMonthlyPremium != null)
				return false;
		} else if (!totalMonthlyPremium.equals(other.totalMonthlyPremium))
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
		return "PhicContributionTable [phicContributionTableId="
				+ phicContributionTableId + ", salaryBase=" + salaryBase
				+ ", employeeShare=" + employeeShare + ", employerShare="
				+ employerShare + ", totalMonthlyPremium="
				+ totalMonthlyPremium + ", createdAt=" + createdAt
				+ ", updatedAt=" + updatedAt + "]";
	}
	
}
