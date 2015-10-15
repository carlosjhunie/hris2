package dai.hris.service.filemanager.timeintimeout;

import java.sql.SQLException;

import dai.hris.dao.filemanager.EmployeeTimeEntryDAO;
import dai.hris.model.EmployeeTimeEntry;

public class EmployeeTimeEntryService implements IEmployeeTimeEntryService {

	public EmployeeTimeEntry getEmployeeTimeEntryForCurrentDayByEmpNo(int empNo) throws SQLException, Exception  {
		EmployeeTimeEntryDAO empTimeEntryDAO = new EmployeeTimeEntryDAO();
		EmployeeTimeEntry empTimeEntry = empTimeEntryDAO.getEmployeeTimeEntryForCurrentDayByEmpNo(empNo);
		empTimeEntryDAO.closeConnection();
		return empTimeEntry;
	}



	public EmployeeTimeEntry getMostRecentEmployeeTimeEntry() throws SQLException, Exception  {
		EmployeeTimeEntryDAO empTimeEntryDAO = new EmployeeTimeEntryDAO();
		EmployeeTimeEntry empTimeEntry = empTimeEntryDAO.getMostRecentEmployeeTimeEntry();
		empTimeEntryDAO.closeConnection();
		return empTimeEntry;
	}
	
	

}
