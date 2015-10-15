package dai.hris.service.payroll.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import dai.hris.dao.payroll.PhicContributionTableDAO;
import dai.hris.model.PhicContributionTable;
import dai.hris.service.payroll.IPhicContributionTableService;

public class PhicContributionTableService implements
		IPhicContributionTableService {

	@Override
	public int saveOrUpdate(PhicContributionTable entry) throws SQLException {
		PhicContributionTableDAO dao = new PhicContributionTableDAO();
		int res = dao.saveOrUpdate(entry);
		dao.closeConnection();
		return res;
	}

	@Override
	public PhicContributionTable getPhicContributionBySalary(BigDecimal in) throws SQLException {
		PhicContributionTableDAO dao = new PhicContributionTableDAO();
		PhicContributionTable res = dao.getPhicContributionBySalary(in);
		dao.closeConnection();
		return res;
	}

	@Override
	public List<PhicContributionTable> getAll() throws SQLException {
		PhicContributionTableDAO dao = new PhicContributionTableDAO();
		List<PhicContributionTable> res = dao.getAll();
		dao.closeConnection();
		return res;
	}

}
