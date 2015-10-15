package dai.hris.service.filemanager.timeintimeout;

import java.sql.SQLException;

import dai.hris.model.EmployeeTimeEntry;

public interface IEmployeeTimeEntryService {
	public EmployeeTimeEntry getEmployeeTimeEntryForCurrentDayByEmpNo(int empNo) throws SQLException, Exception ;
	public EmployeeTimeEntry getMostRecentEmployeeTimeEntry() throws SQLException, Exception;
}
