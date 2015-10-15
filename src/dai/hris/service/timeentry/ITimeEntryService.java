package dai.hris.service.timeentry;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dai.hris.model.DisplayKioskTimeEntry;
import dai.hris.model.Employee;
import dai.hris.model.EmployeeSchedule;
import dai.hris.model.Resolution;
import dai.hris.model.TimeEntry;
import dai.hris.model.TimeEntryDispute;

public interface ITimeEntryService {
	public List<Employee> getEmployeeListPerSuperVisor(int supervisorId) throws SQLException, Exception;
//	public void resolveTimeEntryUsingScheduledTime(Resolution resolution) throws SQLException, Exception;	
	public void resolveTimeEntryUsingAssignedTime(Resolution resolution) throws SQLException, Exception;
	public void disputeTimeEntry(Resolution resolution) throws SQLException, Exception;
	public void disputeTimeEntryBySupervisor(Resolution resolution) throws SQLException, Exception;
	public void updateTimeEntryDispute(String newStatus, int timeEntryDisputeId) throws SQLException, Exception;	
	public void saveEmployeeSchedule(EmployeeSchedule empSched) throws SQLException, Exception;
	public void addEmployeeSchedule(EmployeeSchedule empSched) throws SQLException, Exception;
	public void editEmployeeSchedule(EmployeeSchedule empSched) throws SQLException, Exception;
	public List<TimeEntry> getAllTimeEntryBySuperVisor(int superVisorId) throws SQLException, Exception;
	public  List<TimeEntry> getAllTimeEntryByEmpId(int empId) throws SQLException, Exception;
	public  List<TimeEntryDispute> getAllTimeEntryDisputeByEmpId(int empId) throws SQLException, Exception;
	public  List<TimeEntryDispute> getAllTimeEntryDisputeBySupervisorId(int empId) throws SQLException, Exception;
	public  List<TimeEntryDispute> getAllTimeEntryDisputeHR() throws SQLException, Exception;
	public  List<TimeEntryDispute> getAllTimeEntryDisputeBySupervisorIdAndClockDate(int supervisorId, String clockDate) throws SQLException, Exception;
	public List<TimeEntry> getTimeEntryByDateAndSuperVisorAction(int superVisorId, String clockDate) throws SQLException, Exception;
	public DisplayKioskTimeEntry displayKioskTimeEntry(String sn, DisplayKioskTimeEntry display) throws Exception;
	public List<EmployeeSchedule> getEmployeeScheduleForTheMonth(int supervisorId) throws SQLException, Exception;
	public List<EmployeeSchedule> getEmployeeScheduleCalendar(int supervisorId) throws SQLException, Exception;
	public List<EmployeeSchedule> getEmployeeScheduleCalendarSpecificDate(int supervisorId, String scheduleDate) throws SQLException, Exception;
	public void deleteEmployeeSchedule(int empScheduleId) throws SQLException, Exception;
	public boolean checkIfCalendarHasSchedule(EmployeeSchedule empSched) throws SQLException, Exception;

}
