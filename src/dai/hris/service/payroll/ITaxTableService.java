package dai.hris.service.payroll;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import dai.hris.model.TaxTable;

public interface ITaxTableService {

	public int saveOrUpdate(TaxTable entry) throws SQLException;
	public TaxTable getTaxTableBySalaryAndTaxStatusAndPayrollType(BigDecimal in, String taxStatus, String payrollType) throws SQLException;
	public List<TaxTable> getAll() throws SQLException;
}
