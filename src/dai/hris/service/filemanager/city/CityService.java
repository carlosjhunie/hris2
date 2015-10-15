package dai.hris.service.filemanager.city;

import java.sql.SQLException;
import java.util.ArrayList;

import dai.hris.dao.filemanager.CityDAO;
import dai.hris.model.City;


public class CityService implements ICityService {

	@Override
	public ArrayList<City> getAll() throws SQLException, Exception {
		
		CityDAO dao = new CityDAO();
		ArrayList<City> list = dao.getAll();
		dao.closeConnection();
		return list;
	}

	@Override
	public void add(City city) throws SQLException, Exception {
		CityDAO dao = new CityDAO();
		dao.add(city);
		dao.closeConnection();
		
		

	}

	@Override
	public void delete(City city) throws SQLException, Exception {
		CityDAO dao = new CityDAO();
		dao.delete(city);
		dao.closeConnection();

	}

	@Override
	public void update(City city) throws SQLException, Exception {
		CityDAO dao = new CityDAO();
		dao.update(city);
		dao.closeConnection();

	}

}
