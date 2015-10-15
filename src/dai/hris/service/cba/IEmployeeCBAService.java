package dai.hris.service.cba;

import java.sql.SQLException;
import java.util.ArrayList;

import dai.hris.model.EmployeeCBA;

public interface IEmployeeCBAService {
	public EmployeeCBA getEmployeeCbaByCbaId(int cbaId) throws SQLException, Exception;
	public ArrayList<EmployeeCBA> getEmployeeCBAByEmpId(int empId) throws SQLException, Exception;	
	public int add(EmployeeCBA empCBA) throws SQLException, Exception;
	public int update(EmployeeCBA empCBA) throws SQLException, Exception;

}
