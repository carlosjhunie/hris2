package dai.hris.services.shiftingSchedule;

import java.sql.SQLException;
import java.util.ArrayList;

import dai.hris.dao.filemanager.ProvinceDAO;
import dai.hris.dao.shiftingSchedule.ShiftingScheduleDAO;
import dai.hris.model.Province;
import dai.hris.model.ShiftingSchedule;

public class ShiftingScheduleService implements IShiftingScheduleService {

	public ShiftingScheduleService() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public ArrayList<dai.hris.model.ShiftingSchedule> getAll()
			throws SQLException, Exception {
		ShiftingScheduleDAO dao = new ShiftingScheduleDAO();
		ArrayList<ShiftingSchedule> list = dao.getAll();
		dao.closeConnection();
		return list;
		
		
	}

	@Override
	public void add(dai.hris.model.ShiftingSchedule shiftingSchedule)
			throws SQLException, Exception {
		ShiftingScheduleDAO dao = new ShiftingScheduleDAO();
		dao.add(shiftingSchedule);
		dao.closeConnection();

	}
	
	@Override
	public void update(dai.hris.model.ShiftingSchedule shiftingSchedule)
			throws SQLException, Exception {
		ShiftingScheduleDAO dao = new ShiftingScheduleDAO();
		dao.update(shiftingSchedule);
		dao.closeConnection();

	}

}
