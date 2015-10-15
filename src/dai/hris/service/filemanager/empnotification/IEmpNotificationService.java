package dai.hris.service.filemanager.empnotification;

import java.sql.SQLException;
import java.util.ArrayList;
import dai.hris.model.EmployeeNotification;

public interface IEmpNotificationService {
	public EmployeeNotification getEmployeeNotificationByEmployeeNotificationId(int empNotificationId) throws SQLException, Exception;
	public ArrayList<EmployeeNotification> getEmployeeNotificationListByCreatedByEmpId(int createdByEmpId) throws SQLException, Exception;
	public ArrayList<EmployeeNotification> getEmployeeNotificationListByToRecipientEmpId(int memoRecipientEmpId) throws SQLException, Exception;
	public boolean add(EmployeeNotification employeeNotification) throws SQLException, Exception;
	public boolean update(EmployeeNotification employeeNotification) throws SQLException, Exception;
	
}
