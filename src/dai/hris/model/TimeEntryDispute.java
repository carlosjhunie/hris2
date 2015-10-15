package dai.hris.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

public class TimeEntryDispute extends TimeEntry implements Serializable{

	public TimeEntryDispute() {
		// TODO Auto-generated constructor stub
	}
	private static final long serialVersionUID = 1L; 
	
	private int timeEntryDisputeId;
	private String approvalStatus;

	public int getTimeEntryDisputeId() {
		return timeEntryDisputeId;
	}

	public void setTimeEntryDisputeId(int timeEntryDisputeId) {
		this.timeEntryDisputeId = timeEntryDisputeId;
	}

	public String getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}	
	
	
	
}
