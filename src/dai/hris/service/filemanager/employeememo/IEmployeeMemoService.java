package dai.hris.service.filemanager.employeememo;

import java.sql.SQLException;
import java.util.ArrayList;

import dai.hris.model.EmployeeMemo;

public interface IEmployeeMemoService {
	public EmployeeMemo getEmployeeMemoByEmployeeMemoId(int employeeMemoId) throws SQLException, Exception;
	public ArrayList<EmployeeMemo> getEmployeeMemoListByCreatedByEmpId(int createdByEmpId) throws SQLException, Exception;
	public ArrayList<EmployeeMemo> getEmployeeMemoListByToRecipientEmpId(int memoRecipientEmpId) throws SQLException, Exception;
	public boolean add(EmployeeMemo employeeMemo) throws SQLException, Exception;
	public boolean update(EmployeeMemo employeeMemo) throws SQLException, Exception;
	
}
