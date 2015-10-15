package dai.hris.service.filemanager.leavetype;

import java.sql.SQLException;
import java.util.ArrayList;

import dai.hris.dao.filemanager.LeaveTypeDAO;
import dai.hris.model.LeaveType;


public class LeaveTypeService implements ILeaveTypeService {

	@Override
	public ArrayList<LeaveType> getAll() throws SQLException, Exception {
		
		LeaveTypeDAO dao = new LeaveTypeDAO();
		ArrayList<LeaveType> list = dao.getAll();
		dao.closeConnection();
		return list;
	}
	
	
	@Override
	public LeaveType getLeaveTypeByLeaveTypeId(int leaveTypeId) throws SQLException,
			Exception {

		LeaveTypeDAO dao = new LeaveTypeDAO();
		LeaveType leaveType = dao.getLeaveTypeByLeaveTypeId(leaveTypeId);
		dao.closeConnection();
		return leaveType;
	}
	

	@Override
	public void add(LeaveType leaveType) throws SQLException, Exception {
		LeaveTypeDAO dao = new LeaveTypeDAO();
		dao.add(leaveType);
		dao.closeConnection();
		
		

	}

	@Override
	public void delete(LeaveType leaveType) throws SQLException, Exception {
		LeaveTypeDAO dao = new LeaveTypeDAO();
		dao.delete(leaveType);
		dao.closeConnection();

	}

	@Override
	public void update(LeaveType leaveType) throws SQLException, Exception {
		LeaveTypeDAO dao = new LeaveTypeDAO();
		dao.update(leaveType);
		dao.closeConnection();

	}



}
