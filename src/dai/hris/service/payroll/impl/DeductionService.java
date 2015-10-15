package dai.hris.service.payroll.impl;

import java.sql.SQLException;
import java.util.List;

import dai.hris.dao.payroll.DeductionDAO;
import dai.hris.model.Deduction;
import dai.hris.service.payroll.IDeductionService;

public class DeductionService implements IDeductionService {

	@Override
	public int saveOrUpdate(Deduction dd) throws SQLException {
		DeductionDAO dao = new DeductionDAO();
		int res = dao.saveOrUpdate(dd);
		dao.closeConnection();
		return res;
	}

	@Override
	public Deduction getDeductionById(int ddId) throws SQLException {
		DeductionDAO dao = new DeductionDAO();
		Deduction res = dao.getDeductionById(ddId);
		dao.closeConnection();
		return res;
	}

	@Override
	public List<Deduction> getAll() throws SQLException {
		DeductionDAO dao = new DeductionDAO();
		List<Deduction> res = dao.getAll();
		dao.closeConnection();
		return res;
	}

}
