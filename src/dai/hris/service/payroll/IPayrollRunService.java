package dai.hris.service.payroll;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dai.hris.model.EmployeeOvertime;
import dai.hris.model.EmployeePayrollRunExt;

public interface IPayrollRunService {
	public List<EmployeePayrollRunExt> generatePayrollByPayrollPeriod (int payrollPeriodId, String payrollType, int loggedEmpId) throws SQLException, Exception;
	public boolean lockPayrollByPayrollPeriod (int payrollPeriodId);
	public void updatePayrollPeriodStatusToLocked(int payrollPeriodId, int loggedEmpId) throws SQLException, Exception;
	public ArrayList<EmployeeOvertime> getEmployeeRenderedOvertimeWithinPayPeriod(int empId, Date payPeriodFromDate, Date payPeriodToDate) throws SQLException, Exception;
	

}
