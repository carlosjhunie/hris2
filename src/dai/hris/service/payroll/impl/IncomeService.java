package dai.hris.service.payroll.impl;

import java.sql.SQLException;
import java.util.List;

import dai.hris.dao.payroll.IncomeDAO;
import dai.hris.model.Income;
import dai.hris.service.payroll.IIncomeService;

public class IncomeService implements IIncomeService {

	@Override
	public int saveOrUpdate(Income in) throws SQLException {
		IncomeDAO dao = new IncomeDAO();
		int res = dao.saveOrUpdate(in);
		dao.closeConnection();
		return res;
	}

	@Override
	public Income getIncomeById(int inId) throws SQLException {
		IncomeDAO dao = new IncomeDAO();
		Income res = dao.getIncomeById(inId);
		dao.closeConnection();
		return res;
	}

	@Override
	public List<Income> getAll() throws SQLException {
		IncomeDAO dao = new IncomeDAO();
		List<Income> res = dao.getAll();
		dao.closeConnection();
		return res;
	}

}
