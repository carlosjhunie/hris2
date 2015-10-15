package dai.hris.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

public class GsisContributionTable implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3393143708368360771L;

	private int gsisContributionTableId;
	private BigDecimal salaryBase;
	private Date createdAt;
	private Date updatedAt;
	public int getGsisContributionTableId() {
		return gsisContributionTableId;
	}
	public void setGsisContributionTableId(int gsisContributionTableId) {
		this.gsisContributionTableId = gsisContributionTableId;
	}
	public BigDecimal getSalaryBase() {
		return salaryBase;
	}
	public void setSalaryBase(BigDecimal salaryBase) {
		this.salaryBase = salaryBase;
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
}
