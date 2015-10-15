package dai.hris.service.filemanager.employeeoutofoffice;

import java.sql.SQLException;
import java.util.ArrayList;

import dai.hris.dao.filemanager.employeeoutofoffice.EmployeeOutOfOfficeDAO;
import dai.hris.model.EmployeeOutOfOffice;

public class EmployeeOutOfOfficeService implements IEmployeeOutOfOfficeService {

	public EmployeeOutOfOffice getEmployeeOutOfOfficeByEmpOOOId(int empOOOId)
			throws SQLException, Exception {
		EmployeeOutOfOffice eOOO = new EmployeeOutOfOffice();
		EmployeeOutOfOfficeDAO eOOODAO = new EmployeeOutOfOfficeDAO();
		eOOO = eOOODAO.getEmployeeOutOfOfficeByEmpOOOId(empOOOId);
		eOOODAO.closeConnection();
		return eOOO;
	}

	public ArrayList<EmployeeOutOfOffice> getEmployeeOutOfOfficeListByEmpId(
			int empId) throws SQLException, Exception {
		ArrayList<EmployeeOutOfOffice> eOOOList= new ArrayList<EmployeeOutOfOffice>();
		EmployeeOutOfOfficeDAO eOOODAO = new EmployeeOutOfOfficeDAO();
		eOOOList = eOOODAO.getEmployeeOutOfOfficeListByEmpId(empId);
		eOOODAO.closeConnection();
		return eOOOList;		
	}

	public int add(EmployeeOutOfOffice employeeOutOfOffice)
			throws SQLException, Exception {
		int status;
		EmployeeOutOfOfficeDAO eOOODAO = new EmployeeOutOfOfficeDAO();
		status = eOOODAO.add(employeeOutOfOffice);
		eOOODAO.closeConnection();
		return status;
		
	}

	public int update(EmployeeOutOfOffice employeeOutOfOffice)
			throws SQLException, Exception {
		int status;
		EmployeeOutOfOfficeDAO eOOODAO = new EmployeeOutOfOfficeDAO();
		status = eOOODAO.update(employeeOutOfOffice);
		eOOODAO.closeConnection();
		return status;
	}
	
	public int approveOOO(EmployeeOutOfOffice employeeOutOfOffice)
			throws SQLException, Exception {
		int status;
		EmployeeOutOfOfficeDAO eOOODAO = new EmployeeOutOfOfficeDAO();
		status = eOOODAO.approveOOO(employeeOutOfOffice);
		eOOODAO.closeConnection();
		return status;
	}
	
		
	public ArrayList<EmployeeOutOfOffice> getAllEmployeeOOOForSvApprovalBySuperVisorId(int superVisorId) throws SQLException, Exception {
		ArrayList<EmployeeOutOfOffice> eOOOList= new ArrayList<EmployeeOutOfOffice>();
		EmployeeOutOfOfficeDAO eOOODAO = new EmployeeOutOfOfficeDAO();
		eOOOList = eOOODAO.getAllEmployeeOOOForSvApprovalBySuperVisorId(superVisorId);
		eOOODAO.closeConnection();
		return eOOOList;
	}

}
