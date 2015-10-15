package dai.hris.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

public class MedicareShareAllowance implements Serializable {

	private static final long serialVersionUID = 6480148950279501365L;
    private int medicareShareAllowanceId;
    private int numDays;
    private int ratePerDay;
    private BigDecimal netAmountDue;
    private Date date;
    private String remarks;
    private int empId;
    
	public int getMedicareShareAllowanceId() {
		return medicareShareAllowanceId;
	}
	public void setMedicareShareAllowanceId(int id) {
		this.medicareShareAllowanceId = id;
	}
	public int getNumDays() {
		return numDays;
	}
	public void setNumDays(int numDays) {
		this.numDays = numDays;
	}
	public int getRatePerDay() {
		return ratePerDay;
	}
	public void setRatePerDay(int ratePerDay) {
		this.ratePerDay = ratePerDay;
	}
	public BigDecimal getNetAmountDue() {
		return netAmountDue;
	}
	public void setNetAmountDue(BigDecimal netAmountDue) {
		this.netAmountDue = netAmountDue;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
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
    
}
