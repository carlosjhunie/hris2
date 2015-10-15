package dai.hris.service.payroll;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dai.hris.model.PayrollPeriod;

public interface IPayrollPeriodService {
    public int saveOrUpdate(PayrollPeriod payrollPeriod) throws SQLException;
    public PayrollPeriod getPayrollPeriodById(int id) throws SQLException;
    public List<PayrollPeriod> getAll() throws SQLException;
    public int[] batchUpdate(List<PayrollPeriod> ppList) throws SQLException;
    public int[] batchInsert(List<PayrollPeriod> ppList) throws SQLException;
    /*public List<PayrollPeriod> getAllByEmployeeId(int employeeId) throws SQLException;
    public List<PayrollPeriod> getAllByJobTitleId(int jtId) throws SQLException;*/
    
    
	public ArrayList<PayrollPeriod> getAll(boolean includeLocked) throws SQLException;
    public ArrayList<PayrollPeriod> getAllGenerated() throws SQLException;
}
