package dai.hris.service.filemanager.empnotification;

import java.sql.SQLException;
import java.util.ArrayList;
import dai.hris.dao.filemanager.EmpNotificationDAO;
import dai.hris.model.EmployeeNotification;

public class EmpNotificationService implements IEmpNotificationService {

	public EmployeeNotification getEmployeeNotificationByEmployeeNotificationId(int employeeNotificationId)
			throws SQLException, Exception {
		EmpNotificationDAO eMDAO = new EmpNotificationDAO();
		EmployeeNotification empMemo = new EmployeeNotification();
		empMemo = eMDAO.getEmployeeNotificationByEmployeeNotificationId(employeeNotificationId);
		eMDAO.closeConnection();
		return empMemo;
		
	}

	public ArrayList<EmployeeNotification> getEmployeeNotificationListByCreatedByEmpId(
			int createdByEmpId) throws SQLException, Exception {
		EmpNotificationDAO eMDAO = new EmpNotificationDAO();
		ArrayList<EmployeeNotification> empMemList = new ArrayList<EmployeeNotification>();
		empMemList = eMDAO.getEmployeeNotificationListByCreatedByEmpId(createdByEmpId);
		eMDAO.closeConnection();
		return empMemList;
	}


	public ArrayList<EmployeeNotification> getEmployeeNotificationListByToRecipientEmpId(
			int memoRecipientEmpId) throws SQLException, Exception {
		EmpNotificationDAO eMDAO = new EmpNotificationDAO();
		ArrayList<EmployeeNotification> empMemList = new ArrayList<EmployeeNotification>();
		empMemList = eMDAO.getEmployeeNotificationListByToRecipientEmpId(memoRecipientEmpId);
		eMDAO.closeConnection();
		return empMemList;
	}


	public boolean add(EmployeeNotification employeeNotification) throws SQLException,
			Exception {
		boolean status;
		EmpNotificationDAO eMDAO = new EmpNotificationDAO();
		status = eMDAO.add(employeeNotification);
		eMDAO.closeConnection();
		return status;
	}

	@Override
	public boolean update(EmployeeNotification employeeNotification) throws SQLException,
			Exception {
		boolean status;
		EmpNotificationDAO eMDAO = new EmpNotificationDAO();
		status = eMDAO.update(employeeNotification);
		eMDAO.closeConnection();
		return status;
	}

}
