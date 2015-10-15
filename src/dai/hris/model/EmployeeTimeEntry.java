package dai.hris.model;

import java.sql.Timestamp;

/*
[dailyTimeEntryId] [int] IDENTITY(1,1) NOT NULL,
[empNo] [int] NULL,
[empLoginTS] [datetime] NULL,
[empLoginProtocol] [nvarchar](10) NULL,
[empLogoutTS] [datetime] NULL,
[empLogoutProtocol] [nvarchar](10) NULL,
[createTS] [datetime] NULL,
[createdBy] [nvarchar](20) NULL,
[lastUpdatedTS] [datetime] NULL,
[lastUpdatedBy] [nvarchar](20) NULL,
[comments] [nvarchar](70) NULL
*/

public class EmployeeTimeEntry {
long dailyTimeEntryId;
int empNo;
Timestamp empLoginTS;
String empLoginProtocol;
Timestamp empLogoutTS;
String empLogoutProtocol;
Timestamp createTS;
String createdBy;
Timestamp lastUpdatedTS;
String lastUpdatedBy;
String comments;

//these are added fields that are from Employee object. Will use this to display
//data in JSP for login/logout confirmation
//start
private String lastname;
private String firstname;
private int deptId;
//note-picloc is not added - just assumed as append empNo to filename for employee pics
//end


public long getDailyTimeEntryId() {
	return dailyTimeEntryId;
}
public void setDailyTimeEntryId(long dailyTimeEntryId) {
	this.dailyTimeEntryId = dailyTimeEntryId;
}
public int getEmpNo() {
	return empNo;
}
public void setEmpNo(int empNo) {
	this.empNo = empNo;
}
public Timestamp getEmpLoginTS() {
	return empLoginTS;
}
public void setEmpLoginTS(Timestamp empLoginTS) {
	this.empLoginTS = empLoginTS;
}
public String getEmpLoginProtocol() {
	return empLoginProtocol;
}
public void setEmpLoginProtocol(String empLoginProtocol) {
	this.empLoginProtocol = empLoginProtocol;
}
public Timestamp getEmpLogoutTS() {
	return empLogoutTS;
}
public void setEmpLogoutTS(Timestamp empLogoutTS) {
	this.empLogoutTS = empLogoutTS;
}
public String getEmpLogoutProtocol() {
	return empLogoutProtocol;
}
public void setEmpLogoutProtocol(String empLogoutProtocol) {
	this.empLogoutProtocol = empLogoutProtocol;
}
public Timestamp getCreateTS() {
	return createTS;
}
public void setCreateTS(Timestamp createTS) {
	this.createTS = createTS;
}
public String getCreatedBy() {
	return createdBy;
}
public void setCreatedBy(String createdBy) {
	this.createdBy = createdBy;
}
public Timestamp getLastUpdatedTS() {
	return lastUpdatedTS;
}
public void setLastUpdatedTS(Timestamp lastUpdatedTS) {
	this.lastUpdatedTS = lastUpdatedTS;
}
public String getLastUpdatedBy() {
	return lastUpdatedBy;
}
public void setLastUpdatedBy(String lastUpdatedBy) {
	this.lastUpdatedBy = lastUpdatedBy;
}
public String getComments() {
	return comments;
}
public void setComments(String comments) {
	this.comments = comments;
}
public String getLastname() {
	return lastname;
}
public void setLastname(String lastname) {
	this.lastname = lastname;
}
public String getFirstname() {
	return firstname;
}
public void setFirstname(String firstname) {
	this.firstname = firstname;
}
public int getDeptId() {
	return deptId;
}
public void setDeptId(int deptId) {
	this.deptId = deptId;
}
}
