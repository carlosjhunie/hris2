package dai.hris.services.shiftingSchedule;

import java.sql.SQLException;
import java.util.ArrayList;


import dai.hris.model.ShiftingSchedule;

public interface IShiftingScheduleService {

	public  ArrayList<ShiftingSchedule> getAll() throws SQLException, Exception;
	public  void add(ShiftingSchedule shiftingSchedule) throws SQLException, Exception;
	public  void update(ShiftingSchedule shiftingSchedule) throws SQLException, Exception;
	
}
