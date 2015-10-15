package dai.hris.model;

import java.io.Serializable;
import java.sql.Date;

public class PayrollPeriod implements Serializable {

	private static final long serialVersionUID = -3464911315552418925L;
	private int payrollPeriodId;
	private int payYear;
	private int payMonth;
	private String payrollType;
	private Date fromDate;
	private Date toDate;
	private Date payDate;
	private String payrollCode; //system generated, submitted to Finance Dept.
	private int numWorkDays;
	private String payPeriod;
	private Date lockedAt;
	private Date createdAt;
	private Date updatedAt;
	private String deductGovtFlag;
	private String status;
	private String isForSecondHalf;
	
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDeductGovtFlag() {
		return deductGovtFlag;
	}
	public void setDeductGovtFlag(String deductGovtFlag) {
		this.deductGovtFlag = deductGovtFlag;
	}
	public int getPayrollPeriodId() {
		return payrollPeriodId;
	}
	public void setPayrollPeriodId(int id) {
		this.payrollPeriodId = id;
	}
	public int getPayYear() {
		return payYear;
	}
	public void setPayYear(int payYear) {
		this.payYear = payYear;
	}
	public int getPayMonth() {
		return payMonth;
	}
	public void setPayMonth(int payMonth) {
		this.payMonth = payMonth;
	}
	public String getPayrollType() {
		return payrollType;
	}
	public void setPayrollType(String payrollType) {
		this.payrollType = payrollType;
	}
	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	public Date getToDate() {
		return toDate;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	public Date getPayDate() {
		return payDate;
	}
	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}
	public String getPayrollCode() {
		return payrollCode;
	}
	public void setPayrollCode(String payrollCode) {
		this.payrollCode = payrollCode;
	}
	public int getNumWorkDays() {
		return numWorkDays;
	}
	public void setNumWorkDays(int numWorkDays) {
		this.numWorkDays = numWorkDays;
	}
	public String getPayPeriod() {
		return payPeriod;
	}
	public void setPayPeriod(String payPeriod) {
		this.payPeriod = payPeriod;
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
	public String toString() {
		return "PayrollPeriod [payrollPeriodId=" + payrollPeriodId
				+ ", payYear=" + payYear + ", payMonth=" + payMonth
				+ ", payrollType=" + payrollType + ", toDate=" + toDate
				+ ", fromDate=" + fromDate + ", payDate=" + payDate
				+ ", payrollCode=" + payrollCode + ", numWorkDays="
				+ numWorkDays + ", payPeriod=" + payPeriod + ", createdAt="
				+ createdAt + ", updatedAt=" + updatedAt + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((createdAt == null) ? 0 : createdAt.hashCode());
		result = prime * result
				+ ((fromDate == null) ? 0 : fromDate.hashCode());
		result = prime * result + numWorkDays;
		result = prime * result + ((payDate == null) ? 0 : payDate.hashCode());
		result = prime * result + payMonth;
		result = prime * result
				+ ((payPeriod == null) ? 0 : payPeriod.hashCode());
		result = prime * result + payYear;
		result = prime * result
				+ ((payrollCode == null) ? 0 : payrollCode.hashCode());
		result = prime * result + payrollPeriodId;
		result = prime * result
				+ ((payrollType == null) ? 0 : payrollType.hashCode());
		result = prime * result + ((toDate == null) ? 0 : toDate.hashCode());
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
		PayrollPeriod other = (PayrollPeriod) obj;
		if (createdAt == null) {
			if (other.createdAt != null)
				return false;
		} else if (!createdAt.equals(other.createdAt))
			return false;
		if (fromDate == null) {
			if (other.fromDate != null)
				return false;
		} else if (!fromDate.equals(other.fromDate))
			return false;
		if (numWorkDays != other.numWorkDays)
			return false;
		if (payDate == null) {
			if (other.payDate != null)
				return false;
		} else if (!payDate.equals(other.payDate))
			return false;
		if (payMonth != other.payMonth)
			return false;
		if (payPeriod == null) {
			if (other.payPeriod != null)
				return false;
		} else if (!payPeriod.equals(other.payPeriod))
			return false;
		if (payYear != other.payYear)
			return false;
		if (payrollCode == null) {
			if (other.payrollCode != null)
				return false;
		} else if (!payrollCode.equals(other.payrollCode))
			return false;
		if (payrollPeriodId != other.payrollPeriodId)
			return false;
		if (payrollType == null) {
			if (other.payrollType != null)
				return false;
		} else if (!payrollType.equals(other.payrollType))
			return false;
		if (toDate == null) {
			if (other.toDate != null)
				return false;
		} else if (!toDate.equals(other.toDate))
			return false;
		if (updatedAt == null) {
			if (other.updatedAt != null)
				return false;
		} else if (!updatedAt.equals(other.updatedAt))
			return false;
		return true;
	}
	public Date getLockedAt() {
		return lockedAt;
	}
	public void setLockedAt(Date lockedAt) {
		this.lockedAt = lockedAt;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getIsForSecondHalf() {
		return isForSecondHalf;
	}
	public void setIsForSecondHalf(String isForSecondHalf) {
		this.isForSecondHalf = isForSecondHalf;
	}
}
