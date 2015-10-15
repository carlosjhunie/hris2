package dai.hris.service.payroll;

import java.sql.SQLException;
import java.util.List;

import dai.hris.model.Income;

public interface IIncomeService {

	public int saveOrUpdate(Income in) throws SQLException;
	public Income getIncomeById(int inId) throws SQLException;
	public List<Income> getAll() throws SQLException;
}
