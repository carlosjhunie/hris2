package dai.hris.service.filemanager.division;

import java.sql.SQLException;
import java.util.ArrayList;

import dai.hris.model.Division;



public interface IDivisionService {
	
	public  ArrayList<Division> getAll() throws SQLException, Exception;
	public  void add(Division division) throws SQLException, Exception;
	public  void delete(Division division) throws SQLException, Exception;
	public  void update(Division division) throws SQLException, Exception;

}
