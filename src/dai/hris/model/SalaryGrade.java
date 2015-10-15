package dai.hris.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

public class SalaryGrade implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5185162266821280571L;

	private int salaryGradeId;
	private int salaryGradeNumber;
	private int step;
	private BigDecimal basicSalary;
	private Date createdAt;
	private Date updatedAt;
	
	public int getSalaryGradeId() {
		return salaryGradeId;
	}
	public void setSalaryGradeId(int salaryGradeId) {
		this.salaryGradeId = salaryGradeId;
	}
	public int getSalaryGradeNumber() {
		return salaryGradeNumber;
	}
	public void setSalaryGradeNumber(int salaryGradeNumber) {
		this.salaryGradeNumber = salaryGradeNumber;
	}
	public int getStep() {
		return step;
	}
	public void setStep(int step) {
		this.step = step;
	}
	public BigDecimal getBasicSalary() {
		return basicSalary;
	}
	public void setBasicSalary(BigDecimal basicSalary) {
		this.basicSalary = basicSalary;
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
				+ ((basicSalary == null) ? 0 : basicSalary.hashCode());
		result = prime * result
				+ ((createdAt == null) ? 0 : createdAt.hashCode());
		result = prime * result + salaryGradeId;
		result = prime * result + salaryGradeNumber;
		result = prime * result + step;
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
		SalaryGrade other = (SalaryGrade) obj;
		if (basicSalary == null) {
			if (other.basicSalary != null)
				return false;
		} else if (!basicSalary.equals(other.basicSalary))
			return false;
		if (createdAt == null) {
			if (other.createdAt != null)
				return false;
		} else if (!createdAt.equals(other.createdAt))
			return false;
		if (salaryGradeId != other.salaryGradeId)
			return false;
		if (salaryGradeNumber != other.salaryGradeNumber)
			return false;
		if (step != other.step)
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
		return "SalaryGrade [salaryGradeId=" + salaryGradeId
				+ ", salaryGradeNumber=" + salaryGradeNumber + ", step=" + step
				+ ", basicSalary=" + basicSalary + ", createdAt=" + createdAt
				+ ", updatedAt=" + updatedAt + "]";
	}
	
}
