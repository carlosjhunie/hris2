package dai.hris.service.filemanager.holiday;

import java.sql.SQLException;
import java.util.ArrayList;

import dai.hris.model.Holiday;

public interface IHolidayService {
	
	public ArrayList<Holiday> getAll() throws SQLException, Exception;
	public int add(Holiday holiday) throws SQLException, Exception;
	public int update(Holiday holiday) throws SQLException, Exception;

}
