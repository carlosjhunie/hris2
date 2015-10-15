package dai.hris.service.filemanager.employeepayrollinfo;

import java.sql.SQLException;

import dai.hris.dao.filemanager.EmployeePayrollInfoDAO;
import dai.hris.model.EmployeePayrollInfo;

public class EmployeePayrollInfoService implements IEmployeePayrollInfoService {

	public EmployeePayrollInfo getEmployeePayrollInfoByEmpPayrollInfoByEmpId(
			int empId) throws SQLException, Exception {
		EmployeePayrollInfoDAO ePIDAO = new EmployeePayrollInfoDAO();
		EmployeePayrollInfo employeePayrollInfo = new EmployeePayrollInfo();
		employeePayrollInfo = ePIDAO.getEmployeePayrollInfoByEmpId(empId);
		ePIDAO.closeConnection();
		return employeePayrollInfo;
		
	}

	public EmployeePayrollInfo getEmployeePayrollInfo(
			int empId) throws SQLException, Exception {
		EmployeePayrollInfoDAO ePIDAO = new EmployeePayrollInfoDAO();
		EmployeePayrollInfo employeePayrollInfo = new EmployeePayrollInfo();
		employeePayrollInfo = ePIDAO.getEmployeePayrollInfo(empId);
		ePIDAO.closeConnection();
		return employeePayrollInfo;
	}
	
	public boolean isEmployeePayrollInfoExist(int empId) throws SQLException, Exception {
		boolean isExist = true;
		EmployeePayrollInfoDAO ePIDAO = new EmployeePayrollInfoDAO();		
		isExist = ePIDAO.isEmployeePayrollInfoExist(empId);
		ePIDAO.closeConnection();
		return isExist;
	}

	/**
	 * Note for BigDecimal setting for EmployeePayrollInfo..
	 * use String instead of double: ex: ePI.setAllowance(new BigDecimal("300.90"));
	 * 
	 * if you use this instead: ePI.setAllowance(new BigDecimal(300.90)), you will encounter "Error converting data type nvarchar to decimal" exception
	 * 
	 */
	public int add(EmployeePayrollInfo employeePayrollInfo)
			throws SQLException, Exception {
		int status;
		EmployeePayrollInfoDAO ePIDAO = new EmployeePayrollInfoDAO();
		status = ePIDAO.add(employeePayrollInfo);
		ePIDAO.closeConnection();
		return status;

	}

	
	/**
	 * Note for BigDecimal setting for EmployeePayrollInfo..
	 * use String instead of double: ex: ePI.setAllowance(new BigDecimal("300.90"));
	 * 
	 * if you use this instead: ePI.setAllowance(new BigDecimal(300.90)), you will encounter "Error converting data type nvarchar to decimal" exception
	 * 
	 */
	public int update(EmployeePayrollInfo employeePayrollInfo)
			throws SQLException, Exception {
		int status;
		EmployeePayrollInfoDAO ePIDAO = new EmployeePayrollInfoDAO();
		status = ePIDAO.update(employeePayrollInfo);
		ePIDAO.closeConnection();
		return status;
	}

}
