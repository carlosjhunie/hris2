package dai.hris.model;

import java.sql.Date;

/**
 * 
 * 	[leaveId] [int] IDENTITY(1,1) NOT NULL,
	[empId] [int] NULL,
	[dateFiled] [datetime] NULL,
	[dateFrom] [datetime] NULL,
	[dateTo] [datetime] NULL,
	[noDays] [int] NULL,
	[remarks] [varchar](50) NULL,
	[approvedBy] [int] NULL,
	[secondaryApprover] [int] NULL,
	[need2Approvers] [varchar](1) NULL,
	[leaveTypeId] [int] NULL,
	[createdBy] [int] NULL,
	[createdDate] [datetime] NULL,
 *
 */
public class Leave {

	private int leaveId;
	private int empId;
	private Date dateFiled;
	private Date dateFrom;
	private Date dateTo;
	private int noDays;
	private String remarks;
	private int status;
	private int approvedBy;
	private int secondaryApprover;
	private String need2Approvers;
	private int leaveTypeId;
	private int createdBy;
	private Date createdDate;
	private String leaveType;
	
	
	public String getLeaveType() {
		return leaveType;
	}
	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}
	public int getLeaveId() {
		return leaveId;
	}
	public void setLeaveId(int leaveId) {
		this.leaveId = leaveId;
	}
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public Date getDateFiled() {
		return dateFiled;
	}
	public void setDateFiled(Date dateFiled) {
		this.dateFiled = dateFiled;
	}
	public Date getDateFrom() {
		return dateFrom;
	}
	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}
	public Date getDateTo() {
		return dateTo;
	}
	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}
	public int getNoDays() {
		return noDays;
	}
	public void setNoDays(int noDays) {
		this.noDays = noDays;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
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
	public String getNeed2Approvers() {
		return need2Approvers;
	}
	public void setNeed2Approvers(String need2Approvers) {
		this.need2Approvers = need2Approvers;
	}
	public int getLeaveTypeId() {
		return leaveTypeId;
	}
	public void setLeaveTypeId(int leaveTypeId) {
		this.leaveTypeId = leaveTypeId;
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
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
}
