package dai.hris.service.payroll.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import dai.hris.dao.payroll.TaxTableDAO;
import dai.hris.model.TaxTable;
import dai.hris.service.payroll.ITaxTableService;

public class TaxTableService implements ITaxTableService {

	@Override
	public int saveOrUpdate(TaxTable entry) throws SQLException {
		TaxTableDAO dao = new TaxTableDAO();
		int res = dao.saveOrUpdate(entry);
		dao.closeConnection();
		return res;
	}

	@Override
	public TaxTable getTaxTableBySalaryAndTaxStatusAndPayrollType(BigDecimal in, String taxStatus, String payrollType) throws SQLException {
		TaxTableDAO dao = new TaxTableDAO();
		TaxTable res = dao.getTaxTableBySalaryAndTaxStatusAndPayrollType(in, taxStatus, payrollType);
		dao.closeConnection();
		return res;
	}

	@Override
	public List<TaxTable> getAll() throws SQLException {
		TaxTableDAO dao = new TaxTableDAO();
		List<TaxTable> res = dao.getAll();
		dao.closeConnection();
		return res;
	}

}
