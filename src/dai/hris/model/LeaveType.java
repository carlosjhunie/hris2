package dai.hris.model;

import java.io.Serializable;

public class LeaveType  implements Serializable {
	private static final long serialVersionUID = 1L; 
	private int leaveTypeId;
	private String leaveTypeName;
	
	
	public int getLeaveTypeId() {
		return leaveTypeId;
	}
	public void setLeaveTypeId(int leaveTypeId) {
		this.leaveTypeId = leaveTypeId;
	}
	public String getLeaveTypeName() {
		return leaveTypeName;
	}
	public void setLeaveTypeName(String leaveTypeName) {
		this.leaveTypeName = leaveTypeName;
	}

	

}
