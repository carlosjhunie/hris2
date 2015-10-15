package dai.hris.service.filemanager.country;

import java.sql.SQLException;
import java.util.ArrayList;

import dai.hris.model.Country;



public interface ICountryService {
	
	public  ArrayList<Country> getAll() throws SQLException, Exception;
	public  void add(Country country) throws SQLException, Exception;
	public  void delete(Country country) throws SQLException, Exception;
	public  void update(Country country) throws SQLException, Exception;

}
