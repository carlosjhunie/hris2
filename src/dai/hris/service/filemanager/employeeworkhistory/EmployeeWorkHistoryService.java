package dai.hris.service.filemanager.employeeworkhistory;

import java.sql.SQLException;
import java.util.ArrayList;

import dai.hris.dao.filemanager.EmployeeWorkHistoryDAO;
import dai.hris.model.EmployeeWorkHistory;

/**
 * 
 * @author danielpadilla
 *
 */
public class EmployeeWorkHistoryService implements IEmployeeWorkHistoryService  {

	public EmployeeWorkHistory getEmployeeWorkHistoryByEmpWorkHistoryId(
			int empWorkHistoryId) throws SQLException, Exception {
		EmployeeWorkHistory eWH = new EmployeeWorkHistory();
		EmployeeWorkHistoryDAO eWHDAO = new EmployeeWorkHistoryDAO();
		eWH = eWHDAO.getEmployeeWorkHistoryByEmpWorkHistoryId(empWorkHistoryId);
		eWHDAO.closeConnection();
		return eWH;
		
	}

	public ArrayList<EmployeeWorkHistory> getEmployeeWorkHistoryListByEmpId(
			int empId) throws SQLException, Exception {
		ArrayList<EmployeeWorkHistory> eWHList = new ArrayList<EmployeeWorkHistory>();
		EmployeeWorkHistoryDAO eWHDAO = new EmployeeWorkHistoryDAO();
		eWHList = eWHDAO.getEmployeeWorkHistoryListByEmpId(empId);
		eWHDAO.closeConnection();
		return eWHList;
	}

	public int add(EmployeeWorkHistory employeeWorkHistory)
			throws SQLException, Exception {
		int status;
		EmployeeWorkHistoryDAO eWHDAO = new EmployeeWorkHistoryDAO();
		status = eWHDAO.add(employeeWorkHistory);
		return status;
	}

	public int update(EmployeeWorkHistory employeeWorkHistory)
			throws SQLException, Exception {
		int status;
		EmployeeWorkHistoryDAO eWHDAO = new EmployeeWorkHistoryDAO();
		status = eWHDAO.update(employeeWorkHistory);
		return status;  //not empWorkhistoryid
	}

}
