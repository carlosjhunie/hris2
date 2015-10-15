package dai.hris.model;

import java.sql.Date;

public class Holiday {
/**
 * 	[holidayId] [int] IDENTITY(1,1) NOT NULL,
	[holidayName] [varchar](50) NULL,
	[holidayDate] [varchar](50) NULL,
	[holidayType] [varchar](50) NULL,
	[createdBy] [int] NULL,
	[createdDate] [datetime] NULL,
 */
	private int holidayId;
	private String holidayName;
	private String holidayDate;
	private String holidayType;
	private int createdBy;
	private Date createdDate;
	public int getHolidayId() {
		return holidayId;
	}
	public void setHolidayId(int holidayId) {
		this.holidayId = holidayId;
	}
	public String getHolidayName() {
		return holidayName;
	}
	public void setHolidayName(String holidayName) {
		this.holidayName = holidayName;
	}
	public String getHolidayDate() {
		return holidayDate;
	}
	public void setHolidayDate(String holidayDate) {
		this.holidayDate = holidayDate;
	}
	public String getHolidayType() {
		return holidayType;
	}
	public void setHolidayType(String holidayType) {
		this.holidayType = holidayType;
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
