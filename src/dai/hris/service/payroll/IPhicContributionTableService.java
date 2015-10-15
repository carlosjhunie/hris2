package dai.hris.service.payroll;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import dai.hris.model.PhicContributionTable;

public interface IPhicContributionTableService {

	public int saveOrUpdate(PhicContributionTable entry) throws SQLException;
	public PhicContributionTable getPhicContributionBySalary(BigDecimal in) throws SQLException;
	public List<PhicContributionTable> getAll() throws SQLException;
}
