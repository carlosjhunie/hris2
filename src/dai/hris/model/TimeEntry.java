package dai.hris.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

public class TimeEntry implements Serializable{

	public TimeEntry() {
		// TODO Auto-generated constructor stub
	}
	private static final long serialVersionUID = 1L; 
	
	
	private int timeEntryId;
	private int empId;
	private int shiftId;
	private String timeIn;
	private String timeOut;
	private String resolutionRemarks;
	private int resolvedBy;
	private String empName;
	private String shiftScheduleDesc;
	private String shiftType;
	private int verifyCode;
	private String deviceNo;
	private String scheduleDate;
	private int shiftScheduleId;
	private String timeInHrsText;
	private String timeOutHrsText;
	
	
	public String getTimeInHrsText() {
		return timeInHrsText;
	}

	public void setTimeInHrsText(String timeInHrsText) {
		this.timeInHrsText = timeInHrsText;
	}

	public String getTimeOutHrsText() {
		return timeOutHrsText;
	}

	public void setTimeOutHrsText(String timeOutHrsText) {
		this.timeOutHrsText = timeOutHrsText;
	}

	public int getShiftScheduleId() {
		return shiftScheduleId;
	}

	public void setShiftScheduleId(int shiftScheduleId) {
		this.shiftScheduleId = shiftScheduleId;
	}

	public String getScheduleDate() {
		return scheduleDate;
	}

	public void setScheduleDate(String scheduleDate) {
		this.scheduleDate = scheduleDate;
	}
	public String getDeviceNo() {
		return deviceNo;
	}
	public void setDeviceNo(String deviceNo) {
		this.deviceNo = deviceNo;
	}
	public int getVerifyCode() {
		return verifyCode;
	}
	public void setVerifyCode(int verifyCode) {
		this.verifyCode = verifyCode;
	}
	public Timestamp getTimeInTS() {
		return timeInTS;
	}
	public void setTimeInTS(Timestamp timeInTS) {
		this.timeInTS = timeInTS;
	}
	public Timestamp getTimeOutTS() {
		return timeOutTS;
	}
	public void setTimeOutTS(Timestamp timeOutTS) {
		this.timeOutTS = timeOutTS;
	}
	private Timestamp timeInTS;
	private Timestamp timeOutTS;
	
	
	
	public String getShiftType() {
		return shiftType;
	}
	public void setShiftType(String shiftType) {
		this.shiftType = shiftType;
	}
	public String getShiftScheduleDesc() {
		return shiftScheduleDesc;
	}
	public void setShiftScheduleDesc(String shiftScheduleDesc) {
		this.shiftScheduleDesc = shiftScheduleDesc;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public int getTimeEntryId() {
		return timeEntryId;
	}
	public void setTimeEntryId(int timeEntryId) {
		this.timeEntryId = timeEntryId;
	}
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public int getShiftId() {
		return shiftId;
	}
	public void setShiftId(int shiftId) {
		this.shiftId = shiftId;
	}
	public String getTimeIn() {
		return timeIn;
	}
	public void setTimeIn(String timeIn) {
		this.timeIn = timeIn;
	}
	public String getTimeOut() {
		return timeOut;
	}
	public void setTimeOut(String timeOut) {
		this.timeOut = timeOut;
	}
	public String getResolutionRemarks() {
		return resolutionRemarks;
	}
	public void setResolutionRemarks(String resolutionRemarks) {
		this.resolutionRemarks = resolutionRemarks;
	}
	public int getResolvedBy() {
		return resolvedBy;
	}
	public void setResolvedBy(int resolvedBy) {
		this.resolvedBy = resolvedBy;
	}
	
	
	
	
	
	
}
