package dai.hris.service.filemanager.leave;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import dai.hris.model.Leave;

public interface ILeaveService {
	public ArrayList<Leave> getAllLeavesByEmpId(int empId) throws SQLException, Exception;
	public int add(Leave leave) throws SQLException, Exception;
	public int update(Leave leave) throws SQLException, Exception;
	
	public int approveLeave(Leave leave) throws SQLException, Exception;
	public int approveLeaveHR(Leave leave) throws SQLException, Exception;
	
	public ArrayList<Leave> getAllLeavesForSvApprovalBySuperVisorId(int superVisorId) throws SQLException, Exception;
	public ArrayList<Leave> getAllLeavesForHRApproval() throws SQLException, Exception;

	public Map<String, Object> getAllLeavesCountByEmpId(int empId, Date startDate, Date endDate, int status) throws SQLException, Exception;
	public double getAllEarnedLeavesCountByEmpId(int empId, Date endDate, double multiplyingFactor) throws SQLException, Exception;
}
