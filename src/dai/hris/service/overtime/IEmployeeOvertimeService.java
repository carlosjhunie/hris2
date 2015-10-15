package dai.hris.service.overtime;

import java.sql.SQLException;
import java.util.ArrayList;

import dai.hris.model.EmployeeOvertime;
import dai.hris.model.Leave;

public interface IEmployeeOvertimeService {
	public EmployeeOvertime getEmployeeOutOfOfficeByEmpOvertimeId(int empOTId) throws SQLException, Exception;
	public ArrayList<EmployeeOvertime> getEmployeeOvertimeByEmpId(int empId) throws SQLException, Exception;
	public int add(EmployeeOvertime employeeOvertime) throws SQLException, Exception;
	public int update(EmployeeOvertime employeeOvertime) throws SQLException, Exception;
	
	public ArrayList<EmployeeOvertime> getAllOvertimeForSvApprovalBySuperVisorId(int superVisorId) throws SQLException, Exception;
}
