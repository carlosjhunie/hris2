package dai.hris.service.filemanager.holiday;

import java.sql.SQLException;
import java.util.ArrayList;

import dai.hris.dao.filemanager.HolidayDAO;
import dai.hris.model.Holiday;


public class HolidayService implements IHolidayService {

	
	public ArrayList<Holiday> getAll() throws SQLException, Exception {
		
		HolidayDAO dao = new HolidayDAO();
		ArrayList<Holiday> list = dao.getAll();
		dao.closeConnection();
		return list;
	}

	
	public int add(Holiday Holiday) throws SQLException, Exception {
		HolidayDAO dao = new HolidayDAO();
		int ctr = dao.add(Holiday);
		dao.closeConnection();		
		return ctr;
	}

	
	public int update(Holiday Holiday) throws SQLException, Exception {
		HolidayDAO dao = new HolidayDAO();
		int ctr = dao.update(Holiday);
		dao.closeConnection();
		return ctr;

	}

}
