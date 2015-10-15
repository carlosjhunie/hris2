package dai.hris.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class ShiftingSchedule implements Serializable {

	public ShiftingSchedule() {
		// TODO Auto-generated constructor stub
	}
	
	private static final long serialVersionUID = 1L; 
	
	private int shiftingScheduleId;
	private String shiftType;
	private String timeIn;
	private String timeOut;
	private String description;
	private Timestamp timeInTimeStamp;
	private Timestamp tImeOutTimestamp;
	private Date scheduleDate;
	private int noOfHours;
	
	
	
	public int getNoOfHours() {
		return noOfHours;
	}
	public void setNoOfHours(int noOfHours) {
		this.noOfHours = noOfHours;
	}
	public Date getScheduleDate() {
		return scheduleDate;
	}
	public void setScheduleDate(Date scheduleDate) {
		this.scheduleDate = scheduleDate;
	}
	public int getShiftingScheduleId() {
		return shiftingScheduleId;
	}
	public void setShiftingScheduleId(int shiftingScheduleId) {
		this.shiftingScheduleId = shiftingScheduleId;
	}
	public String getShiftType() {
		return shiftType;
	}
	public void setShiftType(String shiftType) {
		this.shiftType = shiftType;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Timestamp getTimeInTimeStamp() {
		return timeInTimeStamp;
	}
	public void setTimeInTimeStamp(Timestamp timeInTimeStamp) {
		this.timeInTimeStamp = timeInTimeStamp;
	}
	public Timestamp gettImeOutTimestamp() {
		return tImeOutTimestamp;
	}
	public void settImeOutTimestamp(Timestamp tImeOutTimestamp) {
		this.tImeOutTimestamp = tImeOutTimestamp;
	}

	

}