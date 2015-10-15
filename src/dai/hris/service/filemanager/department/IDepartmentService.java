package dai.hris.service.filemanager.department;

import java.sql.SQLException;
import java.util.ArrayList;

import dai.hris.model.Country;
import dai.hris.model.Department;



public interface IDepartmentService {
	
	public  ArrayList<Department> getAll() throws SQLException, Exception;
	public  void add(Department department) throws SQLException, Exception;
	public  void delete(Department department) throws SQLException, Exception;
	public  void update(Department department) throws SQLException, Exception;

}
