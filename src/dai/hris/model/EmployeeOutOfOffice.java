package dai.hris.model;

import java.io.Serializable;
import java.sql.Date;

public class EmployeeOutOfOffice implements Serializable{

	public EmployeeOutOfOffice() {
		// TODO Auto-generated constructor stub
	}
	private static final long serialVersionUID = 1L; 
	
	/**
	 * 	[empOOOId] [int] IDENTITY(1,1) NOT NULL,
	[empId] [int] NOT NULL,
	[dateFrom] [varchar](50) NOT NULL,
	[dateTo] [varchar](50) NOT NULL,
	[titleActivity] [varchar](50) NULL,
	[provider] [varchar](50) NULL,
	[remarks] [varchar](50) NULL,
	[status] [int] NULL,
	[approvedBy] [int] NULL
	
	 */
	private int empOOOId;
	private int empId;
	private String dateFrom;
	private String dateTo;
	private String titleActivity;
	private String provider;
	private String remarks;
	private int noOfHours;
	private int status;
	private int approvedBy;
	private int secondaryApprover;
	private int createdBy;
	private Date createdDate;
	
	public int getEmpOOOId() {
		return empOOOId;
	}
	public void setEmpOOOId(int empOOOId) {
		this.empOOOId = empOOOId;
	}
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public String getDateFrom() {
		return dateFrom;
	}
	public void setDateFrom(String dateFrom) {
		this.dateFrom = dateFrom;
	}
	public String getDateTo() {
		return dateTo;
	}
	public void setDateTo(String dateTo) {
		this.dateTo = dateTo;
	}
	public String getTitleActivity() {
		return titleActivity;
	}
	public void setTitleActivity(String titleActivity) {
		this.titleActivity = titleActivity;
	}
	public String getProvider() {
		return provider;
	}
	public void setProvider(String provider) {
		this.provider = provider;
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
	public int getNoOfHours() {
		return noOfHours;
	}
	public void setNoOfHours(int noOfHours) {
		this.noOfHours = noOfHours;
	}
	public int getSecondaryApprover() {
		return secondaryApprover;
	}
	public void setSecondaryApprover(int secondaryApprover) {
		this.secondaryApprover = secondaryApprover;
	}	
	
	
	
}
