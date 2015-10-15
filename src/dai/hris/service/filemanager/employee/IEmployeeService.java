package dai.hris.service.filemanager.employee;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dai.hris.model.Employee;
import dai.hris.model.SearchEmployee;

public interface IEmployeeService {
	public ArrayList<Employee> getAll() throws SQLException, Exception;
	public Employee getEmployee(int empId) throws SQLException, Exception;
	public List<SearchEmployee> selectEmployee(String oSearchText) throws SQLException, Exception;
	public int checkExistingUserName(String username) throws SQLException, Exception;
	public List<Integer> getAllEmpIdsWithEmployeeModuleAccess() throws SQLException, Exception;
	public HashMap<String, Object> getEmployeeSupervisors(int empId) throws SQLException, Exception;
	public int add(Employee employee) throws SQLException, Exception;
	public int update(Employee employee) throws SQLException, Exception;
	public int updateExcludingPassword(Employee employee) throws SQLException, Exception;
	public boolean updatePassword(String username, String password) throws SQLException, Exception;
	public boolean verifyOldPassword(String empId, String password) throws SQLException, Exception;
}
