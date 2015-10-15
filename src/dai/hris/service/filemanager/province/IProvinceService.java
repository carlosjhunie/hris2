package dai.hris.service.filemanager.province;

import java.sql.SQLException;
import java.util.ArrayList;

import dai.hris.model.Province;



public interface IProvinceService {
	
	public  ArrayList<Province> getAll() throws SQLException, Exception;
	public  void add(Province province) throws SQLException, Exception;
	public  void delete(Province province) throws SQLException, Exception;
	public  void update(Province province) throws SQLException, Exception;

}
