package dai.hris.service.filemanager.employeetype;

import java.sql.SQLException;
import java.util.ArrayList;

import dai.hris.model.EmployeeType;



public interface IEmployeeTypeService {
	
	public  ArrayList<EmployeeType> getAll() throws SQLException, Exception;
	public  void add(EmployeeType employeeType) throws SQLException, Exception;
	/*public  void delete(EmployeeType employeeType) throws SQLException, Exception;*/
	public  void update(EmployeeType employeeType) throws SQLException, Exception;

}
