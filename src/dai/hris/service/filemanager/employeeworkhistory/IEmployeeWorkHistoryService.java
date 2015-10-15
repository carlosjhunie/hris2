package dai.hris.service.filemanager.employeeworkhistory;

import java.sql.SQLException;
import java.util.ArrayList;

import dai.hris.model.EmployeeWorkHistory;

/**
 * 
 * @author danielpadilla
 *
 */
public interface IEmployeeWorkHistoryService {
	public EmployeeWorkHistory getEmployeeWorkHistoryByEmpWorkHistoryId(int empWorkHistoryId) throws SQLException, Exception;
	public ArrayList<EmployeeWorkHistory> getEmployeeWorkHistoryListByEmpId(int empId) throws SQLException, Exception;
	public int add(EmployeeWorkHistory employeeWorkHistory) throws SQLException, Exception;
	public int update(EmployeeWorkHistory employeeWorkHistory) throws SQLException, Exception;

}
