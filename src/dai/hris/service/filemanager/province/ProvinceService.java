package dai.hris.service.filemanager.province;

import java.sql.SQLException;
import java.util.ArrayList;

import dai.hris.dao.filemanager.ProvinceDAO;
import dai.hris.model.Province;


public class ProvinceService implements IProvinceService {

	@Override
	public ArrayList<Province> getAll() throws SQLException, Exception {
		
		ProvinceDAO dao = new ProvinceDAO();
		ArrayList<Province> list = dao.getAll();
		dao.closeConnection();
		return list;
	}

	@Override
	public void add(Province province) throws SQLException, Exception {
		ProvinceDAO dao = new ProvinceDAO();
		dao.add(province);
		dao.closeConnection();
		
		

	}

	@Override
	public void delete(Province province) throws SQLException, Exception {
		ProvinceDAO dao = new ProvinceDAO();
		dao.delete(province);
		dao.closeConnection();

	}

	@Override
	public void update(Province province) throws SQLException, Exception {
		ProvinceDAO dao = new ProvinceDAO();
		dao.update(province);
		dao.closeConnection();

	}

}
