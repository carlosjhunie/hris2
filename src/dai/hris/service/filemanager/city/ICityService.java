package dai.hris.service.filemanager.city;

import java.sql.SQLException;
import java.util.ArrayList;

import dai.hris.model.City;



public interface ICityService {
	
	public  ArrayList<City> getAll() throws SQLException, Exception;
	public  void add(City city) throws SQLException, Exception;
	public  void delete(City city) throws SQLException, Exception;
	public  void update(City city) throws SQLException, Exception;

}
