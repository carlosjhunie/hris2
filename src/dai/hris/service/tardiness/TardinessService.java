package dai.hris.service.tardiness;

import java.sql.Date;
import java.sql.SQLException;
import dai.hris.dao.tardiness.TardinessDAO;


public class TardinessService implements ITardinessService {

	public TardinessService() {
		// TODO Auto-generated constructor stub
	}
	
	public int  getTotalNumberOfTardiness(int empId, Date dateFrom, Date dateTo) throws SQLException, Exception {
		TardinessDAO dao = new TardinessDAO();
		int totalTardinessMins = dao.getTotalNumberOfTardiness(empId, dateFrom, dateTo);
		dao.closeConnection();
		return totalTardinessMins;
	}


}
