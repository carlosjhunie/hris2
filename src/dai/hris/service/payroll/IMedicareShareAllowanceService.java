package dai.hris.service.payroll;

import java.sql.SQLException;
import java.util.List;

import dai.hris.model.MedicareShareAllowance;

public interface IMedicareShareAllowanceService {
	public void add(MedicareShareAllowance msa) throws SQLException, Exception;
    public void update(MedicareShareAllowance msa) throws SQLException, Exception;
    public void delete(MedicareShareAllowance msa) throws SQLException, Exception;
    public int[] batchUpdate(List<MedicareShareAllowance> msaList) throws SQLException, Exception;
    public int[] batchInsert(List<MedicareShareAllowance> msaList) throws SQLException, Exception;
    public List<MedicareShareAllowance> getAllByEmployeeId(int empId) throws SQLException, Exception;
    public List<MedicareShareAllowance> getAllByJobTitleId(int jtId) throws SQLException, Exception;
}
