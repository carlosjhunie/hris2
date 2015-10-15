package dai.hris.service.filemanager.division;

import java.sql.SQLException;
import java.util.ArrayList;

import dai.hris.dao.filemanager.DivisionDAO;
import dai.hris.model.Division;


public class DivisionService implements IDivisionService {

	@Override
	public ArrayList<Division> getAll() throws SQLException, Exception {
		
		DivisionDAO dao = new DivisionDAO();
		ArrayList<Division> list = dao.getAll();
		dao.closeConnection();
		return list;
	}

	@Override
	public void add(Division division) throws SQLException, Exception {
		DivisionDAO dao = new DivisionDAO();
		dao.add(division);
		dao.closeConnection();
		
		

	}

	@Override
	public void delete(Division division) throws SQLException, Exception {
		DivisionDAO dao = new DivisionDAO();
		dao.delete(division);
		dao.closeConnection();

	}

	@Override
	public void update(Division division) throws SQLException, Exception {
		DivisionDAO dao = new DivisionDAO();
		dao.update(division);
		dao.closeConnection();

	}

}
