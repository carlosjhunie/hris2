package dai.hris.service.payroll;

import java.sql.SQLException;
import java.util.List;

import dai.hris.model.Deduction;

public interface IDeductionService {

	public int saveOrUpdate(Deduction dd) throws SQLException;
	public Deduction getDeductionById(int ddId) throws SQLException;
	public List<Deduction> getAll() throws SQLException;
}
