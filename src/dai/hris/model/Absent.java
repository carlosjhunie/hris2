package dai.hris.model;

import java.sql.Date;

/*
 * 	[absentId] [int] NOT NULL,
	[empNo] [int] NULL,
	[absentDate] [datetime] NULL,
	[superVisorId] [int] NULL,
	[remarks] [varchar](100) NULL,
 */

public class Absent {
int absentId;
int empNo;
Date absentdate;
int superVisorId;
String remarks;
public int getAbsentId() {
	return absentId;
}
public void setAbsentId(int absentId) {
	this.absentId = absentId;
}
public int getEmpNo() {
	return empNo;
}
public void setEmpNo(int empNo) {
	this.empNo = empNo;
}
public Date getAbsentdate() {
	return absentdate;
}
public void setAbsentdate(Date absentdate) {
	this.absentdate = absentdate;
}
public int getSuperVisorId() {
	return superVisorId;
}
public void setSuperVisorId(int superVisorId) {
	this.superVisorId = superVisorId;
}
public String getRemarks() {
	return remarks;
}
public void setRemarks(String remarks) {
	this.remarks = remarks;
}
}
