package dai.hris.service.filemanager.employeeoutofoffice;

import java.sql.SQLException;
import java.util.ArrayList;

import dai.hris.model.EmployeeOutOfOffice;

public interface IEmployeeOutOfOfficeService {
	public EmployeeOutOfOffice getEmployeeOutOfOfficeByEmpOOOId(int empOOOId) throws SQLException, Exception;
	public ArrayList<EmployeeOutOfOffice> getEmployeeOutOfOfficeListByEmpId(int empId) throws SQLException, Exception;
	public int add(EmployeeOutOfOffice employeeOutOfOffice) throws SQLException, Exception;
	public int update(EmployeeOutOfOffice employeeOutOfOffice) throws SQLException, Exception;
	public int approveOOO(EmployeeOutOfOffice employeeOutOfOffice) throws SQLException, Exception;
	
	
	public ArrayList<EmployeeOutOfOffice> getAllEmployeeOOOForSvApprovalBySuperVisorId(int superVisorId) throws SQLException, Exception;
}
