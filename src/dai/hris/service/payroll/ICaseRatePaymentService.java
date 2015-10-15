package dai.hris.service.payroll;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import dai.hris.model.CaseRatePayment;

public interface ICaseRatePaymentService {
    public void saveOrUpdate(CaseRatePayment crp) throws SQLException;
    public List<CaseRatePayment> getAllByEmployeeId(int empId) throws SQLException;
    public List<CaseRatePayment> getAllByEmpIdAndOrDateRange(int empId, Date from, Date to) throws SQLException;
}
