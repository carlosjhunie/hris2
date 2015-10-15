package dai.hris.service.payroll;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import dai.hris.model.ProfessionalFee;

public interface IProfessionalFeeService {
    public void saveOrUpdate(ProfessionalFee pf) throws SQLException;
    public List<ProfessionalFee> getAllByEmployeeId(int empId) throws SQLException;
    public List<ProfessionalFee> getAllByEmpIdAndOrDateRange(int empId, Date from, Date to) throws SQLException;
}
