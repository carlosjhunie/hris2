package dai.hris.service.filemanager.employeememo;

import java.sql.SQLException;
import java.util.ArrayList;

import dai.hris.dao.filemanager.EmployeeMemoDAO;
import dai.hris.model.EmployeeMemo;

public class EmployeeMemoService implements IEmployeeMemoService {

	public EmployeeMemo getEmployeeMemoByEmployeeMemoId(int employeeMemoId)
			throws SQLException, Exception {
		EmployeeMemoDAO eMDAO = new EmployeeMemoDAO();
		EmployeeMemo empMemo = new EmployeeMemo();
		empMemo = eMDAO.getEmployeeMemoByEmployeeMemoId(employeeMemoId);
		eMDAO.closeConnection();
		return empMemo;
		
	}

	public ArrayList<EmployeeMemo> getEmployeeMemoListByCreatedByEmpId(
			int createdByEmpId) throws SQLException, Exception {
		EmployeeMemoDAO eMDAO = new EmployeeMemoDAO();
		ArrayList<EmployeeMemo> empMemList = new ArrayList<EmployeeMemo>();
		empMemList = eMDAO.getEmployeeMemoListByCreatedByEmpId(createdByEmpId);
		eMDAO.closeConnection();
		return empMemList;
	}


	public ArrayList<EmployeeMemo> getEmployeeMemoListByToRecipientEmpId(
			int memoRecipientEmpId) throws SQLException, Exception {
		EmployeeMemoDAO eMDAO = new EmployeeMemoDAO();
		ArrayList<EmployeeMemo> empMemList = new ArrayList<EmployeeMemo>();
		empMemList = eMDAO.getEmployeeMemoListByToRecipientEmpId(memoRecipientEmpId);
		eMDAO.closeConnection();
		return empMemList;
	}


	public boolean add(EmployeeMemo employeeMemo) throws SQLException,
			Exception {
		boolean status;
		EmployeeMemoDAO eMDAO = new EmployeeMemoDAO();
		status = eMDAO.add(employeeMemo);
		eMDAO.closeConnection();
		return status;
	}

	@Override
	public boolean update(EmployeeMemo employeeMemo) throws SQLException,
			Exception {
		boolean status;
		EmployeeMemoDAO eMDAO = new EmployeeMemoDAO();
		status = eMDAO.update(employeeMemo);
		eMDAO.closeConnection();
		return status;
	}

}
