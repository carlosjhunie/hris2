package dai.hris.service.overtime;

import java.sql.SQLException;
import java.util.ArrayList;

import dai.hris.dao.leave.LeaveDAO;
import dai.hris.dao.overtime.EmployeeOvertimeDAO;
import dai.hris.model.EmployeeOvertime;
import dai.hris.model.Leave;


public class EmployeeOvertimeService implements IEmployeeOvertimeService {

	@Override
	public EmployeeOvertime getEmployeeOutOfOfficeByEmpOvertimeId(int empOTId)
			throws SQLException, Exception {
		EmployeeOvertimeDAO dao = new EmployeeOvertimeDAO();
		EmployeeOvertime employeeOvertime = dao.getEmployeeOutOfOfficeByEmpOvertimeId(empOTId);
		dao.closeConnection();
		return employeeOvertime;
	}

	@Override
	public ArrayList<EmployeeOvertime> getEmployeeOvertimeByEmpId(int empId)
			throws SQLException, Exception {
		EmployeeOvertimeDAO dao = new EmployeeOvertimeDAO();
		ArrayList<EmployeeOvertime> list = dao.getEmployeeOvertimeByEmpId(empId);
		dao.closeConnection();
		return list;
	}


	public int add(EmployeeOvertime employeeOvertime) throws SQLException, Exception {
		EmployeeOvertimeDAO dao = new EmployeeOvertimeDAO();
		int ctr;
		ctr = dao.add(employeeOvertime);
		dao.closeConnection();
		return ctr;
	}
	
	public int update(EmployeeOvertime employeeOvertime) throws SQLException, Exception {
		EmployeeOvertimeDAO dao = new EmployeeOvertimeDAO();
		int ctr;
		ctr = dao.update(employeeOvertime);
		dao.closeConnection();
		return ctr;
	}



	public ArrayList<EmployeeOvertime> getAllOvertimeForSvApprovalBySuperVisorId(int superVisorId) throws SQLException, Exception {
		EmployeeOvertimeDAO dao = new EmployeeOvertimeDAO();
		ArrayList<EmployeeOvertime> list = dao.getAllOvertimeForSvApprovalBySuperVisorId(superVisorId);
		dao.closeConnection();
		return list;
	}
	

}
