package dai.hris.model;

import java.sql.Date;

/*
 * 	[timeExId] [int] NOT NULL,
	[empNo] [int] NULL,
	[dateFrom] [datetime] NULL,
	[dateTo] [datetime] NULL,
	[purpose] [varchar](50) NULL,
	[provider] [varchar](50) NULL,
	[remarks] [varchar](50) NULL,
	[approvedBy] [int] NULL,
 * 
 */
public class TimeExclusion {
int timeExId;  //note this is INT
int empNo;
Date dateFrom;
Date dateTo;
String provider;
String purpose;
String remarks;
int approvedBy;

public int getTimeExId() {
	return timeExId;
}
public void setTimeExId(int timeExId) {
	this.timeExId = timeExId;
}
public int getEmpNo() {
	return empNo;
}
public void setEmpNo(int empNo) {
	this.empNo = empNo;
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
public int getApprovedBy() {
	return approvedBy;
}
public void setApprovedBy(int approvedBy) {
	this.approvedBy = approvedBy;
}
public String getPurpose() {
	return purpose;
}
public void setPurpose(String purpose) {
	this.purpose = purpose;
}

}
