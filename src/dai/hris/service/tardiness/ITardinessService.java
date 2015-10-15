package dai.hris.service.tardiness;

import java.sql.Date;
import java.sql.SQLException;

public interface ITardinessService {

	public int  getTotalNumberOfTardiness(int empId, Date dateFrom, Date dateTo) throws SQLException, Exception;
	
}
