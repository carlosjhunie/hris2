package dai.hris.service.filemanager.country;

import java.sql.SQLException;
import java.util.ArrayList;

import dai.hris.dao.filemanager.CountryDAO;
import dai.hris.model.Country;


public class CountryService implements ICountryService {

	@Override
	public ArrayList<Country> getAll() throws SQLException, Exception {
		
		CountryDAO dao = new CountryDAO();
		ArrayList<Country> list = dao.getAll();
		dao.closeConnection();
		return list;
	}

	@Override
	public void add(Country country) throws SQLException, Exception {
		CountryDAO dao = new CountryDAO();
		dao.add(country);
		dao.closeConnection();
		
		

	}

	@Override
	public void delete(Country country) throws SQLException, Exception {
		CountryDAO dao = new CountryDAO();
		dao.delete(country);
		dao.closeConnection();

	}

	@Override
	public void update(Country country) throws SQLException, Exception {
		CountryDAO dao = new CountryDAO();
		dao.update(country);
		dao.closeConnection();

	}

}
