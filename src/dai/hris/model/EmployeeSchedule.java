package dai.hris.model;

import java.sql.Timestamp;

/*
 [empScheduleId] [bigint] IDENTITY(1,1) NOT NULL,
 [shiftingScheduleId] [int] NULL,
 [empId] [int] NULL,
 [scheduleDate] [date] NULL
 */

public class EmployeeSchedule {

	int empScheduleId;
	int shiftingScheduleId;
	int empId;
	String scheduleDate;
	String empName;
	String empShift;
	int superVisorId;
	int updatedBy;
	
	//For Leave and OOO
	String toDate;
	
	
	
	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public int getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(int updatedBy) {
		this.updatedBy = updatedBy;
	}

	public int getSuperVisorId() {
		return superVisorId;
	}

	public void setSuperVisorId(int superVisorId) {
		this.superVisorId = superVisorId;
	}

	public int getEmpScheduleId() {
		return empScheduleId;
	}

	public void setEmpScheduleId(int empScheduleId) {
		this.empScheduleId = empScheduleId;
	}

	public int getShiftingScheduleId() {
		return shiftingScheduleId;
	}

	public void setShiftingScheduleId(int shiftingScheduleId) {
		this.shiftingScheduleId = shiftingScheduleId;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getScheduleDate() {
		return scheduleDate;
	}

	public void setScheduleDate(String scheduleDate) {
		this.scheduleDate = scheduleDate;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmpShift() {
		return empShift;
	}

	public void setEmpShift(String empShift) {
		this.empShift = empShift;
	}

}
