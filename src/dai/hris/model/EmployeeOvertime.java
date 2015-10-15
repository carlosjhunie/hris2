package dai.hris.model;

import java.sql.Date;

public class EmployeeOvertime {

	private int empOvertimeId;
	private int empId;
	private String dateRendered;
	private int noOfHours;
	private String remarks;
	private int status;
	private int approvedBy;
	private int secondaryApprover;
	private int createdBy;
	private Date createdDate;
	public int getEmpOvertimeId() {
		return empOvertimeId;
	}
	public void setEmpOvertimeId(int empOvertimeId) {
		this.empOvertimeId = empOvertimeId;
	}
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public String getDateRendered() {
		return dateRendered;
	}
	public void setDateRendered(String dateRendered) {
		this.dateRendered = dateRendered;
	}
	public int getNoOfHours() {
		return noOfHours;
	}
	public void setNoOfHours(int noOfHours) {
		this.noOfHours = noOfHours;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getApprovedBy() {
		return approvedBy;
	}
	public void setApprovedBy(int approvedBy) {
		this.approvedBy = approvedBy;
	}
	public int getSecondaryApprover() {
		return secondaryApprover;
	}
	public void setSecondaryApprover(int secondaryApprover) {
		this.secondaryApprover = secondaryApprover;
	}
	public int getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	
	
	
	
}
