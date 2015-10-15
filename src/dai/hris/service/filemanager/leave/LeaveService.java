package dai.hris.service.filemanager.leave;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import dai.hris.dao.leave.LeaveDAO;
import dai.hris.model.Leave;


public class LeaveService implements ILeaveService {

	
	public ArrayList<Leave> getAllLeavesByEmpId(int empId) throws SQLException, Exception {
		LeaveDAO dao = new LeaveDAO();
		ArrayList<Leave> list = dao.getAllLeavesByEmpId(empId);
		dao.closeConnection();
		return list;
	}

		public int add(Leave leave) throws SQLException, Exception {
		LeaveDAO dao = new LeaveDAO();
		int ctr;
		ctr = dao.add(leave);
		dao.closeConnection();
		return ctr;
	}
	
	public int update(Leave leave) throws SQLException, Exception {
		LeaveDAO dao = new LeaveDAO();
		int ctr;
		ctr = dao.update(leave);
		dao.closeConnection();
		return ctr;
	}
	
	public int approveLeave(Leave leave) throws SQLException, Exception {
		LeaveDAO dao = new LeaveDAO();
		int ctr;
		ctr = dao.approveLeave(leave);
		dao.closeConnection();
		return ctr;
	}
	
	public int approveLeaveHR(Leave leave) throws SQLException, Exception {
		LeaveDAO dao = new LeaveDAO();
		int ctr;
		ctr = dao.approveLeaveHR(leave);
		dao.closeConnection();
		return ctr;
	}
	
	
	public ArrayList<Leave> getAllLeavesForSvApprovalBySuperVisorId(int superVisorId) throws SQLException, Exception {
		LeaveDAO dao = new LeaveDAO();
		ArrayList<Leave> list = dao.getAllLeavesForSvApprovalBySuperVisorId(superVisorId);
		dao.closeConnection();
		return list;
	}
	
	public ArrayList<Leave> getAllLeavesForHRApproval() throws SQLException, Exception {
		LeaveDAO dao = new LeaveDAO();
		ArrayList<Leave> list = dao.getAllLeavesForHRApproval();
		dao.closeConnection();
		return list;
	}
	
	

	//
	/**
	 * get all leaves based from status (new, approved, rejected) from selected time period
	 * status = 2 (APPROVED)
	 * 
	 * returned leave count is in days
	 */
	public Map<String, Object> getAllLeavesCountByEmpId(int empId, Date startDate, Date endDate, int status) throws SQLException, Exception {
		LeaveDAO ld = new LeaveDAO();
		Map<String, Object> map = ld.getAllLeavesByEmpId(empId, startDate, endDate, status);
		

		return map;		
	}
	
	/**
	 * The the number of leaves banked or incurred by employee
	 * returned leave count is in days
	 */
	
	public double getAllEarnedLeavesCountByEmpId(int empId, Date endDate, double multiplyingFactor) throws SQLException, Exception {
		LeaveDAO ld = new LeaveDAO();
		Double allEarnedLeaves = ld.getAllEarnedLeavesByEmpId(empId, endDate, multiplyingFactor);

		return allEarnedLeaves;		
	}



	

}
