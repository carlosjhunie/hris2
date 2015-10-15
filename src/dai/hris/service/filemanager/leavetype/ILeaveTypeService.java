package dai.hris.service.filemanager.leavetype;

import java.sql.SQLException;
import java.util.ArrayList;

import dai.hris.model.LeaveType;



public interface ILeaveTypeService {
	
	public  ArrayList<LeaveType> getAll() throws SQLException, Exception;
	public  LeaveType getLeaveTypeByLeaveTypeId(int leaveTypeId) throws SQLException, Exception;
	public  void add(LeaveType LeaveType) throws SQLException, Exception;
	public  void delete(LeaveType LeaveType) throws SQLException, Exception;
	public  void update(LeaveType LeaveType) throws SQLException, Exception;

}
