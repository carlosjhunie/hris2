package dai.hris.service.filemanager.employeepayrollinfo;

import java.sql.SQLException;
import java.util.ArrayList;

import dai.hris.model.EmployeePayrollInfo;

public interface IEmployeePayrollInfoService {
	public EmployeePayrollInfo getEmployeePayrollInfoByEmpPayrollInfoByEmpId(int empId) throws SQLException, Exception;
	public EmployeePayrollInfo getEmployeePayrollInfo(int empId) throws SQLException, Exception;
	public boolean isEmployeePayrollInfoExist(int empId) throws SQLException, Exception;
	public int add(EmployeePayrollInfo employeePayrollInfo) throws SQLException, Exception;
	public int update(EmployeePayrollInfo employeePayrollInfo) throws SQLException, Exception;
}
